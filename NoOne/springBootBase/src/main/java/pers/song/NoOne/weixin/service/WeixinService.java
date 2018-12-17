package pers.song.NoOne.weixin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WeixinService {
	void service(HttpServletRequest request,HttpServletResponse response);
}
