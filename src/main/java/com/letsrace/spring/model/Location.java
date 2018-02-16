package com.letsrace.spring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

	@Id
	@Column(name = "LOCATIONID", nullable = false)
	private Integer locationid;

	@Column(name = "LOCATIONNAME", nullable = false)
	private String locationname;

	@Column(name = "DATESTARTED", nullable = true)
	private Date datestarted;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	public Location() {
	}

	public Location(Integer locationid, String locationname, Date datestarted,
			String description) {
		this.locationid = locationid;
		this.locationname = locationname;
		this.datestarted = datestarted;
		this.description = description;
	}

	public Integer getLocationid() {
		return locationid;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public Date getDatestarted() {
		return datestarted;
	}

	public void setDatestarted(Date datestarted) {
		this.datestarted = datestarted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
