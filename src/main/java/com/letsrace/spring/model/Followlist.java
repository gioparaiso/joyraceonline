package com.letsrace.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FOLLOWLIST")
public class Followlist {

	@Id
	@Column(name = "FOLLOWERID", nullable = false)
	private Integer followerid;

	@Column(name = "USERID", nullable = false)
	private Integer userid;

	@Column(name = "REQUESTED", nullable = true)
	private Character requested;

	public Followlist() {
	}

	public Followlist(Integer followerid, Integer userid, Character requested) {
		this.followerid = followerid;
		this.userid = userid;
		this.requested = requested;
	}

	public Integer getFollowerid() {
		return followerid;
	}

	public void setFollowerid(Integer followerid) {
		this.followerid = followerid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Character getRequested() {
		return requested;
	}

	public void setRequested(Character requested) {
		this.requested = requested;
	}

}