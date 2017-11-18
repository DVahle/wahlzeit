package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * This equals a call of getCartesianDistance.
     * Returns +Infinity if coordinate is null
     *
     * @methodtype query-method
     */
    public double getDistance(Coordinate coordinate) {
        return getCartesianDistance(coordinate);
    }


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * Returns +Infinity if coordinate is null
     *
     * @methodtype query-method
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if (coordinate == null) {
            return Double.POSITIVE_INFINITY;
        }
        CartesianCoordinate own = this.asCartesianCoordinate();
        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double deltaX = own.getX() - other.getX();
        final double deltaY = own.getY() - other.getY();
        final double deltaZ = own.getZ() - other.getZ();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }


    /**
     * Computes the distance around a sphere between two points on that sphere. If the radius differs, the bigger radius is used.
     * Returns +Infinity if coordinate is null
     *
     * @methodtype query-method
     */
    @Override
    public double getSphericDistance(Coordinate coordinate) {
        if (coordinate == null) return Double.POSITIVE_INFINITY;
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
     */
    public boolean isEqual(Coordinate coordinate) {
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
            return isEqual((Coordinate) obj);
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

}
