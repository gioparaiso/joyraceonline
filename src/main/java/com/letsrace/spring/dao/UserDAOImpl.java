package com.letsrace.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.User;

/**
 * An implementation of the UserDAO interface.
 * 
 */

@Repository("userDAO")
public class UserDAOImpl extends AbstractDao<Integer, User> implements UserDAO {

	public User findUserByUsername(String username) {
		// Criteria criteria = createEntityCriteria();
		// criteria.add(Restrictions.eq("username", username));

		// select all data of a year

		SQLQuery query = getSession()
				.createSQLQuery(
						"SELECT password FROM User where username = '"
								+ username + "'");

		List<Object[]> result = query.list();

		// more than 1 username (error)
		if (result.size() > 1) {
			return null;
		} else if (result.size() == 0) {
			return null;
		} else {
			User user = new User();
			String[] passwords = (String[]) result.get(0);
			user.setPassword(passwords[0].toString());
			user.setUsername(username);
			return user;
		}

		// Query query = getSession()
		// .createQuery(
		// "SELECT * FROM User where username = '" + username + "'");

		// return (User) query.uniqueResult();
	}

	@Override
	public void update(User user) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET ");
		sql.append("	besttimerace=:besttimerace, ");
		sql.append("	bestlaprace=:bestlaprace, ");
		sql.append("	besttimesolo=:besttimesolo, ");
		sql.append("	bestlapsolo=:bestlapsolo, ");
		sql.append("	pct=:pct, ");
		sql.append("	careerpts=:careerpts ");
		sql.append("WHERE ");
		sql.append("	userid=:userid");

		Query query = getSession().createSQLQuery(sql.toString());
		query.setInteger("userid", user.getUserid());
		query.setFloat("besttimerace", user.getBesttimerace());
		query.setFloat("bestlaprace", user.getBestlaprace());
		query.setFloat("besttimesolo", user.getBesttimesolo());
		query.setFloat("bestlapsolo", user.getBestlapsolo());
		query.setFloat("pct", user.getPct());
		query.setInteger("careerpts", user.getCareerpts());
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(User user) {
		if (user.getUserid() > 0) {
			// update
			// String sql = "UPDATE user SET name=?, email=?, address=?, "
			// + "telephone=? WHERE userid=?";
			// jdbcTemplate.update(sql, user.getUsername(), user.getEmail(),
			// user.getAddress(), user.getTelephone(), user.getId());
		} else {
			// insert
			// String sql = "INSERT INTO user (name, email, address, telephone)"
			// + " VALUES (?, ?, ?, ?)";
			// jdbcTemplate.update(sql, user.getName(), user.getEmail(),
			// user.getAddress(), user.getTelephone());
		}

	}

	@Override
	public void delete(int userId) {
		String sql = "DELETE FROM user WHERE userid=?";
		// jdbcTemplate.update(sql, userId);
	}

	@Override
	public List<User> list() {
		String sql = "SELECT * FROM user";
		// List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

		// @Override
		// public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// User aUser = new User();
		//
		// aUser.setUserid(rs.getInt("userid"));
		// aUser.setUsername(rs.getString("username"));
		// aUser.setPassword(rs.getString("password"));
		// aUser.setEmail(rs.getString("email"));
		// aUser.setPicture(rs.getString("picture"));
		//
		// return aUser;
		// }
		//
		// });
		//
		// return listUser;
		return null;
	}

	@Override
	public User get(int userId) {
		String sql = "SELECT * FROM user WHERE userid=" + userId;
		// return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
		//
		// @Override
		// public User extractData(ResultSet rs) throws SQLException,
		// DataAccessException {
		// if (rs.next()) {
		// User user = new User();
		// user.setUserid(rs.getInt("userid"));
		// user.setUsername(rs.getString("username"));
		// user.setEmail(rs.getString("email"));
		// user.setPassword(rs.getString("password"));
		// user.setPicture(rs.getString("picture"));
		// return user;
		// }
		//
		// return null;
		// }
		//
		// });
		return null;
	}

	@Override
	public int saveUser(User user) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("	INSERT into User ");
		sql.append("	 	(username, password, email) ");
		sql.append("	VALUES( ");
		sql.append("		'" + user.getUsername() + "', ");
		sql.append("		'" + user.getPassword() + "', ");
		sql.append("		'" + user.getEmail() + "') ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int isUserExist(String username) {
		SQLQuery query = getSession()
				.createSQLQuery(
						"SELECT password FROM User where username = '"
								+ username + "'");

		List<Object[]> result = query.list();

		if (result == null)
			return 0;
		else
			return result.size();
	}

