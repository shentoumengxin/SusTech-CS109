import java.util.Scanner;
public class Main {
    static int fla = 0;
    static int count = 0;
    static int[] symbol = new int[]{0, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();//du入换行符
        while (n-- != 0) {
            fla=0;
            count=0;
            int[] position = new int[3];
            String e=sc.nextLine();
            StringBuilder equation = new StringBuilder(e.replaceAll(" ",""));

            position[0] = find(equation);
            if(position[0]!=0)equation.replace(position[0], position[0]+1, "P");
            position[1] = find(equation );  //search from the formal symbol
            if(position[1]!=0)equation.replace(position[1], position[1]+1, "P"); //make signal
            position[2] = find(equation);
            if(position[2]!=0)equation.replace(position[2], position[2]+1, "P");
            double[] number = trans(equation.toString());
            double ans=caculate(number);
            System.out.printf("%.3f\n",ans);
        }


    }

    public static int find(StringBuilder equation) {  //check the symbol and record its position and (*1,/2,+3,-4)
        int mid = 1000;
        int x = equation.indexOf("*", 0);
        if (x != -1) {
            mid = Math.min(x, mid);
            if (mid == x) symbol[fla] = 1;
        }
        x = equation.indexOf("/", 0);
        if (x != -1) {
            mid = Math.min(x, mid);
            if (mid == x) symbol[fla] = 2;
        }
        x = equation.indexOf("+", 0);
        if (x != -1) {
            mid = Math.min(x, mid);
            if (mid == x) symbol[fla] = 3;
        }
        x = equation.indexOf("-", 0);
        if (x != -1) {
            mid = Math.min(x, mid);
            if (mid == x) symbol[fla] = 4;
        }

        if(mid!=1000)fla++;
        if(mid==1000)mid=0;
        return mid;
    }

    public static double[] trans(String equation) {
        double[] number = new double[4];
        String[] num = equation.split("P");
        for (String i : num) {
            number[count++] = Double.parseDouble(i);
        }
        return number;
    }

    public static double caculate(double[] number) {
        double sum = 0;
        int[] plus = new int[]{0, 0, 0};
        int[] position = new int[]{0, 0, 0, 0};
        int[] flag=new int[]{0,0,0,0};
        if(count==3){
            if(symbol[1]==1&&symbol[0]==1)return number[0] * number[1]*number[2];
            if(symbol[1]==2&&symbol[0]==1)return number[0] * number[ 1]/number[2];
            if(symbol[1]==2&&symbol[0]==2)return number[0] / number[1]/number[2];
            if(symbol[1]==1&&symbol[0]==2)return number[0] / number[ 1]*number[2];
            if(symbol[1]==3&&symbol[0]==3)return number[0] + number[ 1]+number[2];
            if(symbol[1]==4&&symbol[0]==3)return number[0] + number[1]-number[2];
            if(symbol[1]==3&&symbol[0]==4)return number[0] - number[1]+number[2];
            if(symbol[1]==4&&symbol[0]==4)return number[0] - number[ 1]-number[2];
            if(symbol[0]==1&&symbol[1]==3)return number[0] * number[1]+number[2];
            if(symbol[0]==3&&symbol[1]==1)return number[0] + number[ 1]*number[2];
            if(symbol[0]==2&&symbol[1]==3)return number[0] / number[ 1]+number[2];
            if(symbol[0]==3&&symbol[1]==2)return number[0] + number[ 1]/number[2];
            if(symbol[0]==2&&symbol[1]==4)return number[0] / number[ 1]-number[2];
            if(symbol[0]==4&&symbol[1]==2)return number[0] - number[1]/number[2];
            if(symbol[0]==1&&symbol[1]==4)return number[0] * number[ 1]-number[2];
            if(symbol[0]==4&&symbol[1]==1)return number[0] - number[ 1]*number[2];

        }
        if(count==2){
            if(symbol[0]==1)return number[0] * number[1];
            if(symbol[0]==2)return number[0] / number[1];
            if(symbol[0]==3)return number[0] + number[1];
            if(symbol[0]==4)return number[0] - number[ 1];
        }

    return 0;
    }
}
