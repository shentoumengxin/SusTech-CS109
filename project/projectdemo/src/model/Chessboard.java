package model;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This class store the real chess information.
 * The Chessboard has 8 * 8 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;

    public Chessboard(int randomSeed) {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];

        initGrid();
        initPieces(randomSeed);
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void initPieces(int randomSeed) {//初始化棋盘
        Random random = new Random(randomSeed);
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
            }
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum()-2; j++) {
                if((grid[i][j].getPiece()).equals(grid[i][j+1].getPiece())&&(grid[i][j+2].getPiece()).equals(grid[i][j].getPiece())){
                    while(true){
                        grid[i][j+2].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                        if(!(grid[i][j+2].getPiece()).equals(grid[i][j].getPiece())){
                            break;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < Constant.CHESSBOARD_ROW_SIZE.getNum(); j++) {
            for (int i = 0; i < Constant.CHESSBOARD_COL_SIZE.getNum() - 2; i++) {
                boolean judge=true;
                if((grid[i][j].getPiece()).equals(grid[i+1][j].getPiece())&&(grid[i+2][j].getPiece()).equals(grid[i][j].getPiece())){
                    while(judge){
                        grid[i+2][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                        if(j<2){
                            if(!(grid[i+2][j].getPiece()).equals(grid[i][j].getPiece())&&!(grid[i+2][j].getPiece().equals(grid[i+1][j].getPiece()))){
                                judge=false;
                            }
                        }else {
                            if(!grid[i+2][j].getPiece().equals(grid[i][j].getPiece())&&!(grid[i+2][j].getPiece().equals(grid[i+1][j].getPiece()))&&!grid[i+2][j].getPiece().equals(grid[i+2][j-1].getPiece())&&!grid[i+2][j-2].getPiece().equals(grid[i+2][j].getPiece())){
                                judge=false;
                            }
                        }

                    }
                }
            }
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum()-2; j++) {
                if((grid[i][j].getPiece()).equals(grid[i][j+1].getPiece())&&(grid[i][j+2].getPiece()).equals(grid[i][j].getPiece())){
                    while(true){
                        grid[i][j+2].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                        if(!(grid[i][j+2].getPiece()).equals(grid[i][j].getPiece())){
                            break;
                        }
                    }
                }
            }
        }
        if(Detect_Stuck()){
            initPieces(randomSeed);
        }
    }//初始化棋盘

    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }


    public void swapChessPiece(ChessboardPoint point1, ChessboardPoint point2) {
        var p1 = getChessPieceAt(point1);
        var p2 = getChessPieceAt(point2);
        setChessPiece(point1, p2);
        setChessPiece(point2, p1);
    }


    public Cell[][] getGrid() {
        return grid;
    }
    public static int recordSameNumber=0;
    public static boolean judge_project1_Horizontal=false;
    public static boolean judge_project2_Horizontal=false;
    public static boolean judge_project1_Vertical=false;
    public static boolean judge_project2_Vertical=false;
    public  static int []Delate_Number;
    public  int Score_Number=0;
    public int Score=Score_Number*10;//分数
    public int getScore_Number() {
        return Score_Number;
    }
    public void setScore_Number(int score_Number) {
        Score_Number = score_Number;
    }

    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
    }

    public  void updateScore() {
        Score = Score_Number * 10; // 根据更新后的 Score_Number 重新计算分数
    }
    public boolean canSwap(ChessboardPoint point1,ChessboardPoint point2){
        Delate_Number=new int[8];
        boolean ifCanTrue=false;
        recordSameNumber=0;
        swapChessPiece(point1,point2);
        int point1GetCol=point1.getCol();
        int point2GetCol=point2.getCol();
        int point1GetRow=point1.getRow();
        int point2GetRow=point2.getRow();
        //point2向右找是否有三消
        for(int i=point2GetCol;i<Constant.CHESSBOARD_COL_SIZE.getNum()-1;i++){
            if(grid[point2GetRow][i].getPiece().equals(grid[point2GetRow][i+1].getPiece())){
                Delate_Number[0]++;
            }else {
                break;
            }
        }
        //point2向左找是否有三消
        for(int i=point2GetCol;i>0;i--){
            if(grid[point2GetRow][i].getPiece().equals(grid[point2GetRow][i-1].getPiece())){
                Delate_Number[1]++;
            }else {
                break;
            }
        }
        if(Delate_Number[0]+Delate_Number[1]>=2){
            judge_project2_Horizontal=true;
            ifCanTrue=true;
        }else {
            Delate_Number[0]=0;
            Delate_Number[1]=0;
        }
        recordSameNumber=0;
        //point2向下找是否有三消
        for(int i=point2GetRow;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-1;i++){
            if(grid[i][point2GetCol].getPiece().equals(grid[i+1][point2GetCol].getPiece())){
                Delate_Number[2]++;
            }else {
                break;
            }
        }
        //point2想向上找是否有三消
        for(int i=point2GetRow;i>0;i--){
            if(grid[i][point2GetCol].getPiece().equals(grid[i-1][point2GetCol].getPiece())){
                Delate_Number[3]++;
            }else {
                break;
            }
        }
        if(Delate_Number[2]+Delate_Number[3]>=2){
            judge_project2_Vertical=true;
            ifCanTrue=true;
        }else {
            Delate_Number[2]=0;
            Delate_Number[3]=0;
        }
        recordSameNumber=0;
        //判断point2可否三消
        //判断point1可否三消
        //point1向右
        for(int i=point1GetCol;i<Constant.CHESSBOARD_COL_SIZE.getNum()-1;i++){
            if(grid[point1GetRow][i].getPiece().equals(grid[point1GetRow][i+1].getPiece())){
                Delate_Number[4]++;
            }else {
                break;
            }
        }
        //point1向左
        for(int i=point1GetCol;i>0;i--){
            if(grid[point1GetRow][i].getPiece().equals(grid[point1GetRow][i-1].getPiece())){
                Delate_Number[5]++;
            }else {
                break;
            }
        }
        if(Delate_Number[4]+Delate_Number[5]>=2){
            judge_project1_Horizontal=true;
            ifCanTrue=true;
        }else {
            Delate_Number[4]=0;
            Delate_Number[5]=0;
        }
        recordSameNumber=0;
        //point1向下
        for(int i=point1GetRow;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-1;i++){
            if(grid[i][point1GetCol].getPiece().equals(grid[i+1][point1GetCol].getPiece())){
                Delate_Number[6]++;
            }else {
                break;
            }
        }
        //point1向上
        for(int i=point1GetRow;i>0;i--){
            if(grid[i][point1GetCol].getPiece().equals(grid[i-1][point1GetCol].getPiece())){
                Delate_Number[7]++;
            }else {
                break;
            }
        }
        if(Delate_Number[6]+Delate_Number[7]>=2){
            judge_project1_Vertical=true;
            ifCanTrue=true;
        }else {
            Delate_Number[6]=0;
            Delate_Number[7]=0;
        }
        if(ifCanTrue){
            return true;
        }
        recordSameNumber=0;
        return false;
    }//判断是否可以消除
    public void realSwap(ChessboardPoint point1,ChessboardPoint point2){
        swapChessPiece( point1,point2);
        ArrayList<Cell>Automatic_Eliminate_Cell=new ArrayList<>();
        for(int j =0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
            for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-2;i++){
                if(grid[i][j].getPiece().equals(grid[i+1][j].getPiece())&&grid[i][j].getPiece().equals(grid[i+2][j].getPiece())){
                    for(int z=i;z<Constant.CHESSBOARD_ROW_SIZE.getNum();z++){
                        if(grid[z][j].getPiece().equals(grid[i][j].getPiece())){
                            Automatic_Eliminate_Cell.add(grid[z][j]);
                        }else {
                            break;
                        }
                    }

                }
            }
        }
        for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++){
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum()-2;j++){
                if (grid[i][j].getPiece().equals(grid[i][j+1].getPiece())&&grid[i][j].getPiece().equals(grid[i][j+2].getPiece())){
                    for(int z=j;z<Constant.CHESSBOARD_COL_SIZE.getNum();z++){
                        if(grid[i][z].getPiece().equals(grid[i][j].getPiece())){
                            Automatic_Eliminate_Cell.add(grid[i][z]);
                        }else {
                            break;
                        }
                    }
                }

            }
        }
        for(Cell cell:Automatic_Eliminate_Cell){
            cell.removePiece();
        }
        if(Automatic_Eliminate_Cell.isEmpty()){
            Judge_NeedMore_NextStep=true;
        }
        System.out.println();
        writeGrid();
        HashSet<Cell> uniqueElements = new HashSet<>(Automatic_Eliminate_Cell);

