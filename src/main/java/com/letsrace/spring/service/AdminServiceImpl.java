package com.letsrace.spring.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.MinFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.AdminDAO;
import com.letsrace.spring.model.Admin;
import com.letsrace.spring.model.User;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;

	ServiceUtil util = new ServiceUtil();

	@Override
	public int isAdminExist(String adminname) {
		return this.dao.isAdminExist(adminname);
	}

	@Override
	public boolean isCorrectPassword(String adminname, String password) {
		String dbpassword = this.dao.getPassword(adminname);

		try {
			System.out.println("password (not encrytpted) :" + password);
			password = util.generateStorngPasswordHash(password);
			System.out.println("password :" + password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (dbpassword == null)
			return false;
		if (password.equalsIgnoreCase(dbpassword) == true)
			return true;
		else
			return false;
	}

	@Override
	public Admin getAdminDetails(String adminname) {
		return this.dao.getAdminDetails(adminname);
	}

	@Override
	public int getPasscode(String adminname) {
		try {
			return this.dao.getPasscode(adminname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * returns [0:not updated] [1:update success] [2:error]
	 */
	@Override
	public int updateAdminConfig(Admin adminForUpdate, String loggedAdmin) {
		try {
			if (adminForUpdate.getPassword() != null
					&& (adminForUpdate.getPassword().equalsIgnoreCase("") == false))
				adminForUpdate.setPassword(util
						.generateStorngPasswordHash(adminForUpdate
								.getPassword()));
			return this.dao.updateAdminConfig(adminForUpdate, loggedAdmin);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public String getEmailOfAdmin(String adminname) {
		String email = "";
		try {
			email = dao.getEmailOfAdmin(adminname);
			if (email == null)
				return "";
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return email;
	}

	@Override
	public boolean savePasswordForAdmin(String adminname,
			String passwordGenerated) {
		int result = 0;
		try {
			passwordGenerated = util
					.generateStorngPasswordHash(passwordGenerated);
			result = dao.savePasswordForUser(adminname, passwordGenerated);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
		if (result == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean saveAdmin(Admin admin) {
		try {
			admin.setPassword(util.generateStorngPasswordHash(admin
					.getPassword()));
			int retVal = this.dao.saveAdmin(admin);
			if (retVal > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
			// e.printStackTrace();
			return false;
		}
	}
}
