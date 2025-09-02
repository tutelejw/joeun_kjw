package com.sqisoft.ssbr.datasync.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.mitzz.frame.conf.Configuration;
import com.mitzz.frame.conf.ConfigurationException;
import com.mitzz.frame.msg.MessageFactory;

public class DataSourceUtil {
	public static void loggingPoolInfo() throws Exception {
		Logger logger = Logger.getLogger(DataSourceUtil.class);
		StringBuffer strbuf = new StringBuffer();

		String warnMsg = MessageFactory.getInstance().getMsg("DDB00002");
		strbuf.append(warnMsg);

		logger.debug(getPoolInfo());
	}

	public static String getPoolInfo() throws Exception {
		StringBuffer strbuf = new StringBuffer();

		DataSource ds = null;
		InitialContext ctx = null;

		Configuration conf = Configuration.getInstance();
		String jndiName = conf.get("frame.datasource.url");
		String envCtxName = "";
		try {
			envCtxName = conf.get("frame.datasource.envcontext.name");
		} catch (ConfigurationException e) {
			envCtxName = "java:/comp/env";
		}

		ctx = new InitialContext();

		Context envContext = (Context)ctx.lookup(envCtxName);
		ds = (DataSource)envContext.lookup(jndiName);
		BasicDataSource bds = (BasicDataSource)ds;

		strbuf.append("Max Active:" + bds.getMaxActive() + "; ");
		strbuf.append("Active:" + bds.getNumActive() + "; ");

		strbuf.append("Max Idle:" + bds.getMaxIdle() + "; ");
		strbuf.append("Min Idle:" + bds.getMinIdle() + "; ");

		strbuf.append("Idle:" + bds.getNumIdle() + "; ");
		strbuf.append("Max Wait:" + bds.getMaxWait() + "; ");
		strbuf.append("Max OpenPreparedStatements:" + bds.getMaxOpenPreparedStatements() + "; ");

		strbuf.append("Min EvictableIdleTimeMillis:" + bds.getMinEvictableIdleTimeMillis() + "; ");
		strbuf.append("NumTestsPerEvictionRun:" + bds.getNumTestsPerEvictionRun() + "; ");

		strbuf.append("TimeBetweenEvictionRunsMillis:" + bds.getTimeBetweenEvictionRunsMillis() + "; ");

		strbuf.append("ValidationQuery:" + bds.getValidationQuery() + "; ");
		strbuf.append("TestOnBorrow:" + bds.getTestOnBorrow() + "; ");
		strbuf.append("TestOnReturn:" + bds.getTestOnReturn() + "; ");
		strbuf.append("TestWhileIdle:" + bds.getTestWhileIdle() + "; ");

		return strbuf.toString();
	}
}