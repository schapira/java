import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Convert 
{
/**
 * 	
 * @param args
 * @throws IOException
 */
	final static Map<Integer, String> dic = dic();
	public static void main(String[] args) throws IOException
	{
		
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		String[] lines = readLines("input.txt");

		String book="";
		String out="";
		int seder=0;
		String prev="";
		String current="";
		String title="";
		for (String line:lines)
		{
		//	System.out.println(line);

			if (line.indexOf(")")<0) 
				{
				book = line;
				seder=1;
				}
			else
			{
				out+="\n";
				if (title=="")
				{
					title+=makeTitle(book, seder);	
					seder++;
				}
				else {
					out+=title;
					title = makeTitle(book, seder);
					seder++;
					current=prev+"|"+line.substring(line.indexOf(")")+2, line.length()).replace(" ", "|");
					out+=current;
					out+="}}\n";
					out+="[[קטגוריה:סדרים בנ\"ך]]";
					out+= "\nסוףקובץ";
				}
				
				prev =line.substring(line.indexOf(")")+2, line.length()).replace(" ", "|");
			}
		}
		out=out.replace("|תרי עשר", "");
		out = out.replace(",","");
		out = out.replace("מלכים|", "מלכים ");
		out = out.replace("שמואל|", "שמואל ");
		out = out.replace("דברי הימים|", "דברי הימים ");
		out= out.replace("עזרא ונחמיה|", "");
		writer.println("#####תקציר ");
		writer.println("הוספת סדרי הנך על ידי בוט");
		writer.println("סוףקובץ");
		writer.println(out);
		writer.close();
    
	}
	private static String makeTitle(String book, int seder)
	{
		String out = "#####";//כותרת
		out+=book+" סדר "+dic.get(seder)+"\n";
		out+="{{דף סדר|";  ///תוכן
		out+=book+"|";	
		return out;
	}
	/**
	 * 
	 * @param in
	 * @return
	 */
	public static String clean (String in)
	{
		String str = new String(in);
		
	//	str = str.replace("עזרא", "");
	//	str = str.replace("נחמיה", "");
		str = str.replace("הושע", "");
		str = str.replace("יואל", "");
		str = str.replace("עמוס", "");
		str = str.replace("עובדיה", "");
		str = str.replace("מיכה", "");
		str = str.replace("צפניה", "");
		str = str.replace("חבקוק", "");
		 str = str.replace("זכריה", "");
		 return str;
		
	}
	/**
	 * 
	 * @return
	 */
	public static HashMap<Integer, String>  dic()
	{
		HashMap<Integer, String> dic = new HashMap<Integer, String>();
		dic.put(1, "א");
		dic.put(2, "ב");
		dic.put(3, "ג");
		dic.put(4, "ד");
		dic.put(5, "ה");
		dic.put(6, "ו");
		dic.put(7, "ז");
		dic.put(8, "ח");
		dic.put(9, "ט");
		dic.put(10, "י");
		dic.put(20, "כ");
		dic.put(30, "ל");
		dic.put(40, "מ");

		for (int j=10; j<=40;j+=10)
		{
			for (int i=1;i<10;i++)
			{
				dic.put(i+j, ""+dic.get(j)+dic.get(i));
			}
		}
		dic.put(15, "טו");
		dic.put(16, "טז");
		return dic;
		
	}
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	 public static String[] readLines(String filename) throws IOException {
	        FileReader fileReader = new FileReader(filename);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        ArrayList<String> lines = new ArrayList<String>();
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	            lines.add(line);
	        }
	        bufferedReader.close();
	        return lines.toArray(new String[lines.size()]);
	    }
}
