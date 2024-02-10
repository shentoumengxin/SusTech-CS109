import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class MyDate {
    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int day;
    private int month;
    private int flag=0;
    private int year;
    private int capacity;
    private String address;

    public MyDate(int year,int month,int day){
        this.year=year;
        this.day=day;
        this.month=month;
    }
    public void addDays(int days){
        int []month_Day=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if((year%4==0&&year%100!=0)||year%400==0)month_Day[1]=29;
        int left_days=days;
        int left_month=month-1;
        while(day+left_days>month_Day[left_month]){
            left_days=left_days-(month_Day[left_month]-day+1);
            day=1;
            if(left_month+1>11){  //update the year if needed
                this.year++;
                if((year%4==0&&year%100!=0)||year%400==0)month_Day[1]=29;
                if(year%4!=0)month_Day[1]=28;
                left_month=0;
                continue;
            }
            left_month++;

        }
        this.day=day+left_days;
        this.month=left_month+1;
    }
    public String toString(){
        if(month<10&&day<10)return String.format("%d-0%d-0%d",year,month,day);
        if(month<10&&day>10)return String.format("%d-0%d-%d",year,month,day);
        if(month>10&&day<10)return String.format("%d-%d-0%d",year,month,day);
        if(month>10&&day>10)return String.format("%d-%d-%d",year,month,day);
        return null;
    }


    public static int difference(MyDate date1,MyDate date2){
        LocalDate date11 = LocalDate.of(date1.year, date1.month, date1.day);
        LocalDate date22 = LocalDate.of(date2.year, date2.month, date2.day);

        int daysBetween = (int)ChronoUnit.DAYS.between(date11, date22);
        return -daysBetween;
    }

}
