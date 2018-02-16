package com.letsrace.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Admin;
import com.letsrace.spring.model.User;

/**
 * An implementation of the AdminDAO interface.
 * 
 */

@Repository("adminDAO")
public class AdminDAOImpl extends AbstractDao<Integer, Admin> implements
		AdminDAO {

	@Override
	public int isAdminExist(String adminname) {
		SQLQuery query = getSession().createSQLQuery(
				"SELECT password FROM Admin where adminname = '" + adminname
						+ "'");

		List<Object[]> result = query.list();

		if (result == null)
			return 0;
		else
			return result.size();
	}

	@Override
	public String getPassword(String adminname) {
		SQLQuery query = getSession().createSQLQuery(
				"SELECT password FROM Admin where adminname = '" + adminname
						+ "'");

		return (String) query.uniqueResult();
	}

	@Override
	public Admin getAdminDetails(String adminname) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("adminname", adminname));
		return (Admin) criteria.uniqueResult();
	}

	@Override
	public int getPasscode(String adminname) throws Exception {
		SQLQuery query = getSession().createSQLQuery(
				"SELECT passcode FROM Admin where adminname = '" + adminname
						+ "'");

		if (query.uniqueResult() == null)
			return 0;
		return (int) query.uniqueResult();
	}

	@Override
	public int updateAdminConfig(Admin adminForUpdate, String loggedAdmin)
			throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql = new StringBuffer("");
		sql.append(" UPDATE Admin set ");

		boolean adminHasVal = false;
		boolean passwordHasVal = false;
		if ((adminForUpdate.getAdminname() != null)
				&& (adminForUpdate.getAdminname().equalsIgnoreCase("") == false)) {
			sql.append("	adminname='" + adminForUpdate.getAdminname() + "'");
			adminHasVal = true;
		}
		if ((adminForUpdate.getPassword() != null)
				&& (adminForUpdate.getPassword().equalsIgnoreCase("") == false)) {
			if (adminHasVal == true)
				sql.append(",");
			sql.append("	password='" + adminForUpdate.getPassword() + "'");
			passwordHasVal = true;
		}
		if ((adminForUpdate.getPasscode() != null)
				&& (adminForUpdate.getPasscode() != 0)) {
			if (passwordHasVal == true)
				sql.append(",");
			sql.append("	passcode=" + adminForUpdate.getPasscode());
		}
		sql.append(" WHERE ");
		sql.append("	adminname='" + loggedAdmin + "'");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();

	}

	@Override
	public String getEmailOfAdmin(String adminname) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    email ");
		sql.append("	from ");
		sql.append("	    Admin ");
		sql.append("	where ");
		sql.append("	    adminname = '" + adminname + "' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (String) query.uniqueResult();
	}

	@Override
	public int savePasswordForUser(String adminname, String passwordGenerated)
			throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql = new StringBuffer("");
		sql.append(" UPDATE Admin set ");
		sql.append("	password='" + passwordGenerated + "'");
		sql.append(" WHERE ");
		sql.append("	adminname='" + adminname + "'");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int saveAdmin(Admin admin) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (admin.getIssuperuser() != null
				&& admin.getIssuperuser().equalsIgnoreCase("Y") == true) {
			sql.append("	INSERT into Admin ");
			sql.append("	 	(adminname, password, email, issuperuser) ");
		} else {
			sql.append("	INSERT into Admin ");
			sql.append("	 	(adminname, password, email) ");
		}
		sql.append("	VALUES( ");
		sql.append("		'" + admin.getAdminname() + "', ");
		sql.append("		'" + admin.getPassword() + "', ");
		sql.append("		'" + admin.getEmail() + "' ");
		if (admin.getIssuperuser() != null
				&& admin.getIssuperuser().equalsIgnoreCase("Y") == true) {
			sql.append("		,'" + admin.getIssuperuser() + "' ");
		}
		sql.append("		)");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

}
