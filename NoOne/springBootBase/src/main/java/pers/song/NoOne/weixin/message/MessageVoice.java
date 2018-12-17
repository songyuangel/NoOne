package pers.song.NoOne.weixin.message;

public class MessageVoice extends BaseMessage {
	private String MediaId; //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format;	//语音格式，如amr，speex等
	private String Recognition; //语音识别结果，UTF8编码
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public void setFormat(String format) {
		Format = format;
	}

	public MessageVoice( String toUserName, String fromUserName,
			long createTime, String msgType,String mediaId, String format, String msgId ,String recognition) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Format = format;
		MediaId = mediaId;
		MsgID = msgId;
		Recognition = recognition;
	}
}
