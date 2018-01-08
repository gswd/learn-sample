package com.hm707.time.used;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by LH on 2017/10/20.
 */
public class TestWriteUTF {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(baos);
		outputStream.writeUTF("你好");

		ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
		DataInputStream dataInputStream = new DataInputStream(bis);

		String a = dataInputStream.readUTF();
		System.out.println(a);
	}
}
