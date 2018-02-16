package com.letsrace.spring.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Racelog;
import com.letsrace.spring.model.User;

/**
 * An implementation of the RacelogDAO interface.
 * 
 */

@Repository("racelogDAO")
public class RaceLogDAOImpl extends AbstractDao<Integer, User> implements
		RaceLogDAO {

	@Override
	public List<Object[]> getBestLapTime() {

		StringBuffer sql = new StringBuffer("");
		sql.append(" select ");
		sql.append(" 	min(lap1), ");
		sql.append(" 	min(lap2), ");
		sql.append(" 	min(lap3), ");
		sql.append(" 	min(lap4), ");
		sql.append(" 	min(lap5) ");
		sql.append(" from ");
		sql.append(" 	Racelog ");
		sql.append(" where ");
		sql.append(" 	result = '1' ");
		sql.append(" 	and status = 'FIN' ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

	@Override
	public void saveOrUpdate(Racelog racelog) {
		// if (racelog.getId() > 0) {
		// // update
		// String sql = "UPDATE racelog SET name=?, email=?, address=?, "
		// + "telephone=? WHERE racelog_id=?";
		// jdbcTemplate.update(sql, racelog.getName(), racelog.getEmail(),
		// racelog.getAddress(), racelog.getTelephone(), racelog.getId());
		// } else {
		// // insert
		// String sql = "INSERT INTO racelog (name, email, address, telephone)"
		// + " VALUES (?, ?, ?, ?)";
		// jdbcTemplate.update(sql, racelog.getName(), racelog.getEmail(),
		// racelog.getAddress(), racelog.getTelephone());
		// }

	}

	@Override
	public void delete(int racelogId) {
		String sql = "DELETE FROM racelog WHERE racelog_id=?";
		// jdbcTemplate.update(sql, racelogId);
	}

	@Override
	public List<Racelog> list() {
		String sql = "SELECT * FROM racelog";
		return null;
	}

	@Override
	public Racelog get(int racelogId) {
		String sql = "SELECT * FROM racelog WHERE racelog_id=" + racelogId;
		return null;
	}

	@Override
	public List<Object[]> getUserRaceHistory(String loggedUser)
			throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append("	SELECT ");
		sql.append("		date, ");
		sql.append("		totaltime, ");
		sql.append("		result, ");
//		sql.append("		LEAST(lap1,lap2,lap3,lap4,lap5), ");
		sql.append("		lap1, ");
		sql.append("		lap2, ");
		sql.append("		lap3, ");
		sql.append("		lap4, ");
		sql.append("		lap5 ");
		sql.append("	FROM ");
		sql.append("	    Racelog ");
		sql.append("	WHERE ");
		sql.append("	    userid = (select ");
		sql.append("	            userid ");
		sql.append("	        from ");
		sql.append("	            User ");
		sql.append("	        where ");
		sql.append("	            username = '" + loggedUser + "') ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		return (List<Object[]>) query.list();
	}
}
