package pers.song.NoOne.dao;

import org.springframework.stereotype.Repository;
import pers.song.NoOne.entity.Tuser;

@Repository
public interface TuserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated
     */
    int insert(Tuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated
     */
    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Long id);
}