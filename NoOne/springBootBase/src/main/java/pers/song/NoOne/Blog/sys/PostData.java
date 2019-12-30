package pers.song.NoOne.Blog.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import pers.song.NoOne.utils.AesEncryptUtils;

public class PostData {

    private String code;

    private String errMsg;

    private String data;

    private String usercode;

    private String token;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PostData(){}

    public PostData(String code, String errMsg, String data, String usercode, String token) {
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
        this.usercode = usercode;
        this.token = token;
    }

    /**
     * 构造函数
     * @param enc 传入加密的json字符串
     */
    public PostData(String enc){
        /**
         * 传入加密的json字符串
         * 解密后格式：
         * {
         *     info:{   //头部信息
         *         usercode:xxx,
         *         token:xxx
         *     },
         *     data:{    //业务数据
         *        aaa:111,
         *        bbb:222,
         *        ...
         *     }
         * }
         */
        try{
            String json = AesEncryptUtils.decrypt(enc);
            JSONObject obj= JSON.parseObject(json);
            String usercode = obj.getJSONObject("info").getString("usercode");
            String token = obj.getJSONObject("info").getString("token");
            String data = obj.getString("data");

            this.code = "0";
            this.errMsg = "";
            this.data = data;
            this.usercode = usercode;
            this.token = token;

        }catch (Exception e){
            this.code = "-1";
            this.errMsg = e.getMessage();
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {

        JSONObject obj = new JSONObject();
        JSONObject objInfo = new JSONObject();
        objInfo.put("code",this.code);
        objInfo.put("errMsg",this.errMsg);
        objInfo.put("usercode",this.usercode);
        objInfo.put("token",this.token);
        JSONObject objData = JSON.parseObject(this.data);
        obj.put("info",objInfo);
        obj.put("data",objData);

        return obj.toString();

    }

    /**
     * 返回加密的数据格式
     * @return
     */
    public String getEncString(){
        String ret ;
        try{
            ret = AesEncryptUtils.encrypt(this.toString());
        }catch (Exception e){
            ret = "";
        }

        return ret;

    }
}
