import java.util.Scanner;

public class work2 {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        String name=input.next();
        int num=input.nextInt();
        int h=input.nextInt();
        int n=input.nextInt();
        int[]grade=new int[1024];
        int[]credit=new int [1024];
        double[]gpa=new double[1024];
        int i;
        double GPA=0,sum=0;
        for(i=1;i<=n;i++){
            credit[i]=input.nextInt();
            grade[i]=input.nextInt();
            if(grade[i]==100){
                gpa[i]=4.00;
            }
            else if(grade[i]>=90)gpa[i]=3.90;
                else if(grade[i]>=80)gpa[i]=3.80;
                    else if(grade[i]>=70)gpa[i]=3.70;
                        else if(grade[i]>=60)gpa[i]=3.60;
                            else gpa[i]=0.00;
            GPA+=credit[i]*gpa[i];
            sum+=credit[i];
        }  //system in&GPA
        GPA=GPA/sum;
        int male=num%100,clas=((num%10000)-male)/100;
        if(male<50){
            System.out.print(name+" is a "+"boy, he ");
        }else{
            System.out.print(name+" is a "+"girl, she ");
        }//case 1

        if(clas<=5){
            System.out.print("is in "+"ZHIXIN college, ");
        }
        else if(clas<=10){
            System.out.print("is in "+"SHULI college, ");
        }
        else if(clas<=15){
            System.out.print("is in "+"ZHICHENG college, ");
        }
        else if(clas<=20){
            System.out.print("is in "+"SHUDE college, ");
        }
        else if(clas<=25){
            System.out.print("is in "+"ZHIREN college, ");
        }
        else if(clas<=30){
            System.out.print("is in "+"SHUREN college, ");
        }  //class
        if(male<50){
            System.out.print("his ");
        }else{
            System.out.print("her ");
        }
        int height=(h%100-h%10)/10;
        System.out.print("height is in 1"+height+"0-1"+height+"9. "); //tall num
        if(male<50){
            System.out.print("His ");
        }else{
            System.out.print("Her ");
        }
        System.out.print("SID is "+num);
        if(male<50){
            System.out.print(". His ");
        }else{
            System.out.print(". Her ");
        }
        System.out.print("average GPA is "+String.format("%.2f",GPA)+".");
    }
}
