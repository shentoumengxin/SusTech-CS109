
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int m= sc.nextInt();
        int[][]map=new int[105][105];
        int sum=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j]= sc.nextInt();
            }

        }
        while(m--!=0){
            military mi=new military(n);
            int max=-1000000;
            int x=0,y=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                        mi.setPos_x(i);
                        mi.setPos_y(j);
                        if(mi.sum(map)>max){
                            x=i;
                            y=j;
                        }
                        max=Math.max(mi.sum(map),max);

                }

            }
            sum+=max;
            mi.occupy(x,y,map);


        }
        System.out.println(sum);




    }

}
 class military{
    private int pos_x;
    private int pos_y;
    private final int n;



     public void setPos_x(int pos_x) {
         this.pos_x = pos_x;
     }

     public void setPos_y(int pos_y) {
         this.pos_y = pos_y;
     }

    public military(int n) {
        this.n=n;
    }
    private boolean judge(int x,int y){
         return x <n && x >= 0 && y < n && y >= 0;
    }
    public void occupy(int x,int y,int[][]map){
        map[x][y]=0;
        if(judge(x-1,y-1)){
            map[x-1][y-1]=0;
        }
        if(judge(x,y-1)){
            map[x][y-1]=0;
        }
        if(judge(x+1,y-1)){
            map[x+1][y-1]=0;
        }
        if(judge(x-1,y)){
            map[x-1][y]=0;
        }
        if(judge(x+1,y)){
            map[x+1][y]=0;
        }
        if(judge(x-1,y+1)){
            map[x-1][y+1]=0;
        }
        if(judge(x,y+1)){
            map[x][y+1]=0;
        }
        if(judge(x+1,y+1)){
            map[x+1][y+1]=0;
        }
    }

     public int sum(int[][]map){
         int summary=0;
         summary+=map[pos_x][pos_y];
         if(judge(pos_x-1,pos_y-1)){
             summary+=map[pos_x-1][pos_y-1];
         }
         if(judge(pos_x,pos_y-1)){
             summary+=map[pos_x][pos_y-1];
         }
         if(judge(pos_x+1,pos_y-1)){
             summary+=map[pos_x+1][pos_y-1];
         }
         if(judge(pos_x-1,pos_y)){
             summary+=map[pos_x-1][pos_y];
         }
         if(judge(pos_x+1,pos_y)){
             summary+=map[pos_x+1][pos_y];
         }
         if(judge(pos_x-1,pos_y+1)){
             summary+=map[pos_x-1][pos_y+1];
         }
         if(judge(pos_x,pos_y+1)){
             summary+=map[pos_x][pos_y+1];
         }
         if(judge(pos_x+1,pos_y+1)){
             summary+=map[pos_x+1][pos_y+1];
         }
         return summary;
     }

}