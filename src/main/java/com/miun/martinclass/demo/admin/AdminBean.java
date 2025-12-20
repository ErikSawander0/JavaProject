package com.miun.martinclass.demo.admin;

import com.miun.martinclass.demo.OrderInfo.service.CarteService;
import com.miun.martinclass.demo.menu.entity.CarteMenu;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class AdminBean implements Serializable {

    @Inject
    private MenuService menuService;

    @Inject
    private CarteService carteService;


    private List<MenuItem> menuItems;
    private List<DailyMenu> dailyMenus;
    private List<CarteMenu> carteMenus;

    private MenuItem selectedMenuItem;
    private DailyMenu selectedDailyMenu;
    private CarteMenu selectedCarteMenu;

    private MenuItem newMenuItem = new MenuItem();
    private DailyMenu newDailyMenu = new DailyMenu();

    private Long selectedMenuItemId;
    private Long selectedDailyMenuId;



}
