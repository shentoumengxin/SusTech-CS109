import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StringBuilder base_String =new StringBuilder(sc.nextLine());
        int n=sc.nextInt();
        sc.nextLine();
        while(n--!=0){
            char operation =sc.next().charAt(0);
            if(operation=='I'){
                String str=sc.next();
                int position=sc.nextInt();
                base_String.insert(position,str);
                System.out.println(base_String);
            }
            if(operation=='D'){
                String input= sc.nextLine();
                String[]a=input.split(" ");
                int len=a.length;
                if(len==2){
                    base_String.delete(Integer.parseInt(a[1]),Integer.parseInt(a[1])+1);
                    System.out.println(base_String);
                }
                if(len==3){
                    base_String.delete(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                    System.out.println(base_String);
                }
            }
            if(operation=='U'){
                String str=sc.next();
                int fla=0;
                if(sc.hasNextInt()){
                    int pos=sc.nextInt();
                    base_String.delete(pos,pos+1);
                    base_String.insert(pos,str);
                    System.out.println(base_String);
                    //sc.nextLine();
                    fla=1;

                }
                if(sc.hasNext()&&fla==0){
                    String update= sc.next();
                    while(base_String.indexOf(update)!=-1){
                        base_String=new StringBuilder(base_String.toString().replace(update,str));
                    }
                    System.out.println(base_String);
                }
            }

        }

    }
}
