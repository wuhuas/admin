package com.knowledge.common.base.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author jide
 */
public class DateUtil extends DateUtils {

	private static Logger log = LoggerFactory.getLogger(DateUtil.class);

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 两个时间的时间差 返回 分钟 kangkang提示 别把返回类型Long 改成其他
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static Long subtractTime(Date date1, Date date2) {
		Long minute = 0L;
		try {
			if (date1.getTime() >= date2.getTime()) {
				minute = (date1.getTime() - date2.getTime()) / 1000 / 60;
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
		return minute;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns[0]);
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Timestamp getSysTimestamp() {
		return new Timestamp(new Date().getTime());
	}
}
