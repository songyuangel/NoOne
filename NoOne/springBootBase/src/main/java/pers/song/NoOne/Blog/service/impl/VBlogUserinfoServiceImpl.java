package pers.song.NoOne.Blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.song.NoOne.Blog.dao.BlogUserinfoMapper;
import pers.song.NoOne.Blog.entity.BlogUserinfo;
import pers.song.NoOne.Blog.service.VBlogTokenService;
import pers.song.NoOne.Blog.service.VBlogUserinfoService;
import pers.song.NoOne.Blog.sys.PostData;

import java.util.Date;

@Service
public class VBlogUserinfoServiceImpl implements VBlogUserinfoService {
    @Autowired
    private VBlogTokenService vBlogTokenService;
    @Autowired
    private BlogUserinfoMapper blogUserinfoMapper;

    @Override
    public PostData updateUserinfo(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }
        String headUsercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData()).getJSONObject("userinfo");
        String infoUsercode = info.getString("id");

        if(!headUsercode.equals(infoUsercode)){
            retData.setCode("-301");
            retData.setErrMsg("登录的账号和待修改的信息不一致！");
            return retData;
        }

        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByPrimaryKey(Integer.parseInt(headUsercode));
        blogUserinfo.setName(info.getString("name"));
        blogUserinfo.setNickname(info.getString("nickname"));
        blogUserinfo.setSexCode(info.getString("sexCode"));
        blogUserinfo.setBirthday(info.getSqlDate("birthday"));
        blogUserinfo.setTelephone(info.getString("telephone"));
        blogUserinfo.setEmail(info.getString("email"));
        blogUserinfo.setQq(info.getString("qq"));
        blogUserinfo.setAddress(info.getString("address"));
        blogUserinfo.setWeixin(info.getString("weixin"));
        blogUserinfo.setWeibo(info.getString("weibo"));
        blogUserinfo.setAvatarurl(info.getString("avatarurl"));
        blogUserinfo.setSidename(info.getString("sidename"));
        blogUserinfo.setUpdateDate(new Date());

        blogUserinfoMapper.updateByPrimaryKey(blogUserinfo);

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData getUserinfo(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByPrimaryKey(Integer.parseInt(usercode));
        if(blogUserinfo == null){
            retData.setCode("-100");
            retData.setErrMsg("未找到有效的用户信息！");
            return retData;
        }

        String dataStr = JSON.toJSONString(blogUserinfo);
        retData.setCode("0");
        retData.setData(dataStr);

        return retData;
    }
}
