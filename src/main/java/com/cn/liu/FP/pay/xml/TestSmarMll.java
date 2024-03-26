package com.cn.liu.FP.pay.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Map;

public class TestSmarMll {
	public static void generDeclareXml(File file, Map map, String xmltype) {

		Document doc = DocumentHelper.createDocument();//获取Document对象

		Element rootElement = doc.addElement(xmltype);
		rootElement.addAttribute("xsi:noNamespaceSchemaLocation","xx.xsd");
		rootElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		MaptoXml.loopMap(map, rootElement);
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file),
					MaptoXml.getForm());
			writer.setEscapeText(false);// 字符是否转义,默认true
			writer.write(doc);
			writer.close();

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
