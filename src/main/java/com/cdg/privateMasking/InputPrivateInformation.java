package com.cdg.privateMasking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputPrivateInformation {


	private ArrayList<String> lineList = new ArrayList<String>();
	private String inputFile;

	public InputPrivateInformation(String inputFile) {
		this.inputFile = inputFile;
	}

	public ArrayList<String> read() {

		try {
			FileReader in = new FileReader(inputFile);
			BufferedReader bin = new BufferedReader(in);
			String line;

			while ((line = bin.readLine()) != null) {
				lineList.add(line);
			}
			bin.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lineList;
	}
}
