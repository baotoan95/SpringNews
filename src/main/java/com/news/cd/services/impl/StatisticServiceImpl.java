package com.news.cd.services.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.StatisticDAO;
import com.news.cd.services.StatisticService;
import com.news.cd.utils.DateUtils;

@Service("statisticService")
@Transactional
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	private StatisticDAO statisticDAO;

	@Override
	public int getTotalStatistic() {
		return statisticDAO.getTotalStatistic();
	}

	@Override
	public int getTotalStatisticByYesterday() {
		Date date = DateUtils.getDate(DateUtils.currentDay() - 1, DateUtils.currentMonth(), DateUtils.currentYear());
		return statisticDAO.getTotalStatisticByYesterday(date);
	}

	@Override
	public void updateStatistic() {
		Date date = DateUtils.getDate(DateUtils.currentDay(), DateUtils.currentMonth(), DateUtils.currentYear());
		statisticDAO.updateStatistic(date);
	}

	@Override
	public int getMaxValue() {
		return statisticDAO.getMaxValue();
	}

	@Override
	public List<Integer> getDataByMonth(int month, int year) {
		if(month < 1) {
			month = 1;
			year--;
		}
		int dayOfMonth = DateUtils.getDayOfMonth(month, year);
		List<Integer> data = statisticDAO.getDataByMonth(month, year, dayOfMonth);
		while (data.size() < dayOfMonth) {
			data.add(0);
		}
		return data;
	}
}
