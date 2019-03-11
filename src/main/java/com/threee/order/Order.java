package com.threee.order;

import com.threee.food.Food;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Entity

public class Order {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer sumPrice;
    private Integer sumPiece;
   // private List<Food> orderedFood;


    public Order(Integer sumPrice, Integer sumPiece, List<Food> orderedFood) {
        this.sumPrice = sumPrice;
        this.sumPiece = sumPiece;
        //this.orderedFood = orderedFood;
    }
}
