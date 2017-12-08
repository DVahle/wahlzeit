package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * This equals a call of getCartesianDistance.
     * Returns +Infinity if coordinate is null
     *
     * @throws ConversionException      if coordinates can not be converted into CartesianCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    public double getDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        return getCartesianDistance(coordinate);
    }


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     *
     * @throws ConversionException      if coordinates can not be converted into CartesianCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        CartesianCoordinate own = this.asCartesianCoordinate();
        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double deltaX = own.getX() - other.getX();
        final double deltaY = own.getY() - other.getY();
        final double deltaZ = own.getZ() - other.getZ();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }


    /**
     * Computes the distance around a sphere between two points on that sphere. If the radius differs, the bigger radius is used.
     * Coordinate must not be null
     *
     * @throws ConversionException      if coordinates can not be converted into SphericCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    @Override
    public double getSphericDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        SphericCoordinate other = coordinate.asSphericCoordinate();
        SphericCoordinate own = this.asSphericCoordinate();

        double deltaAngle = Math.acos(Math.sin(own.getLatitude()) * Math.sin(other.getLatitude()) +
                Math.cos(own.getLatitude()) * Math.cos(other.getLatitude()) * Math.cos(Math.abs(own.getLongitude() - other.getLongitude())));

        return deltaAngle * Math.max(own.getRadius(), other.getRadius());
    }

    /**
     * Compares this Coordinate with coordinate.
     * This is a default implementation using CartesianCoordinate
     *
     * @return true if coordinate has the same position.
     * @throws ConversionException if coordinate or this can not be converted into required coordinate space
     */
    public boolean isEqual(Coordinate coordinate) throws ConversionException {
        if (coordinate == null) {
            return false;
        }

        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        CartesianCoordinate own = this.asCartesianCoordinate();

        final double EPSILON = 10E-4;

        return (isDoubleEqual(own.getX(), other.getX(), EPSILON)) &&
                (isDoubleEqual(own.getY(), other.getY(), EPSILON)) &&
                (isDoubleEqual(own.getZ(), other.getZ(), EPSILON));
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            try {
                return isEqual((Coordinate) obj);
            } catch (IllegalStateException ise) {
                ise.printStackTrace();
                return false;
            } catch (ConversionException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * compares two double values with epsilon tolerance
     *
     * @methodtype boolean-query
     */
    protected boolean isDoubleEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    /**
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype assertion
     */
    protected void assertCoordinateNotNull(Coordinate coordinate) throws IllegalArgumentException {
        if (coordinate == null) {
            throw new IllegalArgumentException("Parameter coordinate must not be null!");
        }
    }

}
