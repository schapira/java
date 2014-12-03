
import java.io.FileNotFoundException;
import java.io.IOException;


import javax.security.auth.login.*;

import org.wikipedia.Wiki;



public class Main 
{

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, LoginException 
	{
		Wiki wiki;
		wiki = new Wiki("he.wikisource.org"); // create a new wiki connection to en.wikipedia.org
		wiki.setThrottle(5000); // set the edit throttle to 0.2 Hz
		wiki.login("אור שפירא", ""); // log in as user ExampleBot, with the specified password
		wiki.setMarkBot(true);
		//wiki.getCurrentDatabaseLag();
		System.out.println(wiki.getCurrentDatabaseLag());
		//wiki.edit("משתמשת:אור שפירא/ניסיוןקרא", wiki.getPageText("משתמשת:אור שפירא"), "ניסיון בוט");

	}

}
