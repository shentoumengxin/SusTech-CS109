import controller.GameController;
import model.Chessboard;
import view.ChessGameFrame;
import view.ChessboardComponent;
import view.WelcomeFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame welcomeFrame = new WelcomeFrame(500, 500);
            welcomeFrame.setVisible(true);
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(0));
            gameController.registerFrame(mainFrame);
            mainFrame.setModel(gameController.getModel());
            mainFrame.setView(gameController.getView());
            mainFrame.setGameController(gameController);
            welcomeFrame.registerFrame(mainFrame);
            //💣❌💎⚪▲🔶
        });
    }
}
