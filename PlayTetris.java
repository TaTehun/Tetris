import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlayTetris extends Frame {

    static PlayTetris tg;
    static GameBoard b;

    public static void main(String[] args) {
        initializeGame();
    }

    PlayTetris(int speed, int w, int h) {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(1500, 900);
        b = new GameBoard(speed, w, h);
        add("Center", new MainArea(b));
        setVisible(true);
    }

    public static void initializeGame() {
        int speed = 1;
        int width = 10;
        int height = 20;

        tg = new PlayTetris(speed, width, height);
    }
}
