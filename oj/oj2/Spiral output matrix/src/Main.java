import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //long current1=System.currentTimeMillis();
        int m,n;
        Scanner sc=new Scanner(System.in);
        int M= sc.nextInt();
        int N= sc.nextInt();
        int [][]matrix=new int[M+5][N+5];
        for(int i=1;i<=M;i++){
            for(int j=1;j<=N;j++){
                matrix[i][j]= sc.nextInt();
            }
        }
        int step=M*N;
        while(step>0){

        }
        sc.close();
    }
}