// 清空原来的ArrayList
        //Automatic_Eliminate_Cell.clear();

        Score_Number+=uniqueElements.size();
        updateScore();

    }//实现消除
    public void FirstClicking_NextStep(){

        for(int j=Constant.CHESSBOARD_COL_SIZE.getNum()-1;j>=0;j--){
            if(j<0){break;}
            for(int i=Constant.CHESSBOARD_ROW_SIZE.getNum()-1;i>=0;i--){
                int recordNull=0;
                if(i-1<0){break;}
                if(grid[i][j].getPiece()==null){
                    recordNull++;
                }
                if(grid[i][j].getPiece()==null&&grid[i-1][j].getPiece()!=null){
                    for(int z=i;z>=0;z--){
                        if(z<0){break;}
                        if(z-recordNull>=0){
                            grid[z][j].setPiece(grid[z-recordNull][j].getPiece());
                            //System.out.println(grid[z][j].getPiece().getName());
                        }
                        else {
                            for(int q=0;q<recordNull;q++){
                                if(q>=8){
                                    break;
                                }
                                grid[q][j].removePiece();
                            }
                        }
                        i=Constant.CHESSBOARD_ROW_SIZE.getNum();
                    }
                }
            }

        }
        System.out.println();
        writeGrid();
    }//棋子降落
    public void SecondClicking_NextStep(){
        for(int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++){
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
                if(grid[i][j].getPiece()==null){
                    grid[i][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                }
            }
        }
        System.out.println();
        writeGrid();
    }//随机生成棋子
    public static boolean Judge_NeedMore_NextStep=true;
    public boolean ContinueClicking_NextStep(){
        ArrayList<Cell>Automatic_Eliminate_Cell=new ArrayList<>();
        //System.out.println();
        //writeGrid();
        for(int j =0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
            for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-2;i++){
                if(grid[i][j].getPiece()==null){
                    break;
                }
                if(grid[i][j].getPiece().equals(grid[i+1][j].getPiece())&&grid[i][j].getPiece().equals(grid[i+2][j].getPiece())){
                    for(int z=i;z<Constant.CHESSBOARD_ROW_SIZE.getNum();z++){
                        if(grid[z][j].getPiece().equals(grid[i][j].getPiece())){
                            Automatic_Eliminate_Cell.add(grid[z][j]);
                        }else {
                            break;
                        }
                    }

                }
            }
        }
        for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++){
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum()-2;j++){
                if(grid[i][j].getPiece()==null){
                    break;
                }
                if (grid[i][j].getPiece().equals(grid[i][j+1].getPiece())&&grid[i][j].getPiece().equals(grid[i][j+2].getPiece())){
                    for(int z=j;z<Constant.CHESSBOARD_COL_SIZE.getNum();z++){
                        if(grid[i][z].getPiece().equals(grid[i][j].getPiece())){
                            Automatic_Eliminate_Cell.add(grid[i][z]);
                        }else {
                            break;
                        }
                    }
                }

            }
        }
        for(Cell cell:Automatic_Eliminate_Cell){
            cell.removePiece();
        }
        if(Automatic_Eliminate_Cell.isEmpty()){
            Judge_NeedMore_NextStep=true;
            System.out.println();
            writeGrid();
            HashSet<Cell> uniqueElements = new HashSet<>(Automatic_Eliminate_Cell);
            Score_Number+=uniqueElements.size();
            updateScore();
            return false;
        }else{
            System.out.println();
            writeGrid();
            HashSet<Cell> uniqueElements = new HashSet<>(Automatic_Eliminate_Cell);
            Score_Number+=uniqueElements.size();
            updateScore();
            return true;
        }

    }//消除重复棋子
    public void Bomb(ChessboardPoint point){
        initPieces(7878);


    }
    public boolean Detect_Stuck(){//没有可以再交换的棋子，提示使用炸弹"💎", "⚪", "▲", "🔶"
        int number=0;
        for (int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++){
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum()-1;j++){
                if(grid[i][j].getPiece()!=null&&grid[i][j].getPiece().equals(grid[i][j+1].getPiece())){
                    number++;
                    if(j+3<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i][j+3].getPiece())){
                            return false;
                        }
                    }if(i+1<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j+2<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i+1][j+2].getPiece())){
                            return false;
                        }
                    }if(i-1>0&&j+2<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i-1][j+2].getPiece())){
                            return false;
                        }
                    }
                    if(j-2>0){
                        if(grid[i][j].getPiece().equals(grid[i][j-2].getPiece())){
                            return false;
                        }
                    }if(i+1<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i+1][j-1].getPiece())){
                            return false;
                        }
                    }if(i-1>0&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i-1][j-1].getPiece())){
                            return false;
                        }
                    }

                }
            }
        }
        for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
            for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-1;i++) {
                if (grid[i][j].getPiece().equals(grid[i + 1][j].getPiece())) {
                    number++;
                    if(i+3<Constant.CHESSBOARD_ROW_SIZE.getNum()){
                        if (grid[i][j].getPiece().equals(grid[i + 3][j].getPiece())) {
                            return false;
                        }
                    }if(i+2<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j+2<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i + 2][j + 1].getPiece())){
                            return false;
                        }
                    }if(i+2<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i + 2][j - 1].getPiece())){
                            return false;
                        }
                    }
                    if(i-2>0){
                        if (grid[i][j].getPiece().equals(grid[i - 2][j].getPiece())) {
                            return false;
                        }
                    }if(i-1>0&&j+1<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i - 1][j + 1].getPiece())){
                            return false;
                        }
                    }if(i-1>0&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i - 1][j - 1].getPiece())){
                            return false;
                        }
                    }

                }
            }
        }
        if(number==0){
            for (int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++) {
                for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum() - 1; j++) {
                    if (grid[i][j].getPiece().equals(grid[i][j + 1].getPiece())) {
                        return false;
                    }
                }
            }
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++) {
                for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum() - 1; i++) {
                    if (grid[i][j].getPiece().equals(grid[i + 1][j].getPiece())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }//没有可以再交换的棋子，提示使用炸弹
    public ChessboardPoint[] Get_Hint(){//获得提示
        ChessboardPoint[] chessboardPoint_Array=new ChessboardPoint[2];
        for (int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++){
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum()-1;j++){
                if(grid[i][j].getPiece().equals(grid[i][j+1].getPiece())){
                    if(j+3<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i][j+3].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i,j+3);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j+2);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i+1<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j+2<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i+1][j+2].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i+1,j+2);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j+2);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i-1>0&&j+2<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i-1][j+2].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i-1,j+2);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j+2);
                            return chessboardPoint_Array;
                        }
                    }
                    if(j-2>0){
                        if(grid[i][j].getPiece().equals(grid[i][j-2].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i,j-2);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j-1);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i+1<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i+1][j-1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i+1,j-1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j-1);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i-1>0&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i-1][j-1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i-1,j-1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i,j-1);
                            return chessboardPoint_Array;
                        }
                    }
                }
            }
        }
        for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
            for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum()-1;i++){
                if(grid[i][j].getPiece().equals(grid[i+1][j].getPiece())){
                    if(i+3<Constant.CHESSBOARD_ROW_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i+3][j].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i+3,j);
                            chessboardPoint_Array[1]=new ChessboardPoint(i+2,j);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i+2<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j+1<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i+2][j+1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i+2,j+1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i+2,j);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i+2<Constant.CHESSBOARD_ROW_SIZE.getNum()&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i+2][j-1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i+2,j-1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i+2,j);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i-2>0){
                        if(grid[i][j].getPiece().equals(grid[i-2][j].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i-2,j);
                            chessboardPoint_Array[1]=new ChessboardPoint(i-1,j);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i-1>0&&j+1<Constant.CHESSBOARD_COL_SIZE.getNum()){
                        if(grid[i][j].getPiece().equals(grid[i-1][j+1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i-1,j+1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i-1,j);
                            return chessboardPoint_Array;
                        }
                    }
                    if(i-1>0&&j-1>0){
                        if(grid[i][j].getPiece().equals(grid[i-1][j-1].getPiece())){
                            chessboardPoint_Array[0]=new ChessboardPoint(i-1,j-1);
                            chessboardPoint_Array[1]=new ChessboardPoint(i-1,j);
                            return chessboardPoint_Array;

                        }
                    }
                }
            }
        }
        return null;
    }//获得提示
    public void writeGrid(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(grid[i][j].getPiece()==null){
                    System.out.print("   ");
                }else {
                    System.out.print(grid[i][j].getPiece().getName()+"  ");
                }
                if(j==7){
                    System.out.println();
                }
            }
        }
    }//debug用来打印棋盘的



    public List<String> convertBoardToList(){
        List<String>list=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<Constant.CHESSBOARD_ROW_SIZE.getNum();i++){
            sb.setLength(0);
            for(int j=0;j<Constant.CHESSBOARD_COL_SIZE.getNum();j++){
                ChessPiece piece = grid[i][j].getPiece();
                if(piece==null){
                    sb.append("0 ");
                }else{
                    sb.append(piece.getName()+" ");
                }
            }
            list.add(sb.toString());
        }
        sb.setLength(0);
        return list;
    }
}
