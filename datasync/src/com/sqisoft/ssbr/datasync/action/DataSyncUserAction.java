package com.sqisoft.ssbr.datasync.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mitzz.frame.conf.Configuration;
import com.mitzz.frame.conf.ConfigurationException;
import com.mitzz.frame.exception.LException;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.frame.sys.ServiceProxy;
import com.sqisoft.ssbr.datasync.processbc.DeptFMRetrievePrc;
import com.sqisoft.ssbr.datasync.processbc.DeptFMUpdatePrc;
import com.sqisoft.ssbr.datasync.processbc.PostUserFMRetrievePrc;
import com.sqisoft.ssbr.datasync.processbc.PostUserFMUpdatePrc;
import com.sqisoft.ssbr.datasync.processbc.UserFMRetrievePrc;
import com.sqisoft.ssbr.datasync.processbc.UserFMUpdatePrc;
import com.sqisoft.ssbr.datasync.processbc.csvreader.AprvUserFMCsvUpdatePrc;
import com.sqisoft.ssbr.datasync.processbc.csvreader.DeptFMCsvUpdatePrc;
import com.sqisoft.ssbr.datasync.processbc.csvreader.UserFMCsvUpdatePrc;
import com.sqisoft.ssbr.datasync.util.CSVRead;
import com.sqisoft.ssbr.datasync.util.POIReader;
import com.sqisoft.ssbr.datasync.vo.DEPTVO;
import com.sqisoft.ssbr.datasync.vo.POSTUSERVO;
import com.sqisoft.ssbr.datasync.vo.USERVO;
 
public class DataSyncUserAction {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	Configuration conf;
	int useTag = 0;
	int csvTag = 0;
	int aprvTag = 0;
	int header = 0;
	int topdeptusetag = 0;
	String pswd="";
	String path = "";
	String excelFile = "";
	String delimiter = "";
	String userFile = "";
	String deptFile = "";
	String topdeptid = "";
	String topdeptname = "";
	
	/**
	 * 생성자 (frame)에서 데이터를 가져온다.
	 */
	public DataSyncUserAction() {
		try {
			conf=Configuration.getInstance();
			useTag = conf.getInt("frame.import.usetag");
			csvTag = conf.getInt("frame.import.csvtag");
			aprvTag = conf.getInt("frame.aprv.table");
			topdeptusetag = conf.getInt("frame.import.top.usetag");
			pswd = conf.getString("frame.init.pswd");
			path = conf.getString("frame.import.path");
			userFile = conf.getString("frame.import.userfile");
			deptFile = conf.getString("frame.import.deptfile");
			excelFile = conf.getString("frame.import.file");
			delimiter = conf.getString("frame.import.delimiter");
			topdeptid = conf.getString("frame.import.top.deptid");
			topdeptname = conf.getString("frame.import.top.deptname");
			header = conf.getInt("frame.import.header");
		} catch (ConfigurationException e) {
			useTag = 0;
			csvTag = 0;
			aprvTag = 0;
			pswd="qwe123!@#";
			path = "/data/excel/";
			excelFile = "userExcel.csv";
			delimiter = ",";
			header = 0;
			logger.error(e.getMessage());
		}
		
		this.logger.debug("DMI0001 (f) DataSyncAction initialized");
	}

