import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculater {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2023, 2, 1);
        LocalDate date2 = LocalDate.of(2023, 2, 5);

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("The number of days between " + date1 + " and " + date2 + " is: " + daysBetween);
    }
}
  /*  int dif=0;
    MyDate date11=new MyDate(date1.year,date1.month,date1.day);
    MyDate date22=new MyDate(date2.year,date2.month,date2.day);
        if (date11.year > date22.year ||(date11.year==date22.year&&date11.month>date22.month)||(date11.year==date22.year&&date11.month==date22.month&&date11.day>=date22.day)) {
                if(date11.year>date22.year+1){
                dif+=365*(date11.year-date22.year-1);
                for (int i = date22.year; i <date11.year-1 ; i++) {
        if(i%4==0)dif++;
        }
        date22.year=date11.year-1;
        }
        while(!(date11.year==date22.year&&date11.month==date22.month&&date11.day==date22.day)){
        date22.addDays(1);
        dif++;
        }

        return dif;

        }
        else{
        if(date22.year> date11.year+1){
        dif+=365*(date22.year-date11.year-1);
        for (int i = date11.year; i <date22.year-1 ; i++) {
        if(i%4==0)dif++;
        }
        date11.year=date22.year-1;
        }
        while(!(date11.year==date22.year&&date11.month==date22.month&&date11.day==date22.day)){
        date11.addDays(1);
        dif++;
        }

        return -dif;

        }*/