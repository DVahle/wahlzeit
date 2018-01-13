package org.wahlzeit.model.mymodel;

public class Watch {

    /**
     * Manager of this object. Has no use but was modeled in UML-Classdiagram.
     */
    private WatchManager manager;

    /**
     * WatchType of this Watch.
     */
    private WatchType type;

    /**
     * Material of which the watches wristband is made of.
     * Should never be null. Use empty String instead.
     */
    protected String wristBandMaterial = "";

    /**
     * Material of which the watches housing is made of
     * Should never be null. Use empty String instead.
     */
    protected String housingMaterial = "";

    /**
     * Name of the brand who produced the watch
     * Should never be null. Use empty String instead.
     */
    protected String brand = "";


    /**
     * @methodtype constructor
     */
    public Watch(WatchType type) {
        this.type = type;
    }

    /**
     * @methodtype get
     */
    public String getWristBandMaterial() {
        return wristBandMaterial;
    }

    /**
     * @throws IllegalArgumentException if wristBandMaterial is null
     * @methodtype set
     */
    public void setWristBandMaterial(String wristBandMaterial) throws IllegalArgumentException {
        if (wristBandMaterial == null) {
            throw new IllegalArgumentException("wristBandMaterial must not be null");
        }
        this.wristBandMaterial = wristBandMaterial;
    }

    /**
     * @methodtype get
     */
    public String getHousingMaterial() {
        return housingMaterial;
    }

    /**
     * @throws IllegalArgumentException if housingMaterial is null
     * @methodtype set
     */
    public void setHousingMaterial(String housingMaterial) throws IllegalArgumentException {
        if (housingMaterial == null) {
            throw new IllegalArgumentException("housingMaterial must not be null");
        }
        this.housingMaterial = housingMaterial;
    }

    /**
     * @methodtype get
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @throws IllegalArgumentException if brand is null
     * @methodtype set
     */
    public void setBrand(String brand) throws IllegalArgumentException {
        if (brand == null) {
            throw new IllegalArgumentException("brand must not be null");
        }
        this.brand = brand;
    }


    /**
     * @methodtype get
     */
    public WatchType getType() {
        return type;
    }

}
