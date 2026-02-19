package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("/")
@PageTitle("Album Collector")
public class MainLayout extends VerticalLayout {

    private Grid<Record> grid = new Grid<>(Record.class);
    private RecordService recordService;
    private List<Record> recordList;

    public MainLayout(RecordService recordService) {
//      Init the nav bar and render it.
        MenuBar navBar = buildNav();
        add(navBar);

//        UI ui = UI.getCurrent();
//        ui.access(() -> grid.getDataProvider().refreshAll());

        add(new H1("Album Collector"));
//      End of Header. There will be an actual header sometime I hope.

//      Init service and grid
//        this.grid = new Grid<>(Record.class);
        this.recordService = recordService;

        recordList = recordService.findAll();
        ListDataProvider<Record> recordProvider = new ListDataProvider<>(recordList);
        grid.setDataProvider(recordProvider);

//      Add grid component and some test functions.
        add(String.valueOf(ammountOfRecords()));
//        grid.setItems(recordList);
        add(grid);
//        listRecords();

        add(addRecordButton, removeRecordButton, refreshButton);
    }

    //  button to add records
    Button addRecordButton = new Button("Add a record",
        e -> new AddRecordForm(recordService,recordList).open()){

    };

    Button refreshButton = new Button("Refresh", buttonClickEvent -> {
        refreshGrid();


    });

//  Button that removes records.
    Button removeRecordButton = new Button("Delete record", buttonClickEvent -> {
        new DeleteRecordForm(recordService).open();
//      Add a method to refresh the grid after
    });

//  Functions
//    private void listRecords() {
//        grid.setItems(recordService.findAll());
//    }
    private long ammountOfRecords() {
        long count = recordService.count();
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

    public void refreshGrid(){
        grid.getDataProvider().refreshAll();

//        recordProvider.refreshAll();
    }

}
