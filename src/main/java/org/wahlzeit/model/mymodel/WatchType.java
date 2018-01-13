package org.wahlzeit.model.mymodel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WatchType {

    /**
     * Super type of this WatchType.
     */
    private WatchType superType;

    /**
     * Sub types of this WatchType.
     */
    private Set<WatchType> subTypes = new HashSet<>();

    private final WatchManager manager;

    private final String typeName;

    /**
     * constructor
     *
     * @param typeName name of the WatchType must not be null.
     * @throws IllegalArgumentException if typeName was null.
     */
    public WatchType(String typeName, WatchManager manager) {
        if (typeName == null) {
            throw new IllegalArgumentException("typeName must not be null");
        }
        this.typeName = typeName;
        this.manager = manager;
    }

    /**
     * Creates a new Watch as instance of this WatchType.
     */
    public Watch createInstance() {
        return new Watch(this);
    }

    /**
     * Set a new WatchType as super type. This also sets this as a subtype of superType.
     * Does nothing if this WatchType has a super type already.
     *
     * @param superType the new WatchType to be set as super type.
     * @throws IllegalArgumentException if superType was null.
     */
    public void setSuperType(WatchType superType) {
        if (this.superType != null) {
            return;
        }
        if (superType == null) {
            throw new IllegalArgumentException("superType must not be null");
        }
        this.superType = superType;
        superType.addSubType(this);
    }

    /**
     * Set all subtypes at once and set their super type to this.
     * This method removes any WatchType that was added as subtype before.
     *
     * @param types new Set of subtypes.
     * @throws IllegalArgumentException if types is null.
     */
    public void setSubTypes(Set<WatchType> types) {
        if (types == null) {
            throw new IllegalArgumentException("New Set of subTypes must not be null");
        }
        this.subTypes.clear();
        this.subTypes.addAll(types);
        //set this as their superType
        for (WatchType child : this.subTypes) {
            child.setSuperType(this);
        }
    }

    /**
     * Add a new WatchType as subtype. This also sets this as super type.
     *
     * @param subType is the new WatchType set to be subtype of this.
     * @return true if subType was added. False if it has already been added.
     * @throws IllegalArgumentException if typeName was null.
     */
    public boolean addSubType(WatchType subType) {
        if (subType == null) {
            throw new IllegalArgumentException("Must not set null as subType");
        }
        if (this.subTypes.contains(subType)) {
            return false;
        }
        //Set this as super type.
        subType.setSuperType(this);
        return this.subTypes.add(subType);
    }

    /**
     * @return the name of this WatchType.
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * Searches itself and the whole subtree for a WatchType with name typeName.
     *
     * @param typeName name of the WatchType to be found.
     * @return true if typeName is the name of this WatchType or the name of one of the subtypes, else false.
     * @methodtype boolean query
     */
    public boolean isSubType(String typeName) {
        if (typeName == null) {
            return false;
        }
        return this.isSubType(new WatchType(typeName, manager));
    }

    /**
     * Searches itself and the whole subtree for the WatchType type.
     *
     * @param type is the WatchType to be found.
     * @return true if type has an equal name of this WatchType or the name of one of the subtypes, else false.
     * @methodtype boolean query
     */
    public boolean isSubType(WatchType type) {
        if (type == null) {
            return false;
        } else if (this.equals(type)) {
            return true;
        }

        //Search in all child nodes.
        for (WatchType child : this.subTypes) {
            if (child.isSubType(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches itself and all its super types for a WatchType with name typeName.
     *
     * @param typeName name of the WatchType to be found.
     * @return true if typeName is the name of this WatchType or the name of one of the super types, else false.
     * @methodtype boolean query
     */
    public boolean isSuperType(String typeName) {
        if (typeName == null) {
            return false;
        }
        return this.isSuperType(new WatchType(typeName, manager));
    }

    /**
     * Searches itself and all its super types for the WatchType type.
     *
     * @param type is the WatchType to be found.
     * @return true if type has an equal name of this WatchType or the name of one of the super types, else false.
     * @methodtype boolean query
     */
    public boolean isSuperType(WatchType type) {
        if (type == null) {
            return false;
        } else if (this.equals(type)) {
            return true;
        }

        //Search in all super nodes.
        if (this.superType != null) {
            return this.superType.isSuperType(type);
        } else {
            return false;
        }
    }

    /**
     * WatchType is equal if typeName is equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchType watchType = (WatchType) o;
        return Objects.equals(getTypeName(), watchType.getTypeName());
    }

    /**
     * Only hashes the typeName.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTypeName());
    }

    /**
     * @methodtype get
     */
    public WatchManager getManager() {
        return manager;
    }
}
