import java.util.ArrayList;
import java.util.List;

import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;

import com.example.tanachyomi.NachCalendar;


public class Tester 
{
	public static void main(String[] args) 
	{
		NachCalendar cal = new NachCalendar();
		HebrewDateFormatter formater = new HebrewDateFormatter();
		formater.setHebrewFormat(true);
		int index = 0;
		int year=cal.getJewishYear()-2;
		int cholNum=0;
		ArrayList<String> l = new ArrayList<String>();
		String k = formater.getFormattedKviah(year);
		while (index<14)
		{
			while(l.contains(k)) 
			{
				cal.setJewishYear(year+1);
				year=cal.getJewishYear();
				k = formater.getFormattedKviah(year);
			}
			l.add(k);
			cholNum =cal.getYemeCholNum();
			System.out.println("ימים להוסיף "+ ((int)cholNum-293));
			System.out.print(formater.getFormattedKviah(year)+"\t");
			System.out.print("ימי חול "+cholNum+"\t");
			System.out.print(formater.formatHebrewNumber(year)+ "\t");
			if (cal.isJewishLeapYear()) 
			{
				System.out.print("מעוברת\t");
				cholNum-=25;
			}
		
			index++;
		}
	}
	public void printYear(int year)
	{
		NachCalendar cal = new NachCalendar();
		HebrewDateFormatter formater = new HebrewDateFormatter();
		formater.setHebrewFormat(true);
		cal.setJewishYear(year);
		boolean isChol = false;
		
		int moedCount = 0;
		int cholCount=0;
		int shabbatCount = 0;
		while(cal.getJewishYear() == year)
		{
			isChol = cal.isYomChol();
			
			if (cal.getDayOfWeek()==7)
			{
				shabbatCount++;
				System.out.print(shabbatCount+"\t");
			}
			if (isChol) 
			{
				cholCount++;
				System.out.print(" חול "+cholCount+"\t");
			}
			else 
				{
					moedCount++;
					System.out.print(" מועד "+moedCount+"\t");
				}
			System.out.print(formater.formatYomTov(cal)+"\t");
			System.out.print(formater.formatDayOfWeek(cal)+"\t");
			System.out.println(formater.format(cal));
			cal.forward();
		}
	}
}
