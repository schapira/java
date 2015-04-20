package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConvertPsukim {

	public static void main(String[] args) throws IOException {

		FileReader fr = new FileReader("baruch.txt");
		BufferedReader br = new BufferedReader(fr);
		PrintWriter out = new PrintWriter("baruch.out.txt");
		String s;
		String str="";
		while((s = br.readLine()) != null) {
			str+=s+"\n";
		}
		fr.close();
		String arr[] = str.split("\\(");
		int i=0;
		for (String pasuk:arr){
			if (pasuk.indexOf(')')>0){
				i = Integer.parseInt(pasuk.substring(0, pasuk.indexOf(')')));		
				out.println("=="+Utils.gimetria(i)+"==");
				out.println(pasuk.substring( pasuk.indexOf(')')+2));
			}	
			else out.println(pasuk);

		}
		out.close();
	} 


}


