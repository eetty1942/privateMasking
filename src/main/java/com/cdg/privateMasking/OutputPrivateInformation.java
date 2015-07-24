package com.cdg.privateMasking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputPrivateInformation {

	//결과를 String result를 통해 매개변수로 받아 파일로 출력하는 메소드
	public void createFile(String result) {
		try {
			FileWriter fileWriter = new FileWriter("private_masking.txt");
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

			bufferWriter.write(result);
			bufferWriter.write("/r/n");
			bufferWriter.close();

		} catch (IOException e) {

		}
	}
}
