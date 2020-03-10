package pers.song.NoOne.Blog.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.Blog.entity.BlogCategory;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer id);

    BlogCategory selectByCategoryName(Map<String,Object> map);

    int updateByPrimaryKey(BlogCategory record);

    List<BlogCategory> selectByUserId(Integer userId);
}