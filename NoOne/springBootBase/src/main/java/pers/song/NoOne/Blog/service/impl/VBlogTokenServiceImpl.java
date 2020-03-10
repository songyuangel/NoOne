package pers.song.NoOne.Blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.song.NoOne.Blog.dao.BlogAccountMapper;
import pers.song.NoOne.Blog.dao.BlogUserinfoMapper;
import pers.song.NoOne.Blog.dao.SysTokenMapper;
import pers.song.NoOne.Blog.entity.BlogAccount;
import pers.song.NoOne.Blog.entity.BlogAccountKey;
import pers.song.NoOne.Blog.entity.BlogUserinfo;
import pers.song.NoOne.Blog.entity.SysToken;
import pers.song.NoOne.Blog.service.VBlogTokenService;
import pers.song.NoOne.Blog.sys.PostData;
import pers.song.NoOne.utils.UUIDUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application-blog.yml")
public class VBlogTokenServiceImpl implements VBlogTokenService {

    @Autowired
    private BlogAccountMapper blogAccountMapper;
    @Autowired
    private BlogUserinfoMapper blogUserinfoMapper;
    @Autowired
    private SysTokenMapper sysTokenMapper;
    @Value("${timeout}")
    private int tokenTimeOut;

    final private int UPDATE_TOKEN_SECOND = 300 ;

    @Override
    @Transactional
    public PostData login(PostData data) {
        String userCode =  data.getUsercode();
        Date currentDat = new Date();

        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByPrimaryKey(Integer.valueOf(userCode));
        BlogAccountKey blogAccountKey = new BlogAccountKey();
        blogAccountKey.setAccount(blogUserinfo.getAccount());
        blogAccountKey.setId(blogUserinfo.getAccountId());
        BlogAccount blogAccount = blogAccountMapper.selectByPrimaryKey(blogAccountKey);

        //获取token
        String token = UUIDUtils.create().toString();
        long validTime = tokenTimeOut * 1000 ; //有效时长
        //更新原有的token，然后新增记录
        Map updateMap = new HashMap<>();
        updateMap.put("statue","2");    //状态更新为更新
        updateMap.put("accountId",blogAccount.getId());
        sysTokenMapper.updateStatueByAccountId(updateMap);

        SysToken sysToken = new SysToken();
        sysToken.setAccount(blogAccount.getAccount());
        sysToken.setAccountId(blogAccount.getId());
        sysToken.setToken(token);
        sysToken.setStatue("0");    //有效
        sysToken.setCreateDate(currentDat); // 当前时间
        sysToken.setDueDate(new Date(currentDat.getTime() + validTime ));   //过期时间
        sysTokenMapper.insert(sysToken);

        data.setToken(token);

        return data;
    }

    @Override
    @Transactional
    public PostData logout(PostData data) {
        PostData retData = new PostData();
        String usercode = data.getUsercode();
        String token = data.getToken();
        Map tokenMap = new HashMap();
        tokenMap.put("token",token);
        tokenMap.put("statue","0");
        SysToken oldToken = sysTokenMapper.selectByToken(tokenMap);
        if(oldToken == null){
            retData.setCode("100");
            retData.setErrMsg("未找到有效的token！");
            return retData;
        }

        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByPrimaryKey(Integer.valueOf(usercode));
        if(blogUserinfo == null || !oldToken.getAccountId().equals(blogUserinfo.getAccountId()) ){
            retData.setCode("-102");
            retData.setErrMsg("传入的token和用户名不符！" + oldToken.getAccountId() + "    " + blogUserinfo.getAccountId());
            return retData;
        }

        Map updateMap = new HashMap();
        updateMap.put("token",token);
        updateMap.put("accountId",blogUserinfo.getAccountId());
        updateMap.put("statue","1");
        sysTokenMapper.updateStatueByAccountId(updateMap);

        retData.setCode("0");

        return retData;
    }

    @Override
    @Transactional
    public PostData checkToken(PostData data) {
        PostData retData = new PostData();
        Date currentDate = new Date();
        String usercode = data.getUsercode();
        String token = data.getToken();

        if(null == usercode || "".equals(usercode)){
            retData.setCode("-104");
            retData.setErrMsg("未传入有效的用户编号！");
            return retData;
        }

        if(null == token || "".equals(token)){
            retData.setCode("-105");
            retData.setErrMsg("未传入有效的token！");
            return retData;
        }

        Map tokenMap = new HashMap();
        tokenMap.put("token",token);
        tokenMap.put("statue","0");
        SysToken oldToken = sysTokenMapper.selectByToken(tokenMap);
        if(oldToken == null){
            retData.setCode("-102");
            retData.setErrMsg("未找到有效的token！");
            return retData;
        }

        BlogUserinfo blogUserinfo = blogUserinfoMapper.selectByPrimaryKey(Integer.valueOf(usercode));
        if(blogUserinfo == null || !oldToken.getAccountId().equals(blogUserinfo.getAccountId()) ){
            retData.setCode("-102");
            retData.setErrMsg("传入的token和用户名不符！" + oldToken.getAccountId() + "    " + blogUserinfo.getAccountId());
            return retData;
        }
        // 过期的情况
        if(currentDate.getTime() > oldToken.getDueDate().getTime()){
            Map updateMap = new HashMap();
            updateMap.put("token",token);
            updateMap.put("accountId",blogUserinfo.getAccountId());
            updateMap.put("statue","3"); // 过期
            sysTokenMapper.updateStatueByAccountId(updateMap);

            retData.setCode("-103");
            retData.setErrMsg("传入的token已经过期！");
            return retData;
        }

        // 更新
        if(currentDate.getTime() + UPDATE_TOKEN_SECOND * 1000 > oldToken.getDueDate().getTime()){
            Map updateMap = new HashMap();
            updateMap.put("token",token);
            updateMap.put("accountId",blogUserinfo.getAccountId());
            updateMap.put("statue","2"); // 更新
            sysTokenMapper.updateStatueByAccountId(updateMap);

            // 生成新的token返回
            String newToken = UUIDUtils.create().toString();
            SysToken sysToken = new SysToken();
            sysToken.setAccount(oldToken.getAccount());
            sysToken.setAccountId(oldToken.getAccountId());
            sysToken.setToken(newToken);
            sysToken.setStatue("0");    //有效
            sysToken.setCreateDate(currentDate); // 当前时间
            sysToken.setDueDate(new Date(currentDate.getTime() + tokenTimeOut * 1000 ));   //过期时间
            sysTokenMapper.insert(sysToken);

            retData.setToken(newToken);
        }

        return retData;
    }
}
