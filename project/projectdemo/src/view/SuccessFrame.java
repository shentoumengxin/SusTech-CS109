package view;

import controller.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SuccessFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    private List<String> user_list;
    public SuccessFrame() throws IOException {
        super();
        setTitle("SUCCESS!!!"); //设置标题
        this.setBackground(Color.LIGHT_GRAY);
        JLabel label=new JLabel();
        this.WIDTH = 700;
        this.HEIGTH = 800;
        label.setSize(WIDTH,HEIGTH);
        label.setBackground(Color.YELLOW);
        getContentPane().setBackground(Color.YELLOW);
        add(label);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        user_list=User.user_list_print();
        addPanel();
        this.setVisible(true);
    }

    private void addPanel() {
        final int interval=70;
        int yPosition=0;
        int num=1;
        JLabel label=new JLabel("WINNER LIST");
        label.setBounds(WIDTH/2-140, yPosition, 300, interval+20);//
        label.setSize(300, interval);
        label.setFont(new Font("Rockwell", Font.BOLD, 37));
        label.setForeground(Color.RED);
        //yPosition+=interval+20;
        add(label);
            while(!user_list.isEmpty()){

                JLabel title = new JLabel(num+"."+user_list.get(0));
                user_list.remove(0);
                yPosition+=interval;
                title.setBounds(WIDTH/2-130, yPosition, 500, interval);//
                title.setSize(300, interval);
                title.setFont(new Font("Rockwell", Font.BOLD, 26));
                title.setForeground(Color.BLACK);
                num++;
                this.getLayeredPane().add(title);
                add(title);
            }


    }

}