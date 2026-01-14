package com.albumcollector.albumcollector.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/")
@PageTitle("Album Collector")
public class MainLayout extends VerticalLayout {

    MainLayout() {
        var grid = new Grid<>();

        add(new H1("Album Collector"));
        add(grid);
    }

}
