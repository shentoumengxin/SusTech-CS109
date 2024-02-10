import java.util.Scanner;
public class work3 {
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);
        int n= input.nextInt();
        int i,j;
        for(i=1;i<=n;i++){
            System.out.print("+");
            for(j=1;j<=n;j++){
                System.out.print("-+");
            }
            System.out.println();
            System.out.print("|");
            for(j=1;j<=n;j++){
                System.out.print("*|");
            }
            System.out.println();
        }
        System.out.print("+");
        for(j=1;j<=n;j++){
            System.out.print("-+");
        }

    }
}
