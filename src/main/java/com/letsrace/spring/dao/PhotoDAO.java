package com.letsrace.spring.dao;

import java.io.File;
import java.util.List;

import com.letsrace.spring.model.Photo;

/**
 * Defines DAO operations for the Photo model.
 * 
 * @author www.codejava.net
 * 
 */
public interface PhotoDAO {

	public void delete(int photoId);

	public Photo get(int photoId);

	public List<Photo> list();

	public void update(int userid, File serverFile) throws Exception;

	public void save(int userid, byte[] photoBytes) throws Exception;

}
