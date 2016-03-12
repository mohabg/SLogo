package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import data.CanvasData;
import data.Line;
import data.Point;
import data.TurtleData;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import slogo.Controller;
import slogo.Resources;

public class MyCanvas {

	private Canvas canvas;
	private Controller controller;
	private List<Color> palette;
	private ContextMenu backgroundContextMenu, turtleContextMenu;
	// private Color backgroundColor;
	private List<TurtleData> turtles, selectedTurtles;
	public static final Point CANVAS_MOUSE_OFFSET = new Point(-10, -24);

	public MyCanvas(int width, int height, Controller controller) {
		this.controller = controller;
		this.canvas = new Canvas(width, height);
		// TODO: resource
		this.palette = Arrays.asList(new Color[] { Color.ALICEBLUE, Color.ANTIQUEWHITE });
		this.backgroundContextMenu = new ContextMenu();
		this.turtleContextMenu = new ContextMenu();
		initControls();

		this.turtles = new ArrayList<TurtleData>();
		this.selectedTurtles = new ArrayList<TurtleData>();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void update(CanvasData data) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		drawLines(gc, data);

		// Color backgroundColor = data.getBackgroundColor();

		turtles = data.getTurtles();
		drawTurtles(gc, turtles);

		// palette = data.getPalette();
	}

	private void highlightImageView(ImageView imageView, Color color, double offset) {
		DropShadow ds = new DropShadow(offset, color);
		ds.setOffsetY(offset);
		ds.setOffsetX(offset);
		imageView.setEffect(ds);
	}

	private void drawTurtles(GraphicsContext gc, Collection<TurtleData> turtles) {
		for (TurtleData turtle : turtles) {
			if (!turtle.isVisible())
				continue;

			TurtleView imageView = new TurtleView(turtle, this);
			Bounds b = imageView.getBoundsInParent();
			double x = b.getMinX();
			double y = b.getMinY();

			SnapshotParameters params = new SnapshotParameters();
			params.setFill(Color.TRANSPARENT);
			Image rotated = imageView.snapshot(params, null);
			gc.drawImage(rotated, x, y);

			if (selectedTurtles.contains(turtle)) {
				// TODO: resources
				// highlightImageView(imageView, Color.CORAL, 4.0);

				gc.setStroke(Color.RED);
				gc.strokeRect(b.getMinX() - 2, b.getMinY() - 2, b.getWidth() + 4, b.getHeight() + 4);
			}
		}
	}

	private void drawLines(GraphicsContext gc, CanvasData data) {
		Collection<Line> lines = data.getLines();

		for (Line l : lines) { // TODO: stream
			Point a = convertCartesianToCanvasPos(l.getA());
			Point b = convertCartesianToCanvasPos(l.getB());
			int color = (int) l.getColor() % data.getPalette().size();
			gc.setStroke(data.getPalette().get(color));
			gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
		}
	}

	private Menu createColorSubmenu(String name, List<Color> palette) {
		Menu submenu = new Menu(name);
		for (Color color : palette) { // TODO: stream
			submenu.getItems().add(new MenuItem(color.toString()));
		}
		return submenu;
	}

	// TODO: refactor, combine with background
	private void updateTurtleContextMenu() {
		// TODO: place in resources
		Menu penColorSubmenu = createColorSubmenu("Select pen color", palette);
		MenuItem turtleImageSubmenu = new MenuItem("Select turtle image");
		for (MenuItem penColorItem : penColorSubmenu.getItems()) {
			penColorItem.setOnAction(e -> handleSelectPenColor(penColorItem.getText()));
		}
		// selectTurtleImage.setOnAction(e -> handleSelectTurtleImage());

		turtleContextMenu.getItems().clear();
		turtleContextMenu.getItems().addAll(penColorSubmenu, turtleImageSubmenu);
	}

	private void updateBackgroundContextMenu() {
		// TODO: place in resources
		Menu backgroundColorSubmenu = createColorSubmenu("Select background color", palette);
		for (MenuItem backgroundColorItem : backgroundColorSubmenu.getItems()) {
			backgroundColorItem.setOnAction(e -> handleSelectBackgroundColor(backgroundColorItem.getText()));
		}
		// selectBackgroundColor.setOnAction(e ->
		// handleSelectBackgroundColor());

		backgroundContextMenu.getItems().clear();
		backgroundContextMenu.getItems().addAll(backgroundColorSubmenu);
	}

