package com.letsrace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.LocationDAO;

@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;

}
