package com.sqisoft.ssbr.datasync.processbc.csvreader;

import com.mitzz.frame.exception.LException;
import com.mitzz.frame.processbc.AbstractPrc;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.entitybc.IDataSyncDept;
import java.sql.Connection;

public class DeptFMCsvUpdatePrc extends AbstractPrc {
	private static final long serialVersionUID = 1L;
	private IDataSyncDept is = null;

	public ValueObject process() throws LException {
		this.is = new IDataSyncDept();

		ValueObject vo = (ValueObject)this.infoVO.getDataVO();

		int[] count = this.is.updateDeptCsvList(vo);

		this.infoVO.setInt("count", count[0]);
		return this.infoVO;
	}

	public ValueObject process(Connection arg0) throws LException {
		return null;
	}
}