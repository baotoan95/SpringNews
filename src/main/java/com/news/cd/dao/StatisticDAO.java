package com.news.cd.dao;

import java.util.Date;
import java.util.List;

import com.news.cd.entities.Statistic;

public interface StatisticDAO {
    int getTotalStatistic();
    int getTotalStatisticByYesterday(Date date);
    Statistic getStatisticToday(Date date);
    List<Integer> getDataByMonth(int month, int year, int days);
    void updateStatistic(Date date);
    int getMaxValue();
}
