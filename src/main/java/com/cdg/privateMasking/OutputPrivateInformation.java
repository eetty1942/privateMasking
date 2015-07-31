package com.cdg.privateMasking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputPrivateInformation {

	public void createFile(String resultPhone, String resultEmail) {
		
		String result = "전화 : " + resultPhone + "\r\n" + "이메일 : " + resultEmail;
		
		try {
			FileWriter fileWriter = new FileWriter("private_masking.txt");
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

			bufferWriter.write(result);
			bufferWriter.write("\r\n");
			bufferWriter.close();

		} catch (IOException e) {

		}
	}
}
