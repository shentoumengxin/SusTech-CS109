import java.util.Scanner;

public class work3 {
    public static void main(String[] args) {
        int[]a=new int[200];
        int c=1;
        int []fla=new int[1005];
        int[]ans=new int[1005];
        int max=0;
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]= sc.nextInt();
        }
        for(int i=1;i<n&&c==1;i++){
            max=Math.max(max,a[i]);
            for(int j=i+1;j<=n;j++){
                if(a[j]<=max){
                    break;
                }
                if(i>=2&&j==n){
                    c=i;
                    break;
                }

            }
        }
        int j=1;
        for(int i=1;i<=c;i++){    //quchong
          if(fla[a[i]]==0){
              ans[j]=a[i];
              fla[a[i]]=1;
              j++;
          }
        }
        for(int i=1;i<=j-1;i++){   //maopaopaixu
            for(int m=1;m<=j-1;m++){
                if(ans[i]<ans[m]){
                    int p=ans[i];
                    ans[i]=ans[m];
                    ans[m]=p;
                }
            }
        }
        for (int i = 1; i <= j-1; i++) {
            System.out.print(ans[i]+" ");
        }

    }
}

