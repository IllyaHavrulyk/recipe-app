package com.havrulyk.spring.recipebook.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnitOfMeasure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
}
