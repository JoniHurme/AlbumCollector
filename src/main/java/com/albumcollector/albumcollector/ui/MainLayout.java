package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route("/")
@PageTitle("Album Collector")
public class MainLayout extends VerticalLayout {

    private RecordService recordService;
    private List<Record> recordList;

    public MainLayout(RecordService recordService) {
//      Start of the Header.

        add(new H1("Album Collector"));


//      End of Header. There will be an actual header sometime I hope.

//      Init service
        this.recordService = recordService;

//      Init the nav bar and render it.
        SideNav nav = new SideNav();
//      Add links to the nav bar
        SideNavItem homeLink = new SideNavItem("Home", MainLayout.class);
        SideNavItem collectionLink = new SideNavItem("Collection", CollectionView.class);

        nav.addItem(homeLink, collectionLink);
        add(nav);
        add(String.valueOf(amountOfRecords()));
        add(String.valueOf(amountOfFavourites()));
        add(addRecordButton);
    }


    //  button to add records
    Button addRecordButton = new Button("Add a record",
        e -> new AddRecordForm(recordService,recordList).open()){
    };


//  Button that removes records by ID for now.
    Button removeRecordButton = new Button("Delete record", buttonClickEvent -> {
        new DeleteRecordForm(recordService).open();
    });

//  Functions

    private long amountOfRecords() {
        return recordService.count();
    }

    private Long amountOfFavourites(){
        return recordService.countFavourites();
    }



}
