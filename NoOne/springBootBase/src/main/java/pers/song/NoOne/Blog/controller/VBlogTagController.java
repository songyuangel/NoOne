package pers.song.NoOne.Blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogTagService;
import pers.song.NoOne.Blog.sys.PostData;

@RestController
@Api(tags = "博客标签相关接口")
@RequestMapping(value = "/api/tag")
@CrossOrigin(allowCredentials="true")
public class VBlogTagController {
    @Autowired
    private VBlogTagService vBlogTagService;

    @ResponseBody
    @ApiOperation("获取标签信息")
    @RequestMapping(value = "/queryAllTag", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryAllTag(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogTagService.queryAllTag(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("删除标签")
    @RequestMapping(value = "/deleteTag", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteTag(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogTagService.deleteTag(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("新增标签")
    @RequestMapping(value = "/addTag", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addTag(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogTagService.insertTag(data);
        return retData.getEncString();
    }
}
