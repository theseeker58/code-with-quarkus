package org.acme.adapters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DateAdapter.class);
    private static final String ISO_PATTERN = "yyyy-MM-dd";


    private DateAdapter() {
    }

    public static Date parseBasicIsoDate(String dateStr) {
        return doParseDate(dateStr, ISO_PATTERN);
    }

    public static String printBasicIsoDate(Date date) {
        DateFormat df = new SimpleDateFormat(ISO_PATTERN);
        return df.format(date);
    }

    private static Date doParseDate(String dateStr, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Parse Exception trying to parse {}", dateStr, e);
        }
        return date;
    }

}
