package pers.song.NoOne.Blog.service;

import pers.song.NoOne.Blog.sys.PostData;

public interface VBlogTokenService {
    public PostData login(PostData data);
    public PostData logout(PostData data);
    public PostData checkToken(PostData data);
}
