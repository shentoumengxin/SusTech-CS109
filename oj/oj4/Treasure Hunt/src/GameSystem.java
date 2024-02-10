import java.util.ArrayList;

public class GameSystem {
    ArrayList<Player>players=new ArrayList<>();
    Map map;
    public void addPlayer(Player player){
        players.add(player);
    }
    public Map newGame(int rows,int columns,Treasure...treasures){
        return map=new Map(rows,columns,treasures);
    }
    public Player getWinner(){
        Player winner=new Player(map,new Position(0,0));
        for (Player ply:players) {
            if(winner.getScore()<ply.getScore()){
                winner=ply;
            }
            if(winner.getScore()==ply.getScore()){
                if(winner.getSteps()>= ply.getSteps())winner=ply;
            }
        }
        return winner;
    }
}