	public ValueObject searchUserList() throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		UserFMRetrievePrc prc = new UserFMRetrievePrc();

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}

	public ValueObject updateUserList(USERVO vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		UserFMUpdatePrc prc = new UserFMUpdatePrc();
		vo.set("initPswd",pswd);
		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}
	
	
	/**
	 * csv파일에서 읽어온 정보를 통한 user정보 업데이트
	 * @param vo
	 * @return
	 * @throws LException
	 */
	public ValueObject updateUserCsvList(ValueObject vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		UserFMCsvUpdatePrc prc = new UserFMCsvUpdatePrc();
		vo.set("initPswd",pswd);
		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}

	public ValueObject searchDeptList() throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject(); 

		DeptFMRetrievePrc prc = new DeptFMRetrievePrc();

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}

	/**
	 * 부서테이블 업데이트 및 인서트
	 * @param vo
	 * @return
	 * @throws LException
	 */
	public ValueObject updateDeptList(DEPTVO vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		DeptFMUpdatePrc prc = new DeptFMUpdatePrc();

		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}
	
	/**
	 * CSV 파일에서 읽어온 정보를 통한 부서정보 업데이트
	 * @param vo
	 * @return
	 * @throws LException
	 */
	public ValueObject updateDeptCsvList(ValueObject vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		DeptFMCsvUpdatePrc prc = new DeptFMCsvUpdatePrc();

		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}

	/**
	 * 결재자 DB Select
	 * @param vo
	 * @return 다른서버에서 결재자 정보 조회
	 * @throws LException
	 */
	public ValueObject searchPostUserList() throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		PostUserFMRetrievePrc prc = new PostUserFMRetrievePrc();

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}

	/**
	 * 결재자 DB Update/Insert
	 * @param vo
	 * @return 결재자 DB Update/Insert 성공 횟 수 
	 * @throws LException
	 */
	public ValueObject updatePostUserList(POSTUSERVO vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		PostUserFMUpdatePrc prc = new PostUserFMUpdatePrc();
		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}
	
	/**
	 * csv파일에서 읽어온 결재자정보 업데이트
	 * @param vo
	 * @return
	 * @throws LException
	 */
	public ValueObject updateAprvUserCsvList(ValueObject vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		AprvUserFMCsvUpdatePrc prc = new AprvUserFMCsvUpdatePrc();
		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}
	
	
	public ValueObject updateAprvUserList(ValueObject vo) throws LException {
		ServiceProxy px = new ServiceProxy();

		ValueObject infoVO = new ValueObject();
		UserFMUpdatePrc prc = new UserFMUpdatePrc();
		vo.set("initPswd",pswd);
		infoVO.setDataVO(vo);

		prc.setInfoVO(infoVO);
		infoVO = px.process(prc);

		return infoVO;
	}
	
	
	protected ValueObject process(ValueObject infoVO) throws LException {
		return infoVO;
	}

	/**
	 * 메인 실행 함수
	 * (엑셀 /DB 데이터중 frame값을 통해 선택하여 진행)
	 * @since 2017.02.24
	 */
	protected void sub_main() {
		if(useTag == 0){
			getDbInfo();
		}
		else{
			getExcelInfo();
		}
	}
	
	/**
	 * 다른 서버의 DB에 접근하여 데이터를 조회한뒤 실제 우리 ssbr DB에 데이터를 넣는다.
	 * @since 2017.02.24
	 */
	protected void getDbInfo() {
		
		System.out.println("Get DB Sync START");
		
		ValueObject dataVO = null;
		ValueObject data2VO = null;
		ValueObject data3VO = null;
		long startTimeMilisecond = System.currentTimeMillis();
		long currTimeMilisecond = System.currentTimeMillis();

		String pid = "------";
		String ppid = "------";

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		StringBuffer strBuf = new StringBuffer();

		Date date = new Date();
		String time = sdf.format(date);
		
		strBuf.append(time)
		.append(" DAI0001")
		.append(" IN01")
		.append(" DataSyncUserAction")
		.append(" 186")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Sync Data from User to ssBridge ");

		this.logger.info("DMI0001 " + strBuf.toString());
		System.out.println(strBuf.toString());
		try {
			logger.info("serchList Start");
			dataVO = searchUserList();
			logger.info("serchList End");
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0001")
			.append(" IN01")
			.append(" DataSyncUserAction")
			.append(" 203")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" User Infomation Search Failed " + e.getMessage());

			this.logger.error("DMF0001 " + strBuf.toString());
			System.out.println(strBuf.toString());
			e.printStackTrace();
		}
		currTimeMilisecond = System.currentTimeMillis();
		strBuf = new StringBuffer();
		USERVO userVO = (USERVO)dataVO.getDataVO();
		strBuf.append(time)
		.append(" DMI0001")
		.append(" IN01")
		.append(" DataSyncUserAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Data Sync User Report")
		.append(" Search User Count : " + userVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");

		this.logger.info("DMI0001 " + strBuf.toString());
		
		try {
			if(userVO.getCount()>0){
				logger.info("update User List Start");
				updateUserList(userVO);
				logger.info("update User List End");	
			}else{
				logger.info("Select User Data is Zero");
			}
			
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0001")
			.append(" IN01")
			.append(" DataSyncUserAction")
			.append(" 231")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" USER_SSBR Sync Data Failed " + e.getMessage());

			this.logger.error("DMF0001 " + strBuf.toString());
			System.out.println(strBuf.toString());
		}
		currTimeMilisecond = System.currentTimeMillis();

		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DMI0001")
		.append(" IN01")
		.append(" DataSyncUserAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Data Sync User Report")
		.append(" User Count : " + userVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");

		this.logger.info("DMI0001 " + strBuf.toString());
		System.out.println(strBuf.toString());

		startTimeMilisecond = System.currentTimeMillis();

		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0002")
		.append(" IN01")
		.append(" DataSyncDeptAction")
		.append(" 186")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Sync Data from Dept to ssBridge ");

		this.logger.info("DMI0002 " + strBuf.toString());
		
		
		try {
			logger.info("search Dept List Start");
			data2VO = searchDeptList();
			logger.info("search Dept List End");
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0002")
			.append(" IN01")
			.append(" DataSyncDeptAction")
			.append(" 203")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" Dept Infomation Search Failed " + e.getMessage());

			this.logger.error("DMF0002 " + strBuf.toString());
			System.out.println(strBuf.toString());
			e.printStackTrace();
		}
		currTimeMilisecond = System.currentTimeMillis();
		strBuf = new StringBuffer();
		DEPTVO deptVO = (DEPTVO)data2VO.getDataVO();
		strBuf.append(time)
		.append(" DMI0002")
		.append(" IN01")
		.append(" DataSyncDeptAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Search Data Sync Dept Report")
		.append(" Dept Count : " + deptVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		this.logger.info("DMI0002 " + strBuf.toString());
		
		try {
			if(deptVO.getCount()>0){
				logger.info("update Dept List Start");
				updateDeptList(deptVO);
				logger.info("update Dept List End");	
			}else{
				logger.info("Select Dept Data is Zero");
			}
			
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0002")
			.append(" IN01")
			.append(" DataSyncDeptrAction")
			.append(" 231")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" POST_DEPT Sync Data Failed " + e.getMessage());

			this.logger.error("DMF0002 " + strBuf.toString());
			System.out.println(strBuf.toString());
		}
		currTimeMilisecond = System.currentTimeMillis();

		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DMI0002")
		.append(" IN01")
		.append(" DataSyncDeptrAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Data Sync Dept Report")
		.append(" Dept Count : " + deptVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");

		this.logger.info("DMI0002 " + strBuf.toString());
		System.out.println(strBuf.toString());
		
		//사용자 리스트에서 결재자리스트  조회 
		startTimeMilisecond = System.currentTimeMillis();

		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0003")
		.append(" IN01")
		.append(" DataSyncPostUserAction")
		.append(" 186")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Sync Data from PostUser to ssBridge ");

		this.logger.info("DMI0003 " + strBuf.toString());
		try {
			logger.info("search PostUser List Start");
			data3VO = searchPostUserList();
			logger.info("search PostUser List End");
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0003")
			.append(" IN01")
			.append(" DataSyncPostUserAction")
			.append(" 203")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" PostUser Infomation Search Failed " + e.getMessage());

			this.logger.error("DMF0003 " + strBuf.toString());
			System.out.println(strBuf.toString());
			e.printStackTrace();
		}
		
		//결재자 등록
		currTimeMilisecond = System.currentTimeMillis();
		strBuf = new StringBuffer();
		POSTUSERVO postUserVO = (POSTUSERVO)data3VO.getDataVO();
		strBuf.append(time)
		.append(" DMI0003")
		.append(" IN01")
		.append(" DataSyncPostUserAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Search Data Sync PostUser Report")
		.append(" Dept Count : " + postUserVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		this.logger.info("DMI0003 " + strBuf.toString());
		
		try {
			if(postUserVO.getCount()> 0){
				logger.info("update PostUser List Start");
				updatePostUserList(postUserVO);
				logger.info("update PostUser List End");	
			}else{
				logger.info("Select PostUser Data is Zero");
			}
			
		} catch (LException e) {
			strBuf = new StringBuffer();
			strBuf.append(time)
			.append(" DMF0003")
			.append(" IN01")
			.append(" DataSyncPostUserAction")
			.append(" 231")
			.append(" " + pid)
			.append(" " + ppid)
			.append(" 1")
			.append(" POST_USER Sync Data Failed " + e.getMessage());

			this.logger.error("DMF0003 " + strBuf.toString());
			System.out.println(strBuf.toString());
		}
		currTimeMilisecond = System.currentTimeMillis();

		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DMI0003")
		.append(" IN01")
		.append(" DataSyncPostUserAction")
		.append(" 250")
		.append(" " + pid)
		.append(" " + ppid)
		.append(" 1")
		.append(" Data Sync PostUser Report")
		.append(" PostUser Count : " + postUserVO.size())
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");

		this.logger.info("DMI0003 " + strBuf.toString());
		System.out.println(strBuf.toString());
		
		System.out.println("Get DB Sync END");
	}
	
	/**
	 * frame에서 가져온 경로, 파일명, 구분자를 통해 엑셀파일 정보를 읽어와 실제 ssbr DB에 데이터를 넣는다.
	 * @since 2017.02.24
	 */
	protected void getExcelInfo() {
		this.logger.info("DAI0101 GetExcelSync START");
		System.out.println("DAI0101 GetExcelSync START");
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		StringBuffer strBuf = new StringBuffer();

		Date date = new Date();
		String time = sdf.format(date);
		
		long startTimeMilisecond = System.currentTimeMillis();
		long currTimeMilisecond = System.currentTimeMillis();
		
		ValueObject deptVO = new ValueObject();
		ValueObject userVO = new ValueObject();
		
		if(csvTag == 0){
			//CSV파일 읽어온다.
			CSVRead read = new CSVRead();
			
			deptVO = read.getDeptCsvVo(read.readCsv(path+deptFile, delimiter, "UTF-8"));
			userVO = read.getUserCsvVo(read.readCsv(path+userFile, delimiter, "UTF-8"));
			
			/*if(header == 1){
				deptVO = read.getDeptCsvVo(read.readCsv(path+deptFile, delimiter));
				userVO = read.getUserCsvVo(read.readCsv(path+userFile, delimiter));
			}
			else{
				deptVO = read.getCsvVo(read.readCsv(path+deptFile, delimiter));
				userVO = read.getCsvVo(read.readCsv(path+userFile, delimiter));
			}*/
		}
		else{
			//xlsx 혹은 xls 파일을 읽어 온다.
			POIReader reader = new POIReader();
			ValueObject vo = reader.reader(path+excelFile);
			userVO = vo.get(0);
			deptVO = vo.get(1);
		}
		
		//임의 최상위 부서 설정
		deptVO.set("topdeptusetag", topdeptusetag);
		deptVO.set("topdeptid", topdeptid);
		deptVO.set("topdeptname", topdeptname);
		
		System.out.println("Data Sync Excel USER Data Count ::"+userVO.size());
		System.out.println("Data Sync Excel DEPT Data Count ::"+deptVO.size());
		
		//부서 DB 넣기
		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0102")
		.append(" IN01")
		.append(" DataSyncDeptAction")
		.append(" Data Sync Dept Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());
		
		try {
			if(deptVO.size() >= 0){
				logger.info("update excel List Start");
				updateDeptCsvList(deptVO);
				logger.info("update excel List End");	
			}else{
				logger.info("get excel Data is Zero");
			}
		} catch (LException e) {
			e.printStackTrace();
		}
		
		startTimeMilisecond = System.currentTimeMillis();
		currTimeMilisecond = System.currentTimeMillis();
		
		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0102")
		.append(" OUT01")
		.append(" DataSyncDeptAction")
		.append(" ExcelDataCount :: " + deptVO.getCount())
		.append(" Data Sync Dept Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());
		
		//유저 DB 데이터 넣기
		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0103")
		.append(" IN02")
		.append(" DataSyncUserAction")
		.append(" Data Sync User Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());
		
		try {
			if(userVO.size() >= 0){
				logger.info("update excel List Start");
				updateUserCsvList(userVO);
				logger.info("update excel List End");	
			}else{
				logger.info("get excel Data is Zero");
			}
		} catch (LException e) {
			e.printStackTrace();
		}
		
		startTimeMilisecond = System.currentTimeMillis();
		currTimeMilisecond = System.currentTimeMillis();
		
		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0103")
		.append(" OUT02")
		.append(" DataSyncUserAction")
		.append(" ExcelDataCount :: " + userVO.getCount())
		.append(" Data Sync User Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());
		
		//결재자 정보 업데이트
		/*strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0104")
		.append(" IN03")
		.append(" DataSyncAprvUserAction")
		.append(" Set AprvDB Flag :: "+aprvTag)
		.append(" Data Sync AprvUser Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());
		
		userVO.setInt("aprvFlag", aprvTag);
		
		try {
			if(userVO.size() >= 0){
				logger.info("update excel List Start");
				updateAprvUserCsvList(userVO);
				logger.info("update excel List End");	
			}else{
				logger.info("get excel Data is Zero");
			}
		} catch (LException e) {
			e.printStackTrace();
		}
		
		startTimeMilisecond = System.currentTimeMillis();
		currTimeMilisecond = System.currentTimeMillis();
		
		strBuf = new StringBuffer();
		strBuf.append(time)
		.append(" DAI0104")
		.append(" OUT03")
		.append(" DataSyncAprvUserAction")
		.append(" excelDataCount :: " + userVO.getCount())
		.append(" Data Sync AprvUser Excel")
		.append(" Elipse Time : " + (currTimeMilisecond - startTimeMilisecond) + "msec");
		
		logger.debug(strBuf.toString());
		System.out.println(strBuf.toString());*/
		
		this.logger.info("DAI0105 GetExcelSync END");
		System.out.println("DAI0105 GetExcelSync END");
	}
	

	public static void main(String[] args) {
		DataSyncUserAction dsa = new DataSyncUserAction();
		dsa.sub_main();
	}
}
