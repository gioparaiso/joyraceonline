package com.letsrace.spring.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RACELOG")
public class Racelog {

	@Id
	@Column(name = "RACEID", nullable = false)
	private Integer raceid;

	@Column(name = "USERID", nullable = false)
	private Integer userid;

	@Column(name = "RACETRACKID", nullable = true)
	private Integer racetrackid;

	@Column(name = "LOCATIONID", nullable = true)
	private Integer locationid;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "TIME", nullable = false)
	private Time time;

	@Column(name = "RESULT", nullable = false)
	private String result;

	@Column(name = "LAP1", nullable = true)
	private Float lap1;

	@Column(name = "LAP2", nullable = true)
	private Float lap2;

	@Column(name = "LAP3", nullable = true)
	private Float lap3;

	@Column(name = "LAP4", nullable = true)
	private Float lap4;

	@Column(name = "LAP5", nullable = true)
	private Float lap5;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Column(name = "BRIDGELAST", nullable = true)
	private Integer bridgelast;

	@Column(name = "BRIDGETIME", nullable = true)
	private Float bridgetime;

	public Racelog() {
	}

	public Racelog(Integer raceid, Integer userid, Integer racetrackid, Integer locationid,
			Date date, Time time, String result, Float lap1, Float lap2,
			Float lap3, Float lap4, Float lap5, String status, Integer bridgelast,
			Float bridgetime) {
		this.raceid = raceid;
		this.userid = userid;
		this.racetrackid = racetrackid;
		this.locationid = locationid;
		this.date = date;
		this.time = time;
		this.result = result;
		this.lap1 = lap1;
		this.lap2 = lap2;
		this.lap3 = lap3;
		this.lap4 = lap4;
		this.lap5 = lap5;
		this.status = status;
		this.bridgelast = bridgelast;
		this.bridgetime = bridgetime;
	}

	public Integer getRaceid() {
		return raceid;
	}

	public void setRaceid(Integer raceid) {
		this.raceid = raceid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRacetrackid() {
		return racetrackid;
	}

	public void setRacetrackid(Integer racetrackid) {
		this.racetrackid = racetrackid;
	}

	public Integer getLocationid() {
		return locationid;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Float getLap1() {
		return lap1;
	}

	public void setLap1(Float lap1) {
		this.lap1 = lap1;
	}

	public Float getLap2() {
		return lap2;
	}

	public void setLap2(Float lap2) {
		this.lap2 = lap2;
	}

	public Float getLap3() {
		return lap3;
	}

	public void setLap3(Float lap3) {
		this.lap3 = lap3;
	}

	public Float getLap4() {
		return lap4;
	}

	public void setLap4(Float lap4) {
		this.lap4 = lap4;
	}

	public Float getLap5() {
		return lap5;
	}

	public void setLap5(Float lap5) {
		this.lap5 = lap5;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBridgelast() {
		return bridgelast;
	}

	public void setBridgelast(Integer bridgelast) {
		this.bridgelast = bridgelast;
	}

	public Float getBridgetime() {
		return bridgetime;
	}

	public void setBridgetime(Float bridgetime) {
		this.bridgetime = bridgetime;
	}

}
