package com.cdg.privateMasking;

public class Main {
	
		private static String INPUT_FILE = "privateInfo.txt";
		
		public static void main(String[] args) {
			
			InputPrivateInformation fileInput = new InputPrivateInformation(INPUT_FILE);
			PrivateInfoMasking masking = new PrivateInfoMasking(fileInput);
			
			masking.distribute();
		}
}