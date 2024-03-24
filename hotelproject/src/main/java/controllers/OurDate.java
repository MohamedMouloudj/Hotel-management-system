package controllers;

import java.util.Calendar;
public class OurDate {
   private int year ;
   private int month;
   private int day;

   /**
    *
    * @param day the day of the month
    * @param month the month of the year
    * @param year the year
    * */
   public OurDate(int day ,int month ,int  year ) {
      this.day = day;
      this.month = month;
      this.month = month ;
      this.validate();
   }
   /**
    * if no date is provided then the current date is used (Calendar class is used to get the current date)
    * */
   public OurDate(){
     this.MakeTodaydate();
   }

   void MakeTodaydate(){
       long currentTimeMillis = System.currentTimeMillis();

       Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(currentTimeMillis);


       this.year = calendar.get(Calendar.YEAR);
       this.month = calendar.get(Calendar.MONTH) + 1;
       this.day = calendar.get(Calendar.DAY_OF_MONTH);

   }
   /*
       System.currentTimeMillis(); to get the current date in milleseconds
       Calendar
       .setTimeInMillis(currentTimeMillis) =>
        .get(Calendar.YEAR) =>
       .get(Calendar.MONTH) + 1 => // Adjust for 0-based month index
       .get(Calendar.DAY_OF_MONTH) =>

   */


   void validate(){
      if (this.year < 1) {
         throw new Error("Invalid year: Year must be positive.");
      }
      // Check for valid month
      if (this.month < 1 || this.month > 12) {
         throw new Error("Invalid month: Month must be between 1 and 12.");
      }

    int  daysInMonth = this.getDaysInMonth();
      if (this.day < 1 || this.day > daysInMonth) {
         throw new Error("Invalid day: Day must be between 1 and "+ daysInMonth +" for this month.");
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
        return  this.day + "/" + this.year + "/" + this.month;
    }
   /* verify the input year if it is leap or not <s
   */


}