package com.letsrace.spring.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Followlist;

/**
 * An implementation of the FollowlistDAO interface.
 * 
 */

@Repository("followlistDAO")
public class FollowListDAOImpl extends AbstractDao<Integer, Followlist>
		implements FollowListDAO {

	@Override
	public void saveOrUpdate(Followlist followlist) {
		// if (followlist.getId() > 0) {
		// // update
		// String sql = "UPDATE followlist SET name=?, email=?, address=?, "
		// + "telephone=? WHERE followlist_id=?";
		// jdbcTemplate.update(sql, followlist.getName(), followlist.getEmail(),
		// followlist.getAddress(), followlist.getTelephone(),
		// followlist.getId());
		// } else {
		// // insert
		// String sql =
		// "INSERT INTO followlist (name, email, address, telephone)"
		// + " VALUES (?, ?, ?, ?)";
		// jdbcTemplate.update(sql, followlist.getName(), followlist.getEmail(),
		// followlist.getAddress(), followlist.getTelephone());
		// }

	}

	@Override
	public void delete(int followlistId) {
		String sql = "DELETE FROM followlist WHERE followlist_id=?";
		// jdbcTemplate.update(sql, followlistId);
	}

	@Override
	public List<Followlist> list() {
		String sql = "SELECT * FROM followlist";
		return null;
	}

	@Override
	public Followlist get(int followlistId) {
		String sql = "SELECT * FROM followlist WHERE followlist_id="
				+ followlistId;
		return null;
	}

	@Override
	public int followUser(String userIdLogged, String followUser)
			throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("	INSERT into followlist ");
		sql.append("	 	(followerid, userid) ");
		sql.append("	VALUES( ");
		sql.append("		(select userid from User where username='" + userIdLogged
				+ "'), ");
		sql.append("		(select userid from User where username='" + followUser
				+ "')) ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	/**
	 * returns [0:not following] [1:following] [2:requested]
	 */
	@Override
	public int getFollowStat(String userIdLogged, String followUser)
			throws Exception {

		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    requested ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    followerid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + userIdLogged + "') ");
		sql.append("	        and userid = (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + followUser + "') ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		// Object object = query.uniqueResult();
		List<Character> result = query.list();

		if (result == null || result.size() == 0)
			return 0; // not following

		Character record = result.get(0);

		if ((record == null)
				|| (record.toString().equalsIgnoreCase("") == true))
			return 1; // following
		else if (record.toString().equalsIgnoreCase("R") == true)
			return 2; // requested
		else {
			System.out
					.println("ERROR: should not have other values in this field");

			// update requested field to 'R'
			sql = new StringBuffer("");
			sql.append(" UPDATE Followlist set ");
			sql.append("	requested='R'");
			sql.append(" WHERE ");
			sql.append("	followerid=(select userid from User where username = '"
					+ userIdLogged + "') and ");
			sql.append("	userid=(select userid from User where username = '"
					+ followUser + "')");

			query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();

			return 2;
		}
	}

	@Override
	public int unfollowUser(String userIdLogged, String unfollowUser)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	DELETE FROM Followlist  ");
		sql.append("	WHERE ");
		sql.append("		followerid = (SELECT userid from User where username = '"
				+ userIdLogged
				+ "') and userid = (SELECT userid from User where username = '"
				+ unfollowUser + "') ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int requestFollowUser(String userIdLogged, String requestFollow)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	INSERT into followlist ");
		sql.append("	 	(followerid, userid, requested) ");
		sql.append("	VALUES( ");
		sql.append("		(select userid from User where username='" + userIdLogged
				+ "'), ");
		sql.append("		(select userid from User where username='"
				+ requestFollow + "'), ");
		sql.append("'R' )");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int cancelRequest(String userIdLogged, String requestFollow)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	DELETE from followlist ");
		sql.append("	WHERE ");
		sql.append("	    followerid = ");

		sql.append("		(select userid from User where username='" + userIdLogged
				+ "') and  ");
		sql.append("	    userid = ");
		sql.append("		(select userid from User where username='"
				+ requestFollow + "') and  ");
		sql.append("	    requested = 'R' ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int getFollowersCount(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    count(*) ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    userid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and (requested <> 'R' or requested is null) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		BigInteger retVal = (BigInteger) query.uniqueResult();
		return retVal.intValue();
	}

	@Override
	public int getFollowingCount(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    count(*) ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    followerid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and (requested <> 'R' or requested is null) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		BigInteger retVal = (BigInteger) query.uniqueResult();
		return retVal.intValue();
	}

	@Override
	public int getRequestsCount(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("	    count(*) ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    userid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and requested = 'R' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		BigInteger retVal = (BigInteger) query.uniqueResult();
		return retVal.intValue();
	}

	@Override
	public List<String> getFollowersNames(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("		   (select  ");
		sql.append("		            username  ");
		sql.append("		        from  ");
		sql.append("		            User ");
		sql.append("		        where ");
		sql.append("		            userid = followerid) as user ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    userid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and (requested <> 'R' or requested is null) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<String> getFollowingNames(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("		   (select  ");
		sql.append("		            username  ");
		sql.append("		        from  ");
		sql.append("		            User ");
		sql.append("		        where ");
		sql.append("		            userid = a.userid) as user ");
		sql.append("	FROM ");
		sql.append("	    Followlist a ");
		sql.append("	WHERE ");
		sql.append("	    followerid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and (requested <> 'R' or requested is null) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public List<String> getFollowrequestsNames(String loggedUser) {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("		   (select  ");
		sql.append("		            username  ");
		sql.append("		        from  ");
		sql.append("		            User ");
		sql.append("		        where ");
		sql.append("		            userid = followerid) as user ");
		sql.append("	FROM ");
		sql.append("	    Followlist ");
		sql.append("	WHERE ");
		sql.append("	    userid = (Select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		sql.append("	        and requested = 'R' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public int followApprove(String loggedUser, String requestapprove)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	UPDATE followlist ");
		sql.append("	SET ");
		sql.append("		requested = null ");
		sql.append("	WHERE ");
		sql.append("	    followerid = ");

		sql.append("		(select userid from User where username='"
				+ requestapprove + "') and  ");
		sql.append("	    userid = ");
		sql.append("		(select userid from User where username='" + loggedUser
				+ "') and  ");
		sql.append("	    requested = 'R' ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int followDeny(String loggedUser, String requestapprove)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	DELETE from followlist ");
		sql.append("	WHERE ");
		sql.append("	    followerid = ");

		sql.append("		(select userid from User where username='"
				+ requestapprove + "') and  ");
		sql.append("	    userid = ");
		sql.append("		(select userid from User where username='" + loggedUser
				+ "') and  ");
		sql.append("	    requested = 'R' ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public int removeFollower(String loggedUser, String removeUser)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("	DELETE from followlist ");
		sql.append("	WHERE ");
		sql.append("	    followerid = ");

		sql.append("		(select userid from User where username='" + removeUser
				+ "') and  ");
		sql.append("	    userid = ");
		sql.append("		(select userid from User where username='" + loggedUser
				+ "') ");

		Query query = getSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public BigInteger hasHundredFollowing(String loggedUser) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    count(*) ");
		sql.append("	from ");
		sql.append("	    Followlist ");
		sql.append("	where ");
		sql.append("	    followerid = (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (BigInteger) query.uniqueResult();
	}

	@Override
	public BigInteger hasHundredFollowers(String follow) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	select ");
		sql.append("	    count(*) ");
		sql.append("	from ");
		sql.append("	    Followlist ");
		sql.append("	where ");
		sql.append("	    userid = (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + follow + "') ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (BigInteger) query.uniqueResult();
	}

}
