package pers.song.NoOne.Blog.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.Blog.entity.BlogTag;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogTagMapper {

    int insert(BlogTag record);

    BlogTag selectByPrimaryKey(Map<String,Object> id);

    BlogTag selectByTagName(Map<String,Object> map);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> selectByUserId(Integer userId);
}