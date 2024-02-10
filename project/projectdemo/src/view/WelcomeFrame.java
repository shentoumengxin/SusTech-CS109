package view;

import controller.GameController;
import controller.MusicPlayer;
import controller.User;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;

public class WelcomeFrame extends JFrame{
    private final int WIDTH;
    private boolean isLogin = false;
    public boolean isLogin() {
        return isLogin;
    }
    private final int HEIGTH;
    private GameController gameController;
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    private String current_username;
    private String current_password;
    private ChessGameFrame frame;

    public WelcomeFrame(int width, int height) {
        super();
        setTitle("2023 CS109 Project"); //设置标题
        MusicPlayer.playMusic();
        this.WIDTH = width;
        this.HEIGTH = height;
        this.setIconImage(new ImageIcon("src/view/icon.png").getImage());
        this.setBackground(Color.LIGHT_GRAY);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addLabel();
        addTitle();
        addChess();
        addTextField_User();
        addLabel2();
        addTextField_Pwd();
    }

    private void addChess(){
        JLabel title = new JLabel("2023 Project-ZZH and HXF");
        title.setBounds(140, 45, 200, 50);
        title.setSize(300, 80);
        title.setFont(new Font("Rockwell", Font.BOLD, 17));
        title.setForeground(Color.cyan);
        add(title);

    }
    private void addTitle(){
        JLabel title = new JLabel("Welcome to the game");
        title.setBounds(130, 0, 200, 50);
        title.setSize(300, 80);
        title.setFont(new Font("Rockwell", Font.BOLD, 24));
        title.setForeground(Color.PINK);
        this.getContentPane().setBackground(Color.GRAY);
        this.getContentPane().setVisible(true);//如果改为true那么就变成了红色。
        add(title);
        addLoginButton();
        addRegisterButton();
        addTextFieldLevel();
        addLabel3();
    }
    JTextField textField_User = new JTextField();
    private void addTextField_User(){

        textField_User.setBounds(150, 125, 100, 50);
        textField_User.setSize(300, 30);
        textField_User.setFont(new Font("Rockwell", Font.BOLD, 16));
        textField_User.setForeground(Color.BLACK);
        add(textField_User);
    }
    private void addLabel(){
        JLabel label = new JLabel("User Name:");
        label.setBounds(50, 100, 200, 50);
        label.setSize(200, 80);
        label.setFont(new Font("Rockwell", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        add(label);
    }
    JTextField textField_pwd = new JTextField();
    private void addTextField_Pwd(){

        textField_pwd.setBounds(150, 225, 100, 50);
        textField_pwd.setSize(300, 30);
        textField_pwd.setFont(new Font("Rockwell", Font.BOLD, 16));
        textField_pwd.setForeground(Color.BLACK);
        add(textField_pwd);
    }
    private void addLabel3(){
        JLabel label = new JLabel("Choose Level: ");
        label.setBounds(140,355,30,30);
        label.setSize(200, 80);
        label.setFont(new Font("Rockwell", Font.BOLD, 17));
        label.setForeground(Color.BLACK);
        add(label);
    }
    JTextField textField_level = new JTextField();
    private void addTextFieldLevel(){
        textField_level.setBounds(280, 385, 30, 30);
        textField_level.setSize(50, 30);
        textField_level.setFont(new Font("Rockwell", Font.BOLD, 17));
        textField_level.setForeground(Color.BLACK);
        add(textField_level);
    }
    private void addLabel2(){
        JLabel label = new JLabel("Password:");
        label.setBounds(50, 200, 200, 50);
        label.setSize(200, 80);
        label.setFont(new Font("Rockwell", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        add(label);
    }
    private void addRegisterButton() {
        JButton button = new JButton("Register");
        button.addActionListener((e) -> {
            if(textField_User.getText().isEmpty()&&textField_pwd.getText().isEmpty()){
                showMessageDialog("不能为空");
                return;
            }
            current_username= textField_User.getText();
            current_password= textField_pwd.getText();
            User user=new User (current_username,current_password);
            user.registeFrame(this);
            boolean flag=user.user_register(current_username,current_password);
            if(flag){
                if(returnLevel()==-9967){
                    return;
                }
                else{
                    frame.setLevel(returnLevel());
                    this.setVisible(false);
                    frame.setUser_name(current_username);
                    frame.Label_username.setText("Welcome "+current_username+"!");
                    frame.setUser(user);
                    frame.Restart();
                    frame.setVisible(true);

                }
            }

        });
        button.setBackground(Color.LIGHT_GRAY);
        button.setLocation(20,300);
        button.setSize(140, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private int returnLevel(){
        if(textField_level.getText().isEmpty()){
            return 1;
        }
        else
        if(Integer.parseInt(textField_level.getText())>=1&&Integer.parseInt(textField_level.getText())<=5){
            return Integer.parseInt(textField_level.getText());
        }
         else{
            showMessageDialog("请输入1-5的数字");
            return -9967;
        }
    }
    private void addLoginButton(){
        JButton button = new JButton("Login");

        button.addActionListener((e) -> {
            if(textField_User.getText().isEmpty()&&textField_pwd.getText().isEmpty()){
                showMessageDialog("不能为空");
                return;
            }
            current_username= textField_User.getText();
            current_password= textField_pwd.getText();
            User user=new User (current_username,current_password);

            user.registeFrame(this);
            try {
                if(user.user_find()){
                    JOptionPane.showMessageDialog(this, "登录成功");
                    if(returnLevel()==-9967){
                        return;
                    }
                    else{

                        frame.setLevel(returnLevel());
                        this.setVisible(false);
                        frame.setUser_name(current_username);
                        frame.Label_username.setText("Welcome "+current_username+"!");
                        frame.setUser(user);
                        frame.Restart();
                        frame.setVisible(true);

                    }
                }else {
                    JOptionPane.showMessageDialog(this, "登录失败");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setBackground(Color.LIGHT_GRAY);
        button.setLocation(330,300);
        button.setSize(140, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    public void showMessageDialog(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public void registerFrame(ChessGameFrame mainFrame) {
        this.frame = mainFrame;
    }
}
