package com.itany.platform.util;

import java.text.NumberFormat;

/**
 * 日期的工具类
 * @author
 *
 */
public class DateUtil {

	public static Double ms2Hour(long ms) {
		try {
			Double result = 0.0;
			result = (double) (ms / (60 * 60 * 1000.0));
			return formatNumber(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Double ms2Hour(String ms) {
		try {
			long l = Long.parseLong(ms);
			return formatNumber(ms2Hour(l));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Double formatNumber(Double num) {

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		Double d = new Double(nf.format(num));

		return d;
	}
}
