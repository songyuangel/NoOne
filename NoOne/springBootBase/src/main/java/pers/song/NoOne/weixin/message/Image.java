package pers.song.NoOne.weixin.message;

public class Image {
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    public Image() {
    }
}
