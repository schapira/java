
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.security.auth.login.*;

import org.wikipedia.Wiki;



public class Main 
{

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, LoginException 
	{
		Wiki wiki;
		wiki = new Wiki("he.wikisource.org"); // create a new wiki connection to en.wikipedia.org
		wiki.setThrottle(5000); // set the edit throttle to 0.2 Hz
		System.out.println("please enter password");
		Scanner in = new Scanner( System.in);
		String pswrd = in.next();
		wiki.login("OrBoot", pswrd); // log in as user ExampleBot, with the specified password
		wiki.setMarkBot(true);
		String str[] = wiki.prefixIndex("שולחן ערוך אורח חיים ");
		PrintWriter writer = new PrintWriter("file.txt", "UTF-8");
		for (String s: str)
		{
			writer.println(s);
		}
		  writer.close();


		//wiki.edit("משתמשת:אור שפירא/ניסיוןקרא", wiki.getPageText("משתמשת:אור שפירא"), "ניסיון בוט");

	}

}
