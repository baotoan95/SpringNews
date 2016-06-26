package com.news.cd.dao;

import com.news.cd.entities.UserAttempts;

public interface UserAttemptsDAO {
	UserAttempts updateFailAttempts(String email);
	UserAttempts getUserAttempts(String email);
	boolean resetUserAttemps(String email);
}
