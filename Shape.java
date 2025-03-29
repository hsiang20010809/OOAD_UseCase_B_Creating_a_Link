import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

abstract class Shape {
    protected int x, y;
    protected int depth; // 深度屬性

    public Shape(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void draw(Graphics g, boolean isSelected);

    public abstract boolean contains(int mouseX, int mouseY); // 檢查是否點擊到物件

    public abstract List<Point> getConnectionPorts(); // 返回所有連接點
}
