package com.cn.liu.FP.pay.xml;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import java.util.Iterator;
import java.util.Map;

public class MaptoXml {

	/**
	 * 将map依照层次转化
	 * @param map
	 * @param rootElement
	 */
	public static void loopMap(Map map,Element rootElement){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String typeName =map.get(key).getClass().getName();
			System.out.println(key);
			System.out.println(getType(typeName));
			if(!getType(typeName).equals("LinkedHashMap")){
				//if(!getType(typeName).equals("JSONObject")){
				if(key.startsWith("Receivers")){        //特殊处理，传输xml多个接收者的情况
					Element keyElement = rootElement.addElement("Receiver");
					keyElement.setText( (String) map.get(key));
				}
				else{
					Element keyElement = rootElement.addElement(key);
					keyElement.setText((String) map.get(key));
				}
			}
			else if(getType(typeName).equals("LinkedHashMap")){
				//else if(getType(typeName).equals("JSONObject")){
				if(key.equals("ApplyDetail")){						//特殊处理
					Element keyElement0 = rootElement.addElement(key);
					Element keyElement = keyElement0.addElement(key);
					Map map2 = (Map) map.get(key);
					loopMap(map2,keyElement);
				}
				else{
					Element keyElement = rootElement.addElement(key);
					Map map2 = (Map) map.get(key);
					loopMap(map2,keyElement);
				}
			}
		}
	}
	/**
	 * 获取数据类型，用于判断是否添加子元素
	 * @param typeName
	 * @return
	 */
	public static String getType(String typeName){
		int length= typeName.lastIndexOf(".");
		String type =typeName.substring(length+1);
		return typeName.substring(length+1);
	}
	/**
	 * 解决默认第二行为空的情况
	 * @return
	 */
	public static OutputFormat getForm(){
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setIndentSize(4);//改为4
        format.setNewLineAfterDeclaration(false);
        return format;
	}
}
