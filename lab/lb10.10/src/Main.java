import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        double[]mylist1=new double[8];
        double[]mylist2=new double[8];
        int i=0;
        while(n!=i){
            mylist1[i]=input.nextDouble();
            i++;

        }
        i=1;
        for(double e:mylist1){
            mylist2[i]=e;
            if(i+1==n){
                mylist1[0]=e;
                break;
            }
            i++;

        }
        System.out.println(Arrays.toString(mylist1));
        System.out.println(Arrays.toString(mylist2));
        input.close();
    }
}