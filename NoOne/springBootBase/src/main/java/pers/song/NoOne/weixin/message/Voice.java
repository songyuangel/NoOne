package pers.song.NoOne.weixin.message;

public class Voice {
    private String MediaId;

    public Voice(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public Voice() {
    }
}
