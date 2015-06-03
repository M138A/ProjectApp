package info.androidhive.materialnavbar;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mark on 3-6-15.
 */
public class AndroidDate {
    private Date dateObject = null;
    private String dayNumber = null;
    private String monthNumber = null;
    private String yearNumber = null;
    private String dayName = null;

    public String getDayName() {
        return dayName;
    }

    public String getYearNumber() {
        return yearNumber;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public String getMonthNumber() {
        return monthNumber;
    }

    public AndroidDate()

    {
        dateObject = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat yearFormat = new SimpleDateFormat("y");
        DateFormat dayNameFormat = new SimpleDateFormat("E");

        dayNumber = dateFormat.format(dateObject);
        monthNumber = monthFormat.format(dateObject);
        yearNumber = yearFormat.format(dateObject);
        dayName = dayNameFormat.format(dateObject);
    }
}