package com.letsrace.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Racetrack;

/**
 * An implementation of the RacetrackDAO interface.
 * 
 */

@Repository("racetrackDAO")
public class RaceTrackDAOImpl extends AbstractDao<Integer, Racetrack> implements
		RaceTrackDAO {

	// private JdbcTemplate jdbcTemplate;
	//
	// public RaceTrackDAOImpl(DataSource dataSource) {
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }

	@Override
	public void saveOrUpdate(Racetrack racetrack) {
		// if (racetrack.getId() > 0) {
		// // update
		// String sql = "UPDATE racetrack SET name=?, email=?, address=?, "
		// + "telephone=? WHERE racetrack_id=?";
		// jdbcTemplate.update(sql, racetrack.getName(), racetrack.getEmail(),
		// racetrack.getAddress(), racetrack.getTelephone(), racetrack.getId());
		// } else {
		// // insert
		// String sql =
		// "INSERT INTO racetrack (name, email, address, telephone)"
		// + " VALUES (?, ?, ?, ?)";
		// jdbcTemplate.update(sql, racetrack.getName(), racetrack.getEmail(),
		// racetrack.getAddress(), racetrack.getTelephone());
		// }

	}

	@Override
	public void delete(int racetrackId) {
		String sql = "DELETE FROM racetrack WHERE racetrack_id=?";
		// jdbcTemplate.update(sql, racetrackId);
	}

	@Override
	public List<Racetrack> list() {
		String sql = "SELECT * FROM racetrack";
		// List<Racetrack> listRacetrack = jdbcTemplate.query(sql,
		// new RowMapper<Racetrack>() {
		//
		// @Override
		// public Racetrack mapRow(ResultSet rs, int rowNum)
		// throws SQLException {
		// Racetrack aRacetrack = new Racetrack();
		//
		// // aRacetrack.setId(rs.getInt("racetrack_id"));
		// // aRacetrack.setName(rs.getString("name"));
		// // aRacetrack.setEmail(rs.getString("email"));
		// // aRacetrack.setAddress(rs.getString("address"));
		// // aRacetrack.setTelephone(rs.getString("telephone"));
		//
		// return aRacetrack;
		// }
		//
		// });
		//
		// return listRacetrack;
		return null;
	}

	@Override
	public Racetrack get(int racetrackId) {
		String sql = "SELECT * FROM racetrack WHERE racetrack_id="
				+ racetrackId;
		// return jdbcTemplate.query(sql, new ResultSetExtractor<Racetrack>() {
		//
		// @Override
		// public Racetrack extractData(ResultSet rs) throws SQLException,
		// DataAccessException {
		// if (rs.next()) {
		// Racetrack racetrack = new Racetrack();
		// // racetrack.setId(rs.getInt("racetrack_id"));
		// // racetrack.setName(rs.getString("name"));
		// // racetrack.setEmail(rs.getString("email"));
		// // racetrack.setAddress(rs.getString("address"));
		// // racetrack.setTelephone(rs.getString("telephone"));
		// return racetrack;
		// }
		//
		// return null;
		// }
		//
		// });
		return null;
	}

}
