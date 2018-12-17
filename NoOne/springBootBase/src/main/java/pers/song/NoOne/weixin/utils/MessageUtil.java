package pers.song.NoOne.weixin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public static  String messageToxml(MessageText  message) {
		XStream xstream  = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
		//return null;
	}

}
