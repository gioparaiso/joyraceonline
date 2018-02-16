package com.letsrace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.RaceLogDAO;

@Service("raceLogService")
@Transactional
public class RaceLogServiceImpl implements RaceLogService {

	@Autowired
	private RaceLogDAO racelogDAO;

	ServiceUtil util = new ServiceUtil();

	@Override
	public String getBestLapTime() {
		List<Object[]> list = this.racelogDAO.getBestLapTime();

		Object[] result = list.get(0);

		// best lap time
		String retVal = util.getTheLeast(result[0], result[1], result[2],
				result[3], result[4]);

		return retVal;
	}

	@Override
	public List<String[]> getUserRaceHistory(String loggedUser) {
		try {
			List<Object[]> raceHistory = this.racelogDAO
					.getUserRaceHistory(loggedUser);

			List<String[]> retVal = util.arrangeRaceHistory(raceHistory);

			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
