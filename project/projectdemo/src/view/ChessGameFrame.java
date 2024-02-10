package view;

import controller.GameController;
import controller.User;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 * 此类储存level prop_left
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private ChessboardComponent chessboardComponent;
    private JLabel statusLabel;
    public JLabel Label_Score;
    private JLabel Label_Prop_left;
    private JLabel Label_Step_left;
    public JLabel Label_Level;
    private JLabel Label_Target_Score;
    private int wanted_Score;
    public int getScore() {
        return this.model.Score;
    }
   /** public void setScore(int score) {
        this.Score = score;
    }*/
    private int Prop_left=3;
    private int Level=1;
    private String user_name;
    private ChessboardComponent view;
    private Chessboard model;
    private GameController gameController;
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    public void setView(ChessboardComponent view) {
        this.view = view;
    }

    public void setModel(Chessboard model) {
        this.model = model;
    }

    public int getWanted_Score() {
        return Constant.pass_score[getLevel()-1];
    }

    public int getProp_left() {
        if(Prop_left<0){
            return 0;
        }
        else{
            return Prop_left;
        }

    }

    public int getStep_left(){
        return Constant.pass_step[getLevel()-1]-gameController.record_step;
    }

    public int getLevel() {
        return Level;
    }
    public void setLevel(int level) {
        this.Level = level;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_name() {
        return user_name;
    }
public User getUser(){
        return user;
}

    public ChessGameFrame(int width, int height) {
        this.wanted_Score = Constant.pass_score[1];
        this.Prop_left = Constant.pass_prop_left;
        setTitle("2023 CS109 Project"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addChessboard();
        addlabel1();
        addlabel3();
        addlabel_level();
        addBombButton();
        addSwapConfirmButton();
        addNextStepButton();
        addLoadButton();
        addSaveButton();
        addlabel_welcome();
        addTargetScore();
        addRestartButton();
        addCheckbox();
        addHintButton();
        addTextFieldLevel();
        addButtonChangeLevel();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addlabel_level() {
        this.Label_Level = new JLabel("Level: "+getLevel());
        Label_Level.setLocation(getWidth()/2-120, 0);
        Label_Level.setSize(200, 80);
        Label_Level.setFont(new Font("Rockwell", Font.BOLD, 30));
        Label_Level.setForeground(Color.BLUE);
        add(Label_Level);
    }
    JLabel Label_username;
    private void addlabel_welcome() {
        Label_username= new JLabel("Welcome "+user_name+"!");
        Label_username.setLocation(20, 0);
        Label_username.setSize(200, 80);
        Label_username.setFont(new Font("Rockwell", Font.BOLD, 24));
        Label_username.setForeground(Color.yellow);
        add(Label_username);
    }
    private void addTargetScore() {
        this.Label_Target_Score = new JLabel("Target: "+getWanted_Score());
        Label_Target_Score.setLocation(getWidth()/2+100, 20);
        Label_Target_Score.setSize(200, 60);
        Label_Target_Score.setFont(new Font("Rockwell", Font.BOLD, 23));
        Label_Target_Score.setForeground(Color.RED);
        add(Label_Target_Score);
    }
    private void addlabel1() {
        this.Label_Score = new JLabel("Score: "+ 0);
        Label_Score.setLocation(HEIGTH, HEIGTH / 10);
        Label_Score.setSize(200, 60);
        Label_Score.setFont(new Font("Rockwell", Font.BOLD, 26));
        Label_Score.setForeground(Color.ORANGE);
        add(Label_Score);
    }
    private void addlabel3() {
        this.Label_Step_left = new JLabel("Stepleft: "+Constant.pass_step[getLevel()-1]);
        Label_Step_left.setLocation(HEIGTH, HEIGTH / 10+60);
        Label_Step_left.setSize(200, 60);
        Label_Step_left.setFont(new Font("Rockwell", Font.BOLD, 23));
        Label_Step_left.setForeground(Color.RED);
        add(Label_Step_left);
    }
    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }
    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会爆炸
     */
    public JButton Bombbutton = new JButton("Bomb: "+getProp_left()+" left");
    private void addBombButton() {

        Bombbutton.addActionListener((e) -> {
            gameController.bomb();
            Prop_left--;
            this.Label_Score.setText("Score: "+ getScore());
            Bombbutton.setText("Prop: "+getProp_left()+" left");

        });
        Bombbutton.setLocation(HEIGTH, HEIGTH / 10 + 120);
        Bombbutton.setSize(200, 60);
        Bombbutton.setBackground(Color.LIGHT_GRAY);
        Bombbutton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(Bombbutton);
    }
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton swap_button = new JButton("Confirm Swap");
    private void addSwapConfirmButton() {

        swap_button.addActionListener((e) -> {
            if(gameController.getDistinguish_NextStep()!=0){
                System.out.println("You can't swap now!");
                return;
            }else{
                try {
                    chessboardComponent.swapChess();
                }catch (Exception ex){
                    System.out.println("You can't swap now!");
                    return;
                }
                this.Label_Score.setText("Score: "+ getScore());
                this.Label_Step_left.setText("Stepleft: "+getStep_left());
                try {
                    gameController.checkWin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        swap_button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        swap_button.setSize(200, 60);
        swap_button.setBackground(Color.LIGHT_GRAY);
        swap_button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(swap_button);
    }

    private void addNextStepButton() {
        JButton button = new JButton("Next Step");
        button.addActionListener((e) -> {
            if(checkbox.getState()){
                chessboardComponent.nextStepAutomatically();
                //if(model.Detect_Stuck())showMessageDialog("You are stuck! Use bomb to get out!");
                return;
            }
            chessboardComponent.nextStep();
            this.Label_Score.setText("Score: "+ getScore());
            this.Label_Step_left.setText("Stepleft: "+getStep_left());
            try {
                gameController.checkWin();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(model.Detect_Stuck())showMessageDialog("You are stuck! Use prop to get out!");
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            int a=JOptionPane.showConfirmDialog(this,"需要加载上次的游戏记录吗","Load",JOptionPane.YES_NO_OPTION);
            if(a==0){
                String path = System.getProperty("user.dir")+"\\data\\"+user_name+".txt";
                System.out.println(path);
                try {
                    gameController.loadBoardFromFile(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                String path = JOptionPane.showInputDialog(this, "Input Path here");
                System.out.println(path);
                try {
                    gameController.loadBoardFromFile(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            Label_Step_left.setText("Stepleft: "+getStep_left());
            swap_button.setBackground(Color.LIGHT_GRAY);
            gameController.setDistinguish_NextStep(0);

        });
    }
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 440);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);
        button.addActionListener(e -> {   //todo: save the game need to be completed
            System.out.println("Click load");
            //String path = JOptionPane.showInputDialog(this, "Input Path here");
            String path = System.getProperty("user.dir")+"\\data\\"+user_name+".txt";
            System.out.println(path);
            try {
                gameController.saveGameMapToDataBase(path);

                this.showMessageDialog("Save Successfully");
            } catch (IOException ex) {
                this.showMessageDialog("Save Failed");
                throw new RuntimeException(ex);

            }

        });
    }
    public void Restart(){
        this.wanted_Score = getWanted_Score();
        model.Score=0;
        model.Score_Number=0;
        this.Prop_left = 3;
        this.Level = getLevel();
        gameController.record_step=0;
        this.Label_Score.setText("Score: "+ getScore());
        this.Bombbutton.setText("Prop: "+getProp_left()+" left");
        this.Label_Step_left.setText("Stepleft: "+getStep_left());
        this.Label_Level.setText("Level: "+getLevel());
        this.Label_Target_Score.setText("Target Score: "+getWanted_Score());
        gameController.setDistinguish_NextStep(0);
        model.initPieces(1314);
        swap_button.setBackground(Color.LIGHT_GRAY);
        view.removeAllChessComponentsAtGrids();
        view.initiateChessComponent(model);
        view.repaint();
    }
    private void addRestartButton() {
        JButton button = new JButton("Restart");
        button.setLocation(HEIGTH, HEIGTH / 10 + 520);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);
        button.addActionListener(e -> {   //todo: save the game need to be completed
            System.out.println("Click load");
            Level=1;
            Restart();
        });
    }
    Checkbox checkbox=new Checkbox("Auto");
    private void addCheckbox(){

        checkbox.setBounds(HEIGTH+200, HEIGTH / 10 + 250, 100, 50);
        checkbox.setSize(300, 100);
        checkbox.setFont(new Font("Rockwell", Font.BOLD, 21));
        checkbox.setForeground(Color.WHITE);
        add(checkbox);
    }
    private void addHintButton() {
        JButton button = new JButton("Hint");
        button.addActionListener((e) -> {
            var point1 = (ChessComponent)view.getGridComponentAt(model.Get_Hint()[0]).getComponent(0);
            var point2 = (ChessComponent)view.getGridComponentAt(model.Get_Hint()[1]).getComponent(0);
            point1.setHint(true);
            point1.repaint();
            point2.setHint(true);
            point2.repaint();
            System.out.println(point1);
            System.out.println(point2);
            point1.repaint();
            point2.repaint();
        });
        button.setLocation(20, 120);
        button.setSize(90, 40);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    JTextField textField_level = new JTextField();
    private void addTextFieldLevel(){
        textField_level.setBounds(20, 385, 30, 30);
        textField_level.setSize(80, 30);
        textField_level.setFont(new Font("Rockwell", Font.BOLD, 17));
        textField_level.setForeground(Color.BLACK);
        add(textField_level);
    }
    private void addButtonChangeLevel(){
        JButton button = new JButton("Change Level");
        button.addActionListener((e) -> {
            int level=Integer.parseInt(textField_level.getText());
            if(level>Constant.pass_score.length||level<1){
                showMessageDialog("Level should be between 1 and 5");
                return;
            }
            else{
                setLevel(level);
                this.Label_Level.setText("Level: "+getLevel());
                this.Label_Target_Score.setText("Target Score: "+getWanted_Score());
                Restart();
            }
        });
        button.setLocation(10, 415);
        button.setSize(120, 40);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Rockwell", Font.BOLD, 11));
        button.setForeground(Color.WHITE);
        add(button);
    }


    public void setProp_left(int passPropLeft) {
        this.Prop_left = passPropLeft;
    }
}
