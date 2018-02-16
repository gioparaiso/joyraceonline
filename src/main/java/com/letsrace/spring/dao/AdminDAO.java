package com.letsrace.spring.dao;

import com.letsrace.spring.model.Admin;

/**
 * Defines DAO operations for the admin model.
 * 
 */

public interface AdminDAO {

	public int isAdminExist(String adminname);

	public String getPassword(String adminname);

	public Admin getAdminDetails(String adminname);

	public int getPasscode(String adminname) throws Exception;

	public int updateAdminConfig(Admin adminForUpdate, String loggedAdmin)
			throws Exception;

	public String getEmailOfAdmin(String adminname) throws Exception;

	public int savePasswordForUser(String adminname, String passwordGenerated)
			throws Exception;

	public int saveAdmin(Admin admin) throws Exception;

}
