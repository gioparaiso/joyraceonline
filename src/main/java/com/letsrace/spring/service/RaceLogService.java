package com.letsrace.spring.service;

import java.util.List;

public abstract interface RaceLogService {

	public abstract String getBestLapTime();

	public abstract List<String[]> getUserRaceHistory(String loggedUser);

}
