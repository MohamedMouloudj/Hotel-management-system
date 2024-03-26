package controllers;

import java.util.Calendar;
import java.time.LocalDate;
class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}

public class OurDate {
   private int year ;
   private int month;
   private int day;
   final int  currentYear = 2024;
   /**
    *
    * @param day the day of the month
    * @param month the month of the year
    * @param year the year
    * */
   public OurDate(int day ,int month ,int  year ) {
      this.day = day;
      this.month = month;
      this.year = year;
       try {
           this.validate();
       }catch (InvalidDateException e) {
           System.out.println("error : " + e.getMessage());
       }
   }
   /**
    * if no date is provided then the current date is used (Calendar class is used to get the current date)
    * */
   public OurDate(){
     this.MakeTodaydate();
   }

       void MakeTodaydate(){

           LocalDate today = LocalDate.now();
           this.year = today.getYear();
           this.month = today.getMonthValue();
           this.day = today.getDayOfMonth();

       }

   /*
       System.currentTimeMillis(); to get the current date in milleseconds
       Calendar
       .setTimeInMillis(currentTimeMillis) =>
        .get(Calendar.YEAR) =>
       .get(Calendar.MONTH) + 1 => // Adjust for 0-based month index
       .get(Calendar.DAY_OF_MONTH) =>

   */


    void validate() throws InvalidDateException{
        if (this.year < currentYear ) {
            throw new InvalidDateException("Invalid year : year  must be between 2024 and 2025 ");
        }

        if (this.month < 1 || this.month > 12) {
            throw new InvalidDateException("Invalid day: Day must be between 1 and  12 ");
        }

        int  daysInMonth = this.getDaysInMonth();
        if (this.day < 1 || this.day > daysInMonth) {
            throw new InvalidDateException("Invalid day: Day must be between 1 and " + daysInMonth + " for this month.");
        }
    }
   /*
   validate the input date , year and day and month should be in specified ranges
   consider leap years , if leap year or not then verify  the month if it is february
   if it is check the day (28, 29)

    */

   int getDaysInMonth() {
    int[] daysInMonthLookup = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      if (this.isLeapYear() && this.month == 2) {
         return 29; // February has 29 days in a leap year
      } else {
         return daysInMonthLookup[this.month - 1];
      }
   }

   boolean isLeapYear() {
      return (this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0;
   }
    /* verify the input year if it is leap or not <s
    /*to treat the all cases
     */

   int getDay(){
      return this.day;
   }
   int getMonth(){
        return this.month;
    }
    int getYear(){
        return this.year;
    }
    String tostring(){
        return  this.day + "/" + this.month + "/" + this.year;
    }

    static  boolean Compare(OurDate date1 , OurDate date2){

        // Compare years
        if (date1.year < date2.year) {
            return true;
        } else if (date1.year > date2.year) {
            return false;
        }

        // Years are equal, compare months
        if (date1.month < date2.month) {
            return true;
        } else if (date1.month > date2.month) {
            return false;
        }


        return date1.day < date2.day;
    }


   public static void main(String[] args) {
       OurDate a = new OurDate();
       System.out.println(a.tostring());

   }


}