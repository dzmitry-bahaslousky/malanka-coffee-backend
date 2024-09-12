package com.jcs.malanka.coffee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "categories")
@EqualsAndHashCode(callSuper = false, of = "name")
public class CategoryEntity extends AuditEntity<UUID> {

    @Column(name = "name")
    private String name;

}