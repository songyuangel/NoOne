package pers.song.NoOne.Blog.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.Blog.entity.BlogUserinfo;

@Repository
public interface BlogUserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogUserinfo record);

    int insertSelective(BlogUserinfo record);

    BlogUserinfo selectByPrimaryKey(Integer id);

    BlogUserinfo selectByAccountId(Integer id);

    int updateByPrimaryKeySelective(BlogUserinfo record);

    int updateByPrimaryKey(BlogUserinfo record);
}