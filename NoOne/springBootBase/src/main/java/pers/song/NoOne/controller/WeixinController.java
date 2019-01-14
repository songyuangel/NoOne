package pers.song.NoOne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pers.song.NoOne.weixin.message.MessageText;
import pers.song.NoOne.weixin.service.WeixinService;
import pers.song.NoOne.weixin.utils.CheckUtil;
import pers.song.NoOne.weixin.utils.MessageUtil;

@Controller
public class WeixinController {
	@Autowired
	private WeixinService weixinService;

	private static Logger logger = LoggerFactory.getLogger(WeixinController.class);


	@RequestMapping(value = "wx2",method=RequestMethod.GET)
	public void login(HttpServletRequest request,HttpServletResponse response){
		logger.info("wx-get-success");
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
		 	logger.info("wx-post-success");
		 	response.setCharacterEncoding("utf-8");
		 	weixinService.service(request, response);
			
		}
}
