package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public enum Constant {
    CHESSBOARD_ROW_SIZE(8),CHESSBOARD_COL_SIZE(8);

    private final int num;
    public static final int []pass_step={5,4,3,3,3};
    public static final int []pass_score={300,300,350,400,500};
    public static final ChessboardPoint []obstacle1={new ChessboardPoint(0,0),new ChessboardPoint(0,7),new ChessboardPoint(7,0),new ChessboardPoint(7,7)};
    public static final int pass_prop_left=3;
    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    static Map<String, Color> colorMap = new HashMap<>(){{
        put("💎",Color.blue);
        put("⚪",Color.white);
        put("▲",Color.green);
        put("🔶",Color.orange);
        put("❌",Color.red);
    }};

}
