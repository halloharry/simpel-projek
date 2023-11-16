package com.photo.util.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    static void findDifference(String startDate, String endDate){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss"
        );

        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date date1 = simpleDateFormat.parse(startDate);
            Date date2 = simpleDateFormat.parse(endDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
