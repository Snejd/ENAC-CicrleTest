package circle;

import java.awt.Color;
import java.util.Objects;
import java.lang.Math;

/**
 * Circle in a plane with a cartesian coordinate system.
 * Implements Measurable2D (R6)
 */
public class Circle implements Measurable2D{
    
    private double diametr;
    private final Point center;
    private Color color;
    public static final double PI = Math.PI; // Class constant (C5)
    

    // Constructers (overloaded)
    //TODO assert sensible values
    
    /**
     * Construct a circle from its center and its radius. 
     * Default blue Color.
     * @param c center
     * @param r radius
     */
    public Circle(Point c, double r) {
        this.diametr = r*2;
        this.center = Point.newInstance(c); // create copy of point (R18)
        this.color = Color.blue;
    }
    
    /**
     * Construct a circle from its center and its radius and its color.
     * @param c center
     * @param r radius
     * @param col color
     */
    public Circle(Point c, double r, Color col) {
        this.diametr = r*2;
        this.center = Point.newInstance(c); // create copy of point (R18)
        this.color = col;
    }
    
    /**
     * Construct a circle from two diametrically opposed point. 
     * Default blue Color.
     * @param a point 1
     * @param b point 2
     */
    public Circle(Point a, Point b) {
        this.diametr = a.distance(b); // calculate distance between points using Point method
        
        // calculate center of two points and use it as a new point (center of a circle)
        this.center = new Point( (a.getX()+b.getX())/2 , (a.getY()+b.getY())/2 ); 
        this.color = Color.blue;
    }
    
    /**
     * Construct a circle from two diametrically opposed point and its color.
     * Default blue Color.
     * @param a point 1
     * @param b point 2
     * @param col color
     */
    public Circle(Point a, Point b, Color col) {
        this.diametr = a.distance(b);// calculate distance between points using Point method
        
        // calculate center of two points and use it as a new point (center of a circle)
        this.center = new Point( (a.getX()+b.getX())/2 , (a.getY()+b.getY())/2 );
        
        this.color = col;
    }

    
    
    
    // Circle Methods
    
    /**
     * Construct a circle from two points,
     * the first corresponding to the center of the circle and
     * the second being a point of the circle (on the circumference)
     * 
     * is class method (=static) (R14)
     * 
     * @param a center of the circle
     * @param b point of the circle
     * @return new Circle
     */
    public static Circle createCircle(Point a, Point b){
        // compact of "make a new circle with a as centre point and
        // distance between a and b multiplied by 2 as a diameter
        // also set color as blue (explicitly if we decided to change default values)
        return new Circle(a, (a.distance(b))*2, Color.blue);
    }
            
    /**
     * Translate a circle by specifying a displacement along the X axis and 
     * a displacement along the Y axis
     * @param x displacement along the X axis
     * @param y displacement along the Y axis
     */
    public void translate(double x, double y) {
        this.center.translate(x, y); // use Point method
    }

    /**
     * Check if a point is inside (in the broad sense) a circle
     * @param a point to be checked
     * @return true or false
     */
    public boolean contains(Point a) {
        // The point is inside the circle, if the distance between circle center and
        // given point is less than its radius so...
        double distanceFromCentre = this.center.distance(a); //... measure distance
        return distanceFromCentre <= this.diametr/2; // compare and return boolean
    }

    
    
    // Getters
    
    /**
     * Obtain its perimeter defined as 2*pi*r
     * Implements Measurable2D (R6)
     * @return double perimeter
     */
    @Override // Override Measurable2D (R6)
    public double getPerimeter() {
        return (PI*this.diametr);
    }
    
    /**
     * Obtain its Area defined as pi*r^2
     * Implements Measurable2D (R6)
     * @return double Area
     */
    @Override // Override Measurable2D (R6)
    public double getArea() {
       return ((PI*(this.diametr*this.diametr)/4));
    }
    
    /**
     * Obtain its diameter
     * @return double diameter
     */
    public double getRadius() {
        return this.diametr/2;
    }
    
    /**
     * Obtain its center
     * @return newInstance of Point center
     */
    public Point getCenter() {
        // Return a copy (R18)
        return Point.newInstance(this.center); 
    }

    /**
     * Obtain its center
     * @return newInstance of Point center
     */
    public Color getColor() {
        // Color class is alredy immutable (R18)
        return this.color;
    }

    /**
     * Obtain its diameter
     * @return double diameter
     */
    public double getDiameter() {
        return this.diametr;
    }

    
    
    // Setters
    
    /**
     * Set its Color
     * @param color Color new circle color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set its Color
     * @param diametr new circle diameter
     */
    public void setDiameter(double diametr) {
        this.diametr = diametr;
    }
    
    /**
     * Set its Radius
     * @param radius new circle radius
     */
    public void setRadius(double radius) {
        this.diametr = radius*2;
    }
    
    
    
    @Override // nice print (R15)
    public String toString() {
        return "C" + this.diametr/2 + '@' + this.center.toString();
    }


}
