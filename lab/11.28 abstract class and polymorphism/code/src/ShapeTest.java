import java.util.ArrayList;

public class ShapeTest {

	public static void main(String[] args) {
		Shape.setScreenSize(9);
		StdDraw.setXscale(-Shape.getScreenSize(), Shape.getScreenSize());
		StdDraw.setYscale(-Shape.getScreenSize(), Shape.getScreenSize());
		ArrayList<Circle> circleList = new ArrayList<>();
		ArrayList<Rectangle> rectangleList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			circleList.add(new Circle(1, 4 * i + 1, 1));
			rectangleList.add(new Rectangle(4 * i + 1, -1, 1, 1));
		}
		for (Circle circle : circleList) {
			circle.checkColor();
			System.out.println(circle);
			circle.draw();
		}
		for (Rectangle rectangle : rectangleList) {
			rectangle.checkColor();
			System.out.println(rectangle);
			rectangle.draw();
		}

	}
}
