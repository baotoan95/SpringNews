package com.news.cd.services;

import java.util.List;

public interface StatisticService {
	int getTotalStatistic();
    int getTotalStatisticByYesterday();
    void updateStatistic();
    int getMaxValue();
    List<Integer> getDataByMonth(int month, int year);
}
