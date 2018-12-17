package pers.song.NoOne.weixin.message;
/**
 * 图片信息
 * @author song
 *
 */
public class MessageImage extends BaseMessage {
	
	private String PicUrl ; //图片链接（由系统生成）
	private String MediaId ; //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	
	public MessageImage( String toUserName, String fromUserName,
			long createTime, String msgType,String picUrl, String mediaId, String msgId) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		PicUrl = picUrl;
		MediaId = mediaId;
		MsgID = msgId;
	}
	
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
