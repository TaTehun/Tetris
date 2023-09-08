
import java.awt.*;

public class PauseButton {
    public void add(Graphics g, Point origin, int length) {
        g.setFont(new Font("default", Font.BOLD, length));
        g.setColor(new Color(0, 112, 192));
        g.drawRect(origin.x + length, origin.y, length * 4, length * 2);
        g.drawString("PAUSE", origin.x + length * 3 / 2, origin.y + length * 3 / 2);
    }
}
