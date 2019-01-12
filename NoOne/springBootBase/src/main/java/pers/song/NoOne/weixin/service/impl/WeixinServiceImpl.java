package pers.song.NoOne.weixin.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pers.song.NoOne.weixin.message.*;
import pers.song.NoOne.weixin.service.WeixinService;
import pers.song.NoOne.weixin.utils.MessageUtil;

@Service(value = "weixinService")
public class WeixinServiceImpl implements WeixinService {

	private static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		//将微信请求xml转为map格式，获取所需的参数
		Map<String,String> map = MessageUtil.xmlToMap(request);
//		String ToUserName = map.get("ToUserName");
//		String FromUserName = map.get("FromUserName");
//		String MsgType = map.get("MsgType");
//
		String message = null;
		//处理文本类型，实现输入1，回复相应的封装的内容
//		if(MessageUtil.MESSAGETYPE_TEXT.equals(MsgType)){
//			String Content = map.get("Content");
//			MessageText text = new MessageText();
//			text.setToUserName(FromUserName);
//			text.setFromUserName(ToUserName);
//			String resString = "复读机："+Content ;
//			text.setContent(resString);
//			text.setCreateTime(new Date().getTime());
//			text.setMsgType("text");
//			message = MessageUtil.messageToxml(text);
//			System.out.println(resString);
//		}else if (MessageUtil.MESSAGETYPE_IMAGE.equals(MsgType)){
//
//		}
		Object rtnObject = dealMessage(map);
		message = MessageUtil.messageToxml(rtnObject);
		logger.info("returnXml==========/r/n" + message);
		try {
			out = response.getWriter();
			out.write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();

	}

	private Object dealMessage(Map<String,String> map){
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String MsgType = map.get("MsgType");

		String message = null;

		//处理文本类型，实现输入1，回复相应的封装的内容
		if(MessageUtil.MESSAGETYPE_TEXT.equals(MsgType)){
			/**
			 * 参数	描述
			 * ToUserName	开发者微信号
			 * FromUserName	发送方帐号（一个OpenID）
			 * CreateTime	消息创建时间 （整型）
			 * MsgType	text
			 * Content	文本消息内容
			 * MsgId	消息id，64位整型
			 */
			String Content = map.get("Content");
			MessageText text = new MessageText();
			text.setToUserName(FromUserName);
			text.setFromUserName(ToUserName);
			String resString = "复读机："+Content ;
			text.setContent(resString);
			text.setCreateTime(new Date().getTime());
			text.setMsgType(MessageUtil.MESSAGETYPE_TEXT);
			return text;
		}else if (MessageUtil.MESSAGETYPE_IMAGE.equals(MsgType)){
			/**
			 * 参数	描述
			 * ToUserName	开发者微信号
			 * FromUserName	发送方帐号（一个OpenID）
			 * CreateTime	消息创建时间 （整型）
			 * MsgType	image
			 * MediaId	图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
			 */
			MessageImage image = new MessageImage();
			Image i = new Image(map.get("MediaId"));
			image.setToUserName(FromUserName);
			image.setFromUserName(ToUserName);
			image.setCreateTime(new Date().getTime());
			image.setMsgType(MessageUtil.MESSAGETYPE_IMAGE);
			//image.setPicUrl(map.get("PicUrl"));
			//image.setMediaId(map.get("MediaId"));
			image.setImage(i);
			return image;

		}else if(MessageUtil.MESSAGETYPE_VOICE.equals(MsgType)){
			/**
			 *
			 *参数	是否必须	说明
			 *ToUserName	是	接收方帐号（收到的OpenID）
			 *FromUserName	是	开发者微信号
			 *CreateTime	是	消息创建时间戳 （整型）
			 *MsgType	是	语音，voice
			 *MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
			 */
			MessageVoice voice = new MessageVoice();
			Voice v = new Voice(map.get("MediaId"));
			voice.setToUserName(FromUserName);
			voice.setFromUserName(ToUserName);
			voice.setCreateTime(new Date().getTime());
			voice.setMsgType(MessageUtil.MESSAGETYPE_VOICE);
			voice.setVoice(v);
			//voice.setMediaId(map.get("MediaId"));
			//voice.setFormat(map.get("Format"));
			return voice ;

		}else if(MessageUtil.MESSAGETYPE_VIDEO.equals(MsgType)){
			/**
			 *
			 *参数	描述
			 *ToUserName	开发者微信号
			 *FromUserName	发送方帐号（一个OpenID）
			 *CreateTime	消息创建时间 （整型）
			 *MsgType	视频为video
			 *MediaId	视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
			 *Title	否	视频消息的标题
			 * Description	否	视频消息的描述
			 */
			MessageVideo video = new MessageVideo();
			Video v2 = new Video(map.get("MediaId"));
			video.setToUserName(FromUserName);
			video.setFromUserName(ToUserName);
			video.setCreateTime(new Date().getTime());
			video.setMsgType(MessageUtil.MESSAGETYPE_VIDEO);
			//video.setMediaId(map.get("MediaId"));
			//video.setThumbMediaId(map.get("ThumbMediaId"));
			video.setVideo(v2);
			return video;

		}

		return null;
	}

}
