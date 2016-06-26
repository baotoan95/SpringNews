package com.news.cd.entities;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Event implements Serializable {
	private int eventId;
	private String name;
	private String desc;
	private Date startDate;
	private Date endDate;
	private Status status;

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String name, String desc, Date startDate, Date endDate,
			Status status) {
		super();
		this.name = name;
		this.desc = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", name=" + name + ", desc="
				+ desc + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + "]";
	}

}
