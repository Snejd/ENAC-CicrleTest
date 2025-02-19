package circle;

/**
 * Specifies the properties of a closed element of the plane.
 * 
 * @author Xavier Cregut, Sebastion Leriche, Nicolas Saporito
 * @version 2024.12.05
 */
public interface Measurable2D {

    /**
     * Get the perimeter.
     * @return the perimeter
     */
    public double getPerimeter();

    /**
     * Get the area.
     * @return the area
     */
    public double getArea();

}
