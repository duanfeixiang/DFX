package com.feixiang.otrs.restaurant.repository;

import com.feixiang.otrs.restaurant.entity.Entity;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {

    boolean contains(T id);

    Entity get(T id);

    Collection<TE> getAll();
}
