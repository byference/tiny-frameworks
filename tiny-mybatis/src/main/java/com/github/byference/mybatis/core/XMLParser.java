package com.github.byference.mybatis.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * XMLParser
 *
 * @author byference
 * @since 2019-08-04
 */
public class XMLParser {

    public static Properties getDataSourceProperties(InputStream inputStream) {

        Properties properties = new Properties();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);

            Element root = document.getRootElement();
            Iterator iter = root.elementIterator();
            while (iter.hasNext()) {
                Element e = (Element) iter.next();
                //解析environments节点
                if ("environments".equals(e.getName())) {
                    Iterator iter2 = e.elementIterator();
                    while (iter2.hasNext()) {
                        //解析environment节点
                        Element e2 = (Element) iter2.next();
                        Iterator iter3 = e2.elementIterator();
                        while (iter3.hasNext()) {
                            Element e3 = (Element) iter3.next();
                            //解析dataSource节点
                            if ("dataSource".equals(e3.getName())) {
                                if ("POOLED".equals(e3.attributeValue("type"))) {
                                    Iterator iter4 = e3.elementIterator();
                                    //获取数据库连接信息
                                    while (iter4.hasNext()) {
                                        Element e4 = (Element) iter4.next();
                                        properties.setProperty(e4.attributeValue("name"), e4.attributeValue("value"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

}
