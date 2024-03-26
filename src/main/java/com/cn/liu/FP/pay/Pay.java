package com.cn.liu.FP.pay;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.StringWriter;
import java.util.*;

/**
 * @author liushuhuang
 * @date 2023/7/20
 */
public class Pay {
    public static void main(String[] args) throws Exception {
        // 读取 XML 模板文件
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 加载xml文件
        Document document= reader.read(new File("D:\\demo\\src\\main\\resources\\a.xml"));
        // 修改参数值
        List headInfoList = document.selectNodes("//Message/Head");
        List bodyInfoList = document.selectNodes("//Message/body");
        Iterator iterator = headInfoList.iterator();
        Iterator bodyIterator = bodyInfoList.iterator();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> school = new HashMap<>();

        while (iterator.hasNext()) {
            Element headInfoElement = (Element) iterator.next();
            setElementText(headInfoElement,1,"Unitno");
            setElementText(headInfoElement,2,"SubUnitno");
            setElementText(headInfoElement,3,"TransDate");
            setElementText(headInfoElement,4,"TransTime");
            setElementText(headInfoElement,5,"TransCode");
            setElementText(headInfoElement,6,"RetCode");
            setElementText(headInfoElement,7,"RetDesc");
        }
        while (bodyIterator.hasNext()) {
            Element bodyInfoElement = (Element) bodyIterator.next();
            // 解析 user，针对集合
            setElementText(bodyInfoElement,1,"busidate");
            setElementText(bodyInfoElement,2,"busiserno");
            setElementText(bodyInfoElement,3,"payeracc");
            setElementText(bodyInfoElement,4,"liushuhuang");
            setElementText(bodyInfoElement,5,"xxxxxx");
            setElementText(bodyInfoElement,6,"liu");
            setElementText(bodyInfoElement,7,"2352352235235235");
            setElementText(bodyInfoElement,8,"中国银行");
            setElementText(bodyInfoElement,9,"1");
            setElementText(bodyInfoElement,10,"1");
            setElementText(bodyInfoElement,11,"12");
            setElementText(bodyInfoElement,12,"1");
            setElementText(bodyInfoElement,13,"123345");
        }
        // 设置输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        // 创建字符串输出流
        StringWriter sw = new StringWriter();

        // 创建XMLWriter并将XML内容写入字符串
        XMLWriter writer = new XMLWriter(sw, format);
        writer.write(document);
        writer.flush();

        // 获取XML字符串
        String xmlString = sw.toString();

        // 输出XML字符串
        System.out.println(xmlString);
    }

    public static String getElementText(Element element, String elementIteratorName) {
        Iterator iterator = element.elementIterator(elementIteratorName);
        if (iterator.hasNext()) {
            Element subElement = (Element) iterator.next();
            return subElement.getText();
        }
        return null;
    }

    public static void setElementText(Element element, int index,String value) {
        String name = ((Element) element.elements().get(index)).getName();
        Iterator iterator = element.elementIterator(name);
        if (iterator.hasNext()) {
            Element subElement = (Element) iterator.next();
            subElement.setText(value);
            return ;
        }
        return ;
    }
}
