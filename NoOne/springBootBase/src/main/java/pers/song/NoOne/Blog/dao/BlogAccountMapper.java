package pers.song.NoOne.Blog.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.Blog.entity.BlogAccount;
import pers.song.NoOne.Blog.entity.BlogAccountKey;

import java.util.Map;

@Repository
public interface BlogAccountMapper {
    int deleteByPrimaryKey(BlogAccountKey key);

    int insert(BlogAccount record);

    int insertSelective(BlogAccount record);

    BlogAccount selectByPrimaryKey(BlogAccountKey key);

    BlogAccount selectByAccountAndPassword(Map map);

    int updateByPrimaryKeySelective(BlogAccount record);

    int updateByPrimaryKey(BlogAccount record);
}