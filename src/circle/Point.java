package circle;

/**
 * Geometric point in a plane with a cartesian coordinate system.
 * 
 * @author Xavier Cregut, Sebastion Leriche, Nicolas Saporito
 * @version 2024.12.05
 */
public class Point {

    private double x;
    private double y;

    /**
     * Construct a point from its abscissa and its ordinate.
     * @param x abscissa
     * @param y ordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** 
     * Get this point abscissa.
     * @return this point abscissa
     */
    public double getX() {
        return this.x;
    }

    /** 
     * Get this point ordinate.
     * @return this point ordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Distance from another point.
     * @param other the other point
     * @return distance between this point et other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2)
                + Math.pow(other.y - this.y, 2));
    }

    /**
     * Translate this point.
     * @param dx displacement along the X axis
     * @param dy displacement along the Y axis
     */
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * Create a copy of a point. 
     * This method must be overriden in every Point subclass.
     * @param p the point to copy
     * @return a copy of p
     */
    public static Point newInstance(Point p) {
        return new Point(p.getX(), p.getY());
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
