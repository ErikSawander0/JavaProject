package com.miun.martinclass.demo.menu.web;

import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("menuBean")
@RequestScoped
public class MenuBean {
    @Inject
    private MenuService menuService;
    private List<MenuItem> menuItems;

    @PostConstruct
    public void init() {
        menuItems = menuService.getTodaysMenu().getMenuItems();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }


}
