package com.feixiang.otrs.restaurant.entity;

public abstract class BaseEntity<T> extends Entity<T> {

    private T id;
    private boolean isModified;
    private String name;

    public BaseEntity() {}


    public BaseEntity(T id, String name) {
        this.id = id;
        this.name = name;
    }
    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public boolean getIsModified() {
        return this.isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
