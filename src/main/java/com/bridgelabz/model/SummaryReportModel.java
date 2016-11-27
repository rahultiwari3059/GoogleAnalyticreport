package com.bridgelabz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SummaryReportModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column
	private String date;
	@Column
	private String gaDiscription;
	@Column
	private String androidIdCount;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGaDiscription() {
		return gaDiscription;
	}
	public void setGaDiscription(String gaDiscription) {
		this.gaDiscription = gaDiscription;
	}
	public String getAndroidIdCount() {
		return androidIdCount;
	}
	public void setAndroidIdCount(String androidIdCount) {
		this.androidIdCount = androidIdCount;
	}

}
