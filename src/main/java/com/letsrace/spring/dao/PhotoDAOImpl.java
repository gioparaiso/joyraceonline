package com.letsrace.spring.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Photo;

/**
 * An implementation of the PhotoDAO interface.
 * 
 */

@Repository("photoDAO")
public class PhotoDAOImpl extends AbstractDao<Integer, Photo> implements
		PhotoDAO {

	@Override
	public void delete(int userid) {
	}

	@Override
	public List<Photo> list() {
		return null;
	}

	@Override
	public Photo get(int userid) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userid", userid));
		return (Photo) criteria.uniqueResult();
	}

	@Override
	public void save(int userid, byte[] photoBytes) {
		Photo photo = new Photo();
		photo.setUserid(userid);
		photo.setImage(photoBytes);

		persist(photo);
	}

	@Override
	public void update(int userid, File serverFile) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append(" UPDATE Photo set ");
		sql.append("	image = ? ");
		sql.append(" WHERE ");
		sql.append("	userid = ? ");

		// read the file
		FileInputStream input = new FileInputStream(serverFile);

		// set parameters
		SessionImpl sessionImpl = (SessionImpl) getSession();
		Connection conn = sessionImpl.connection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setBinaryStream(1, input);
		pstmt.setInt(2, userid);

		// store the resume file in database
		System.out.println("Reading file " + serverFile.getAbsolutePath());
		System.out.println("Store file in the database.");
		pstmt.executeUpdate();
		conn.close();
	}

}
