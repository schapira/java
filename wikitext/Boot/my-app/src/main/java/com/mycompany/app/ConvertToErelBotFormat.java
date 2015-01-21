package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
/**
 * 
 * @author Or Schapira
 *
 */
@SuppressWarnings("serial")
public class ConvertToErelBotFormat extends SimpleArticle {
	//FINALES
	private final static String EOF = "סוףקובץ";
	private final static String SUMMARY_TITLE = "תקציר";
	private final static String START_OF_TITLE = "#####";
//-----------------------------------------------	
	//STATICS

	private static File file;
	private static String summary;
/*----------------------------------------------*/
	/**
	 * basic constructor
	 * @param title
	 * @param text
	 * @throws FileNotFoundException
	 */
	public ConvertToErelBotFormat(String title, String text) throws FileNotFoundException {
		super(title);
		this.setText(text);
		summary = "Added by bot";
		file =new File("file.txt");
		addSummery();
	}
	/**
	 * constructor with file, this may change the file the all class is working on, use carefully
	 * @param title
	 * @param text
	 * @param newFile
	 * @throws FileNotFoundException
	 */
	public ConvertToErelBotFormat(String title, String text, File newFile) throws FileNotFoundException {
		this(title, text);
		file = newFile;
	}
	/**
	 * constructor with file, this may change the file the all class is working on, use carefully
	 * TODO add the summary to the file
	 * @param title
	 * @param text
	 * @param newFile
	 * @param sum
	 * @throws FileNotFoundException
	 */
	public ConvertToErelBotFormat(String title, String text, File newFile,String sum) throws FileNotFoundException {
		this(title,text, newFile);
		summary = sum;
	}
	/*--------------------------------------------------*/
	private static void addSummery(){
		
	}
	/**
	 * return if there is summary in the file, this will return true for an empty summary
	 * @return true if had summary in the file
	 * @throws IOException
	 */
	public static boolean isSummeryInFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		if (line == null) {
			br.close();
			return false;
		}
		if (line.startsWith(START_OF_TITLE) && line.contains(SUMMARY_TITLE)){
			line = br.readLine();
			line = br.readLine();
			if ( line.contains(EOF)){
				br.close();
				return true;
			}
		}
		br.close();
		return false;
	}
	/**
	 * 
	 * @return current file of the class
	 */
	public static File getFile() {
		return file;
	}
	/**
	 * change the file of the all class, use carefully
	 * @param file
	 */
	public static void setFile(File file) {
		ConvertToErelBotFormat.file = file;
	}
	/**
	 * 
	 * @return current summary
	 */
	public static String getSummery() {
		return summary;
	}
	/**
	 * TODO change the summary in the file and change to public
	 * @param summery
	 */
	@SuppressWarnings("unused")
	private static void setSummery(String summery) {
		ConvertToErelBotFormat.summary = summery;
	}

	

	

}