	private void handleSelectBackgroundColor(String hex) {
		// TODO: refactor duplicate
		Color color = hex2Color(hex);
		int index = palette.indexOf(color);
		controller.compile("setbg " + index); // TODO: language
	}

	/*
	 * private String color2Hex (Color c) { int red = (int) (c.getRed() * 255);
	 * String redHex = Integer.toHexString(red); int green = (int) (c.getGreen()
	 * * 255); String greenHex = Integer.toHexString(green); int blue = (int)
	 * (c.getBlue() * 255); String blueHex = Integer.toHexString(blue);
	 * 
	 * return (redHex + greenHex + blueHex); }
	 */

	// private void updateBackgroundColor (String hex) {
	// canvas.setStyle("-fx-background-color: #" + hex + ";");
	// }

	private Color hex2Color(String hex) {
		return Color.web(hex);
	}

	private void handleSelectPenColor(String hex) {
		Color color = hex2Color(hex);
		int index = palette.indexOf(color);
		controller.compile("setpc " + index); // TODO: language
	}

	private void handleSelectTurtleImage() {

	}

	private void initControls() {
		canvas.setOnMouseClicked(e -> {
			backgroundContextMenu.hide();
			turtleContextMenu.hide();

			Point mouseCanvasPos = new Point(e.getSceneX(), e.getSceneY()).add(CANVAS_MOUSE_OFFSET);
			Point mouseScreenPos = new Point(e.getScreenX(), e.getScreenY());
			/*
			 * GraphicsContext gc = canvas.getGraphicsContext2D();
			 * gc.setFill(Color.DARKORANGE); gc.fillOval(mouseCanvasPos.getX(),
			 * mouseCanvasPos.getY(), 3, 3); gc.setFill(Color.DARKMAGENTA); //
			 * gc.fillOval(turtleView.getX(), turtleView.getY(), 3, 3);
			 */
			if (e.getButton() == MouseButton.PRIMARY) {
				handleLeftClick(mouseCanvasPos);
			} else if (e.getButton() == MouseButton.SECONDARY) {
				handleRightClick(mouseCanvasPos, mouseScreenPos);
			}
		});
	}

	private void handleLeftClick(Point mouseCanvasPos) {
		Resources.debugPrint("mouse: " + convertCanvasPosToCartesian(mouseCanvasPos));
		Collection<TurtleData> clickedTurtles = findTurtlesContainingCanvasPos(turtles, mouseCanvasPos);
		for (TurtleData turtle : clickedTurtles) { // TODO: stream
			Resources.debugPrint("turtle: " + turtle.getPosition());
			if (!selectedTurtles.remove(turtle)) { // toggle selection
				selectedTurtles.add(turtle);
			}
		}
		Resources.debugPrint(selectedTurtles.toString());
	}

	private void handleRightClick(Point mouseCanvasPos, Point mouseScreenPos) {
		double screenX = mouseScreenPos.getX();
		double screenY = mouseScreenPos.getY();

		Collection<TurtleData> clickedTurtles = findTurtlesContainingCanvasPos(turtles, mouseCanvasPos);
		if (clickedTurtles.isEmpty()) {
			updateBackgroundContextMenu();
			backgroundContextMenu.show(canvas, screenX, screenY);
		} else { // handle clicked turtle(s)
			updateTurtleContextMenu();
			turtleContextMenu.show(canvas, screenX, screenY);
		}
	}

	public Point convertCartesianToCanvasPos(Point myCartesian) {
		double x = myCartesian.getX() + canvas.getWidth() / 2;
		double y = canvas.getHeight() / 2 - myCartesian.getY();
		return new Point(x, y, myCartesian.getTheta());
	}

	private Point convertCanvasPosToCartesian(Point canvasPos) {
		double x = canvasPos.getX() - canvas.getWidth() / 2;
		double y = canvas.getHeight() / 2 - canvasPos.getY();
		return new Point(x, y, canvasPos.getTheta());
	}

	private Collection<TurtleData> findTurtlesContainingCanvasPos(Collection<TurtleData> turtles, Point canvasPos) {
		Collection<TurtleData> ret = new ArrayList<TurtleData>();
		for (TurtleData turtle : turtles) { // TODO: stream
			TurtleView turtleView = new TurtleView(turtle, this);
			Point2D p = new Point2D(canvasPos.getX(), canvasPos.getY());
			if (turtleView.contains(p)) {
				ret.add(turtle);
			}
		}
		return ret;
	}
}
