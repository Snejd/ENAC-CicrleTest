package circle;

import java.awt.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test methods of Circle class. 
 * JUnit4 Naming convention: 
 * Always use the "Test" suffix at the end of test classes names.
 *
 * @author Xavier Cregut, Sebastien Leriche, Nicolas Saporito
 * @version 2024.12.05
 */
public class CircleMethodTest {

    // precision for real comparison
    public final static double EPSILON = 0.001;

    private Point a, b, c, d, e;
    private Circle c1, c1Bis, c2, c2Bis;
    
    
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
        e = new Point(8, 4);

        c1 = new Circle(a, 2.5);
        c1Bis = new Circle(a, 2.5, Color.green);
        c2 = new Circle(c, d);
        c2Bis = new Circle(c, d, Color.yellow);
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
    public void testR1() {
        c1.translate(10, 20);
        sameCoordinates("R1: C1", new Point(11, 22), c1.getCenter());
        c2.translate(3, -1);
        sameCoordinates("R1: C2", new Point(9, 0), c2.getCenter());
    }

    @Test
    public void testR2() {
        sameCoordinates("R2: C1", a, c1.getCenter());
        sameCoordinates("R2: C2", new Point(6, 1), c2.getCenter());
    }

    @Test
    public void testR3() {
        assertEquals("R3: C1", 2.5, c1.getRadius(), EPSILON);
        assertEquals("R3: C2", 2.0, c2.getRadius(), EPSILON);
    }

    @Test
    public void testR4() {
        assertEquals("R4: C1", 5.0, c1.getDiameter(), EPSILON);
        assertEquals("R4: C2", 4.0, c2.getDiameter(), EPSILON);
    }

    @Test
    public void testR5() {
        assertTrue("R5", c1.contains(a));
        assertTrue("R5", c1.contains(b));
        assertFalse("R5", c1.contains(c));
        assertFalse("R5", c1.contains(d));
        assertFalse("R5", c1.contains(e));
        assertFalse("R5", c1.contains(c));
        assertFalse("R5", c1.contains(new Point(3, 4)));
        assertTrue("R5", new Circle(d, 3).contains(e));
    }

    @Test
    public void testR6Values() {
        assertEquals("R6", 5 * Math.PI, c1.getPerimeter(), EPSILON);
        assertEquals("R6", 4 * Math.PI, c2.getPerimeter(), EPSILON);
        assertEquals("R6", 6.25 * Math.PI, c1.getArea(), EPSILON);
        assertEquals("R6", 4 * Math.PI, c2.getArea(), EPSILON);
    }

    @Test
    public void testR8() {
        assertEquals("R8", Color.blue, c1.getColor());
        assertEquals("R8", Color.yellow, c2Bis.getColor());
    }

    @Test
    public void testR9() {
        c1.setColor(Color.red);
        assertEquals("R9", Color.red, c1.getColor());
    }

    @Test
    public void testR10() {
        sameCoordinates("R10: wrong center", a, c1.getCenter());
        assertEquals("R10: wrong radius", 2.5, c1.getRadius(), EPSILON);
        assertEquals(Color.blue, c1.getColor());
    }

    @Test
    public void testR11() {
        sameCoordinates("R11: wrong center", a, c1Bis.getCenter());
        assertEquals("R11 : wrong radius", 2.5, c1Bis.getRadius(), EPSILON);
        assertEquals(Color.green, c1Bis.getColor());
    }

    @Test
    public void testR15() {
        assertEquals("R15: toString() method overriden? Correctly?",
                "C2.5@(1.0, 2.0)", c1.toString());
    }

    @Test
    public void testR16() {
        c1.setRadius(10.0);
        assertEquals("R16", 10, c1.getRadius(), EPSILON);
        c1.setRadius(20.0);
        assertEquals("R16", 20, c1.getRadius(), EPSILON);
    }

    @Test
    public void testR17() {
        c1.setDiameter(10.0);
        assertEquals("R17", 5, c1.getRadius(), EPSILON);
        c1.setDiameter(20.0);
        assertEquals("R17", 10, c1.getRadius(), EPSILON);
    }

    @Test
    public void testR18() {
        c1.getCenter().translate(10, 20);
        sameCoordinates("R18: center translation error. ", new Point(1, 2), c1.getCenter());
        a.translate(10, 20);
        sameCoordinates("R18 : error when translating A. ", new Point(1, 2), c1.getCenter());
    }
    
    
    ///////////////////////////////
    // Methods used by the tests //
    ///////////////////////////////

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
