package pers.song.NoOne.weixin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//
import com.thoughtworks.xstream.XStream;

import pers.song.NoOne.weixin.message.MessageText;

public class MessageUtil {
	
	public final static String MESSAGETYPE_TEXT = "text";
	public final static String MESSAGETYPE_IMAGE = "image";
	public final static String MESSAGETYPE_VOICE = "voice";
	public final static String MESSAGETYPE_VIDEO = "video";

	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";

	/**
	 * 将微信的请求中参数转成map
	 * @param request
	 * @return
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request){
		
		Map<String,String> map = new HashMap<String,String>();
		
		SAXReader reader = new SAXReader();
		InputStream in = null;
		try {
			in = request.getInputStream();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 将发送消息封装成对应的xml格式
	 */
	public static  String messageToxml(Object  message) {
		XStream xstream  = initXStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
		//return null;
	}

	/**
	 * xstream初始化方法实现将String类型的属性增加cdata
	 * @return
	 */
	public static XStream initXStream() {
		return new XStream(new XppDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					boolean iscdata=false;
					@Override
					public void startNode(String name, Class clazz) {
						super.startNode(name, clazz);
						if(clazz.getName().equals("java.lang.String")){
							iscdata=true;
						}else{
							iscdata=false;
						}
					}
					protected void writeText(QuickWriter writer, String text) {
						if(iscdata){
							writer.write(PREFIX_CDATA + text + SUFFIX_CDATA);
						}else{
							writer.write(text);
						}
					}
				};
			}
		});
	}

}
