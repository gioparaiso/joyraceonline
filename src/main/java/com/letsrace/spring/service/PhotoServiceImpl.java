package com.letsrace.spring.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.PhotoDAO;
import com.letsrace.spring.dao.UserDAO;
import com.letsrace.spring.model.Photo;

@Service("PhotoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDAO photoDAO;

	@Autowired
	private UserDAO userDAO;

	@Override
	public void saveOrUpdate(String username, byte[] photoBytes, File serverFile) {
		int userid;
		try {
			userid = userDAO.getUserId(username);
			Photo photo = photoDAO.get(userid);
			if (photo == null)
				photoDAO.save(userid, photoBytes);
			else
				photoDAO.update(userid, serverFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
