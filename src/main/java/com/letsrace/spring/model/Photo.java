package com.letsrace.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Photo")
public class Photo {

	@Id
	@Column(name = "USERID", nullable = false)
	private Integer userid;

	@Column(name = "IMAGE", nullable = true)
	private byte[] image;

	public Photo() {
	}

	public Photo(Integer userid, byte[] image) {
		this.userid = userid;
		this.image = image;
	}

	public Integer getUserid() {
		return userid;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
