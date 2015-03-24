import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class AddNameOfPage {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		PrintWriter writer = new PrintWriter("final.txt", "UTF-8");
		FileReader fileReader = new FileReader("sdarim.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        ArrayList<String> lines = new ArrayList<String>();
	        String oneLine = null;
	        while ((oneLine = bufferedReader.readLine()) != null) {
	            lines.add(oneLine);
	        }
	        bufferedReader.close();
	        String title = "";
	        String out="";
			for ( String line:lines){
				if (line.contains("#"))
				{
					title = line.replaceAll("#", "").replaceAll("/"," ");				
					out = line;
				}
				else if (line.contains("{{"))
				{
					out = line.replaceAll("}}", "\\|"+ title+"}}");

				}
				else
					out = line;
				System.out.println(out);
				writer.println(out);	
			}
	}
}
