package com.cn.liu.tcp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverHost = "服务器地址";
        int serverPort = 1234;
        String parameterValue = "参数值";

        try {
            // 建立 TCP 连接
            Socket socket = new Socket(serverHost, serverPort);

            // 获取输入流和输出流
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            // 读取 XML 模板文件
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("模板文件路径");

            // 修改参数值
            NodeList parameterNodes = doc.getElementsByTagName("parameter");
            for (int i = 0; i < parameterNodes.getLength(); i++) {
                Element parameterElement = (Element) parameterNodes.item(i);
                Node textNode = parameterElement.getFirstChild();
                textNode.setNodeValue(parameterValue);
            }

            // 将 XML 报文转换为字符串
            String xmlRequest = convertDocumentToString(doc);

            // 发送 XML 报文
            outputStream.write(xmlRequest.getBytes());
            outputStream.flush();

            // 接收服务器响应
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String xmlResponse = new String(buffer, 0, bytesRead);

            // 处理服务器响应
            System.out.println("服务器响应：");
            System.out.println(xmlResponse);

            // 关闭连接
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String convertDocumentToString(Document doc) throws Exception {
        // 使用 javax.xml.transform 库中的 Transformer 将 Document 转换为字符串
        javax.xml.transform.TransformerFactory transformerFactory =
                javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
        java.io.StringWriter writer = new java.io.StringWriter();
        javax.xml.transform.stream.StreamResult result =
                new javax.xml.transform.stream.StreamResult(writer);
        transformer.transform(source, result);
        return writer.toString();
    }
}
