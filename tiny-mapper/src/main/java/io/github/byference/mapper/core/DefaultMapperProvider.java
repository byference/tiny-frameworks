package io.github.byference.mapper.core;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DefaultMapperProvider
 *
 * @author byference
 * @since 2020-08-29
 */
public class DefaultMapperProvider<T> {

    private final Class<T> mapperInterface;

    private Class<?> entityType;

    private String tableName;

    private String primaryKey;

    private String primaryKeyProperty;

    private List<Field> columnFields;


    public DefaultMapperProvider(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        init();
    }

    private void init() {
        final Type[] genericInterfaces = mapperInterface.getGenericInterfaces();
        // 1、获取 BaseMapper 元信息
        final Type genericInterface = genericInterfaces[0];
        ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.entityType = (Class<?>) actualTypeArguments[0];

        // 2、解析表名
        final TableName tableName = entityType.getAnnotation(TableName.class);
        this.tableName = tableName.value();

        // 3、解析table对应实体属性
        final Field[] declaredFields = this.entityType.getDeclaredFields();
        this.columnFields = Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(FieldName.class))
                .peek(field -> {
                    if (field.isAnnotationPresent(Id.class)) {
                        final FieldName fieldName = field.getAnnotation(FieldName.class);
                        this.primaryKey = fieldName.value();
                        this.primaryKeyProperty = field.getName();
                    }
                }).collect(Collectors.toList());
    }


    public void addMappedStatements(Configuration configuration) {
        insert(configuration);
        updateById(configuration);
        deleteById(configuration);
        findById(configuration);
    }


    private void findById(Configuration configuration) {
        String id = this.mapperInterface.getName() + ".findById";
        String conditions = String.format(" %s = #{%s}", primaryKeyProperty, primaryKey);
        String[] columns = columnFields.stream()
                .map(field -> field.getAnnotation(FieldName.class).value())
                .toArray(String[]::new);
        String findByIdSQL = new SQL().SELECT(columns).FROM(tableName).WHERE(conditions).toString();

        doAddMappedStatement(configuration, id, findByIdSQL, SqlCommandType.SELECT);
    }


    private void deleteById(Configuration configuration) {
        String id = this.mapperInterface.getName() + ".deleteById";
        String conditions = String.format(" %s = #{%s}", primaryKeyProperty, primaryKey);
        String deleteByIdSQL = new SQL().DELETE_FROM(tableName).WHERE(conditions).toString();
        doAddMappedStatement(configuration, id, deleteByIdSQL, SqlCommandType.DELETE);
    }


    private void updateById(Configuration configuration) {
        String id = this.mapperInterface.getName() + ".updateById";
        String conditions = String.format(" %s = #{%s}", primaryKeyProperty, primaryKey);
        String[] sets = columnFields.stream()
                .map(field -> String.format("%s = #{%s}", field.getAnnotation(FieldName.class).value(), field.getName()))
                .toArray(String[]::new);
        String updateByIdSQL = new SQL().UPDATE(tableName).SET(sets).WHERE(conditions).toString();
        doAddMappedStatement(configuration, id, updateByIdSQL, SqlCommandType.UPDATE);
    }


    private void insert(Configuration configuration) {
        String id = this.mapperInterface.getName() + ".insert";
        String[] columns = columnFields.stream().map(field -> field.getAnnotation(FieldName.class).value()).toArray(String[]::new);
        String[] values = columnFields.stream().map(filed -> String.format("#{%s}", filed.getName())).toArray(String[]::new);
        String insertSQL = new SQL().INSERT_INTO(this.tableName).INTO_COLUMNS(columns).INTO_VALUES(values).toString();
        doAddMappedStatement(configuration, id, insertSQL, SqlCommandType.INSERT);
    }


    private void doAddMappedStatement(Configuration configuration, String id, String targetSQL, SqlCommandType sqlCommandType) {
        ResultMap resultMap = new ResultMap.Builder(configuration, id + "-Inline", this.entityType, Collections.emptyList(), null).build();
        SqlSource sqlSource = new SqlSourceBuilder(configuration).parse(targetSQL, this.entityType, Collections.emptyMap());
        MappedStatement statement = new MappedStatement.Builder(configuration, id, sqlSource, sqlCommandType)
                .resultMaps(Collections.singletonList(resultMap))
                .build();
        configuration.addMappedStatement(statement);
    }


}
