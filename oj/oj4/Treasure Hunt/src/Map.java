import java.lang.reflect.Constructor;

public class Map {
    private final int rows;
    private final int columns;
    private  Treasure[] treasures;
    private boolean isActive;
    private int num_treasures;

    public boolean isActive() {
        if(num_treasures==0)isActive=false;
        return isActive;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Map(int rows, int columns, Treasure[] treasures) {
        this.rows = rows-1;
        this.columns = columns-1;
        this.treasures=treasures;
        this.num_treasures=treasures.length;
        this.isActive=true;
    }
    public int hasTreasure(Position position){
        for (Treasure treasure:treasures) {
            if (treasure.getPosition().equals(position)){
                if(treasure.getActive())return treasure.getScore(); //active
                else continue; //inactive
            }else{
                continue;
            }
        }
        return 0;

    }

    public void checkActive() {
        if(num_treasures==0){
            this.isActive=false;
            return;
        }
    }

    public void update(Position position){
        if(num_treasures==0){
            this.isActive=false;
            return;
        }
        for (Treasure treasure:treasures) {
            if (treasure.getPosition().equals(position)){
                treasure.setActive(false);
                num_treasures--;
            }
        }

    }
}
