package com.news.cd.utils;

import java.util.Random;

public class GenerationCommon {
	public static String generateCode(int length) {
		Random rand = new Random();
		String result = "";
		while (result.length() <= length) {
			int c = rand.nextInt(196);
			if (c >= 48 && c <= 57 || c >= 64 && c <= 90 || c >= 97 && c <= 122) {
				result += (char) c;
			}
		}
		return result;
	}
}
