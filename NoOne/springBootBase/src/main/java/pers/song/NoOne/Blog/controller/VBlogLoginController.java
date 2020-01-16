package pers.song.NoOne.Blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogLoginService;
import pers.song.NoOne.Blog.sys.PostData;


@RestController
@Api(tags = "用户登录相关接口")
@RequestMapping(value = "/api/login")
@CrossOrigin(allowCredentials="true")
public class VBlogLoginController {
    @Autowired
    private VBlogLoginService vBlogLoginService;

    @ResponseBody
    @ApiOperation("登录接口")
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String checkLogin(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogLoginService.checkLogin(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("注销接口")
    @RequestMapping(value = "/logOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String logOut(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogLoginService.logOut(data);
        return retData.getEncString();
    }

    @ResponseBody
    @ApiOperation("修改用户密码接口")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String changePassword(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogLoginService.changePassword(data);
        return retData.getEncString();
    }

}
