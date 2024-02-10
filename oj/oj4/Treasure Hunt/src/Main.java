import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    @Test
    public void Test01checkEqualScore() {
        Treasure[] treasures = new Treasure[3];
        treasures[0] = new Treasure(5, new Position(1, 0));
        treasures[1] = new Treasure(5, new Position(0, 2));
        treasures[2] = new Treasure(5, new Position(2, 3));
        GameSystem game = new GameSystem();
        Map map = game.newGame(4, 4, treasures);
        Player player1 = new Player(map, new Position(0, 0));
        Player player2 = new Player(map, new Position(2, 0));
        Player player3 = new Player(map, new Position(0, 3));
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        assertTrue(player3.move(Direction.DOWN, 2));
        assertFalse(player3.move(Direction.RIGHT, 1));
        assertEquals(5, player3.getScore());
        assertTrue(new Position(2, 3).equals(player3.getPosition()));
        assertTrue(player2.move(Direction.UP, 1));
        assertEquals(5, player2.getScore());
        assertTrue(new Position(1, 0).equals(player2.getPosition()));
        assertTrue(player1.move(Direction.RIGHT, 2));
        assertEquals(5, player1.getScore());
        assertTrue(new Position(0, 2).equals(player1.getPosition()));

        assertEquals(2, game.getWinner().getId());
    }

    @Test
    public void Test02checkStepsAndMapActive() {
        GameSystem game = new GameSystem();
        Treasure[] treasures = new Treasure[3];
        treasures[0] = new Treasure(3, new Position(0, 0));
        treasures[1] = new Treasure(5, new Position(2, 2));
        treasures[2] = new Treasure(7, new Position(3, 1));

        Map map = game.newGame(4, 3, treasures);

        Player player1 = new Player(map, new Position(0, 1), 5);
        Player player2 = new Player(map, new Position(3, 2), 0);
        game.addPlayer(player1);
        game.addPlayer(player2);

        assertFalse(player2.move(Direction.UP, 3));//zero step
        assertTrue(new Position(3, 2).equals(player2.getPosition()));

        assertTrue(player1.move(Direction.LEFT, 1));
        assertTrue(player1.move(Direction.DOWN, 3));
        assertFalse(player1.move(Direction.RIGHT, 2));//more steps
        assertTrue(new Position(3, 1).equals(player1.getPosition()));
        assertEquals(10, player1.getScore());
        assertTrue(map.isActive());
    }

    @Test
    public void Test03checkStopGame(){
        GameSystem game = new GameSystem();
        Treasure[] treasures = new Treasure[3];
        treasures[0] = new Treasure(3, new Position(1, 0));
        treasures[1] = new Treasure(5, new Position(2, 0));
        treasures[2] = new Treasure(7, new Position(3, 1));
        Map map = game.newGame(4, 3, treasures);
        Player player1 = new Player(map, new Position(0, 0));

        assertTrue(player1.move(Direction.DOWN,3));
        assertTrue(player1.move(Direction.RIGHT,1));
        assertFalse(player1.move(Direction.UP,2));
        assertFalse(map.isActive());
        assertTrue(new Position(3,1).equals(player1.getPosition()));
    }

    @Test
    public void Test03checkStopGame2(){
        GameSystem game = new GameSystem();
        Treasure[] treasures = new Treasure[3];
        treasures[0] = new Treasure(3, new Position(1, 0));
        treasures[1] = new Treasure(5, new Position(2, 0));
        treasures[2] = new Treasure(7, new Position(3, 1));
        Map map = game.newGame(4, 3, treasures);
        Player player1 = new Player(map, new Position(0, 0));

        assertTrue(player1.move(Direction.DOWN,3));
        assertFalse(player1.move(Direction.RIGHT,3));
        assertFalse(map.isActive());
        assertTrue(new Position(3,1).equals(player1.getPosition()));
    }

    @Test
    public void Test04ExampleInDocument(){
        GameSystem game = new GameSystem();

        Treasure[] treasures = new Treasure[4];
        treasures[0] = new Treasure(5, new Position(0, 1));
        treasures[1] = new Treasure(10, new Position(0, 2));
        treasures[2] = new Treasure(2, new Position(0, 4));
        treasures[3] = new Treasure(15, new Position(1, 3));

        Map map = game.newGame(3, 5, treasures);

        Player player1 = new Player(map, new Position(0, 0));
        Player player2 = new Player(map, new Position(1, 4), 4);
        Player player3 = new Player(map, new Position(0, 3));

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        player1.move(Direction.DOWN, 2);
        player1.move(Direction.RIGHT, 2);
        player1.move(Direction.UP, 4); //player1 can only move 2 steps UP before hitting the boundary

        player2.move(Direction.LEFT, 2);
        player2.move(Direction.UP, 1); //player2 cannot get the treasure at (0,2), which is already obtained by player1

        player1.move(Direction.LEFT, 3); //player1 can only move 2 steps LEFT before hitting the boundary

        player2.move(Direction.RIGHT, 2); //player2 can only move 1 step RIGHT before reaching the maximum steps allowed

        player3.move(Direction.RIGHT, 1);
        player3.move(Direction.DOWN, 2); //player3 cannot move because all treasures have been found and the map is inactive

        assertEquals (8,player1.getSteps());
        assertTrue (player1.getPosition().equals(new Position(0, 0)));
        assertEquals (15,player1.getScore());

        assertEquals (4,player2.getSteps());
        assertTrue (player2.getPosition().equals(new Position(0, 3)));
        assertEquals (15,player2.getScore());

        assertEquals (1,player3.getSteps());
        assertTrue (player3.getPosition().equals(new Position(0, 4)));
        assertEquals (2,player3.getScore());

        Player winner = game.getWinner();
        assertEquals(winner.getId(), player2.getId()); //player2 and player1 have the same scores, but player2 takes less steps, so player2 is the winne
    }

    @Test
    public void Test05isActive(){
        Treasure[] treasures = new Treasure[]{
                new Treasure(3, new Position(5, 5)),
                new Treasure(8, new Position(2, 3)),
                new Treasure(6, new Position(2, 1))
        };
        Map map = new Map(6, 6, treasures);
        Player player1 = new Player(map,new Position(4,5),5);
        Player player2 = new Player(map,new Position(2,4));
        Player player3 = new Player(map,new Position(5,2));
        assertEquals(true,map.isActive());
        assertEquals(3,map.hasTreasure(new Position(5, 5)));
        player1.move(Direction.DOWN,2);
        assertEquals(0,map.hasTreasure(new Position(5, 5)));
        assertEquals(8,map.hasTreasure(new Position(2, 3)));
        assertEquals(true,map.isActive());
        player2.move(Direction.LEFT,2);
        assertEquals(0,map.hasTreasure(new Position(2, 3)));
        assertEquals(6,map.hasTreasure(new Position(2, 1)));
        player3.move(Direction.LEFT,1);
        assertEquals(true,map.isActive());
        player3.move(Direction.UP,3);
        assertEquals(0,map.hasTreasure(new Position(3, 1)));
        assertEquals(false,map.isActive());

    }
    public static void main(String[] args){

    }
}