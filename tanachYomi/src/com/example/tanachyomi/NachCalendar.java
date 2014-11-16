/**
 * 
 */
package com.example.tanachyomi;

import java.util.Calendar;
import java.util.Date;

import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;
import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;


/**
 * @author or
 *
 */
public class NachCalendar extends JewishCalendar {
	/**
	 * 
	 */
	public NachCalendar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param date
	 */
	public NachCalendar(Date date) {
		super(date);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param calendar
	 */
	public NachCalendar(Calendar calendar) {
		super(calendar);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param jewishYear
	 * @param jewishMonth
	 * @param jewishDayOfMonth
	 */
	public NachCalendar(int jewishYear, int jewishMonth, int jewishDayOfMonth) {
		super(jewishYear, jewishMonth, jewishDayOfMonth);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param jewishYear
	 * @param jewishMonth
	 * @param jewishDayOfMonth
	 * @param inIsrael
	 */
	public NachCalendar(int jewishYear, int jewishMonth, int jewishDayOfMonth,
			boolean inIsrael) {
		super(jewishYear, jewishMonth, jewishDayOfMonth, inIsrael);
		// TODO Auto-generated constructor stub
	}
    public NachCalendar(NachCalendar nachCalendar)
    {
    	this(nachCalendar.getJewishYear(), nachCalendar.getJewishMonth(),nachCalendar.getJewishDayOfMonth(),nachCalendar.getInIsrael());
	}

	public boolean isYomChol()
    {
    	if (getDayOfWeek() == 7) return false; //Shabbath
    	int yomtov = getYomTovIndex();
    	switch (yomtov)
    	{
    	case (-1): return true;
    	case (JewishCalendar.PESACH):
    	case (JewishCalendar.SHAVUOS):
    	case (JewishCalendar.SUCCOS):
    	case (JewishCalendar.ROSH_HASHANA):
    	case (JewishCalendar.YOM_KIPPUR):
    	case (JewishCalendar.PURIM):
    	case (JewishCalendar.YOM_HAATZMAUT):
    	case (JewishCalendar.TISHA_BEAV):return false;
    	default: return true; 	
    	}
    }
    	public int getYemeCholNum()
    	{
    		NachCalendar temp = new NachCalendar(this);
    		temp.setJewishDayOfMonth(1);
    		temp.setJewishMonth(1);
    		int year = temp.getJewishYear();
    		int sum = 0;
    		while (temp.getJewishYear() == year)
    		{
    			if (temp.isYomChol()) sum++;
    			System.out.println(temp.toString()+" is calculating;");
    		temp.forward(); 		
    		}
    		return sum;
    	} 
}