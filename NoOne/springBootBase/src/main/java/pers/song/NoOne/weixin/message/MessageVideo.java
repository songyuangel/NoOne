package pers.song.NoOne.weixin.message;

public class MessageVideo extends BaseMessage {
	//private String MediaId; //视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId; //视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private Video Video;

	public pers.song.NoOne.weixin.message.Video getVideo() {
		return Video;
	}

	public void setVideo(pers.song.NoOne.weixin.message.Video video) {
		Video = video;
	}

	//	public String getMediaId() {
//		return MediaId;
//	}
//	public void setMediaId(String mediaId) {
//		MediaId = mediaId;
//	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public  MessageVideo(){

	}


	public MessageVideo( String toUserName, String fromUserName,
			long createTime, String msgType,String mediaId, String thumbMediaId, String msgId ,Video Video) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		//MediaId = mediaId;
		ThumbMediaId = thumbMediaId;
		MsgID = msgId;
		this.Video = Video;
	}

}
