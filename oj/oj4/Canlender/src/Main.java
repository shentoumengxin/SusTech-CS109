public class Main {
    public static void main(String[] args) {
        MyDate date1 = new MyDate(2023,1,30);
        MyDate date2 = new MyDate(2023,3,1);
        MyDate date3 = new MyDate(2023, 2, 5);

        date1.addDays(2);
        assert(date1.toString().equals("2023-02-01"));

        int diff = MyDate.difference(date1, date2);
        assert(diff == -28);

        int diff2 = MyDate.difference(date3, date1);
        assert(diff2 == 4);

        MyCalendar calendar = new MyCalendar(4);
        calendar.addEvent(date1, "meeting");
        calendar.addEvent(date2, "seminar");
        calendar.addEvent(date3, "appointment");
        calendar.addEvent(date1, "laundry");
        boolean success = calendar.addEvent(date1, "exam");
        assert(success == false); // event capacity exceeded
        assert(calendar.finishNextEvent().equals("laundry"));
        assert(calendar.finishNextEvent().equals("meeting"));
        assert(calendar.finishNextEvent().equals("appointment"));
        assert(calendar.finishNextEvent().equals("seminar"));
        assert(calendar.finishNextEvent().equals("NONE"));
    }


}



