package com.letsrace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.RaceTrackDAO;
import com.letsrace.spring.model.User;

@Service("raceTrackService")
@Transactional
public class RaceTrackServiceImpl implements RaceTrackService {

	@Autowired
	private RaceTrackDAO raceTrackDAO;

	public void updateUser(User user) {
	}

}
