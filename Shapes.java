import java.util.*;

public class Shapes {

    public enum ShapeType {
        NULL, J, L, S, O
    }

    static final Coordinate[] bases = new Coordinate[]{
            new Coordinate(-1, -1),
            //  J
            new Coordinate(1, 0),
            //  L
            new Coordinate(1, 0),
            //  S
            new Coordinate(1, -1),
            //  O
            new Coordinate(1, -1),
    };
    static final List<List<List<Coordinate>>> shapes = Collections.unmodifiableList(Arrays.asList(
            Collections.emptyList(),
            //  J
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(2, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(0, 2)
                    )
            ),

            // L
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(2, 0)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(0, 2),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),

            // S
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(0, 2),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),

            // O
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 1),
                            new Coordinate(2, 2)
                    )
            )
    ));

    final static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    //    Color color;
    ShapeType type;
    int status;
    Coordinate base;
    List<List<Coordinate>> coordinates;

    public Shapes(ShapeType type) {
        this.type = type;
        this.base = bases[type.ordinal()];
        this.coordinates = Shapes.shapes.get(type.ordinal());
    }

    public ShapeType getType() {
        return type;
    }

    public List<Coordinate> getShape() {
        List<Coordinate> ret = new ArrayList<>();
        this.coordinates.get(status).forEach(c -> ret.add(new Coordinate(this.base.x + c.x, this.base.y + c.y)));
        return ret;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "type=" + type +
                ", status=" + status +
                ", base=" + base +
                '}';
    }
}
