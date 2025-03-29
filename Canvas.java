import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

class Canvas extends JPanel {
    private List<Shape> shapes = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private Shape startShape = null;
    private Point startPort = null;
    private Shape selectedShape = null; // 用於記錄目前選取的物件
    private LinkType currentLinkType = LinkType.ASSOCIATION; // 預設連結類型

    public void addShape(Shape shape) {
        shapes.add(shape);
        shapes.sort((s1, s2) -> Integer.compare(s2.getDepth(), s1.getDepth()));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g, shape == selectedShape); // 如果是選取的物件，傳遞選取狀態
        }
        for (Link link : links) {
            link.draw(g);
        }
    }

    public Canvas() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Shape shape : shapes) {
                    if (shape.contains(e.getX(), e.getY())) {
                        startShape = shape;
                        startPort = findClosestPort(shape, e.getX(), e.getY());
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startShape != null) {
                    for (Shape shape : shapes) {
                        if (shape.contains(e.getX(), e.getY()) && shape != startShape) {
                            Point endPort = findClosestPort(shape, e.getX(), e.getY());
                            links.add(new Link(startShape, startPort, shape, endPort, currentLinkType));
                            break;
                        }
                    }
                }
                startShape = null;
                startPort = null;
                repaint();
            }
        });
    }

    private Point findClosestPort(Shape shape, int x, int y) {
        Point closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point port : shape.getConnectionPorts()) {
            double distance = port.distance(x, y);
            if (distance < minDistance) {
                minDistance = distance;
                closest = port;
            }
        }
        return closest;
    }

    public void selectShapeAt(int x, int y) {
        selectedShape = null; // 初始化為未選擇任何物件
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                selectedShape = shape; // 選取最上層的物件
                break;
            }
        }
        repaint(); // 更新畫布，顯示選取狀態
    }

    public void setCurrentLinkType(LinkType type) {
        this.currentLinkType = type;
    }
}
