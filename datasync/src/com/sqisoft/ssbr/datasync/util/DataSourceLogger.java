package com.sqisoft.ssbr.datasync.util;

import java.util.Timer;

public class DataSourceLogger {
	private static DataSourceLogger instance;
	private Timer timer = null;

	public static DataSourceLogger getInstance() {
		if (instance == null) instance = new DataSourceLogger();
		return instance;
	}

	public void cancel() {
		if (this.timer != null) this.timer.cancel(); 
	}

	public void runner(boolean logging, long term) {
		if (logging) {
			this.timer = new Timer(false);
			this.timer.schedule(new DataSourceTask(), 0L, term * 1000L);
		}
	}
}