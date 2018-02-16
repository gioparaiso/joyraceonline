package com.letsrace.spring.service;

import com.letsrace.spring.model.Admin;

public abstract interface AdminService {

	public abstract int isAdminExist(String adminname);

	public abstract boolean isCorrectPassword(String adminname, String password);

	public abstract Admin getAdminDetails(String adminname);

	public abstract int getPasscode(String adminname);

	public abstract int updateAdminConfig(Admin adminForUpdate,
			String loggedAdmin);

	public abstract String getEmailOfAdmin(String adminname);

	public abstract boolean savePasswordForAdmin(String adminname,
			String passwordGenerated);

	public abstract boolean saveAdmin(Admin admin);

}
