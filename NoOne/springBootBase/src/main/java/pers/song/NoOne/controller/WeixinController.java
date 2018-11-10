package pers.song.NoOne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pers.song.NoOne.weixin.message.MessageText;
import pers.song.NoOne.weixin.utils.CheckUtil;
import pers.song.NoOne.weixin.utils.MessageUtil;

@Controller
public class WeixinController {
	@RequestMapping(value = "wx2",method=RequestMethod.GET)
	public void login(HttpServletRequest request,HttpServletResponse response){
		System.out.println("get-wxcheck222-success");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			  out = response.getWriter();
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				out.write(echostr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
	
	
	 @RequestMapping(value = "wx",method=RequestMethod.POST)
		public void dopost(HttpServletRequest request,HttpServletResponse response){
		 	System.out.println("wx-post-success");
		 	response.setCharacterEncoding("utf-8");
			PrintWriter out = null;
			//将微信请求xml转为map格式，获取所需的参数
			Map<String,String> map = MessageUtil.xmlToMap(request);
			String ToUserName = map.get("ToUserName");
			String FromUserName = map.get("FromUserName");
			String MsgType = map.get("MsgType");
			String Content = map.get("Content");
			
			String message = null;
			//处理文本类型，实现输入1，回复相应的封装的内容
			if("text".equals(MsgType)){
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
