package pers.song.NoOne.weixin.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import pers.song.NoOne.weixin.message.MessageText;
import pers.song.NoOne.weixin.service.WeixinService;
import pers.song.NoOne.weixin.utils.MessageUtil;

@Service(value = "weixinService")
public class WeixinServiceImpl implements WeixinService {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		//将微信请求xml转为map格式，获取所需的参数
		Map<String,String> map = MessageUtil.xmlToMap(request);
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String MsgType = map.get("MsgType");
		
		String message = null;
		//处理文本类型，实现输入1，回复相应的封装的内容
		if(MessageUtil.MESSAGETYPE_TEXT.equals(MsgType)){
			String Content = map.get("Content");
			MessageText text = new MessageText();
			text.setToUserName(FromUserName);
			text.setFromUserName(ToUserName);
			String resString = "复读机："+Content ;
			text.setContent(resString);
			text.setCreateTime(new Date().getTime());
			text.setMsgType("text");
			message = MessageUtil.messageToxml(text);
			System.out.println(resString);
		}
		try {
			out = response.getWriter();
			out.write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();

	}

}
