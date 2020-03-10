package pers.song.NoOne.Blog.service;

import pers.song.NoOne.Blog.sys.PostData;

public interface VBlogCategoryService {
    public PostData insertCategory(PostData data);
    public PostData updateCategory(PostData data);
    public PostData deleteCategory(PostData data);
    public PostData queryAllCategory(PostData data);
}
