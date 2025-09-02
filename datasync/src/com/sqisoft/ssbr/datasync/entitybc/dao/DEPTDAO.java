package com.sqisoft.ssbr.datasync.entitybc.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.mitzz.frame.conf.Configuration;
import com.mitzz.frame.conf.ConfigurationException;
import com.mitzz.frame.entitydao.AbstractMysqlEntityDao;
import com.mitzz.frame.exception.LException;
import com.mitzz.frame.exception.SysException;
import com.mitzz.frame.sql.SqlManager;
import com.mitzz.frame.sql.SqlManagerXmlFactory;
import com.mitzz.frame.vo.ValueObject;
import com.opencsv.CSVWriter;
import com.sqisoft.ssbr.datasync.vo.DEPTVO;

public class DEPTDAO extends AbstractMysqlEntityDao {
	private CSVWriter cw;

	public DEPTVO retrieveDeptList(DEPTVO vo) throws LException {
		Configuration conf;
		int EXPORT=0;
		int count=0;
		String path="/www/datasync/";
		try {
			conf = Configuration.getInstance();
			EXPORT=conf.getInt("frame.export.csv");
			path = conf.getString("frame.export.path");
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
		}
		
		DEPTVO resultVO = new DEPTVO();
		try {
			SqlManager query = SqlManagerXmlFactory.getInstance().get(getClass().getName(), "retrieveDeptList");

			openDirectConnection("fromsource");
			setPreparedStatement(query.getSql());

			executeJQuery(resultVO);

			while (this.rset.next()) {
				DEPTVO temp = new DEPTVO();

				temp.setORG_CD(this.rset.getString("ORG_CD"));
				temp.setORG_NM(this.rset.getString("ORG_NM"));
				temp.setSUPER_ORG_CD(this.rset.getString("SUPER_ORG_CD"));
				temp.setSEQ(this.rset.getString("SEQ"));

				resultVO.add(temp);
				count++;
			}
		} catch (SQLException e) {
			throw new SysException(e);
		} finally {
			close();
		}
		
		if(EXPORT==1){
			logger.info("exportCSV");
			cw = null;
			try {
				cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(path+"/dept.csv"), "UTF-8"),',');
			} catch (UnsupportedEncodingException | FileNotFoundException e  ) {
				logger.error(e.getMessage());
				try {
					cw.close();
				} catch (IOException e1) {
					logger.error(e.getMessage());
				}
			}
			for(int i=0; i < resultVO.size(); i++){
				DEPTVO temp=(DEPTVO) resultVO.get(i);
				//logger.info(temp.getORG_CD()+","+ temp.getORG_NM()+","+ temp.getSUPER_ORG_CD());					
				cw.writeNext(new String[]{temp.getORG_CD(), temp.getORG_NM(), temp.getSUPER_ORG_CD()});
			}
			try {
				cw.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		resultVO.setCount(count);
		return resultVO;
	}

	public int[] updateDeptList(DEPTVO voList) throws LException {
		int[] result;
		try {
			SqlManager query = SqlManagerXmlFactory.getInstance().get(getClass().getName(), "insertDept");

			openDirectConnection("tosource");

			setPreparedStatement(query.getSql());

			for (int i = 0; i < voList.size(); i++) {
				DEPTVO vo = (DEPTVO)voList.get(i);

				this.prepStmtWrap.setString(vo.getORG_CD());
				this.prepStmtWrap.setString(vo.getORG_NM());
				this.prepStmtWrap.setString(vo.getSUPER_ORG_CD());
				this.prepStmtWrap.setString(vo.getSEQ());

				this.prepStmtWrap.setString(vo.getORG_NM());
				this.prepStmtWrap.setString(vo.getSUPER_ORG_CD());
				this.prepStmtWrap.setString(vo.getSEQ());

				this.prepStmtWrap.addBatch();
				this.prepStmtWrap.clearParameters();

				if ((i <= 0) || (i % 1000 != 0)) continue; executeBatch();
			}

			result = executeBatch();
		}
		catch (SQLException e) {
			throw new SysException(e);
		}
		finally {
			close();
		}
		return result;
	}
	
