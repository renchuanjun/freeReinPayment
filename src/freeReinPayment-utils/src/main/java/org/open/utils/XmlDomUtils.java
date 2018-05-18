package org.open.utils;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * XML的DOM操作辅助类
 * @author Administrator
 *
 */

public class XmlDomUtils {
	
	/**
	 * xPath查询单个节点
	 * @param express
	 * @param source
	 * @return
	 * @throws XPathExpressionException
	 */
	public static Node selectSingleNode(String express, Object source) throws XPathExpressionException{//查找节点，并返回第一个符合条件节点
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        
        return result;
    }
    /**
     * xPath查询多个节点
     * @param express
     * @param source
     * @return
     * @throws XPathExpressionException
     */
    public static NodeList selectNodes(String express, Object source) throws XPathExpressionException {//查找节点，返回符合条件的节点集。
        NodeList result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
        return result;
    }
    
    /**
     * 保存xml文件 
     * @param fileName
     * @param doc
     * @throws Exception
     */
    public static void saveXml(String fileName, Document doc) throws Exception {//将Document输出到文件
        TransformerFactory transFactory=TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty("encoding", "utf-8");
        transformer.setOutputProperty("indent", "yes");

        DOMSource source=new DOMSource();
        source.setNode(doc);
        StreamResult result=new StreamResult();
        result.setOutputStream(new FileOutputStream(fileName));
        
        transformer.transform(source, result);
    }
    
    /**
     * 控制台输出xml文件
     * @param node
     * @throws Exception
     */
    public static void output(Node node) throws Exception {//将node的XML字符串输出到控制台
        TransformerFactory transFactory=TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty("encoding", "utf-8");
        transformer.setOutputProperty("indent", "yes");

        DOMSource source=new DOMSource();
        source.setNode(node);
        StreamResult result=new StreamResult();
        result.setOutputStream(System.out);

        transformer.transform(source, result);
    }

	/**
	 * 查询节点
	 * @param express
	 * @param resource
	 * @param "//*[name() = 'payee']"(获取部分元素) 只获取元素名称为title的元素
	 * @throws XPathExpressionException
	 * @throws Exception 
	 */
	public static NodeList selectNodeList(String express,Resource resource) throws Exception{//查找节点，并返回第一个符合条件节点
		XmlDomUtils domUtils = new XmlDomUtils();
		InputStream inputStream = resource.getInputStream();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder db=factory.newDocumentBuilder();
        Document xmldoc=db.parse(inputStream);
        //定位根节点
        Element root=xmldoc.getDocumentElement();
        //查找引用的节点，进行替换
        NodeList nodeList = XmlDomUtils.selectNodes(express, root);
		return nodeList;
    }
}
