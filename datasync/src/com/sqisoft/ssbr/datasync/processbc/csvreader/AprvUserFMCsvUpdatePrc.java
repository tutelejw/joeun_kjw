package com.sqisoft.ssbr.datasync.processbc.csvreader;

import com.mitzz.frame.exception.LException;
import com.mitzz.frame.processbc.AbstractPrc;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.entitybc.IDataSyncPostUser;
import java.sql.Connection;

public class AprvUserFMCsvUpdatePrc extends AbstractPrc {
	private static final long serialVersionUID = 1L;
	private IDataSyncPostUser is = null;

	public ValueObject process() throws LException {
		this.is = new IDataSyncPostUser();
		ValueObject vo = (ValueObject)this.infoVO.getDataVO();

		int[] count = this.is.updateAprvUserList(vo);

		this.infoVO.setInt("count", count[0]);
		return this.infoVO;
	}

	public ValueObject process(Connection arg0) throws LException {
		return null;
	}
}
