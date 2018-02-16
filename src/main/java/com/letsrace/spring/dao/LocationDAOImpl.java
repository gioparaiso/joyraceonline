package com.letsrace.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.letsrace.spring.model.Location;

/**
 * An implementation of the LocationDAO interface.
 * 
 */

@Repository("locationDAO")
public class LocationDAOImpl extends AbstractDao<Integer, Location> implements
		LocationDAO {

	@Override
	public void saveOrUpdate(Location location) {
		// if (location.getId() > 0) {
		// // update
		// String sql = "UPDATE location SET name=?, email=?, address=?, "
		// + "telephone=? WHERE location_id=?";
		// jdbcTemplate.update(sql, location.getName(), location.getEmail(),
		// location.getAddress(), location.getTelephone(),
		// location.getId());
		// } else {
		// // insert
		// String sql = "INSERT INTO location (name, email, address, telephone)"
		// + " VALUES (?, ?, ?, ?)";
		// jdbcTemplate.update(sql, location.getName(), location.getEmail(),
		// location.getAddress(), location.getTelephone());
		// }

	}

	@Override
	public void delete(int locationId) {
		String sql = "DELETE FROM location WHERE location_id=?";
		// jdbcTemplate.update(sql, locationId);
	}

	@Override
	public List<Location> list() {
		String sql = "SELECT * FROM location";
		return null;
	}

	@Override
	public Location get(int locationId) {
		String sql = "SELECT * FROM location WHERE location_id=" + locationId;

		return null;
	}

}
