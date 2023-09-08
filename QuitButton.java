
import java.awt.*;

public class QuitButton {
    public static void QuitButton(Graphics g, Point origin, int length) {
        g.setFont(new Font("default", Font.BOLD, length * 2 / 3));
        g.setColor(Color.black);
        g.drawRect(origin.x, origin.y - length / 2, length * 14 / 5, length * 3 / 2);
        g.drawString("QUIT", origin.x + length / 2, origin.y + length / 2);
    }
}
