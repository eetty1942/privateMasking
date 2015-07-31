package com.cdg.privateMasking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateInfoMasking {
	private static final Logger logger = LoggerFactory.getLogger(InputPrivateInformation.class);
	/*private final String PHONE_NUM_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";*/
	/*public String PHONE_NUM_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";*/
	public static String PHONE_NUM_PATTERN = "(01[0|1|7|8|9])([- 　\\t\\n\\x0B\\f\\r]*)(\\d{3,4})([- 　\\t\\n\\x0B\\f\\r]*)\\d{4}";
	public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * 이메일이든, 휴대폰번호든 각 포맷에 맞게 마스킹된 결과값 리턴해주는 함수 포맷이 맞지 않을 경우 인풋으로 들어온 값 그대로 리턴
	 *
	 * public은 이거 하나! valid check류의 메서드들도 추후 필요하면 public으로 바꿀 예정
	 *
	 * @param id
	 * @return maskedId
	 */

	private ArrayList<String> lineList;
	ArrayList<String> maskList = new ArrayList<String>();
	OutputPrivateInformation out = new OutputPrivateInformation();

	public static String resultPhone;
	public static String resultEmail;
	 

	public PrivateInfoMasking(InputPrivateInformation fileInput) {
		this.lineList = fileInput.read();
	}
	
	public void distribute() {

		Iterator<String> it = lineList.listIterator();

		while (it.hasNext()) {

			String str = (String) it.next();
			getMaskedId(str);
			out.createFile(resultPhone, resultEmail);

		} 

	}

	public static String getMaskedId(String id) {
		
		//해당 영역에 문제가 있습니다.
		//검증과정을 거쳤음에도 해당 조건문 내부로의 접근을 건너뜁니다. 
		
      if (isEmail(id)) {
    	  resultEmail = getMaskedEmail(id); 
         return resultEmail;
      } else if (isPhoneNum(id)) {
    	  resultPhone = getMaskedPhoneNum(id);
         return resultPhone;
      }
      return id;
   }

	   /**
	    * 이메일 포맷 Validator
	    * @param str
	    * @return isValidEmailFormat
	    */
	   private static boolean isEmail(final String str) {
	      return isValid(EMAIL_PATTERN, str);
	   }

	   /**
	    * 휴대폰 번호 포맷 Validator
	    * @param str
	    * @return isValidCellPhoneNumFormat
	    */
	   private static boolean isPhoneNum(final String str) {
	      return isValid(PHONE_NUM_PATTERN, str);
	   }

	   /**
	    * 문자열이 정규식에 맞는 포맷인지 체크
	    * @param regex
	    * @param target
	    * @return isValid
	    */
	   private static boolean isValid(final String regex, final String target) {
		   Matcher matcher = Pattern.compile(regex).matcher(target);
		      return matcher.matches();
	      
	   }

	   /**
	    * 이메일 주소 마스킹 처리
	    * @param email
	    * @return maskedEmailAddress
	    */
	   private static String getMaskedEmail(String email) {
	      /*
	      * 요구되는 메일 포맷
	      * {userId}@domain.com
	      * */
	      String regex = "\\b(\\S+)+@(\\S+.\\S+)";
	      Matcher matcher = Pattern.compile(regex).matcher(email);
	      if (matcher.find()) {
	         String id = matcher.group(1); // 마스킹 처리할 부분인 userId
	         /*
	         * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고,
	         * 세글자인 경우 뒤 두글자만 마스킹,
	         * 세글자 미만인 경우 모두 마스킹 처리
	         */
	         int length = id.length();
	         if (length < 3) {
	            char[] c = new char[length];
	            Arrays.fill(c, '*');
	            
	            return resultEmail = email.replace(id, String.valueOf(c));
	         } else if (length == 3) {
	            return resultEmail = email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
	         } else {
	            return resultEmail = email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
	         }
	      }
	      return resultEmail = email;
	   }

	   /**
	    * 휴대폰 번호 마스킹 처리
	    * @param phoneNum
	    * @return maskedCellPhoneNumber
	    */
	   private static String getMaskedPhoneNum(String phoneNum) {
	      /*
	      * 요구되는 휴대폰 번호 포맷
	      * 01055557777 또는 0113339999 로 010+네자리+네자리 또는 011~019+세자리+네자리 이!지!만!
	      * 사실 0107770000 과 01188884444 같이 가운데 번호는 3자리 또는 4자리면 돈케어
	      * */
	      String regex = "(01[016789])(\\d{3,4})\\d{4}$";
	      Matcher matcher = Pattern.compile(regex).matcher(phoneNum);
	      if (matcher.find()) {
	         String replaceTarget = matcher.group(2);
	         char[] c = new char[replaceTarget.length()];
	         Arrays.fill(c, '*');
	         return resultPhone = phoneNum.replace(replaceTarget, String.valueOf(c));
	      }
	      return resultPhone = phoneNum;
	   }
	   
	   
	
}
