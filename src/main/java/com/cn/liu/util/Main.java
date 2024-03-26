package com.cn.liu.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File("D:\\OneDrive - 0d1s0\\文档\\response.doc"); // 替换为实际的XML文件路径
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        Element rootElement = document.getDocumentElement();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>XML to HTML</title></head>");
        htmlBuilder.append("<body>");
        // 根据解析结果构建HTML内容，可以使用字符串拼接或其他方式来生成HTML片段
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        String html = htmlBuilder.toString();
        FileWriter fileWriter = new FileWriter("D:\\OneDrive - 0d1s0\\文档\\output.html"); // 替换为实际的输出文件路径
        fileWriter.write(html);
        fileWriter.close();
    }

    public static File getNetUrlHttp(String netUrl,String fileName) {
        File file = null;
        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = new File( fileName);
            //下载
            urlfile = new URL(netUrl);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println("远程图片获取错误："+netUrl);
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
