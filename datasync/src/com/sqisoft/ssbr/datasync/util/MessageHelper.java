package com.sqisoft.ssbr.datasync.util;

import java.util.Locale;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

public class MessageHelper {
	private MessageResources resources = null;
	private boolean loaded = false;

	private static MessageHelper instance = new MessageHelper();

	public static void load(MessageResources resources) {
		instance.setResources(resources);
		instance.setLoaded(true);
	}

	public static MessageHelper getInstance() {
		return instance;
	}

	public String getConfig() {
		return this.resources.getConfig();
	}

	public MessageResourcesFactory getFactory() {
		return this.resources.getFactory();
	}

	public boolean getReturnNull() {
		return this.resources.getReturnNull();
	}

	public void setReturnNull(boolean b) {
		this.resources.setReturnNull(b);
	}

	public String getMessage(String s) {
		return this.resources.getMessage(s);
	}

	public String getMessage(String s, Object[] objects) {
		return this.resources.getMessage(s, objects);
	}

	public String getMessage(String s, Object o) {
		return this.resources.getMessage(s, o);
	}

	public String getMessage(String s, Object o, Object o1) {
		return this.resources.getMessage(s, o, o1);
	}

	public String getMessage(String s, Object o, Object o1, Object o2) {
		return this.resources.getMessage(s, o, o1, o2);
	}

	public String getMessage(String s, Object o, Object o1, Object o2, Object o3) {
		return this.resources.getMessage(s, o, o1, o2, o3);
	}

	public String getMessage(Locale locale, String s) {
		return this.resources.getMessage(locale, s);
	}

	public String getMessage(Locale locale, String s, Object[] objects) {
		return this.resources.getMessage(locale, s, objects);
	}

	public String getMessage(Locale locale, String s, Object o) {
		return this.resources.getMessage(locale, s, o);
	}

	public String getMessage(Locale locale, String s, Object o, Object o1) {
		return this.resources.getMessage(locale, s, o, o1);
	}

	public String getMessage(Locale locale, String s, Object o, Object o1, Object o2) {
		return this.resources.getMessage(locale, s, o, o1, o2);
	}

	public String getMessage(Locale locale, String s, Object o, Object o1, Object o2, Object o3) {
		return this.resources.getMessage(locale, s, o, o1, o2, o3);
	}

	public boolean isPresent(String s) {
		return this.resources.isPresent(s);
	}

	public boolean isPresent(Locale locale, String s) {
		return this.resources.isPresent(locale, s);
	}

	public MessageResources getMessageResources(String s) {
		return MessageResources.getMessageResources(s);
	}

	public MessageResources getResources() {
		return this.resources;
	}

	public void setResources(MessageResources resources) {
		this.resources = resources;
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
}