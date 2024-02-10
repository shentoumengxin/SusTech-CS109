public class Shape {
    private double x;//图形共有的属性
    private double y;//图形共有的属性
    private static int screenSize = 10;//图形共有的属性
    private ShapeColor color = ShapeColor.GRAY;//图形共有的属性

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static int getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(int screenSize) {
        Shape.screenSize = screenSize;
    }

    public ShapeColor getColor() {
        return color;
    }

    public void setColor(ShapeColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return " x=" + x + ", y=" + y + ", color=" + color;
    }
}
