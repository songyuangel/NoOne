package pers.song.NoOne.Blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogUserinfoService;
import pers.song.NoOne.Blog.sys.PostData;

@RestController
@Api(tags = "用户信息相关接口")
@RequestMapping(value = "/api/userinfo")
@CrossOrigin(allowCredentials="true")
public class VBlogUserController {
    @Autowired
    private VBlogUserinfoService vBlogUserinfoService;

    @ResponseBody
    @ApiOperation("更新用户接口")
    @RequestMapping(value = "/updateUserinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateUserinfo(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogUserinfoService.updateUserinfo(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("获取用户信息接口")
    @RequestMapping(value = "/getUserinfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getUserinfo(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogUserinfoService.getUserinfo(data);
        System.out.println(retData.toString());
        return retData.getEncString();
    }

}
