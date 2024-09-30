package com.dailycodework.dreamshops.request;

import com.dailycodework.dreamshops.model.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String Description;
    private Category category;
}
