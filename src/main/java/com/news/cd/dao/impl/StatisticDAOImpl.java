package com.news.cd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.StatisticDAO;
import com.news.cd.entities.Statistic;

@Repository("statisticDAO")
public class StatisticDAOImpl extends AbstractDAO<Statistic, Integer> implements StatisticDAO {
	@Override
    public int getTotalStatistic() {
		try {
        Query query = getSession().createQuery("select sum(s.totalVisits) as sum from Statistic s");
        return ((Long) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
    }

    @Override
    public int getTotalStatisticByYesterday(Date date) {
    	try {
	        Query query = getSession().createQuery("select s.totalVisits from Statistic s where s.date = :date");
	        query.setParameter("date", date);
	        return (int) query.uniqueResult();
	    } catch(Exception e) {
	    	return 0;
	    }
    }

    @Override
    public void updateStatistic(Date date) {
        Statistic statistic = getStatisticToday(date);
        if(null == statistic) {
            statistic = new Statistic(date, 1);
        } else {
            statistic.setTotalVisits(statistic.getTotalVisits() + 1);
        }
        getSession().saveOrUpdate(statistic);
    }

    @Override
    public Statistic getStatisticToday(Date date) {
        try {
        	Query query = getSession().createQuery("select sttt from Statistic sttt where date = :date");
        	query.setParameter("date", date);
        	query.setMaxResults(1);
            return (Statistic) query.uniqueResult();
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

	@Override
	public int getMaxValue() {
		try {
			Query query = getSession().createQuery("select max(sttt.totalVisits) from Statistic sttt");
			query.setMaxResults(1);
			return (int) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDataByMonth(int month, int year, int days) {
		Query query = getSession().createQuery("select totalVisits from Statistic where MONTH(date) = :month and YEAR(date) = :year");
		query.setMaxResults(days);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.list();
	}
}
