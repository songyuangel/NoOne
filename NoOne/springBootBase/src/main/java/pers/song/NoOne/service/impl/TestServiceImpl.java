package pers.song.NoOne.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.song.NoOne.dao.TuserMapper;
import pers.song.NoOne.entity.Tuser;
import pers.song.NoOne.service.TestService;

@Service(value = "testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TuserMapper tuserMapper;

	@Override
	public Tuser findTuser(Long id) {

        return tuserMapper.selectByPrimaryKey(id);
		//return null;
    }


}
