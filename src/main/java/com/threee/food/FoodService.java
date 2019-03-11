package com.threee.food;

import com.threee.food.OrderDTO;

import java.util.List;

public interface FoodService {
   FoodDTO filterFoodByType(String foodType, String foodTitle);
    List<Food> findAllFood();
    Food findFood (String FoodName);
    OrderDTO saveOrder (long id);
    OrderDTO getOrder ();
    OrderDTO modal (int totalAmount,
                    int totalFoodPrice);
}
