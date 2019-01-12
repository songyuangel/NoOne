package pers.song.NoOne.weixin.message;
/**
 * 图片信息
 * @author song
 *
 */
public class MessageImage extends BaseMessage {
	
	private String PicUrl ; //图片链接（由系统生成）
	//private String MediaId ; //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private Image Image;

	public pers.song.NoOne.weixin.message.Image getImage() {
		return Image;
	}

	public void setImage(pers.song.NoOne.weixin.message.Image image) {
		Image = image;
	}

	public MessageImage(){

	}

	public MessageImage( String toUserName, String fromUserName,
			long createTime, String msgType,String picUrl,  String msgId, Image Image) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		PicUrl = picUrl;
		MsgID = msgId;
		this.Image = Image;
	}
	
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
//	public String getMediaId() {
//		return MediaId;
//	}
//	public void setMediaId(String mediaId) {
//		MediaId = mediaId;
//	}

}
