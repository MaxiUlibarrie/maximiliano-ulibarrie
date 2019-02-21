package com.bootcamp.shoppingcart.appshoppingcart.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSale;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
  private List<LineSale> lineSaleList;

  @Column
  private LocalDateTime dateTime;

  @Column
  private double totalPrice;

  public Sale(LocalDateTime dateTime) {
    this.lineSaleList = new ArrayList<>();
    this.totalPrice = 0;
    this.dateTime = dateTime;
  }

  protected Sale() {}

  public Long getIdSale() {
    return idSale;
  }

  public List<LineSale> getLineSaleList() {
    return lineSaleList;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void calculateTotalPrice() {
    totalPrice = lineSaleList.stream()
        .mapToDouble(LineSale::getSubTotalPrice)
        .sum();
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
    return Objects.equals(idSale, sale.idSale) &&
        Objects.equals(lineSaleList, sale.lineSaleList) &&
        Objects.equals(dateTime, sale.dateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSale, lineSaleList, dateTime);
  }
}
