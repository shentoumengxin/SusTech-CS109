public class MortgageDate {
    public static int getDifference(int date1, int date2) {
        int year1 = date1 / 100;
        int year2 = date2 / 100;
        int month1 = date1 % 100;
        int month2 = date2 % 100;
        if (year1 == year2) {
            return Math.abs(month2 - month1);
        } else if (year1 < year2) {
            return (year2 - year1) * 12 + month2 - month1;
        } else {
            return (year1 - year2) * 12 + month1 - month2;
        }
    }
    public static int getMonthsPassedDate(int startDate,int months){
        int year = startDate / 100;
        int month = startDate % 100;
        int newYear = year + months / 12;
        int newMonth = month + months % 12;
        if (newMonth > 12) {
            newYear++;
            newMonth -= 12;
        }
        return newYear * 100 + newMonth;

    }

}
