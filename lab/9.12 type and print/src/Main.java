// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        float a=(float) 1.132;
        char b='a';
        float aa=1.121f;
        String name="JAVA";
        long bb=1000000000000L;
        final int coco=5;
        boolean sb=true;
        System.out.print("a="+a+"\n");
        System.out.println("b="+b+"\n"+"  sb="+sb+"  coco="+coco);
        System.out.printf("i'm %d%s%.1f",bb,name,aa);
    }
}