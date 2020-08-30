package io.github.byference.mapper.core;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;

import static org.springframework.util.Assert.notNull;

/**
 * TinyMapperFactoryBean
 *
 * @author byference
 * @since 2020-08-30
 */
public class TinyMapperFactoryBean<T> extends MapperFactoryBean<T> {

    public TinyMapperFactoryBean() {
    }

    public TinyMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    protected void checkDaoConfig() {
        Class<T> mapperInterface = super.getMapperInterface();
        notNull(mapperInterface, "Property 'mapperInterface' is required");
        Configuration configuration = getSqlSession().getConfiguration();
        if (isAddToConfig() && !configuration.hasMapper(mapperInterface)) {
            try {
                configuration.addMapper(mapperInterface);
                DefaultMapperProvider<T> crudMapperProvider = new DefaultMapperProvider<>(mapperInterface);
                crudMapperProvider.addMappedStatements(configuration);
            } catch (Exception e) {
                logger.error("Error while adding the mapper '" + mapperInterface + "' to configuration.", e);
                throw new IllegalArgumentException(e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }
}
