package com.example.tanachyomi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;





public class Sdarim2WikiText {

	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		

		try 
		{
			BufferedReader in = new BufferedReader(new FileReader("input.txt"));
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("output.txt"), "UTF8"));
			String line = in.readLine();
			String str="";
			while (line!=null)
			{
				str += line+"\n";
				line = in.readLine();
			}
			str=str.replaceAll("\n\n","\n");
			String[] arr = str.split("\n");
			String book="";
			for (String s:arr)
			{
			
				if (s.indexOf(")")<0)book = s;
				System.out.println(book);
				
			}
			out.write(str);
			out.close();
		} 
		catch (Exception e1) 
		{

			e1.printStackTrace();
		}
	}
}
