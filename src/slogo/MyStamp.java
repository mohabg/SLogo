package slogo;

import data.Point;
import data.StampData;
import javafx.scene.image.Image;


public class MyStamp implements StampData {
    private String image;
    private Point position;

    public MyStamp (String image, Point position) {
        this.image = image;
        this.position = position;
    }

    @Override
    public String getImage () {
        return image;
    }

    @Override
    public Point getPos () {
        return position;
    }

}
