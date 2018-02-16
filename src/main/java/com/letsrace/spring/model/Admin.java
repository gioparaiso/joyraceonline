package com.letsrace.spring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
public class Admin {

	@Id
	@Column(name = "ADMINID", nullable = false)
	private Integer adminid;

	@Column(name = "ADMINIDQRCODE", nullable = true)
	private String adminidqrcode;

	@Column(name = "ADMINNAME", nullable = false)
	private String adminname;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "FIRSTNAME", nullable = true)
	private String firstname;

	@Column(name = "LASTNAME", nullable = true)
	private String lastname;

	@Column(name = "BIRTHDATE", nullable = true)
	private Date birthdate;

	@Column(name = "ISSUPERUSER", nullable = true)
	private String issuperuser;

	@Column(name = "PASSCODE", nullable = true)
	private Integer passcode;

	public Admin() {
	}

	public Admin(Integer adminid, String adminidqrcode, String adminname,
			String password, String email, String firstname, String lastname,
			Date birthdate, String issuperuser, Integer passcode) {
		this.adminid = adminid;
		this.adminidqrcode = adminidqrcode;
		this.adminname = adminname;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.issuperuser = issuperuser;
		this.passcode = passcode;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminidqrcode() {
		return adminidqrcode;
	}

	public void setAdminidqrcode(String adminidqrcode) {
		this.adminidqrcode = adminidqrcode;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIssuperuser() {
		return issuperuser;
	}

	public void setIssuperuser(String issuperuser) {
		this.issuperuser = issuperuser;
	}

	public Integer getPasscode() {
		return passcode;
	}

	public void setPasscode(Integer passcode) {
		this.passcode = passcode;
	}

}