package controller;

import listener.GameListener;
import model.ChessPiece;
import model.Constant;
import model.Chessboard;
import model.ChessboardPoint;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static model.Chessboard.Delate_Number;
import static model.Chessboard.Judge_NeedMore_NextStep;


/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and
 * onPlayerClickChessPiece()]
 *
 */
public class GameController implements GameListener,Runnable {
    public int record_step=0;
    private int Distinguish_NextStep=0;
    public void setDistinguish_NextStep(int distinguish_NextStep) {
        Distinguish_NextStep = distinguish_NextStep;
    }
    private Chessboard model;
    private ChessboardComponent view;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private ChessboardPoint selectedPoint2;
    private boolean next_step_flag=true;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        view.registerController(this);
        //initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    public Chessboard getModel() {
        return model;
    }

    public ChessboardComponent getView() {
        return view;
    }
    private ChessGameFrame frame;
    public void registerFrame(ChessGameFrame frame){
        this.frame=frame;
    }
    public int getDistinguish_NextStep() {
        return Distinguish_NextStep;
    }
    public void loadBoardFromFile(String path) throws IOException {
        List<String> list;
        StringBuilder stringBuilder = new StringBuilder(path);
        if(!path.endsWith(".txt")) {
            frame.showMessageDialog("文件格式错误 error:101");
            return;
        }
        if(!path.contains(frame.getUser_name())){
            frame.showMessageDialog("无权限访问");
            return;
        }
        try {
            list = Files.readAllLines(Path.of(path));
        } catch (FileNotFoundException e){
            frame.showMessageDialog("文件不存在 error");
            throw new IOException("文件不存在");
        } catch (IOException e) {
            frame.showMessageDialog("棋子读入错误 error:103");
            throw new IOException("文件读取错误");
        }
        int score=Integer.parseInt(list.get(0)); //对score进行检查
        if(score<0){
            frame.showMessageDialog("存档数据错误");
            throw new IOException("数据错误");
        }
        list.remove(0);
        int prop_left=Integer.parseInt(list.get(0)); //prop_left check
        if(prop_left<0||prop_left>Constant.pass_prop_left){
            frame.showMessageDialog("存档数据错误");
            throw new IOException("数据错误");
        }
        list.remove(0);
        int level=Integer.parseInt(list.get(0)); //level check
        if(level<1||level>5){
            frame.showMessageDialog("存档数据错误");
            throw new IOException("数据错误");
        }
        if(score>Constant.pass_score[level-1]){
            frame.showMessageDialog("存档数据错误");
            throw new IOException("数据错误");
        }
        list.remove(0);
        record_step=Integer.parseInt(list.get(0));
        if(record_step<0||record_step>Constant.pass_step[level-1]){
            frame.showMessageDialog("存档数据错误");
            throw new IOException("数据错误");
        }
        list.remove(0);
        view.removeAllChessComponentsAtGrids();
        model.setScore(score);
        model.setScore_Number(model.Score/10);
        frame.setProp_left(prop_left);
        frame.setLevel(level);
        frame.Label_Level.setText("Level: "+frame.getLevel());
        frame.Bombbutton.setText("Prop: "+frame.getProp_left()+" left");
        frame.Label_Score.setText("Score: "+model.Score);
        if (list.size() != Constant.CHESSBOARD_ROW_SIZE.getNum()) {
            frame.showMessageDialog("棋盘错误 error:102");
            throw new IOException("文件错误");
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            String[] line = list.get(i).split(" ");
            if (line.length != Constant.CHESSBOARD_COL_SIZE.getNum()) {
                frame.showMessageDialog("棋盘错误 error:102");
                throw new IOException("文件错误");
            }
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (line[j].equals("0")) {
                    model.getGrid()[i][j].setPiece(null);
                } else if (!((line[j].equals("💎")) || (line[j].equals("⚪")) || (line[j].equals("▲")) || (line[j].equals("🔶")))) {
                    frame.showMessageDialog("棋子读入错误 error:103");
                    break;
                } else {
                    model.getGrid()[i][j].setPiece(new ChessPiece(line[j]));
                }
            }


        }
        view.initiateChessComponent(model);
        view.repaint();
    }//读取文件
    //private int Level=1;
    public void checkWin() throws IOException {
        if(model.Score>=Constant.pass_score[frame.getLevel()-1]){
            Distinguish_NextStep=0;
           frame.showMessageDialog("恭喜你过关了");
            next_step_flag=true;
           boolean flag=JOptionPane.showConfirmDialog(null,"是否进入下一关","", JOptionPane.YES_NO_OPTION)==0;
           if(flag){
               if(frame.getLevel()==5){
                   frame.showMessageDialog("恭喜你通关了");
                   frame.getUser().userAchievementSave();
                   //frame.showMessageDialog("游戏结束");
                   //frame.dispose();
                   SuccessFrame successFrame=new SuccessFrame();
                   successFrame.setVisible(true);
                   frame.setVisible(false);
                   return;
               }
               frame.setLevel(frame.getLevel()+1);
               model.Score=0;
               model.Score_Number=0;
               record_step=0;
               frame.setProp_left(Constant.pass_prop_left);
               frame.Restart();
           }else{
               model.Score=0;
               model.Score_Number=0;
               record_step=0;
               frame.setProp_left(Constant.pass_prop_left);
               frame.Restart();
           }

        }else if(record_step>=Constant.pass_step[frame.getLevel()-1]&& !next_step_flag){
            Distinguish_NextStep=0;
            frame.showMessageDialog("分数不够，再接再厉");
            model.Score=0;
            frame.setProp_left(Constant.pass_prop_left);
            next_step_flag=true;
            frame.Restart();
        }


    }
    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
    }

    @Override
    public void onPlayerSwapChess(){
        // TODO: Init your swap function here.
        System.out.println("Implement your swap here.");
        if(model.ContinueClicking_NextStep()){
            return;
        }
        if(frame.getStep_left()<=0){
            frame.showMessageDialog("无法交换");
            return;
        }
        if(model.canSwap(selectedPoint,selectedPoint2)){
            if(selectedPoint!=null&&selectedPoint2!=null){
                model.swapChessPiece(selectedPoint,selectedPoint2);
                model.realSwap(selectedPoint,selectedPoint2);
                view.removeAllChessComponentsAtGrids();
                view.initiateChessComponent(model);
                view.repaint();
                selectedPoint=null;
                selectedPoint2=null;
                record_step++;
                frame.swap_button.setBackground(Color.RED);
            }
        }else{
            frame.showMessageDialog("无法交换");
        }


    }

    @Override
    public void onPlayerNextStep(){
        //ChessComponent chess1 =view.removeChessComponentAtGrid(selectedPoint);
        boolean i=true;
        ChessboardPoint point1=new ChessboardPoint( 8,8);
        if(Judge_NeedMore_NextStep){
            if(Distinguish_NextStep==0){
                model.FirstClicking_NextStep();//降落
            }
            if(Distinguish_NextStep==1){
                model.SecondClicking_NextStep();//生成随机棋子
            }
            if(Distinguish_NextStep==2){
                boolean flag=model.ContinueClicking_NextStep();//消除重复棋子
                Distinguish_NextStep=0;
                i=false;
                if(!flag){
                    frame.showMessageDialog("无法再消除重复棋子");
                    frame.swap_button.setBackground(Color.LIGHT_GRAY);
                    if(frame.getStep_left()<=0){
                        next_step_flag=false;
                    }
                }
            }
           /*待解决问题
            图标会变小
            无法再消除重复棋子后要有个弹窗提示无法再点击nextstep*/
        }
        // TODO: Init your next step function here.
        //view.setChessComponentAtGrid(point1,null);
        //view.removeChessComponentAtGrid(point1);
        view.removeAllChessComponentsAtGrids();
        view.initiateChessComponent(model);
        view.repaint();
        System.out.println("Implement your next step here.");
        if(i){
            Distinguish_NextStep++;
        }
    }

    public void bomb(){
        if(frame.getProp_left()>0){
            model.Bomb(selectedPoint);
            setDistinguish_NextStep(0);
            view.removeAllChessComponentsAtGrids();
            view.initiateChessComponent(model);
            view.repaint();
        }else{
            frame.showMessageDialog("无法使用");
        }
    }
    public void saveGameMapToDataBase(String path) throws IOException {
        List<String> saveLines = model.convertBoardToList();
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(model.Score+"\n");
            fw.write(frame.getProp_left()+"\n");
            fw.write(frame.getLevel()+"\n");   //分三行写入该棋盘的当前分数，炸弹，关卡,步数
            fw.write(record_step+"\n");
            for (String line : saveLines) {
                fw.write(line + "\n");
            }
            fw.close();
        }
     catch(IOException e){
                throw new RuntimeException(e);
            }

    }
        // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if(selectedPoint2 != null){
            var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow());
            var distance2point2 = Math.abs(selectedPoint2.getCol() - point.getCol()) + Math.abs(selectedPoint2.getRow() - point.getRow());
            var point1 = (ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
            var point2 = (ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
            if(distance2point1 == 0 && point1!= null){
                point1.setSelected(false);
                point1.repaint();
                selectedPoint = selectedPoint2;
                selectedPoint2 = null;
            }else if(distance2point2 == 0 && point2!= null){
                point2.setSelected(false);
                point2.repaint();
                selectedPoint2 = null;
            }else if(distance2point1 == 1 && point2!= null){
                point2.setSelected(false);
                point2.repaint();
                selectedPoint2 = point;
                component.setSelected(true);
                component.repaint();
            }else if(distance2point2 == 1 && point1!= null){
                point1.setSelected(false);
                point1.repaint();
                selectedPoint = selectedPoint2;
                selectedPoint2 = point;
                component.setSelected(true);
                component.repaint();
            }
            return;
        }


        if (selectedPoint == null) {
            selectedPoint = point;
            component.setSelected(true);
            component.repaint();
            return;
        }

        var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow());

        if(distance2point1 == 0){
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
            return;
        }

        if(distance2point1 == 1){
            selectedPoint2 = point;
            component.setSelected(true);
            component.repaint();
        }else{
            selectedPoint2 = null;
            try{
            var grid = (ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
            if(grid == null) return;
            grid.setSelected(false);
            grid.repaint();}
            catch(Exception e){
                System.err.println("Error: " + e.getMessage());
            }

            selectedPoint = point;
            component.setSelected(true);
            component.repaint();
        }


    }
    private boolean finish_flag=false;
    public void onPlayerNextStepAutomatically() {
        ChessboardPoint point1=new ChessboardPoint( 8,8);
        Thread thread=new Thread(this);
        thread.start();
        // TODO: Init your next step function here.

    }

    @Override
    public void run() {
        frame.swap_button.setEnabled(false);
        while(Judge_NeedMore_NextStep&&!finish_flag){
            //view.removeAllChessComponentsAtGrids();
            finish_flag=false;
            boolean i=true;
            model.FirstClicking_NextStep();//降落
            view.removeAllChessComponentsAtGrids();
            view.repaint();
            view.initiateChessComponent(model);
            try{
                Thread.sleep(800);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            view.repaint();
            model.SecondClicking_NextStep();//生成随机棋子
            view.removeAllChessComponentsAtGrids();
            view.initiateChessComponent(model);
            try{
                Thread.sleep(800);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            view.repaint();
            boolean flag=model.ContinueClicking_NextStep();//消除重复棋子
            frame.Label_Score.setText("Score: "+model.Score);
            view.removeAllChessComponentsAtGrids();
            view.initiateChessComponent(model);
            try{
                Thread.sleep(800);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            view.repaint();
            i=false;
            if(!flag){
                if(frame.getStep_left()<=0){
                    next_step_flag=false;
                }
                finish_flag=true;
            }

            try {
                checkWin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(model.Detect_Stuck())frame.showMessageDialog("You are stuck! Use bomb to get out!");

        }
        finish_flag=false;
        frame.swap_button.setBackground(Color.LIGHT_GRAY);
        frame.swap_button.setEnabled(true);

    }
}
