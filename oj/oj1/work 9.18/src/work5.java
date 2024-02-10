import java.util.Scanner;

public class work5 {
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);
        int m=input.nextInt();
        int n=input.nextInt();
        int sum=0;
        int fac=2;
        int i,j,num=0;
        for(i=m;i<=n;i++){
            fac=2;
            for(j=2;j<i;j++){
                if(i%j==0)fac++;
                if(fac==5){
                    break;
                }
            }
            if(fac==4){
                sum+=i;
            }
        }
        System.out.print(sum);
    }
}
