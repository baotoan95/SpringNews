package com.news.cd.dto;

import java.util.List;

public class StatisticDTO {
	private int maxValue;
	private List<Integer> data;
	private int totalStatistic;

	public StatisticDTO() {
		// TODO Auto-generated constructor stub
	}

	public StatisticDTO(int maxValue, List<Integer> data, int totalStatistic) {
		super();
		this.maxValue = maxValue;
		this.data = data;
		this.totalStatistic = totalStatistic;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public int getTotalStatistic() {
		return totalStatistic;
	}

	public void setTotalStatistic(int totalStatistic) {
		this.totalStatistic = totalStatistic;
	}

}
