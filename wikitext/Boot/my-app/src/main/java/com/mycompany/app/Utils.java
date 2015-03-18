package com.mycompany.app;

public class Utils {
	/**
	 * convert long number (more then one digit) to the represented gimetria chars, 613 -> תריג
	 * @param num
	 * @return
	 */
	public static String gimetria (int num){
		if (num == 15) return "‏טו";
		if (num ==16) return "טז";
		System.out.println(num%10);
		String rtn = singleGimatria(num%10)+"";
		num =num / 10;
		System.out.println(num);
		if (num!=0&& num!=10)	{
			rtn =tensGimatria(num%10)+rtn;
		}
		if (num !=0){
			num = num / 10;
			if (num!=0)
				rtn = hundredsGimatria(num)+rtn;
		}
		return rtn;	
	}
	/**
	 * 
	 * @param num
	 * @return
	 */
	static char singleGimatria (int num){
		if (num==0) return '\0';
		char rtn = (char) ('א'+num - 1);
		return rtn;
	}
	static char tensGimatria(int num){
		switch (num){
		case 1: return 'י';
		case 2: return 'כ';
		case 3: return 'ל';
		case 4: return 'מ';
		case 5:return 'נ';
		case 6:return 'ס';
		case 7:return 'ע';
		case 8:return 'פ';
		case 9:return 'צ';
		}
		return '\0';
	}
	static char hundredsGimatria(int num){
		return (char) ('ק'+num-1);
	}
}