	@Override
	public String getPassword(String username) {
		SQLQuery query = getSession()
				.createSQLQuery(
						"SELECT password FROM User where username = '"
								+ username + "'");

		return (String) query.uniqueResult();
	}

	@Override
	public User getUserDetails(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}

	@Override
	public List<String> findUserByNameOrEmail(String usernameOrEmail,
			String loggedUser) {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    username ");
		sql.append("	FROM ");
		sql.append("	    User ");
		sql.append("	WHERE ");
		sql.append(" 		(username like '" + usernameOrEmail + "%' or ");
		sql.append(" 		email like '" + usernameOrEmail + "%') ");
		sql.append(" 		and username <> '" + loggedUser + "' ");
		sql.append(" 	ORDER BY username asc ");
		sql.append(" 	LIMIT 50 ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<Object[]> getUserRaceStat(String username) {

		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    sum(if(result = '1', 1, 0)) as firsts, ");
		sql.append("	    sum(if(result = '2', 1, 0)) as seconds, ");
		sql.append("	    sum(if(result = '3', 1, 0)) as thirds, ");
		sql.append("	    min(totaltime) as bestrace, ");
		sql.append("	    min(lap1), ");
		sql.append("	    min(lap2), ");
		sql.append("	    min(lap3), ");
		sql.append("	    min(lap4), ");
		sql.append("	    min(lap5) ");
		sql.append("	from ");
		sql.append("	    Racelog a, ");
		sql.append("	    User b ");
		sql.append("	where ");
		sql.append(" 		b.username = '" + username + "' ");
		sql.append("	    and a.userid = b.userid ");
		sql.append("	    and a.status = 'FIN' ");
		sql.append("	    and (result = '1' or result = '2' ");
		sql.append("	    or result = '3'); ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<Object[]> getUserSoloStat(String username) {

		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    sum(if(result = 's', 1, 0)) as solos, ");
		sql.append("	    min(totaltime) as bestsolo, ");
		sql.append("	    min(lap1), ");
		sql.append("	    min(lap2), ");
		sql.append("	    min(lap3), ");
		sql.append("	    min(lap4), ");
		sql.append("	    min(lap5) ");
		sql.append("	from ");
		sql.append("	    Racelog a, ");
		sql.append("	    User b ");
		sql.append("	where ");
		sql.append(" 		b.username = '" + username + "' ");
		sql.append("	    and a.userid = b.userid ");
		sql.append("	    and a.status = 'FIN' ");
		sql.append("	    and result = 's' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<Object[]> getHomeStat() {

		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    min(besttimerace), ");
		sql.append("	    min(bestlaprace), ");
		sql.append("	    min(besttimesolo), ");
		sql.append("	    min(bestlapsolo), ");
		sql.append("	    max(pct), ");
		sql.append("	    max(careerpts) ");
		sql.append("	FROM ");
		sql.append("	    User ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	/**
	 * returns [0:private] [1:public]
	 */
	@Override
	public int getPrivacy(String followUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    privacy ");
		sql.append("	FROM ");
		sql.append("	    User ");
		sql.append("	WHERE ");
		sql.append("	    username = '" + followUser + "'");

		SQLQuery sqlquery = getSession().createSQLQuery(sql.toString());
		Character privacy = (Character) sqlquery.uniqueResult();

		// if no value set, set to public
		if ((privacy == null)
				|| (privacy.toString().equalsIgnoreCase("") == true)) {
			// 0=private, 1=public
			sql = new StringBuffer("");
			sql.append(" UPDATE User set ");
			sql.append("	privacy='1'");
			sql.append(" WHERE ");
			sql.append("	username='" + followUser + "'");

			Query query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();

			return 1;
		} else if (privacy.toString().equalsIgnoreCase("0") == true) {
			return 0;
		} else if (privacy.toString().equalsIgnoreCase("1") == true) {
			return 1;
		} else {
			// other values was set, set to public "1"
			sql = new StringBuffer("");
			sql.append(" UPDATE User set ");
			sql.append("	privacy='1'");
			sql.append(" WHERE ");
			sql.append("	username='" + followUser + "'");

			Query query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();

			return 1;
		}
	}

	@Override
	public int updateUsernamePassword(String loggedUser, String username,
			String passwd) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql = new StringBuffer("");
		sql.append(" UPDATE User set ");
		if ((username != null) && (username.equalsIgnoreCase("") == false))
			sql.append("	username='" + username + "'");
		if ((passwd != null) && (passwd.equalsIgnoreCase("") == false)) {
			if ((username != null) && (username.equalsIgnoreCase("") == false))
				sql.append(",");
			sql.append("	password='" + passwd + "'");
		}
		sql.append(" WHERE ");
		sql.append("	username='" + loggedUser + "'");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public List<Object[]> getWorldStat(String loggedUser) {

		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.besttimerace) <= (uo.besttimerace)) AS arankbesttimerace, ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.bestlaprace) <= (uo.bestlaprace)) AS arankbestlaprace, ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.besttimesolo) <= (uo.besttimesolo)) AS arankbesttimesolo, ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.bestlapsolo) <= (uo.bestlapsolo)) AS arankbestlapsolo, ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.pct) >= (uo.pct)) AS arankbestpct, ");
		sql.append("    (SELECT ");
		sql.append("            COUNT(*) ");
		sql.append("        FROM ");
		sql.append("            User ui ");
		sql.append("        WHERE ");
		sql.append("            (ui.careerpts) >= (uo.careerpts)) AS arankbestcareerpts ");
		sql.append("FROM ");
		sql.append("    User uo ");
		sql.append("WHERE ");
		sql.append("	    userid = (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "'); ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<Object[]> getBesttimeFriends(String loggedUser, String field,
			String order) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    username, " + field);
		sql.append("	from ");
		sql.append("	    User ");
		sql.append("	where ");
		sql.append("	    userid in (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("        		userid = (select ");
		sql.append("                	userid ");
		sql.append("            	from ");
		sql.append("                	User ");
		sql.append("            	where ");
		sql.append("                	username = '" + loggedUser + "') ");
		sql.append("			union ");
		sql.append("			select ");
		sql.append("	            followerid ");
		sql.append("	        from ");
		sql.append("	            Followlist ");
		sql.append("	        where ");
		sql.append("        		userid = (select ");
		sql.append("                	userid ");
		sql.append("            	from ");
		sql.append("                	User ");
		sql.append("            	where ");
		sql.append("                	username = '" + loggedUser + "') ");
		sql.append("			union ");
		sql.append("			select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            Followlist ");
		sql.append("	        where ");
		sql.append("        		followerid = (select ");
		sql.append("                	userid ");
		sql.append("            	from ");
		sql.append("                	User ");
		sql.append("            	where ");
		sql.append("                	username = '" + loggedUser + "') ) ");
		sql.append("	order by " + field + " " + order + "; ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public int getUserId(String username) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    userid ");
		sql.append("	from ");
		sql.append("	    User ");
		sql.append("	where ");
		sql.append("	    username = '" + username + "' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (int) query.uniqueResult();
	}

	@Override
	public String getEmailOfUser(String username) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    email ");
		sql.append("	from ");
		sql.append("	    User ");
		sql.append("	where ");
		sql.append("	    username = '" + username + "' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (String) query.uniqueResult();
	}

	@Override
	public int savePasswordForUser(String username, String passwordGenerated)
			throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql = new StringBuffer("");
		sql.append(" UPDATE User set ");
		sql.append("	password='" + passwordGenerated + "'");
		sql.append(" WHERE ");
		sql.append("	username='" + username + "'");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int updateUserConfig(String loggedUser, String username,
			String passwd, String privacy) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql = new StringBuffer("");
		sql.append(" UPDATE User set ");
		if ((username != null) && (username.equalsIgnoreCase("") == false))
			sql.append("	username='" + username + "'");
		if ((passwd != null) && (passwd.equalsIgnoreCase("") == false)) {
			if ((username != null) && (username.equalsIgnoreCase("") == false))
				sql.append(",");
			sql.append("	password='" + passwd + "'");
		}
		if ((privacy != null) && (privacy.equalsIgnoreCase("Y") == true)) {
			if ((username != null) && (username.equalsIgnoreCase("") == false))
				sql.append(",");
			else if ((passwd != null) && (passwd.equalsIgnoreCase("") == false))
				sql.append(",");
			sql.append("	privacy='Y'");
		} else {
			if ((username != null) && (username.equalsIgnoreCase("") == false))
				sql.append(",");
			else if ((passwd != null) && (passwd.equalsIgnoreCase("") == false))
				sql.append(",");
			sql.append("	privacy='N'");
		}
		sql.append(" WHERE ");
		sql.append("	username='" + loggedUser + "'");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}
}
