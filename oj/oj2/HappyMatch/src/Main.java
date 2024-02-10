import java.net.SocketOption;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        char[][] map = new char[310][310];
        for (int i = 1; i <= M; i++) {   //x
            for (int j = 1; j <= N; j++) {  //y
                map[i][j] = sc.next().charAt(0);
            }
        }
        int flai = 0;  //fla=1:3 fla=2:4 fla=3:5
        int flaj = 0;  //fla=1:3 fla=2:4
        int count=0;
        for (int i = 1; i <= M; i++) {   //x
            for (int j = 1; j <= N; j++) {  //y
                flai=0;
                flaj=0;
                if (map[i][j] == '0') continue;
                if (map[i + 1][j] == map[i][j] && map[i][j] == map[i + 2][j]) {
                    flai = 1;
                    if(map[i + 3][j] == map[i][j]){flai=2;}
                    if(map[i+4][j]==map[i][j]){flai=3;}
                }
                if (map[i][j + 1] == map[i][j] && map[i][j] == map[i][j + 2]) {
                    flaj = 1;
                    if(map[i ][j+ 3] == map[i][j]){flaj=2;}
                    if(map[i][j+4]==map[i][j]){flaj=3;}
                }
                if (flaj != 0 && flai == 0) {
                    map[i][j+1] = '0';
                    map[i ][j+ 2] = '0';
                    if(map[i][j+3]==map[i][j]){
                        map[i][j+3]='0';
                        if(map[i][j+4]==map[i][j])map[i][j+4]='0';
                    }
                    if (map[i + 1][j + 1] == map[i][j] && map[i + 2][j + 1] == map[i][j]) {  //dingzi JJ
                        map[i + 1][j + 1] = '0';
                        map[i + 2][j + 1] = '0';
                        if (j - 1 > 0 && map[i][j - 1] == map[i][j]) {
                            map[i][j - 1] = '0';
                        }
                        if (map[i][j + 3] == map[i][j]) {
                            map [i][j + 3] = '0';
                        }
                    }
                    if (map[i + 2][j + 1] == map[i][j] && map[i + 2][j + 2] == map[i][j]) {  //L down
                        map[i + 2][j + 1] = '0';
                        map[i + 2][j + 2] = '0';
                    }
                    map[i][j] = '0';
                }
                if (flai != 0 && flaj == 0) {
                    map[i + 1][j] = '0';
                    map[i + 2][j] = '0';
                    if(map[i+3][j]==map[i][j]){
                        map[i+3][j]='0';
                        if(map[i+4][j]==map[i][j])map[i+4][j]='0';
                    }
                    if (j - 1 > 0 && map[i + 2][j - 1] == map[i][j] && map[i + 2][j + 1] == map[i][j]) {  //dingzi1
                        map[i + 2][j - 1] = '0';
                        map[i + 2][j + 1] = '0';
                        if (j - 2 > 0 && map[i + 2][j - 2] == map[i][j]) {
                            map[i + 2][j - 2] = '0';
                        }
                        if (map[i + 2][j + 2] == map[i][j]) {
                            map[i + 2][j + 2] = '0';
                        }
                    }
                    if (map[i + 1][j + 1] == map[i][j] && map[i + 1][j + 2] == map[i][j]) {  //ding2
                        map[i + 1][j + 1] = '0';
                        map[i + 1][j + 2] = '0';
                        if (i - 1 > 0 && map[i - 1][j] == map[i][j]) {
                            map[i - 1][j] = '0';
                        }
                        if (map[i + 3][j] == map[i][j]) {
                            map[i + 3][j] = '0';
                        }
                    }
                    if (j - 2 > 0 && map[i + 1][j - 1] == map[i][j] && map[i + 1][j - 2] == map[i][j]) {  //ding3
                        map[i + 1][j - 1] = '0';
                        map[i + 1][j - 2] = '0';
                        if (i - 1 > 0 && map[i - 1][j] == map[i][j]) {
                            map[i - 1][j] = '0';
                        }
                        if (map[i + 3][j] == map[i][j]) {
                            map[i + 3][j] = '0';
                        }
                    }
                    if (map[i + 2][j + 1] == map[i][j] && map[i + 2][j + 2] == map[i][j]) {  //L up
                        map[i + 2][j + 1] = '0';
                        map[i + 2][j + 2] = '0';
                    }
                    if (j - 2 > 0 && map[i + 2][j - 1] == map[i][j] && map[i + 2][j - 2] == map[i][j]) { //Lleft
                        map[i + 2][j - 1] = '0';
                        map[i + 2][j - 2] = '0';
                    }
                    map[i][j] = '0';
                }
                if (flai == 1 && flaj == 1) {   //L right
                    map[i][j] = '0';
                    map[i + 1][j] = '0';
                    map[i + 2][j] = '0';
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                }

            }
        }//xiaochu

        for(int j=1;j<=N;j++){  //drop
            char []ans=new char[400];
            for(int i=1;i<=M;i++) {
                if(map[i][j]!='0') {
                    count++;
                    ans[count]=map[i][j];
                }
            }

            for(int i=1,ii=1;i<=M;i++){
                if(i<=(N-count)){
                    map[i][j]='0';
                }else{
                    map[i][j]=ans[ii];
                    ii++;
                }
            }
            count=0;
        }
        for(int i=1;i<=M;i++){   //x
            for(int j=1;j<=N;j++){  //y
                System.out.printf("%c ",map[i][j]);
            }
            System.out.println();
        }


        sc.close();
    }
}

