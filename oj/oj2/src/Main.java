import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n= input.nextInt();
        int[]a=new int[1000001];
        int ans=0;
        for (int i = 1; i <=n ; i++) {
            for(int j=1;j*i<=n;j++){
                a[i*j]++;
            }
        }
        for (int i = 1; i <=n ; i++) {
            if(a[i]%2==1){
                ans++;
                System.out.print(i+" ");
            }
        }
        System.out.println();
        System.out.print(ans);
    }
}