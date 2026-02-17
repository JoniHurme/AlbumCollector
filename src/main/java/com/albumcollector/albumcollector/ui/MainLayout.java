package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.open.Open;

@Route("/")
@PageTitle("Album Collector")
public class MainLayout extends VerticalLayout {

    private final Grid<Record> grid;
    private RecordService recordService;

    MainLayout(RecordService recordService) {
//      Init the nav bar and render it.
        MenuBar navBar = buildNav();
        add(navBar);


        add(new H1("Album Collector"));
//      End of Header. There will be an actual header sometime I hope.

//      Init service and grid
        this.grid = new Grid<>(Record.class);
        this.recordService = recordService;

//      Add grid component and some test functions.
        add(String.valueOf(ammountOfRecords()));
        add(grid);
        listRecords();

        add(addRecordButton);
    }

//  button to add records
    Button addRecordButton = new Button("Add a record",
        e -> new AddRecordForm(recordService).open()){

    };

//  Functions
    private void listRecords() {
        grid.setItems(recordService.findAll());
    }
    private long ammountOfRecords() {
        long count = recordService.Count();
        return count;
    }
//  The menu is configured.
    private MenuBar buildNav() {
        MenuBar navBar = new MenuBar();

        navBar.addItem("Home");
        navBar.addItem("Collection");
        navBar.addItem("Wishlist");

        return navBar;
    }

}
