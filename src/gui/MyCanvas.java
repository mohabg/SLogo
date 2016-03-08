package gui;

import java.util.List;
import data.CanvasData;
import data.Line;
import data.Point;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import slogo.Controller;

public class MyCanvas {
	Canvas canvas;
	private ContextMenu contextMenu;
	private int width;
	private int height;

	public MyCanvas(int width, int height) {
		this.canvas = new Canvas(width, height);
		this.width = width;
		this.height = height;
		initContextMenu();
		initControls();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void update(CanvasData data) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);

		drawTurtle(gc, data);
		drawLines(gc, data);
	}

	private void drawTurtle(GraphicsContext gc, CanvasData data) {
		Image image = data.getTurtleImage();

		List<Point> posList = data.getTurtlePosition();

		for (Point p : posList) {
			ImageView imageView = new ImageView(image);
			Point draw = getCartesianPos(p);

			double x = draw.getX() - image.getWidth() / 2;
			double y = draw.getY() - image.getHeight() / 2;
			imageView.setRotate(p.getTheta());

			SnapshotParameters params = new SnapshotParameters();
			params.setFill(Color.TRANSPARENT);
			Image rotated = imageView.snapshot(params, null);
			gc.drawImage(rotated, x, y);
		}
	}

	private void drawLines(GraphicsContext gc, CanvasData data) {
		List<Line> lines = data.getLines();

		for (Line l : lines) { // TODO: stream
			Point a = getCartesianPos(l.getA());
			Point b = getCartesianPos(l.getB());
			gc.setStroke(data.getPenColor());
			gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
		}
	}

	private void initContextMenu() {
		contextMenu = new ContextMenu();
		MenuItem selectBackgroundColor = new MenuItem("Select background color");
		MenuItem selectPenColor = new MenuItem("Select pen color");
		contextMenu.getItems().addAll(selectBackgroundColor, selectPenColor);
		selectBackgroundColor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Select background color");
				// TODO: use lambda
			}
		});
		selectPenColor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Select pen color");
				// TODO: use lambda
			}
		});
	}

	private void initControls() {
		canvas.setOnMouseClicked(e -> handleMouseClicked(e));
	}

	private void handleMouseClicked(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			handleLeftClick();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			handleRightClick(e);
		}
	}

	private void handleLeftClick() {
		contextMenu.hide();
	}

	private void handleRightClick(MouseEvent e) {
		double x = e.getScreenX();
		double y = e.getScreenY();
		contextMenu.show(canvas, x, y);
	}

	private Point getCartesianPos(Point pos) {
		double x = pos.getX() + canvas.getWidth() / 2;
		double y = canvas.getHeight() / 2 - pos.getY();
		Point newPos = new Point(x, y, pos.getTheta());
		return newPos;
	}

}
