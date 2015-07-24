package com.cdg.privateMasking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class InputPrivateInformation {

	//파일 불러오기
	//File fp = new File("privateInfo.txt");
	//FileReader fileReader;
	private	FileInputStream fstream = null;
	private String line = null;

	private static final String  PHONE_NUM_PATTERN = "(01[0|1|7|8|9])([- 　\\t\\n\\x0B\\f\\r]*)(\\d{3,4})([- 　\\t\\n\\x0B\\f\\r]*)\\d{4}";
	/*result = "휴대폰 : 010-333-9999".replaceAll(regex,"$1$2$3$4****");
	System.out.println(result);*/

	//private static final String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,}$";
	private static final String  CREDITCARD_NUM_PATTERN = "(\\d{4})([- 　\\t\\n\\x0B\\f\\r])\\d{4}([- 　\\t\\n\\x0B\\f\\r])\\d{4}([- 　\\t\\n\\x0B\\f\\r])\\d{4}";
	/*	String result = "카드번호 : 1111-1111-2222-3333".replaceAll(regex,"$1$2*******");
	System.out.println(result);*/
	private static final String  REGISTRATION_NUM_PATTERN = "(\\d{6})([- 　\\t\\n\\x0B\\f\\r])[1234]\\d{6}";
	/*	String result = "주민번호 : 780510-1111111".replaceAll(regex,"$1$2*******");
	System.out.println(result);*/
	
	private String registrationNumber;
	private String creditCardNumber;
	private String phoneNumber;
	
	
	private HashMap<String, Integer> registrationNumHashmap = new HashMap<String, Integer>();
	private HashMap<String, Integer> creditCardNumHashmap = new HashMap<String, Integer>();
	private HashMap<String, Integer> phoneNumHashmap = new HashMap<String, Integer>();
	
	private PrivateInfoMasking masking = new PrivateInfoMasking();
	
	public String loadFile() throws IOException {
	
		fstream = null;
		
		try {
			File inFile = new File("privateInfo.txt");
			fstream = new FileInputStream(inFile);
			
			DataInputStream inputStream = new DataInputStream(fstream);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			
			while ((line = bufferReader.readLine()) != null && line != "") {
				
				registrationNumber = masking.masking_registrationNum(line);
				creditCardNumber = masking.masking_registrationNum(line);
				phoneNumber = masking.masking_registrationNum(line);
				
				//로직 구현 필요
				//주민번호
				if(registrationNumHashmap.containsKey(registrationNumber)){
					int value=0;
					value = registrationNumHashmap.get(registrationNumber);
					//registrationNumHashmap.put("*", ++value);   ??
				}else{
					//apiServiceHashmap.put(apiServiceId, 1);
				}
				
				//신용카드번호
				if(creditCardNumHashmap.containsKey(creditCardNumber)){
					int value=0;
					value = creditCardNumHashmap.get(creditCardNumber);
					//registrationNumHashmap.put("*", ++value);   ??
				}else{
					//apiServiceHashmap.put(apiServiceId, 1);
				}
				
				//연락처
				if(phoneNumHashmap.containsKey(phoneNumber)){
					int value=0;
					value = phoneNumHashmap.get(phoneNumber);
					//registrationNumHashmap.put("*", ++value);   ??
				}else{
					//apiServiceHashmap.put(apiServiceId, 1);
				}
			}
			
		} catch (FileNotFoundException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,ex);
		}
		String result;
		
		result = "주민번호  : "+registrationNumber + "\r\n" +
				"카드번호 : "+ creditCardNumber + "\r\n" +
				"연락처  : " + phoneNumber;
		
				
		return result;
	}
}
