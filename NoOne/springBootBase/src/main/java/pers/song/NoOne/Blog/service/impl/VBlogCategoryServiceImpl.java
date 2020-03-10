package pers.song.NoOne.Blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.song.NoOne.Blog.dao.BlogCategoryMapper;
import pers.song.NoOne.Blog.entity.BlogCategory;
import pers.song.NoOne.Blog.service.VBlogCategoryService;
import pers.song.NoOne.Blog.service.VBlogTokenService;
import pers.song.NoOne.Blog.sys.PostData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VBlogCategoryServiceImpl implements VBlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private VBlogTokenService vBlogTokenService;

    @Override
    public PostData insertCategory(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData());
        String categoryName = info.getString("category");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",Integer.parseInt(usercode));
        map.put("categoryname",categoryName);
        BlogCategory category = blogCategoryMapper.selectByCategoryName(map);
        if(category == null){
            category = new BlogCategory();
            category.setState("1");
            category.setCateName(categoryName);
            category.setUserId(Integer.parseInt(usercode));
            category.setCreateDate(new Date());
            blogCategoryMapper.insert(category);
        }else {
            category.setState("1");
            category.setUpdateDate(new Date());
            blogCategoryMapper.updateByPrimaryKey(category);
        }

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData updateCategory(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData());
        String categoryName = info.getString("category");
        Integer categoryId = info.getInteger("id");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",Integer.parseInt(usercode));
        map.put("categoryname",categoryName);
        // 查询是否有同名的
        BlogCategory nameCategory = blogCategoryMapper.selectByCategoryName(map);
        if(nameCategory != null && "1".equals(nameCategory.getState())){
            // 如果有同名并且在用的，就不给修改了
            retData.setCode("-202");
            retData.setErrMsg("已经存在该类别！");
            return retData;
        }

        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if(blogCategory == null){
            retData.setCode("-202");
            retData.setErrMsg("未找到有效的类别信息！");
            return retData;
        }
        Date updateDate = new Date();
        if(nameCategory != null){
            // 如果还有同名的，就替换名字
            nameCategory.setCateName(blogCategory.getCateName());
            nameCategory.setUpdateDate(updateDate);
            blogCategoryMapper.updateByPrimaryKey(nameCategory);
        }
        blogCategory.setCateName(categoryName);
        blogCategory.setUpdateDate(updateDate);
        blogCategoryMapper.updateByPrimaryKey(blogCategory);

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData deleteCategory(PostData data) {
        // 验证token有效
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        JSONObject info = JSON.parseObject(data.getData());
        String categoryName = info.getString("category");
        Integer categoryId = info.getInteger("id");
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if(blogCategory == null){
            retData.setCode("-202");
            retData.setErrMsg("未找到有效的类别信息！");
            return retData;
        }
        blogCategory.setState("0");
        blogCategory.setUpdateDate(new Date());
        blogCategoryMapper.updateByPrimaryKey(blogCategory);

        retData.setCode("0");
        retData.setData("");

        return retData;
    }

    @Override
    public PostData queryAllCategory(PostData data) {
        PostData retData = vBlogTokenService.checkToken(data);
        if (retData.getCode() != null && Integer.valueOf(retData.getCode()) < 0){
            return retData;
        }

        String usercode = data.getUsercode();
        List<BlogCategory> list = blogCategoryMapper.selectByUserId(Integer.valueOf(usercode));
        JSONArray jsonArray = new JSONArray();
        for(BlogCategory category : list){
            JSONObject json = new JSONObject();
            json.put("id",category.getId());
            json.put("category",category.getCateName());
            jsonArray.add(json);

        }
        JSONObject retJson = new JSONObject();
        retJson.put("categories",jsonArray);
        String dataStr = retJson.toJSONString();
        retData.setCode("0");
        retData.setData(dataStr);

        return retData;
    }
}
