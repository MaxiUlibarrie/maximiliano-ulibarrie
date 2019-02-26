package com.bootcamp.shoppingcart.appshoppingcart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonPropertyOrder(value = {"idsale","user","dateTime","totalPrice","lineSaleList"})
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idsale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "iduser")
  private User user;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
  private List<LineSale> lineSaleList;

  @Column
  private LocalDateTime dateTime;

  @Column
  private double totalPrice;

  public Sale(User user, LocalDateTime dateTime) {
    this.user = user;
    this.lineSaleList = new ArrayList<>();
    this.totalPrice = 0;
    this.dateTime = dateTime;
  }

  protected Sale() {}

  public Long getIdsale() {
    return idsale;
  }

  @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="iduser")
  @JsonIdentityReference(alwaysAsId = true)
  public User getUser() {
    return user;
  }

  public List<LineSale> getLineSaleList() {
    return lineSaleList;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
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
    return Double.compare(sale.totalPrice, totalPrice) == 0 &&
        Objects.equals(idsale, sale.idsale) &&
        Objects.equals(user, sale.user) &&
        Objects.equals(lineSaleList, sale.lineSaleList) &&
        Objects.equals(dateTime, sale.dateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idsale, user, lineSaleList, dateTime, totalPrice);
  }
}
