package pers.song.NoOne.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pers.song.NoOne.Blog.service.VBlogLoginService;
import pers.song.NoOne.Blog.sys.PostData;


@RestController
@RequestMapping(value = "/api/login")
@CrossOrigin(allowCredentials="true")
public class VBlogLoginController {
    @Autowired
    private VBlogLoginService vBlogLoginService;

    @ResponseBody
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String checkLogin(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogLoginService.checkLogin(data);
        System.out.println(retData.toString());
        return retData.getEncString();
    }

    @ResponseBody
    @RequestMapping(value = "/logOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String logOut(@RequestParam String encrypt ){
        PostData data = new PostData(encrypt);
        PostData retData = vBlogLoginService.logOut(data);
        System.out.println(retData.toString());
        return retData.getEncString();
    }

}
