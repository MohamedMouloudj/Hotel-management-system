package controllers;

import java.util.Calendar;
class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}

public class OurDate {

   private  int year ;
   private int month;
   private int day;

   public OurDate(int day ,int month ,int  year ) {
      this.day = day;
      this.month = month;
      this.month = month ;
      this.validate();
   }
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


   void validate() throws InvalidDateException{
      if (this.year < 2024 && this.year > 2025 ) {
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

    public int compareTo(OurDate otherDate) {

        if (this.year < otherDate.year) {
            return -1; // This date is older
        } else if (this.year > otherDate.year) {
            return 1; // This date is newer
        }


        if (this.month < otherDate.month) {
            return -1; // This date is older
        } else if (this.month > otherDate.month) {
            return 1; // This date is newer
        }


        if (this.day < otherDate.day) {
            return -1;
        } else if (this.day > otherDate.day) {
            return 1;
        }

        return 0;
    }
    /*
    compare years then monthes than days
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
        return  this.day + "/" + this.year + "/" + this.month;
    }
   /* verify the input year if it is leap or not <s
   */


}