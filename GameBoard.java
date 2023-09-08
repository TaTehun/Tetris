import java.util.LinkedList;

public class GameBoard {

    Shapes currentShape;
    Shapes nextShape;
    boolean over = false;

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    int w;
    int h;
    LinkedList<int[]> area;

    int level = 1;
    int line = 0;
    int score = 0;
    int speed;

    public GameBoard(int speed, int w, int h) {
        this.w = w + 2;
        this.h = h + 1;
        this.area = new LinkedList<>();
        initialize();
    }

    private void initialize() {

        int[] line;

        for (int i = 0; i < this.h - 1; i++) {
            line = new int[this.w];

            line[0] = line[this.w - 1] = Integer.MAX_VALUE;
            for (int j = 1; j <= this.w - 2; j++)
                line[j] = 0;

            this.area.add(line);
        }

        line = new int[this.w];
        for (int i = 0; i < this.w; i++)
            line[i] = Integer.MAX_VALUE;
        this.area.add(line);

        int[] lastLine = area.get(h - 2);
        lastLine[w - 2] = Shapes.ShapeType.J.ordinal();
        lastLine[w - 3] = Shapes.ShapeType.J.ordinal();
        area.get(h - 3)[w - 2] = Shapes.ShapeType.J.ordinal();
        area.get(h - 4)[w - 2] = Shapes.ShapeType.J.ordinal();
    
        lastLine[w - 4] = Shapes.ShapeType.S.ordinal();
        lastLine[w - 5] = Shapes.ShapeType.S.ordinal();
        area.get(h - 3)[w - 3] = Shapes.ShapeType.S.ordinal();
        area.get(h - 3)[w - 4] = Shapes.ShapeType.S.ordinal();

        currentShape = new Shapes(Shapes.ShapeType.O);
        currentShape.base = new Shapes.Coordinate(w / 3, 1);
    
        nextShape = new Shapes(Shapes.ShapeType.L);
        nextShape.base = new Shapes.Coordinate(2, 0); 

    }
    
    public Shapes getNextShape() {
        return nextShape; 
    }

    public Shapes getCurrentShape() {
        return currentShape;
    }

    public void stop() {
        running = false;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }
}
