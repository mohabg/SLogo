package data;

import javafx.scene.paint.Color;


public class Line {
    private Point a, b;
    private Color color;
    private int thickness;

    @Deprecated
    public Line (Point a, Point b) {
        this.a = a.clone();
        this.b = b.clone();
    }

    public Line (Point a, Point b, Color color, int thickness) {
        this.a = a.clone();
        this.b = b.clone();
        this.color = color;
        this.thickness = thickness;
    }

    public Point getA () {
        return a;
    }

    public Point getB () {
        return b;
    }

    public Color getColor () {
        return color;
    }

    public int getThickness () {
        return thickness;
    }
}
