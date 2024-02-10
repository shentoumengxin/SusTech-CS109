package model;


import java.awt.*;
import java.util.Objects;

public class ChessPiece {
    // Diamond, Circle, ...
    private String name;

    private Color color;
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChessPiece otherPiece = (ChessPiece) obj;
        return Objects.equals(this.name, otherPiece.name);
    }

    public ChessPiece(String name) {
        this.name = name;
        this.color = Constant.colorMap.get(name);
    }

    public String getName() {
        return name;
    }

    public Color getColor(){return color;}

}