	/**
	 * csv파일에서 읽어온 부서정보 업데이트
	 * @param voList
	 * @return
	 * @throws LException
	 */
	public int[] updateDeptCsvList(ValueObject voList) throws LException {
		int[] result;
		try {
			SqlManager query = SqlManagerXmlFactory.getInstance().get(getClass().getName(), "insertDept");

			openDirectConnection("tosource");

			setPreparedStatement(query.getSql());

			for (int i = 0; i < voList.size(); i++) {
				DEPTVO vo = (DEPTVO)voList.get(i);
				String deptname = vo.getORG_NM();
				/*if(deptname.length() > 32){
					deptname = deptname.substring(0, 32);
				}*/
				
				if(vo.getSUPER_ORG_CD().toLowerCase().equals("null")){
					vo.setSUPER_ORG_CD("");
				}
				
				this.prepStmtWrap.setString(vo.getORG_CD());
				this.prepStmtWrap.setString(deptname);
				this.prepStmtWrap.setString(vo.getSUPER_ORG_CD());
				this.prepStmtWrap.setString("0");

				this.prepStmtWrap.setString(deptname);
				this.prepStmtWrap.setString(vo.getSUPER_ORG_CD());
				this.prepStmtWrap.setString("0");

				this.prepStmtWrap.addBatch();
				this.prepStmtWrap.clearParameters();

				if ((i <= 0) || (i % 1000 != 0)) continue; executeBatch();
			}

			result = executeBatch();
		}
		catch (SQLException e) {
			throw new SysException(e);
		}
		finally {
			close();
		}
		return result;
	}
	
	/**
	 * 최상위 부서 생성
	 * @param voList
	 * @return
	 * @throws LException
	 */
	public int updateDeptTOP(ValueObject vo) throws LException {
		int result;
		try {
			SqlManager query = SqlManagerXmlFactory.getInstance().get(getClass().getName(), "insertDept");

			openDirectConnection("tosource");

			setPreparedStatement(query.getSql());
			this.prepStmtWrap.setString(vo.getString("topdeptid"));
			this.prepStmtWrap.setString(vo.getString("topdeptname"));
			this.prepStmtWrap.setString("");
			this.prepStmtWrap.setString("0");

			this.prepStmtWrap.setString(vo.getString("topdeptname"));
			this.prepStmtWrap.setString("");
			this.prepStmtWrap.setString("0");
			result = executeUpdate();
		}
		catch (SQLException e) {
			throw new SysException(e);
		}
		finally {
			close();
		}
		return result;
	}
	
	public int[] insertDept() throws LException {
		int[] result;
		try {
			SqlManager query = SqlManagerXmlFactory.getInstance().get(getClass().getName(), "insertDept");

			openDirectConnection("tosource");

			setPreparedStatement(query.getSql());
			int par = 1;
			int temp = 1;
			for (int i = 1; i < 100000; i++) {
				if (temp == 11) {
					par++;
					temp = 1;
				}
				this.prepStmtWrap.setString("dept" + i);
				this.prepStmtWrap.setString("부서" + i);
				this.prepStmtWrap.setString("dept"+par);
				this.prepStmtWrap.setString(Integer.toString(i));

				this.prepStmtWrap.setString("dept" + i);
				this.prepStmtWrap.setString("dept"+par);
				this.prepStmtWrap.setString(Integer.toString(i));

				this.prepStmtWrap.addBatch();
				this.prepStmtWrap.clearParameters();
				System.out.println("dept"+i + ", par"+par);
				temp++;
				if ((i <= 0) || (i % 1000 != 0)) continue; executeBatch();
			}

			result = executeBatch();
		}
		catch (SQLException e) {
			throw new SysException(e);
		}
		finally {
			close();
		}
		return result;
	}
	
	public static void main(String[] args) throws LException {
		DEPTDAO ded = new DEPTDAO();
		ded.insertDept();
	}
}