package slogo;

import data.Point;
import data.StampData;
import javafx.scene.image.Image;


public class MyStamp implements StampData {
    private Image image;
    private Point pos;

    public MyStamp (Image image, Point pos) {
        this.image = image;
        this.pos = pos;
    }

    @Override
    public Image getImage () {
        return image;
    }

    @Override
    public Point getPos () {
        return pos;
    }

}
