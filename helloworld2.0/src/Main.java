public class Main{
    public static void main(String[]args){
        int i,j;
        for(i=1;i<=9;i++){
            for(j=1;j<=i;j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
            }
            System.out.println("");
        }

    }
}