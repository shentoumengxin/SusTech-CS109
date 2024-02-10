import java.util.Scanner;
public class work1 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        float [] len=new float[10];
        int i=1;
        for(i=1;i<=n;i++){
            len[i]=input.nextFloat();
        }
        if(n==2){
            System.out.printf("0.00");
        }
        if(n==3){

            float sum=len[1]+len[2]+len[3];
            double s=sum/2;
            double area=Math.sqrt(s*(s-len[1])*(s-len[2])*(s-len[3]));
            System.out.printf("%.2f",area);

        }
        if(n==4){
            if(len[1]==len[2]){
                System.out.printf("%.2f",len[1]*len[3]);
            }else System.out.printf("%.2f", len[1] * len[2]);
        }
    }
}