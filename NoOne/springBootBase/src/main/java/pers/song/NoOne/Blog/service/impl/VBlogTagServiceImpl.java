package pers.song.NoOne.Blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.song.NoOne.Blog.dao.BlogTagMapper;
import pers.song.NoOne.Blog.entity.BlogTag;
import pers.song.NoOne.Blog.service.VBlogTagService;
import pers.song.NoOne.Blog.service.VBlogTokenService;
import pers.song.NoOne.Blog.sys.PostData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VBlogTagServiceImpl implements VBlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private VBlogTokenService vBlogTokenService;

    @Override
    @Transactional
    public PostData insertTag(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData());
        String tagName = info.getString("tagName");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",Integer.parseInt(usercode));
        map.put("tagname",tagName);
        BlogTag tag = blogTagMapper.selectByTagName(map);
        if(tag == null){
            tag = new BlogTag();
            tag.setState("1");
            tag.setTagName(tagName);
            tag.setUserId(Integer.parseInt(usercode));
            tag.setCreateDate(new Date());
            blogTagMapper.insert(tag);
        }else {
            tag.setState("1");
            tag.setUpdateDate(new Date());
            blogTagMapper.updateByPrimaryKey(tag);
        }

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData updateTag(PostData data) {
        return null;
    }

    @Override
    @Transactional
    public PostData deleteTag(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData());
        Integer tagId = info.getInteger("tagId");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",Integer.parseInt(usercode));
        map.put("id",tagId);
        BlogTag tag = blogTagMapper.selectByPrimaryKey(map);
        if(tag == null){
            retData.setCode("-202");
            retData.setErrMsg("根据传入id未找到对应数据！");
            return retData;
        }
        tag.setState("0");
        tag.setUpdateDate(new Date());

        blogTagMapper.updateByPrimaryKey(tag);

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData queryAllTag(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        List<BlogTag> list = blogTagMapper.selectByUserId(Integer.valueOf(usercode));
        JSONArray jsonArray = new JSONArray();
        for(BlogTag tag : list){
            JSONObject json = new JSONObject();
            json.put("id",tag.getId());
            json.put("tag",tag.getTagName());
            jsonArray.add(json);

        }
        JSONObject retJson = new JSONObject();
        retJson.put("tags",jsonArray);
        String dataStr = retJson.toJSONString();
        retData.setCode("0");
        retData.setData(dataStr);

        return retData;
    }
}
