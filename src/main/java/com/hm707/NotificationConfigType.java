package com.hm707;

public enum NotificationConfigType {
	OFF("OFF"),
	MALWARE("MAL"),
	MALWARE_AND_ERROR("MAE"),
	ERROR("ERR"),
	ALL("ALL");

	private String code;

	private NotificationConfigType(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}