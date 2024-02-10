import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.ArrayList;

public class MyCalendar {
    private int capacity;
    ArrayList<MyDate>event_date=new ArrayList<>();

    public MyCalendar(int capacity){
        this.capacity=capacity;
        event_date.add(new MyDate(-99,1,1));

    }

    public boolean addEvent(MyDate date,String eventName ) {
        MyDate datee=new MyDate(date.getYear(),date.getMonth(),date.getDay());
        int n=event_date.size();
       if (n<=capacity) {
           //address++;
           datee.setAddress(eventName);
           if(n==1){
               //datee.setAddress(n);
               event_date.add(datee);
               return true;
           }
           for(int i=1;true;i++){
                if((MyDate.difference(datee,event_date.get(i))<0)){
                    //datee.setAddress(address);
                    event_date.add(i,datee);
                    return true;
               }
               if((MyDate.difference(datee,event_date.get(i))==0)){
                   //String temp=event[event_date.get(i).getAddress()];
                   String temp=event_date.get(i).getAddress();
                   //String temp2=event[address];
                   String temp2=datee.getAddress();
                   if(temp.compareTo(temp2)<0){
                      // datee.setAddress(address);
                       event_date.add(i+1,datee);
                       return true;
                   }
                   else{
                      // datee.setAddress(address);
                       event_date.add(i,datee);
                       return true;
                   }

               }

               if(MyDate.difference(datee,event_date.get(i))>0&&(i==n-1||MyDate.difference(datee,event_date.get(i+1))<0)){
                  //datee.setAddress(address);
                   event_date.add(i+1,datee);
                   return true;
               }

            }


       }else{
            return false;
        }
    }
    public String finishNextEvent() {

       if (event_date.size() == 1) return "NONE";
        String result=event_date.get(1).getAddress();
        event_date.remove(1);
        return result;

    }

}
