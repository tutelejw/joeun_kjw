package com.sqisoft.ssbr.datasync.processbc;

import com.mitzz.frame.exception.LException;
import com.mitzz.frame.processbc.AbstractPrc;
import com.mitzz.frame.vo.ValueObject;
import com.sqisoft.ssbr.datasync.entitybc.IDataSyncPostUser;
import com.sqisoft.ssbr.datasync.vo.POSTUSERVO;
import java.sql.Connection;

public class PostUserFMUpdatePrc extends AbstractPrc {
	private static final long serialVersionUID = 1L;
	private IDataSyncPostUser is = null;

	public ValueObject process() throws LException {
		this.is = new IDataSyncPostUser();
		POSTUSERVO vo = (POSTUSERVO)this.infoVO.getDataVO();

		int[] count = this.is.updatePostUserList(vo);

		this.infoVO.setInt("count", count[0]);
		System.out.println("this is");
		return this.infoVO;
	}

	public ValueObject process(Connection arg0) throws LException {
		return null;
	}
}
