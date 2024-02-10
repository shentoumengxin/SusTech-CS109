public class Treasure {
    private final int score;
    private final Position position;
    private boolean isActive=true;

    public int getScore() {
        return score;
    }


    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getActive(){
        return isActive;
    }
    public Position getPosition() {
        return position;
    }

    public Treasure(int score, Position position) {  //score>0
        this.score = score;
        this.position = position;
    }
}
