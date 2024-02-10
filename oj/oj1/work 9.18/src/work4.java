import java.util.Scanner;

public class work4 {
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);
        int opr=input.nextInt();
        int bond_x1=input.nextInt();
        int bond_x2=input.nextInt();
        int bond_y1=input.nextInt();
        int bond_y2=input.nextInt();
        int x=input.nextInt();
        int y=input.nextInt();
        char move;
        while(opr!=0){
            opr--;
            move=input.next().charAt(0);
            switch(move){
                case 'w':
                    if(y-1>=bond_y1&&y-1<=bond_y2){
                        y--;


                    }
                    break;
                case 'a':
                    if(x-1>=bond_x1&&x-1<=bond_x2){
                        x--;
                    }
                    break;
                case 's':
                    if(y+1>=bond_y1&&y+1<=bond_y2){

                        y++;
                    }
                    break;
                case 'd':
                    if((x+1>=bond_x1&&x+1<=bond_x2)){
                        x++;
                    }
                    break;
                case 'q':
                    if((x-1>=bond_x1&&x-1<=bond_x2)&&(y-1>=bond_y1&&y-1<=bond_y2)){
                        y--;
                        x--;
                    }
                    break;
                case 'e':
                    if((x+1>=bond_x1&&x+1<=bond_x2)&&(y-1>=bond_y1&&y-1<=bond_y2)){
                        y--;
                        x++;
                    }
                    break;
                case'z':
                    if((x-1>=bond_x1&&x-1<=bond_x2)&&(y+1>=bond_y1&&y+1<=bond_y2)){
                        y++;
                        x--;
                    }
                    break;
                case 'c':
                    if((x+1>=bond_x1&&x+1<=bond_x2)&&(y+1>=bond_y1&&y+1<=bond_y2)){
                        y++;
                        x++;
                    }
                    break;


            }


        }
        System.out.print("("+x+","+y+")");


    }
}
