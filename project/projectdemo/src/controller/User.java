package controller;

import view.WelcomeFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class User {
    private String user_name;
    private int last_prop_left;
    private String password;
    private WelcomeFrame welcomeFrame;
    private int last_score;
    private int last_level;
    private int total_score;
    private int lastLevel;
    private  final String user_data_path;
    private final String password_data_path;
    public void registeFrame(WelcomeFrame welcomeFrame){
        this.welcomeFrame=welcomeFrame;
    }
    public User(String user_name,String password){
        this.user_name=user_name;
        this.password=password;
        this.user_data_path=System.getProperty("user.dir")+"\\data\\message\\username.txt";
        this.password_data_path=System.getProperty("user.dir")+"\\data\\message\\password.txt";
    }
    public  boolean user_find() throws IOException {
        List<String> list;
        try {
            list = Files.readAllLines(Path.of(user_data_path));
        } catch (IOException e) {
            welcomeFrame.showMessageDialog("文件读取错误");
            throw new IOException("文件读取错误");
        }
        int position=0;
       for(String s:list){
           if(s.equals(user_name)){
               if(checkPassword(position)){
                     return true;
               }
               return false;
           }
           position++;
       }

       return false;
    }
    public  boolean user_find(boolean fl) throws IOException {
        List<String> list;
        try {
            list = Files.readAllLines(Path.of(user_data_path));
        } catch (IOException e) {
            welcomeFrame.showMessageDialog("文件读取错误");
            throw new IOException("文件读取错误");
        }
        if(list.contains(user_name)){
            return true;
        }
        int position=0;
        return false;
    }
    public void userDataSave(){
        File file=new File(System.getProperty("user.dir")+"\\data\\message\\"+user_name+".txt");
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write(  "\n"); //total score
            fw.write( last_level+ "\n");
            fw.write( last_prop_left+ "\n");
            fw.close();
        }
        catch(IOException e){
            welcomeFrame.showMessageDialog("Error 文件读取错误");
            throw new RuntimeException(e);
        }
    }
    public void userAchievementSave(){
        File file=new File(System.getProperty("user.dir")+"\\data\\message\\"+"achievement.txt");
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write( user_name+" "); //username
            String scor="";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.format(new Date());
            scor=String.format("%tF",new Date());
            System.out.println(scor);
            fw.write(scor + "\n");  //time
            fw.close();
        }
        catch(IOException e){
            welcomeFrame.showMessageDialog("Error 文件读取错误");
            throw new RuntimeException(e);
        }
    }
    public static List<String> user_list_print() throws IOException {
        List<String> list;
        StringBuilder user_list=new StringBuilder();
        try {
            list = Files.readAllLines(Path.of(System.getProperty("user.dir")+"\\data\\message\\"+"achievement.txt"));
        } catch (IOException e) {
            System.out.println("文件读取错误");
            throw new IOException("文件读取错误");
        }

        return list;
    }
    public boolean checkPassword(int position) throws IOException {
        List<String> list;
        try {
            list = Files.readAllLines(Path.of(password_data_path));
        } catch (IOException e) {
            welcomeFrame.showMessageDialog("文件读取错误");
            throw new IOException("文件读取错误");
        }
        if(list.get(position).equals(password)){
            return true;
        }else{
            return false;
        }
    }
    public  boolean user_register(String user_name,String password){
        try {
            if(user_find(true)){
                welcomeFrame.showMessageDialog("用户名已存在");
                return false;
            }else{
                FileWriter fw = new FileWriter(user_data_path, true);
                fw.write( user_name+ "\n");
                fw.close();
                FileWriter fw1 = new FileWriter(password_data_path, true);
                fw1.write( password+ "\n");
                welcomeFrame.showMessageDialog("注册成功");
                fw1.close();
                return true;

            }
        }
        catch(IOException e){
            welcomeFrame.showMessageDialog("Error 文件读取错误");
            throw new RuntimeException(e);
        }

    }
}
