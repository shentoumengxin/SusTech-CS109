import java.util.Scanner;
import java.util.Random;
//private default() public
//对类封装；
class Person{
    int age;
    String sex="Man";
    public Person(){
        //无参构造用法
    }
    public Person(int age,String sex){  //有参构造用法
        this.age=age;
        this.sex=sex;
        this();  //调用无参构造
    }
    public Person(int age){
        this.age=age;
        //成员方法和构造方法均可重载
    }
    void Speak(){   //成员方法
        System.out.print(age+"  "+sex);
    }
}
public class Main {
    public static void main(String[] args) {
        //array.length=lenth;
        int[] array=new int[10];
        Random generator =new Random();
        int a=generator.nextInt(6);//[0,5]
        //foreach statement
        for(double e:array){
            System.out.print(e);
        }//print the array for(elementType identifier:array)
        //array1=array2 表示地址指向，值的改变会影响二者
        //System.out.println(Arrays.toString(array2));打印数组
        Scanner input=new Scanner(System.in);
        System.out.printf("%3d%8d",1,2);  //以x位右对齐
        //对类具象
        Person p1=new Person(18);
        Person p2=new Person(18,"male");
        //调用
        p1.sex="female";
        p1.Speak();
        p2.Speak();
        input.close();
    }
}