package com.feixiang.otrs.restaurant.service;

import com.feixiang.otrs.restaurant.entity.Entity;
import com.feixiang.otrs.restaurant.entity.Restaurant;
import com.feixiang.otrs.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("restaurantService")
public class RestaurantServiceImp extends BaseService<Restaurant, String> implements RestaurantService {

    private RestaurantRepository<Restaurant, String> restaurantRepository;

    @Autowired
    public RestaurantServiceImp(RestaurantRepository<Restaurant, String> restaurantRepository) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void add(Restaurant restaurant) throws Exception {
        if (restaurant == null || "".equals(restaurant.getName())) {
            throw new Exception("Restaurrant name cannot be null or empty string.");
        }

        if (restaurantRepository.containsName(restaurant.getName())) {
            throw new Exception(String.format("There is already a product with the name - %S", restaurant.getName()));
        }

        super.add(restaurant);
    }

    @Override
    public Collection<Restaurant> findByName(String name) throws Exception {
        return restaurantRepository.findByName(name);
    }

    @Override
    public void update(Restaurant restaurant) throws Exception {
        restaurantRepository.update(restaurant);
    }

    @Override
    public void delete(String id) throws Exception {
        restaurantRepository.remove(id);
    }

    @Override
    public Entity findById(String id) throws Exception{
        return  restaurantRepository.get(id);
    }

    @Override
    public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
