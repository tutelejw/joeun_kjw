package com.sqisoft.ssbr.datasync.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.mitzz.frame.conf.Configuration;
import com.mitzz.frame.conf.ConfigurationException;
import com.mitzz.frame.vo.ValueObject;
import com.opencsv.CSVReader;
import com.sqisoft.ssbr.datasync.vo.APRVUSERGROUPVO;
import com.sqisoft.ssbr.datasync.vo.DEPTVO;
import com.sqisoft.ssbr.datasync.vo.USERVO;

/**
 * @author root
 *
 */
public class CSVRead {
	protected Logger logger = Logger.getLogger(this.getClass());
	private CSVReader reader;
	Configuration conf;
	int header =0;
	int userno, userid, username, userlname,usermname,userfname, aprvid, phone, tell, loc, email, deptid,deptname, pardeptid, deptrank, dutyname, teammanger;
	
	public CSVRead() {
		System.out.println("CRI0101 CSVFileRead");
	}
	
	public List<String []> readCsv(String path, String delimiter, String encoding) {
		System.out.println("CRI0201 readCsv() START");
		logger.debug("CRI0201 readCsv() START");
		
		List<String[]> data = new ArrayList<String[]>();
		String[] s;
		
		System.out.println("CRI0202 File Path ::: "+path);
		logger.debug("CRI0202 File Path ::: "+path);
		
		try {
			reader = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding), delimiter.charAt(0));
			while ((s = reader.readNext()) != null) {
				data.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
		}
		System.out.println("CRI0203 readCsv() END");
		logger.debug("CRI0203 readCsv() END");
		return data;
	}
	
	
	public ValueObject getCsvVo(List<String[]> data) {
		System.out.println("CRI0301 getCsvVo() START");
		logger.debug("CRI0301 getCsvVo() START");
		
		Iterator<String[]> it = data.iterator();
		ValueObject resiltVO = new ValueObject();
		
		String[] arrHead = it.next();
		
		while (it.hasNext()) {
			String[] array = (String[]) it.next();
			ValueObject vo = new ValueObject();
			int i =0;
			
			for (String s : array) {
				vo.set(arrHead[i], s);
				i++;
			}
			resiltVO.add(vo);
		}
		
		System.out.println("CRI0302 getCsvVo() END");
		logger.debug("CRI0302 getCsvVo() END");
		return resiltVO;
	}
	
	
	public ValueObject getUserCsvVo(List<String[]> data) {
		System.out.println("CRI0401 getCsvVo() START");
		logger.debug("CRI0401 getCsvVo() START");
		
		try {
			conf=Configuration.getInstance();
			userid = conf.getInt("frame.import.userfile.userid");
			username = conf.getInt("frame.import.userfile.username");
			deptrank = conf.getInt("frame.import.userfile.deptrank");
			dutyname = conf.getInt("frame.import.userfile.dutyname");
			deptid = conf.getInt("frame.import.userfile.deptid");
			phone = conf.getInt("frame.import.userfile.phone");
			tell = conf.getInt("frame.import.userfile.tell");
			email = conf.getInt("frame.import.userfile.email");
			userno = conf.getInt("frame.import.userfile.userno");
			
			// teammanger 컬럼 추가
			teammanger = conf.getInt("frame.import.userfile.teammanager");
			
		} catch (ConfigurationException e) {
			System.out.println("Frame Error");
			logger.error(e.getMessage());
		}
		
		Iterator<String[]> it = data.iterator();
		ValueObject resiltVO = new ValueObject();
		
		if(header == 0){
			it.next();
		}
		
		while (it.hasNext()) {
			String[] array = (String[]) it.next();
			USERVO vo = new USERVO();
			vo.setUserNo(array[userno]);
			vo.setUserId(array[userid]);
			vo.setUserName(array[username]);
			vo.setUserDeptRank(array[deptrank]);
			vo.setDutyName(array[dutyname]);
			vo.setDeptId(array[deptid]);
			vo.setPhone(array[phone]);
			vo.setTel(array[tell]);
			vo.setEMail(array[email]);
			
			if (array[teammanger].equals("Y")) {
				vo.setTeamManager("1");	
			} else {
				vo.setTeamManager("0");
			}
			
			resiltVO.add(vo);
		}
		
		System.out.println("CRI0402 getCsvVo() END");
		logger.debug("CRI0402 getCsvVo() END");
		return resiltVO;
	}
	
	
	public ValueObject getDeptCsvVo(List<String[]> data) {
		System.out.println("CRI0501 getCsvVo() START");
		logger.debug("CRI0501 getCsvVo() START");
		
		try {
			conf=Configuration.getInstance();
			deptid = conf.getInt("frame.import.deptfile.deptid");
			pardeptid= conf.getInt("frame.import.deptfile.parid");
			deptname= conf.getInt("frame.import.deptfile.deptname");
			header = conf.getInt("frame.import.header");
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
		}
		
		Iterator<String[]> it = data.iterator();
		ValueObject resiltVO = new ValueObject();
		
		if(header == 0){
			it.next();
		}
		
		
		while (it.hasNext()) {
			String[] array = (String[]) it.next();
						
			// 빈문자열로만 이루어진 줄이 있을 때 넘어가기
			if (array[0].trim().equals("")) {
				logger.info("deprtment csv has empty line");
				continue;
			}
			
			DEPTVO vo = new DEPTVO();
			vo.setORG_CD(array[deptid]);
			vo.setORG_NM(array[deptname]);
			vo.setSUPER_ORG_CD(array[pardeptid]);
			
			logger.info("array.length = " + array.length);
			logger.info("array[" + deptid + "] = " + array[deptid]);
			logger.info("array[" + deptname + "] = " + array[deptname]);
			logger.info("array[" + pardeptid + "] = " + array[pardeptid]);
			
			resiltVO.add(vo);
		}
		
		System.out.println("CRI0502 getCsvVo() END");
		logger.debug("CRI0502 getCsvVo() END");
		return resiltVO;
	}
	
}
