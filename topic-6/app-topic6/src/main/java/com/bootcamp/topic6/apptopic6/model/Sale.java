package com.bootcamp.topic6.apptopic6.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Sale {

  @Id @GeneratedValue
  private Long id;

  @OneToMany
  private List<LineSale> lineSaleList;

  @Column
  private LocalDateTime dateTime;

  public Sale(List<LineSale> lineSaleList, LocalDateTime dateTime) {
    this.lineSaleList = lineSaleList;
    this.dateTime = dateTime;
  }

  public Sale() {}
}
