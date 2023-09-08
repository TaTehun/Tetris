import java.awt.*;

public class TextDec {
    public void setString(Graphics g, String s, Point origin, int length) {
        // Set text size.
        g.setFont(new Font("default", Font.BOLD, length * 2 / 3));
        g.drawString(s, origin.x, origin.y);

    }
    public void setValue(Graphics g, int i, Point origin, int length) {
        g.drawString(Integer.toString(i), origin.x + length * 9 / 2, origin.y);
    }
}
