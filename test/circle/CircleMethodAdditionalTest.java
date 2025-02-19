package circle;

import java.awt.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vlada
 */
public class CircleMethodAdditionalTest {
    
    // precision for real comparison
    public final static double EPSILON = 0.001;

    private Point a, b, c, d, e, cntr;
    private Circle cR12, cR13, cR14;
    
    
    //////////////////////////////
    // Test preparation methods //
    //////////////////////////////

    // The method annotated @Before is called before the execution of each test method
    // (it is important that the variables declared in the test class are reset 
    // to the same value before each test).
    @Before
    public void setUpBefore() {
        a = new Point(1, 2);
        b = new Point(2, 1);
        c = new Point(4, 1);
        d = new Point(8, 1);
        cntr = new Point(6,1);
        e = new Point(8, 4);

        cR12 = new Circle(c, d);
        cR13 = new Circle(c, d, Color.PINK);
        
        cR14 = Circle.createCircle(a, b);
    }
    
    // Other preparation methods :
    //
    // @After
    // The methods annotated @After are called after each test method has been executed.
    //
    // @BeforeClass and @AfterClass 
    // These annotations are used to respectively mark methods that execute once 
    // on startup and once on shutdown of the test class. The associated methods 
    // must be public, static, return no value (void) and accept no parameters.
    
    
    //////////////////
    // Test methods //
    //////////////////

    // The methods annotated @Test run automatically, in an unspecified order.
    @Test
    public void testR12() {
        
        sameCoordinates("R12: wrong center", cntr, cR12.getCenter());
        assertEquals("R12 : wrong radius", 2, cR12.getRadius(), EPSILON);
        assertEquals(Color.blue, cR12.getColor());
    }
    
    @Test
    public void testR13() {
        
        sameCoordinates("R13: wrong center", cntr, cR13.getCenter());
        assertEquals("R13 : wrong radius", 2, cR13.getRadius(), EPSILON);
        assertEquals(Color.PINK, cR13.getColor());
    }
    
    
    @Test
    public void testR14() {
        sameCoordinates("R14: wrong center", a, cR14.getCenter());
        assertEquals("R14 : wrong radius", 1.414213562, cR14.getRadius(), EPSILON);
        assertEquals(Color.blue, cR14.getColor());
    }
    
    
    /**
     * Check wether two points have the same coordinates.
     * @param message error message if the points don't have the same coordinates
     * @param p1 first point
     * @param p2 second point
     */
    public static void sameCoordinates(String message, Point p1, Point p2) {
        assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
        assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
    }
    
    
    //////////////////////
    // Main test method //
    //////////////////////

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(CircleMethodTest.class.getName());
    }
}
