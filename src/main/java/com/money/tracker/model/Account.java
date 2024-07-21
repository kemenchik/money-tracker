package com.money.tracker.model;

import com.money.tracker.model.enumeration.AccountCurrency;
import com.money.tracker.model.enumeration.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String externalId;
  private String title;
  private String owner;

  private Integer order;

  @Enumerated(value = EnumType.STRING)
  private AccountCurrency currency;
  @Enumerated(value = EnumType.STRING)
  private AccountType type;

  private BigDecimal sum;

  private String comment;

  @OneToMany(mappedBy = "account")
  private List<Transaction> transactions;
}
