package com.example.tanachyomi;


import java.io.FileInputStream;
import java.io.FileOutputStream;



public class Sdarim2WikiText {

	public static void main(String[] args)
	{
		FileInputStream in = null;
		FileOutputStream out = null;

		try
		{
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");
			int c;
			while ((c = in.read()) != -1)
			{
				out.write(c);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
