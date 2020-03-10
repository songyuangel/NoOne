package pers.song.NoOne.Blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogCategoryService;
import pers.song.NoOne.Blog.sys.PostData;

@RestController
@Api(tags = "博客类别相关接口")
@RequestMapping(value = "/api/category")
@CrossOrigin(allowCredentials="true")
public class VBlogCategoryController {
    @Autowired
    private VBlogCategoryService vBlogCategoryService;

    @ResponseBody
    @ApiOperation("获取类别信息")
    @RequestMapping(value = "/queryAllCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryAllCategory(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogCategoryService.queryAllCategory(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("获取类别信息")
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addCategory(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogCategoryService.insertCategory(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("修改类别信息")
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateCategory(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogCategoryService.updateCategory(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("修删除类别信息")
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteCategory(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogCategoryService.deleteCategory(data);
        return retData.getEncString();
    }


}
