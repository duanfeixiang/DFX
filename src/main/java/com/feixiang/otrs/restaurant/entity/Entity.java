package com.feixiang.otrs.restaurant.entity;

public abstract class Entity<T> {

    T id;
    String name;

    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
