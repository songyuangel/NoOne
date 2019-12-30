package pers.song.NoOne.Blog.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.Blog.entity.SysToken;

import java.util.Map;

@Repository
public interface SysTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysToken record);

    int insertSelective(SysToken record);

    SysToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysToken record);

    int updateByPrimaryKey(SysToken record);

    int updateStatueByAccountId(Map record);

    SysToken selectByToken(Map token);
}