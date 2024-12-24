package com.rdb.core.controller.ceftlcinterface.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  // Format - yyyy_MM_dd_HH_mm_ss (2019-02-15 14:34:15)
  public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
  // Format - ddMMyyyy (19122020)
  public static String ddMMyyyy = "ddMMyyyy";
  // Format - HHmmss (132200)
  public static String HHmmss = "mmssSS";

  public static final String TRANSAMISSION_DATE_TIME_FORMAT = "MMddHHmmss";

  public static final String DATE_MM_DD_FORMAT = "MMdd";

  /**
   * Returns time in HHmmss Format
   * Current Time : 15:09:57 ----> Returns 150957
   * @return Formated Time
   */
  public static String getTime() {

    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    DateFormat df = new SimpleDateFormat("HHmmss");
    String time = df.format(date);

    return time;
  }

  /**
   * Returns Date for given Format
   * @param date Current Date
   * @param dateFormat Date Format
   * @return Formtted Date
   */
  public static String format(Date date, String dateFormat) {

    DateFormat df = new SimpleDateFormat(dateFormat);
    return df.format(date);
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
                        .atZone( ZoneId.systemDefault())
                        .toLocalDate();
  }

  public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli( dateToConvert.getTime())
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate();
  }

  public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
  }

  /**
   * The method convert the local date time to date
   * @param dateToConvert
   * @return
   */
  public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {

    return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date convertToDateViaInstant(LocalDate dateToConvert) {

    Date date = Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return date;
  }

  public static Date convertToDateViaInstant(LocalTime dateToConvert) {

    LocalDate now = LocalDate.now();
    Instant instant = dateToConvert.atDate(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth())).
                                                                             atZone(ZoneId.systemDefault()).toInstant();
    Date time = Date.from(instant);
    return time;
  }

  /**
   * The method convert the date to local date time
   * @param dateToConvert
   * @return
   */
  public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {

    return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
  }

  /**
   * The method convert the date to local date time
   * @param dateToConvert
   * @return
   */
  public static LocalDate convertToLocalDate(Date dateToConvert) {

    return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
  }

  /**
   * The method convert the date to local date time
   * @param dateToConvert
   * @return
   */
  public static LocalTime convertToLocalTime(Date dateToConvert) {

    return LocalTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
  }

  /**
   * this method will return string type of date with given format for given date object
   *
   * @param format date format
   * @param date   date to be converted to string
   * @return string formatted date
   */
  public static String getDate(final String format, final Date date) {

    DateFormat df = new SimpleDateFormat(format);
    return df.format(date);
  }

  /**
   * Get Time for required format
   * @param format Time Format
   * @return Time in String
   */
  public static String getTime(String format) {
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    DateFormat df = new SimpleDateFormat(format);
    return df.format(date);
  }

  public static String format(LocalDateTime dateTime, String pattern) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    String formatDateTime = dateTime.format(formatter);
    return formatDateTime;
  }

  public static String format(LocalDate date, String pattern) {

    if (date == null) {

      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    String formatDateTime = date.format(formatter);
    return formatDateTime;
  }
}
