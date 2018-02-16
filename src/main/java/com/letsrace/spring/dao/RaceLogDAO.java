package com.letsrace.spring.dao;

import java.util.List;

import com.letsrace.spring.model.Racelog;

/**
 * Defines DAO operations for the racelog model.
 * 
 * @author www.codejava.net
 * 
 */
public interface RaceLogDAO {

	public void saveOrUpdate(Racelog racelog);

	public void delete(int racelogId);

	public Racelog get(int racelogId);

	public List<Racelog> list();

	List<Object[]> getBestLapTime();

	public List<Object[]> getUserRaceHistory(String loggedUser)
			throws Exception;

}
