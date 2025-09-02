package com.sqisoft.ssbr.datasync.resources;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

public class LocalizedMessageResourcesFactory extends MessageResourcesFactory {

	private static final long serialVersionUID = 1L;

	public MessageResources createResources(String config) {
		return new LocalizedMessageResources(this, config, this.returnNull);
	}
}
