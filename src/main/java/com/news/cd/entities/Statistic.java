package com.news.cd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "statistic")
public class Statistic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sttc_id")
	private int sttsId;
    @Column(name = "sttc_date")
	private Date date;
    @Column(name = "sttc_total")
	private int totalVisits;

	public Statistic() {
		// TODO Auto-generated constructor stub
	}

	public Statistic(Date date, int totalVisits) {
		this.date = date;
		this.totalVisits = totalVisits;
	}

	public int getSttsId() {
		return sttsId;
	}

	public void setSttsId(int sttsId) {
		this.sttsId = sttsId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(int totalVisits) {
		this.totalVisits = totalVisits;
	}

	@Override
	public String toString() {
		return "Statistic [sttsId=" + sttsId + ", date=" + date
				+ ", totalVisits=" + totalVisits + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Statistic) {
			Statistic other = (Statistic) obj;
			System.out.println(other.date.equals(this.date));
			return other.date.equals(this.date);
		}
		return false;
	}

}
