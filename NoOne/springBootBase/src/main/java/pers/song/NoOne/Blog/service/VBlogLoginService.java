package pers.song.NoOne.Blog.service;

import pers.song.NoOne.Blog.sys.PostData;

public interface VBlogLoginService {
    public PostData checkLogin(PostData data);
    public PostData logOut(PostData data);
}
