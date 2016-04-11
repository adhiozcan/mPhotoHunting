package com.wisnu.photohunting.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Wahyu Adi S on 21/12/2015.
 */
public class DateTime
{
    private static Locale locale = Locale.getDefault();
    private static SimpleDateFormat ft;

    public static String monthConversion(int monthNumber)
    {
        String monthInString = null;

        switch (monthNumber)
        {
            case 0:
                monthInString = "Januari";
                break;
            case 1:
                monthInString = "Februari";
                break;
            case 2:
                monthInString = "Maret";
                break;
            case 3:
                monthInString = "April";
                break;
            case 4:
                monthInString = "Mei";
                break;
            case 5:
                monthInString = "Juni";
                break;
            case 6:
                monthInString = "Juli";
                break;
            case 7:
                monthInString = "Agustus";
                break;
            case 8:
                monthInString = "September";
                break;
            case 9:
                monthInString = "Oktober";
                break;
            case 10:
                monthInString = "November";
                break;
            case 11:
                monthInString = "Desember";
                break;
        }

        return monthInString;
    }

    public static String dateFromString(String date)
    {
        Date stringAsDate = new Date(date);
        ft = new SimpleDateFormat("dd MMMM yyyy", locale);
        return ft.format(stringAsDate);
    }
}
