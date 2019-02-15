package com.bootcamp.topic6.apptopic6.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

  public Long getId() {
    return id;
  }

  public List<LineSale> getLineSaleList() {
    return lineSaleList;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sale sale = (Sale) o;
    return Objects.equals(id, sale.id) &&
        Objects.equals(lineSaleList, sale.lineSaleList) &&
        Objects.equals(dateTime, sale.dateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lineSaleList, dateTime);
  }
}
