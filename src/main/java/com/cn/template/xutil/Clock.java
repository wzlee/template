package com.cn.template.xutil;

import java.util.Date;

/**
 * 日期提供者，使用它而不是直接取得系统时间，方便测试.
 * 
 * @author calvin
 * 
 */
public interface Clock {

	static final Clock DEFAULT = new DefaultClock();

	Date getCurrentDate();

	long getCurrentTimeInMillis();

	/**
	 * 默认时间提供者，返回当前的时间，线程安全.
	 * 
	 * @author Libra
	 * 
	 */
	public static class DefaultClock implements Clock {

		@Override
		public Date getCurrentDate() {
			return new Date();
		}

		@Override
		public long getCurrentTimeInMillis() {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 可配置的时间提供者，用于测试.
	 * 
	 * @author Libra
	 * 
	 */
	public static class MockClock implements Clock {

		private long time;

		public MockClock() {
			this(0);
		}

		public MockClock(Date date) {
			this.time = date.getTime();
		}

		public MockClock(long time) {
			this.time = time;
		}

		@Override
		public Date getCurrentDate() {
			return new Date(time);
		}

		@Override
		public long getCurrentTimeInMillis() {
			return time;
		}

		/**
		 * 重新设置日期.
		 * 
		 * @param newDate
		 */
		public void update(Date newDate) {
			time = newDate.getTime();
		}

		/**
		 * 重新设置日期.
		 * 
		 * @param newTime
		 */
		public void update(long newTime) {
			this.time = newTime;
		}

		/**
		 * 滚动时间.
		 * 
		 * @param millis
		 */
		public void increaseTime(int millis) {
			time += millis;
		}

		/**
		 * 滚动时间.
		 * 
		 * @param millis
		 */
		public void decreaseTime(int millis) {
			time -= millis;
		}
	}

}
