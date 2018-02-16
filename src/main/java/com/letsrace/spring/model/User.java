package com.letsrace.spring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "USERID", nullable = false)
	private Integer userid;

	@Column(name = "USERIDQRCODE", nullable = true)
	private String useridqrcode;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PRIVACY", nullable = true)
	private String privacy;

	@Column(name = "FIRSTNAME", nullable = true)
	private String firstname;

	@Column(name = "LASTNAME", nullable = true)
	private String lastname;

	@Column(name = "BESTTIMERACE", nullable = true)
	private Float besttimerace;

	@Column(name = "BESTLAPRACE", nullable = true)
	private Float bestlaprace;

	@Column(name = "BESTTIMESOLO", nullable = true)
	private Float besttimesolo;

	@Column(name = "BESTLAPSOLO", nullable = true)
	private Float bestlapsolo;

	@Column(name = "PCT", nullable = true)
	private Float pct;

	@Column(name = "CAREERPTS", nullable = true)
	private Integer careerpts;

	@Column(name = "BIRTHDATE", nullable = true)
	private Date birthdate;

	public User() {
	}

	public User(Integer userid, String useridqrcode, String username,
			String password, String email, String privacy, String firstname,
			String lastname, Float besttimerace, Float bestlaprace,
			Float besttimesolo, Float bestlapsolo, Float pct,
			Integer careerpts, Date birthdate) {
		this.userid = userid;
		this.useridqrcode = useridqrcode;
		this.username = username;
		this.password = password;
		this.email = email;
		this.privacy = privacy;
		this.firstname = firstname;
		this.lastname = lastname;
		this.besttimerace = besttimerace;
		this.bestlaprace = bestlaprace;
		this.besttimesolo = besttimesolo;
		this.bestlapsolo = bestlapsolo;
		this.pct = pct;
		this.careerpts = careerpts;
		this.birthdate = birthdate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUseridqrcode() {
		return useridqrcode;
	}

	public void setUseridqrcode(String useridqrcode) {
		this.useridqrcode = useridqrcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
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

	public Float getBesttimerace() {
		return besttimerace;
	}

	public void setBesttimerace(Float besttimerace) {
		this.besttimerace = besttimerace;
	}

	public Float getBestlaprace() {
		return bestlaprace;
	}

	public void setBestlaprace(Float bestlaprace) {
		this.bestlaprace = bestlaprace;
	}

	public Float getBesttimesolo() {
		return besttimesolo;
	}

	public void setBesttimesolo(Float besttimesolo) {
		this.besttimesolo = besttimesolo;
	}

	public Float getBestlapsolo() {
		return bestlapsolo;
	}

	public void setBestlapsolo(Float bestlapsolo) {
		this.bestlapsolo = bestlapsolo;
	}

	public Float getPct() {
		return pct;
	}

	public void setPct(Float pct) {
		this.pct = pct;
	}

	public Integer getCareerpts() {
		return careerpts;
	}

	public void setCareerpts(Integer careerpts) {
		this.careerpts = careerpts;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}