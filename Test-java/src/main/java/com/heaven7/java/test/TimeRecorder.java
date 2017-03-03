package com.heaven7.java.test;

/**
 * the time recorder
 * 
 * @author heaven7
 */
public final class TimeRecorder {

	private static TimeRecorder INSTANCE;
	private long startTime;
	private long endTime;
	
	private TimeRecorder(){}

	public static TimeRecorder create() {
		return new TimeRecorder();
	}

	public static synchronized TimeRecorder getDefault() {
		if (INSTANCE == null) {
			INSTANCE = new TimeRecorder();
		}
		return INSTANCE;
	}

	public void begin() {
		startTime = System.currentTimeMillis();
	}

	public void end() {
		endTime = System.currentTimeMillis();
	}

	/** @return the cost time in mills */
	public long getCostTime() {
		if (startTime == 0 || endTime == 0 || startTime > endTime)
			throw new IllegalStateException("wrong startTime or endTime");
		return endTime - startTime;
	}

	public void reset() {
		startTime = endTime = 0;
	}

	public String getCostTimeString(String tag) {
		return "[ " + tag + " ] cost time: " + getCostTime();
	}
}
