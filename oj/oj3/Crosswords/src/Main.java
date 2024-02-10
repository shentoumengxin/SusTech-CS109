import java.util.Scanner;
public class Main {
    static int n;
    public static int find_left(String map,String target,int x,int y) {
        int lenth=target.length();
        int fla=1;
        if(y-lenth+1>=0){
            for (int i = 0; i < lenth; i++) {
                if(map.charAt(y-i)!=target.charAt(i)){
                    fla=0;
                    break;
                }
            }
            if(fla==1){
                System.out.println(x+" "+y+" "+0);
                return 1;
            }else{
                return -1;
            }
        }
        return -1;
    }
    public static int find_right(String map,String target,int x,int y) {
        int lenth=target.length();
        int fla=1;
        if(y+lenth<=n){
            for (int i = 0; i < lenth; i++) {
                if(map.charAt(y+i)!=target.charAt(i)){
                    fla=0;
                    break;
                }
            }
            if(fla==1){
                System.out.println(x+" "+y+" "+1);
                return 1;
            }else{
                return -1;
            }
        }
        return -1;
    }
    public static int find_up(String[] map,String target,int x,int y) {
        int lenth=target.length();
        int fla=1;
        if(x+1-lenth>=0){
            for (int i = 0; i < lenth; i++) {
                if(map[x-i].charAt(y)!=target.charAt(i)){
                    fla=0;
                    break;
                }
            }
            if(fla==1){
                System.out.println(x+" "+y+" "+2);
                return 1;
            }else {
                return  -1;
            }
        }
        return -1;
    }
    public static int find_down(String[] map,String target,int x,int y) {
        int lenth=target.length();
        int fla=1;
        if(x+lenth<=n){
            for (int i = 0; i < lenth; i++) {
                if(map[x+i].charAt(y)!=target.charAt(i)){
                    fla=0;
                    break;
                }
            }
            if(fla==1){
                System.out.println(x+" "+y+" "+3);
                return 1;
            }else {
                return -1;
            }

        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int m=sc.nextInt();
        sc.nextLine();
        String[] map =new String[1000];
        String[]target=new String[1000];
        for (int i = 0; i < n; i++) {
            map[i]= sc.nextLine();
        }
        for (int i = 0; i <m ; i++) {
            target[i]= sc.nextLine();
        }
        for(int mm=0;mm<m;mm++){
            int fla=-1;
            fla:for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n; j++) {
                    if(fla==-1)fla=find_left(map[i],target[mm],i,j);
                    if(fla==-1)fla=find_right(map[i],target[mm],i,j);
                    if(fla==-1)fla=find_up(map,target[mm],i,j );
                    if(fla==-1)fla=find_down(map,target[mm],i,j );
                    if(fla==1)break fla;

                }

            }
            if(fla==-1) System.out.println(-1);
        }
    }
}