package com.sqisoft.ssbr.datasync.entitybc;

import com.mitzz.frame.exception.BizException;
import com.mitzz.frame.exception.FKException;
import com.mitzz.frame.exception.LException;
import com.mitzz.frame.msg.MessageFactory;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.entitybc.dao.DEPTDAO;
import com.sqisoft.ssbr.datasync.vo.DEPTVO;

public class IDataSyncDept {
	public DEPTVO retrieveDeptList(DEPTVO vo) throws LException {
		DEPTDAO dao = new DEPTDAO();

		return dao.retrieveDeptList(vo);
	}

	public int[] updateDeptList(DEPTVO vo) throws LException {
		DEPTDAO dao = new DEPTDAO();
		try {
			return dao.updateDeptList(vo);
		}
		catch (FKException fe) {
			throw new BizException(MessageFactory.getInstance().getMsg("EAA0001"),fe);
		}
	}
	
	
	public int[] updateDeptCsvList(ValueObject vo) throws LException {
		DEPTDAO dao = new DEPTDAO();
		try {
			
			//최상위 부서 설정
			if(vo.getInt("topdeptusetag") == 0){
				if(dao.updateDeptTOP(vo) < 1){
					return null;
				}
			}
			
			return dao.updateDeptCsvList(vo);
		}
		catch (FKException fe) {
			throw new BizException(MessageFactory.getInstance().getMsg("EAA0001"),fe);
		}
	}
}
