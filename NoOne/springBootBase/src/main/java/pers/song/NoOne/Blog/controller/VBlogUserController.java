package pers.song.NoOne.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogUserinfoService;
import pers.song.NoOne.Blog.sys.PostData;

@RestController
@RequestMapping(value = "/api/userinfo")
@CrossOrigin(allowCredentials="true")
public class VBlogUserController {
    @Autowired
    private VBlogUserinfoService vBlogUserinfoService;

    @ResponseBody
    @RequestMapping(value = "/updateUserinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateUserinfo(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogUserinfoService.updateUserinfo(data);
        return retData.getEncString();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getUserinfo(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogUserinfoService.getUserinfo(data);
        System.out.println(retData.toString());
        return retData.getEncString();
    }

}
