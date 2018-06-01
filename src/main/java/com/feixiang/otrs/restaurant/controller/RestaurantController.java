package com.feixiang.otrs.restaurant.controller;

import com.feixiang.otrs.restaurant.entity.Entity;
import com.feixiang.otrs.restaurant.entity.Restaurant;
import com.feixiang.otrs.restaurant.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("v1/restaurants")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    protected RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * 获得指定名称的餐馆
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Restaurant>> findByName(HttpServletRequest request, @RequestParam("name") String name) {
        logger.info("restaurant-service findByName() invoked: {} for {}", restaurantService.getClass().getName(), name);
        name = name.trim().toLowerCase();
        Collection<Restaurant> restaurants;
        try {
            restaurants = restaurantService.findByName(name);
        } catch (Exception e) {
            logger.error("Exception raised findByName REST Call: {}", e);
            return new ResponseEntity<Collection<Restaurant>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants.size() > 0 ? new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK) : new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
    }

    /**
     * 获取指定ID的餐馆
     */
    @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(HttpServletRequest request, @PathVariable("restaurant_id") String id) {
        logger.info("restaurant-service findById() invoked: {} for {}", restaurantService.getClass().getName(), id);
        id = id.trim().toLowerCase();
       Entity restaurants;
        try {
            restaurants = restaurantService.findById(id);
        } catch (Exception e) {
            logger.error("Exception raised findById REST Call: {}", e);
            return new ResponseEntity<Entity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants != null ? new ResponseEntity<Entity>(restaurants, HttpStatus.OK) : new ResponseEntity<Entity>(HttpStatus.NO_CONTENT);
    }

    /**
     * 添加具有指定信息的餐馆
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Restaurant> add(HttpServletRequest request, @RequestBody Restaurant restaurantVO) {
        logger.info("restaurant-service add() invoked: {} for {}", restaurantService.getClass().getName(), restaurantVO.getName());
        Restaurant restaurant = new Restaurant(null, null, null);
        BeanUtils.copyProperties(restaurantVO, restaurant);
        try {
            restaurantService.add(restaurant);
        } catch (Exception e) {
            logger.error("Exception raised findByName REST Call: {}", e);
            return new ResponseEntity<Restaurant>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Restaurant>(HttpStatus.CREATED);
    }
}
