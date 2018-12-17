package pers.song.NoOne.weixin.message;

public class BaseMessage {
	protected String ToUserName;
	protected String FromUserName;
	protected long CreateTime;
	protected String MsgType;
	protected String MsgID;
 
	public BaseMessage() {
		super();
	}
 
	public String getToUserName() {
		return ToUserName;
	}
 
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
 
	public String getFromUserName() {
		return FromUserName;
	}
 
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
 
	public long getCreateTime() {
		return CreateTime;
	}
 
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
 
	public String getMsgType() {
		return MsgType;
	}
 
	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
