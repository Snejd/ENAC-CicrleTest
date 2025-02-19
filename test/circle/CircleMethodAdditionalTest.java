package circle;

import static circle.CircleMethodTest.sameCoordinates;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vlada
 */
public class CircleMethodAdditionalTest {
    
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
    
}
