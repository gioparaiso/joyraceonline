package com.letsrace.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RACETRACK")
public class Racetrack {

	@Id
	@Column(name = "RACETRACKID", nullable = false)
	private Integer racetrackid;

	@Column(name = "RACETRACKNAME", nullable = false)
	private String racetrackname;

	@Column(name = "TRACKLENGTH", nullable = true)
	private String tracklength;

	@Column(name = "TOTALBRIDGE", nullable = true)
	private Integer totalbridge;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	public Racetrack() {
	}

	public Racetrack(Integer racetrackid, String racetrackname,
			String tracklength, Integer totalbridge, String description) {
		this.racetrackid = racetrackid;
		this.racetrackname = racetrackname;
		this.tracklength = tracklength;
		this.totalbridge = totalbridge;
		this.description = description;
	}

	public Integer getRacetrackid() {
		return racetrackid;
	}

	public void setRacetrackid(Integer racetrackid) {
		this.racetrackid = racetrackid;
	}

	public String getRacetrackname() {
		return racetrackname;
	}

	public void setRacetrackname(String racetrackname) {
		this.racetrackname = racetrackname;
	}

	public String getTracklength() {
		return tracklength;
	}

	public void setTracklength(String tracklength) {
		this.tracklength = tracklength;
	}

	public Integer getTotalbridge() {
		return totalbridge;
	}

	public void setTotalbridge(Integer totalbridge) {
		this.totalbridge = totalbridge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
