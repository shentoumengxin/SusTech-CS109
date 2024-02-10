import java.util.Scanner;
public class work2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        double[][] gra =new double[102][3];
        int [][]fla=new int[200][200];
        for (int i = 0; i < n; i++) {
            double rec=sc.nextDouble();
            if(rec>=90){
                gra[i][1]=4.0;
            } else if (rec>=80) {
                gra[i][1]=3.0;
            } else if (rec>=70) {
                gra[i][1]=2.0;
            } else if (rec>=60) {
                gra[i][1]=1.0;
            } else {
                gra[i][1]=0.0;
            }

        }
        for (int i = 0; i <n ; i++) {
            gra[i][2]= sc.nextDouble();
        }
        double ans=sc.nextDouble();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                double gpa=(gra[i][1]*gra[i][2]+gra[j][1]*gra[j][2])/(gra[i][2]+gra[j][2]);
                if(Math.abs(gpa-ans)<0.01&&fla[j][i]==0&&i!=j){
                    fla[i][j]=1;
                    System.out.println(i+" "+j);
                }
            }
        }
        sc.close();
    }
}
