import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        long current1=System.currentTimeMillis(); //time
        /* your algorithm */
        Scanner sc=new Scanner(System.in);
        double n= sc.nextInt();
        int sum=0;
        double arg=0;
        int []a=new int[100001];
        for(int i=0;i<=n;i++){
            a[i]= sc.nextInt();
            sum+=a[i];
        }
        arg=sum/n;
        for (int i = 1; i <=n ; i++) {
            int b=sum/a[i];
        }
        long current2=System.currentTimeMillis();
        System.out.printf("your program using %.3f second",(current2-current1)/1000.0d);
    }
}
