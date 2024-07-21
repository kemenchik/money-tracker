package com.money.tracker.model;

import com.money.tracker.model.enumeration.TransactionCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Data
@Accessors(chain = true)
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String externalId;

  private BigDecimal sum;
  private LocalDateTime date;

  @Enumerated(value = EnumType.STRING)
  private TransactionCategory category;
  private String comment;

  @ManyToOne
  private Account account;
}
