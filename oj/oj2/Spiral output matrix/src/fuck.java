import java.util.Scanner;
public class fuck {
    public static void main(String[] args) {
        //long current1=System.currentTimeMillis();
        int m,n;

        Scanner sc=new Scanner(System.in);
        m= sc.nextInt();
        n= sc.nextInt();
        int M,N;
        int [][]matrix=new int[m+5][n+5];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                matrix[i][j]= sc.nextInt();
            }
        }

        M=m ;
        N=2;

        System.out.print(matrix[m][1]+" ");
        long step=m*n;
        outer:
        while(step>0){
            step-=4;
            for(int i=N;i<=n-(m-M);i++){   //Nr
                if(matrix[M][i]==0){
                    break outer;
                }
                System.out.print(matrix[M][i]+" ");
                matrix[M][i]=0;

            }
            N=n-(m-M);
            M=M-1;

            for(int j=M;j>=1+n-N;j--){  //Mup
                if(matrix[j][N]==0){
                    break outer;
                }
                System.out.print(matrix[j][N]+" ");
                matrix[j][N]=0;

            }
            M=1+n-N;
            N=N-1;
            for(int i=N;i>=M;i--){   //NL
                if(matrix[M][i]==0){
                    break outer;
                }
                System.out.print(matrix[M][i]+" ");
                matrix[M][i]=0;
            }

            N=M;
            M=M+1;
            for(int j=M;j<=m-N;j++){  //Md
                if(matrix[j][N]==0){
                    break outer;
                }
                System.out.print(matrix[j][N]+" ");
                matrix[j][N]=0;
            }
            M=m-N;
            N=N+1;
        }
        sc.close();
    }
}
