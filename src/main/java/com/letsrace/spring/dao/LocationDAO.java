package com.letsrace.spring.dao;

import java.util.List;

import com.letsrace.spring.model.Location;

/**
 * Defines DAO operations for the location model.
 * @author www.codejava.net
 *
 */
public interface LocationDAO {
	
	public void saveOrUpdate(Location location);
	
	public void delete(int locationId);
	
	public Location get(int locationId);
	
	public List<Location> list();
}
