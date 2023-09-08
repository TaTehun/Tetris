import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainArea extends Canvas implements MouseMotionListener, MouseListener{

    private static List<Color> palette = Collections.unmodifiableList(Arrays.asList(
        Color.white,  // NULL
        new Color(0, 112, 192),  // J
        new Color(255, 0, 0),  // L
        new Color(255, 255, 0),  // S
        new Color(0, 176, 80)  //  C
    ));
    

    private Point mainArea;
    private Point quitPosition;
    private int squareLen;
    private boolean pause = false;
    private boolean cheat = false;

    private GameBoard board;
    private Point rightArea;

    public MainArea(GameBoard board) {
        this.board = board;

        addMouseMotionListener(this);
        addMouseListener(this);
    }


    public void paint(Graphics g)

    {
        Dimension d = getSize();
        squareLen = Math.min(d.width / (board.w + 10), d.height / (board.h + 9));

        int leftbound = (d.width - (board.w + 6) * squareLen) / (2 * squareLen);
        int upperbound = (d.height - (board.h - 1) * squareLen) / (2 * squareLen);

        mainArea = new Point((leftbound - 1) * squareLen, upperbound * squareLen);
        rightArea = new Point((leftbound + board.w) * squareLen, (upperbound + 1) * squareLen);

        this.drawScore(g, leftbound, upperbound);

        this.drawBoard(g, mainArea, leftbound, upperbound);

        this.drawShape(g, mainArea, board.getCurrentShape());

        this.drawShape(g, rightArea, board.getNextShape());

        this.drawPause(g);

        this.drawQuit(g, leftbound, upperbound);

        this.repaint();
    }


    private void drawShape(Graphics g, Point origin, Shapes shape) {

        Color color = board.over ? Color.GRAY : getColor(shape.getType());

        for (Shapes.Coordinate cube : shape.getShape()) {
            this.drawRect(g, origin, cube.x, cube.y, color);
        }
    }

    private void drawBoard(Graphics g, Point origin, int leftbound, int upperbound) {

        g.drawRect(leftbound * squareLen, upperbound * squareLen, (board.w - 2) * squareLen, (board.h - 1) * squareLen);
        g.drawRect((leftbound + board.w) * squareLen, upperbound * squareLen, 6 * squareLen, 4 * squareLen);

        int i = 0;
        for (int[] line : board.area) {
            for (int j = 0; j < board.w; j++) {
                if (line[j] == 0 || line[j] == Integer.MAX_VALUE)
                    continue;
                this.drawRect(g, origin, j, i,
                        board.over ? Color.GRAY : getColor(Shapes.ShapeType.values()[line[j]]));
            }
            i++;
        }
    }

    private void drawQuit(Graphics g, int leftbound, int upperbound) {
        this.quitPosition = new Point((leftbound + board.w) * squareLen, (upperbound + board.h - 2) * squareLen);
        QuitButton.QuitButton(g, quitPosition, squareLen);
    }

    private void drawPause(Graphics g) {
        if (pause) {
            Point pausePosition = new Point(mainArea.x + ((board.w - 6) / 2) * squareLen, mainArea.y + (board.h - 3) / 2 * squareLen);
            PauseButton pause = new PauseButton();
            pause.add(g, pausePosition, squareLen);
        }
    }

    private void drawScore(Graphics g, int leftbound, int upperbound) {
        TextDec text = new TextDec();
        Point levelPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 8) * squareLen);
        text.setString(g, "Level:", levelPosition, squareLen);
        text.setValue(g, board.level, levelPosition, squareLen);

        Point linesPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 10) * squareLen);
        text.setString(g, "Lines:", linesPosition, squareLen);
        text.setValue(g, board.line, linesPosition, squareLen);
        
        Point scoresPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 12) * squareLen);
        text.setString(g, "Score:", scoresPosition, squareLen);
        text.setValue(g, board.score, scoresPosition, squareLen);
    }

    private void drawRect(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
        g.setColor(Color.black);
        g.drawRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
    }

    static Color getColor(Shapes.ShapeType color) {
        return MainArea.palette.get(color.ordinal());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (board.over)
            return;
        int x = e.getX(), y = e.getY();
        boolean MouseInMainArea =
                x >= mainArea.getX() + squareLen &&
                        x <= mainArea.getX() + (board.w - 1) * squareLen &&
                        y >= mainArea.getY() &&
                        y <= mainArea.getY() + (board.h - 1) * squareLen;

        if (pause != MouseInMainArea) {
            pause = MouseInMainArea;
            if (pause)
                this.board.pause();
            else {
                this.board.resume();
                cheat = false;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX(), y = e.getY();
        if (!e.isConsumed()) {
            if (x >= quitPosition.getX() && x <= quitPosition.getX() + squareLen * 14 / 5 &&
                    y >= quitPosition.getY() && y <= quitPosition.getY() + squareLen * 3 / 2) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
    }
}