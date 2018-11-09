package pers.song.NoOne.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.song.NoOne.dao.t_userMapper;
import pers.song.NoOne.entity.Tuser;
import pers.song.NoOne.service.TestService;

@Service(value = "testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private t_userMapper tuserMapper;

	@Override
	public Tuser findTuser(Long id) {
		
		return tuserMapper.selectByPrimaryKey(id);
	}

}
