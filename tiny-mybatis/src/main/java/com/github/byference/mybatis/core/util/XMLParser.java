package com.github.byference.mybatis.core.util;

import com.github.byference.mybatis.core.mapping.MapperStatement;
import lombok.Getter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

/**
 * XMLParser
 *
 * @author byference
 * @since 2019-08-04
 */
@Getter
public class XMLParser {


    private Properties properties;

    private Map<String, MapperStatement> mapperStatementMap;


    public XMLParser(InputStream inputStream) {

        this.parse(inputStream);
    }


    private void parse(InputStream inputStream) {

        properties = new Properties();
        mapperStatementMap = new HashMap<>();
        Set<String> mappers = new HashSet<>();


        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);

            Element root = document.getRootElement();
            Iterator iter = root.elementIterator();
            while (iter.hasNext()) {
                Element e = (Element) iter.next();
                // environments
                if ("environments".equals(e.getName())) {
                    Iterator iter2 = e.elementIterator();
                    while (iter2.hasNext()) {
                        // environment
                        Element e2 = (Element) iter2.next();
                        Iterator iter3 = e2.elementIterator();
                        while (iter3.hasNext()) {
                            Element e3 = (Element) iter3.next();
                            // dataSource
                            if ("dataSource".equals(e3.getName())) {
                                if ("POOLED".equals(e3.attributeValue("type"))) {
                                    Iterator iter4 = e3.elementIterator();
                                    while (iter4.hasNext()) {
                                        Element e4 = (Element) iter4.next();
                                        properties.setProperty(e4.attributeValue("name"), e4.attributeValue("value"));
                                    }
                                }
                            }
                        }
                    }
                }

                // mappers
                if ("mappers".equals(e.getName())) {
                    Iterator iter2 = e.elementIterator();
                    while (iter2.hasNext()) {
                        // 解析environment节点
                        Element e2 = (Element) iter2.next();
                        String mapperUrl = e2.attributeValue("resource");
                        mappers.add(mapperUrl);
                    }
                }

                // mapperStatementMap
                for (String mapperUrl : mappers) {

                    InputStream mapperInputStream = XMLParser.class.getClassLoader().getResourceAsStream(mapperUrl);
                    Document mapperDocument = reader.read(mapperInputStream);
                    Element mapperRoot = mapperDocument.getRootElement();
                    String namespace = mapperRoot.attributeValue("namespace");

                    Iterator mapperIter = mapperRoot.elementIterator();
                    while (mapperIter.hasNext()) {
                        Element e1 = (Element) mapperIter.next();

                        MapperStatement mapperStatement = new MapperStatement();


                        String id = e1.attributeValue("id");
                        String parameterType = e1.attributeValue("parameterType");
                        String resultType = e1.attributeValue("resultType");
                        String sql = e1.getTextTrim();

                        mapperStatement.setNamespace(namespace);
                        mapperStatement.setId(id);
                        mapperStatement.setParameterType(parameterType);
                        mapperStatement.setResultType(resultType);
                        mapperStatement.setSql(sql);

                        String statementKey = namespace + "." + id;
                        mapperStatementMap.put(statementKey, mapperStatement);
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Properties getDataSourceProperties() {
        return this.properties;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return this.mapperStatementMap;
    }


}
