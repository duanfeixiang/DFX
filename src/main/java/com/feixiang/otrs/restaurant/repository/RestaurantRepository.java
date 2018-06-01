package com.feixiang.otrs.restaurant.repository;

import java.util.Collection;

public interface RestaurantRepository<Restaurant, String> extends Repository<Restaurant, String> {

    boolean containsName(String name) throws Exception;

    Collection<Restaurant> findByName(String name) throws Exception;
}
