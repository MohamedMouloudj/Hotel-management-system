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

   boolean  isLeapYear() {
      return (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0);
   }
    /* verify the input year if it is leap or not <s
    /*to treat the all cases
     */

   public int getDay(){
      return this.day;
   }
   public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }

    public void setDay(int day) throws  InvalidDateException{
        if (day < 1 || day > 31) {
            throw new InvalidDateException("day is invalid");
        }
        this.day = day;
    }

    public void setMonth(int month) throws InvalidDateException {
        if (month < 1 || month > 12) {
            throw new InvalidDateException("month no valid number");
        }
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
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



    public static long getDaysBetweenDates(OurDate date1 , OurDate date2 ) throws  InvalidDateException {

        if (date1.month < 1 || date1.month > 12 || date2.month < 1 || date2.month > 12) {
            throw new InvalidDateException("Invalid date") ;
        }

        // Ensure date1 is before date2 (swap if needed)
        if (!Compare(date1 ,date2)) {
            int tempDay = date1.getDay();
            int tempMonth = date1.getMonth();
            int tempYear = date1.getYear();

            date1.setDay(date2.getDay());
            date1.setMonth(date2.getMonth());
            date1.setYear(date2.getYear());

            date2.setDay(tempDay);
            date2.setMonth(tempMonth);
            date2.setYear(tempYear);
        }

        long totalDays = 0;

        // Handle year difference (including leap years)
        for (int y = date1.year; y < date2.year; y++) {
            totalDays += (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0) ? 366 : 365;
        }

        // Handle month difference
        for (int m = date1.getMonth(); m < date2.getMonth(); m++) {
            int daysInMonth;
            switch (m) {
                case 1:  // January
                case 3:  // March
                case 5:  // May
                case 7:  // July
                case 8:  // August
                case 10: // October
                case 12: // December
                    daysInMonth = 31;
                    break;
                case 4:  // April
                case 6:  // June
                case 9:  // September
                case 11: // November
                    daysInMonth = 30;
                    break;
                case 2:  // February
                    daysInMonth = (date2.year % 4 == 0 && date2.year % 100 != 0) || (date2.year % 400 == 0) ? 29 : 28;  // Use year from date2
                    break;
                default:
                    daysInMonth = 0;
            }
            totalDays += daysInMonth;
        }

        // Handle day difference within the same month
        totalDays += date2.day - date1.day;

        return totalDays;
    }


    /**
     * Parse a date string in the format day/month/year
     * @param dateString the date string to parse
     * @return a new OurDate object
     * @throws InvalidDateException if the date string is not in the expected format
     * */
    public static OurDate parse(String dateString) throws InvalidDateException {
        String[] parts = dateString.split("/");
        if (parts.length != 3) {
            throw new InvalidDateException("Invalid date format. Expected format is day/month/year");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new OurDate(day, month, year);
    }


   public static void main(String[] args) {
       OurDate a = new OurDate();
       OurDate b = new OurDate(30,5,2024);
       try{
           System.out.println("the difference is " + OurDate.getDaysBetweenDates(a,b) );
       }catch (InvalidDateException e) {
           System.out.println(e.getMessage());
       }


   }


}