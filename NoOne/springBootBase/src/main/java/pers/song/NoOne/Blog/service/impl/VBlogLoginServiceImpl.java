package pers.song.NoOne.Blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.song.NoOne.Blog.dao.BlogAccountMapper;
import pers.song.NoOne.Blog.dao.BlogUserinfoMapper;
import pers.song.NoOne.Blog.dao.SysTokenMapper;
import pers.song.NoOne.Blog.entity.BlogAccount;
import pers.song.NoOne.Blog.entity.BlogUserinfo;
import pers.song.NoOne.Blog.entity.SysToken;
import pers.song.NoOne.Blog.service.VBlogLoginService;
import pers.song.NoOne.Blog.service.VBlogTokenService;
import pers.song.NoOne.Blog.sys.PostData;
import pers.song.NoOne.utils.UUIDUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application-blog.yml")
public class VBlogLoginServiceImpl implements VBlogLoginService {

    @Autowired
    private BlogAccountMapper blogAccountMapper;
    @Autowired
    private BlogUserinfoMapper blogUserinfoMapper;
    @Autowired
    private SysTokenMapper sysTokenMapper;
    @Autowired
    private VBlogTokenService tokenService;
    @Autowired
    private VBlogTokenService vBlogTokenService;
    @Value("${timeout}")
    private int tokenTimeOut;

    @Override
    @Transactional
    public PostData checkLogin(PostData data) {
        PostData retData = new PostData();
        String dataStr = data.getData();
        JSONObject obj= JSON.parseObject(dataStr);
        String account = obj.getString("account");
        String password = obj.getString("password");
        Map<String,String> map = new HashMap<>();
        map.put("account",account);
        map.put("password",password);
        //判断账号密码是否正确
        BlogAccount blogAccount = blogAccountMapper.selectByAccountAndPassword(map);

        if(blogAccount == null){
            retData.setCode("-101");
            retData.setErrMsg("用户名或密码错误！");
            return retData;
        }

        //获取账号相关信息
        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByAccountId(blogAccount.getId());
        retData.setUsercode(String.valueOf(blogUserinfo.getId()));

        tokenService.login(retData);

        JSONObject dataJson = new JSONObject();
        dataJson.put("nickname",blogUserinfo.getNickname());
        dataJson.put("usercode",blogUserinfo.getId());
        dataJson.put("avatarurl",blogUserinfo.getAvatarurl());
        dataJson.put("sidename",blogUserinfo.getSidename());
        //retData.setToken(token);
        retData.setCode("0");
        retData.setData(dataJson.toString());


        return retData;
    }

    @Override
    @Transactional
    public PostData logOut(PostData data) {

        PostData retData =  tokenService.logout(data);

        return retData;
    }

    /**
     * 修改密码，输入串data：
     * {
     *     oldPass:xxx,
     *     pass:xxx,
     *     checkPass:xxx
     * }
     * @param data
     * @return
     */
    @Override
    public PostData changePassword(PostData data) {
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

        String dataStr = data.getData();
        JSONObject obj= JSON.parseObject(dataStr);
        String oldPass = obj.getString("oldPass");

        Map<String,String> map = new HashMap<>();
        map.put("account",blogUserinfo.getAccount());
        map.put("password",oldPass);
        //判断账号密码是否正确
        BlogAccount blogAccount = blogAccountMapper.selectByAccountAndPassword(map);

        if(blogAccount == null){
            retData.setCode("-301");
            retData.setErrMsg("旧密码错误！");
            return retData;
        }

        String pass = obj.getString("pass");
        String checkPass = obj.getString("checkPass");
        if(pass == null || "".equals(pass) || checkPass == null || "".equals(checkPass)){
            retData.setCode("-301");
            retData.setErrMsg("传入的新密码为空！");
            return retData;
        }

        if(!checkPass.equals(pass)){
            retData.setCode("-301");
            retData.setErrMsg("传入的新密码和确认密码不一致！");
            return retData;
        }

        blogAccount.setPassword(pass);
        blogAccount.setUpdateDate(new Date());
        blogAccountMapper.updateByPrimaryKey(blogAccount);

        retData.setCode("0");
        retData.setData("");

        return retData;
    }
}
