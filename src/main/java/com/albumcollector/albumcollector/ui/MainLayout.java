package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/")
@PageTitle("Album Collector")
public class MainLayout extends VerticalLayout {

    private final Grid<Record> grid;
    private final RecordService recordService;

    MainLayout( RecordService recordService) {

        this.grid = new Grid<>(Record.class);
        this.recordService = recordService;

        add(new H1("Album Collector"));
        add(grid);
        listRecords();
    }
    private void listRecords() {
        grid.setItems(recordService.findAll());
    }

}
