package com.hm707;

public class TestEnum {

	public static void main(String[] args) {
		NotificationConfigType type = NotificationConfigType.valueOf("MALWARE");
		System.out.println(type);
	}
}
