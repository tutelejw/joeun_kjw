package com.sqisoft.ssbr.datasync.util;

import java.util.TimerTask;

public class DataSourceTask extends TimerTask
{
	public void run() {
		try {
			DataSourceUtil.loggingPoolInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}