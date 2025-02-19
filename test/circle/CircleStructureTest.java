package circle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * Test structure of Circle class. 
 * JUnit4 Naming convention: 
 * Always use the "Test" suffix at the end of test classes names.
 *
 * @author Xavier Cregut, Sebastien Leriche, Nicolas Saporito
 * @version 2024.12.05
 */
public class CircleStructureTest {

    private Class circleClass;

    //////////////////////////////
    // Test preparation methods //
    //////////////////////////////

    // The method annotated @Before is called before the execution of each test method
    // (it is important that the variables declared in the test class are reset 
    // to the same value before each test).
    @Before
    public void setUpBefore() {
        this.circleClass = Circle.class;
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
    public void testInstanceFieldsAllPrivate() {
        for (Field f : circleClass.getDeclaredFields()) {
            if (!Modifier.isFinal(f.getModifiers())) {
                assertFalse(f + " field shouldn't be public!",
                        Modifier.isPublic(f.getModifiers()));
                assertFalse(f + " field is protected, why?",
                        Modifier.isProtected(f.getModifiers()));
                assertTrue(f + " field has no access modifiers; why?",
                        Modifier.isPrivate(f.getModifiers()));
            }
        }
    }

    @Test
    public void testNoDefaultConstructor() {
        try {
            Constructor defaut = circleClass.getConstructor();
        } catch (NoSuchMethodException e) {
            // OK there is no default constructor
            return;
        }
        fail("Why define a default constructor?");
    }

    @Test
    public void testNumberOfConstructor() {
        int expected = 4; // Expected number of constructors (R10, R11, R12, R13)
        int declared = circleClass.getConstructors().length; // declared constructors
        assertFalse("Too many constructors: " + declared, declared > expected);
        assertFalse("Not enough public constructors: " + declared, declared < expected);
    }

    @Test
    public void testC4Partly() throws Exception {
        // This test is incomplete:
        // Failure indicates a probable violation of C4.
        // Success is not conclusive.
        int expected = 3; // expected instance fields number
        int declared = 0; // declared instance fiels number
        for (Field f : circleClass.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())) {
                declared++;
            }
        }
        assertFalse("Too many instance fields: " + declared
                + " instead of " + expected
                + " expected! There's probably some redudant information.",
                declared > expected);
        assertFalse("Not enough instance fields: " + declared
                + " instead of " + expected 
                + " expected! Some information is probably missing.", 
                declared < expected);
    }

    @Test
    public void testC5() {
        checkClassConstant(circleClass, "PI");
    }

    @Test
    public void testR6Struct() {
        assertTrue("Circle should be a Mesurable2D !",
                Measurable2D.class.isAssignableFrom(circleClass));
        // This test would also work if Measurable2D were a class instead of an interface.
        // If the latter is provided, we know for sure that it's an interface, so the test is sufficient.
        // If it has yet to be written, it's better to use testR6StructBis.
    }

    @Test
    public void testR6StructBis() {
        boolean implementsMeasurable2D = false;
        for (Class<?> interf : circleClass.getInterfaces()) {
            if (implementsMeasurable2D = (interf == Measurable2D.class)) {
                break;
            }
        }
        assertTrue("Circle must implement Mesurable2D!", implementsMeasurable2D);
    }

    @Test
    public void testR14Struct() throws Exception {
        try {
            Method createCircle = getMethod(circleClass, "createCircle", Point.class, Point.class);
            int modifiers = createCircle.getModifiers();
            assertTrue("createCircle should be public!",
                    Modifier.isPublic(modifiers));
            assertTrue("createCircle should be a class method!",
                    Modifier.isStatic(modifiers));
        } catch (NoSuchMethodException e) {
            fail("createCircle(Point, Point) doesn't exist!");
        }
    }
    
    
    ///////////////////////////////
    // Methods used by the tests //
    ///////////////////////////////

    private static Field getField(Class c, String name) throws NoSuchFieldException {
        return c.getDeclaredField(name);
    }

    private static Method getMethod(Class c, String name, Class... types) throws NoSuchMethodException {
        return c.getDeclaredMethod(name, types);
    }

    private static void checkClassConstant(Class c, String name) {
        try {
            Field PI = getField(c, name);
            assertTrue(name + " should be a constant!",
                    Modifier.isFinal(PI.getModifiers()));
            assertTrue(name + " should be a class constant!",
                    Modifier.isStatic(PI.getModifiers()));
            assertTrue("Why isn't " + name + " public?",
                    Modifier.isPublic(PI.getModifiers()));
        } catch (NoSuchFieldException ex) {
            fail(name + " field doesn't exist!");
        }
    }
    
    
    //////////////////////
    // Main test method //
    /////////////////////
    /// @param args/

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(CircleStructureTest.class.getName());
    }

}
