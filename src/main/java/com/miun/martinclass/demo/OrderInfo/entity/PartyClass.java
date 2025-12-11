package com.miun.martinclass.demo.OrderInfo.entity;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PartyClass {
    @PersistenceContext
    private EntityManager em;
    public void createParty(Party part){
        em.persist(part);
    }
}
