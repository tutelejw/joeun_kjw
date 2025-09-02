package com.sqisoft.ssbr.datasync.processbc;

import com.mitzz.frame.exception.LException;
import com.mitzz.frame.processbc.AbstractPrc;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.entitybc.IDataSyncDept;
import com.sqisoft.ssbr.datasync.vo.DEPTVO;
import java.sql.Connection;

public class DeptFMRetrievePrc extends AbstractPrc {
	private static final long serialVersionUID = 1L;
	private IDataSyncDept is = null;

	public ValueObject process() throws LException {
		this.is = new IDataSyncDept();

		DEPTVO vo = (DEPTVO)this.infoVO.getDataVO();
		vo = this.is.retrieveDeptList(vo);

		this.infoVO.setDataVO(vo);

		return this.infoVO;
	}

	public ValueObject process(Connection arg0) throws LException {
		return null;
	}
}
