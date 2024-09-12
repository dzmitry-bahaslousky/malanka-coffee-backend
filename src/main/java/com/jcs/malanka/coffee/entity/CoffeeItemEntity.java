package com.jcs.malanka.coffee.entity;

import com.jcs.malanka.coffee.dto.coffee.BeanType;
import com.jcs.malanka.coffee.dto.coffee.RoastLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coffee_items")
@PrimaryKeyJoinColumn(name = "item_id")
public class CoffeeItemEntity extends MenuItemEntity {

    @Column(name = "roast_level")
    @Enumerated(EnumType.STRING)
    private RoastLevel roastLevel;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "bean_type")
    @Enumerated(EnumType.STRING)
    private BeanType beanType;

}