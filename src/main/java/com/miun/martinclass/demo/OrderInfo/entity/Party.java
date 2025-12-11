package com.miun.martinclass.demo.OrderInfo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party")

public class Party {
    public int tableNum;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}
