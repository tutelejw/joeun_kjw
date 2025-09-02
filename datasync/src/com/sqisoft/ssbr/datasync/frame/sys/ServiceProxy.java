package com.sqisoft.ssbr.datasync.frame.sys;

import com.mitzz.frame.exception.LException;
import com.mitzz.frame.processbc.AbstractPrc;
import com.mitzz.frame.vo.ValueObject;

public class ServiceProxy {
	public ValueObject process(AbstractPrc prc) throws LException {
		return prc.execute();
	}
}