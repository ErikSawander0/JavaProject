package com.miun.martinclass.demo.PointOfSale;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "party")

public class Party {
    public int tableNum;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}
