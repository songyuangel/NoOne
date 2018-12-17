package pers.song.NoOne.weixin.message;
/**
 * 文本信息
 * @author song
 *
 */

public class MessageText extends BaseMessage {
	
	private String Content;//文本消息内容
	
	public MessageText(){
		
	}
 
	
	public MessageText(String toUserName, String fromUserName,
			long createTime, String msgType, String content, String msgId) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
		MsgID = msgId;
	}
 
 
	public String getContent() {
		return Content;
	}
 
	public void setContent(String content) {
		Content = content;
	}
 
}
