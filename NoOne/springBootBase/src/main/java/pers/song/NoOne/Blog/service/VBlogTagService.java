package pers.song.NoOne.Blog.service;

import pers.song.NoOne.Blog.sys.PostData;

public interface VBlogTagService {
    public PostData insertTag(PostData data);
    public PostData updateTag(PostData data);
    public PostData deleteTag(PostData data);
    public PostData queryAllTag(PostData data);
}
