package com.albumcollector.albumcollector.ui;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.List;


@Route("/collection")
public class CollectionView extends VerticalLayout {
        Grid<Record> recordGrid = new Grid<>(Record.class, false);
        private RecordService recordService;


        public CollectionView(RecordService recordService){
        add(new H1("This is the collection page!"));


        recordGrid.addColumn(Record::getTitle).setHeader("Record title");
        recordGrid.addColumn(Record::getArtist).setHeader("Record artist");
        recordGrid.addColumn(Record::getYear).setHeader("Record Year");
        recordGrid.addColumn(Record::getMedium).setHeader("Medium");

        List<Record> recordList = recordService.findAll();
        recordGrid.setItems(recordList);


        add(recordGrid);
    }
}
