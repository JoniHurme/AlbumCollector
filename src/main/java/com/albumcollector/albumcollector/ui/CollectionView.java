package com.albumcollector.albumcollector.ui;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import java.util.List;


@Route("/collection")
public class CollectionView extends VerticalLayout {
        Grid<Record> recordGrid = new Grid<>(Record.class, false);
        private RecordService recordService;
        private final Binder<Record> recordBinder = new Binder<>(Record.class);
        private final Dialog dialog = new Dialog();


        public CollectionView(RecordService recordService){
        add(new H1("This is the collection page!"));

//      Configuration must be called somewhere
        ConfigureRecordDialog();

        recordGrid.addColumn(Record::getTitle).setHeader("Record title");
        recordGrid.addColumn(Record::getArtist).setHeader("Record artist");
        recordGrid.addColumn(Record::getYear).setHeader("Record Year");
        recordGrid.addColumn(Record::getMedium).setHeader("Medium");

        List<Record> recordList = recordService.findAll();
        recordGrid.setItems(recordList);

        recordGrid.addItemClickListener(recordItemClickEvent -> {
                openRecord(recordItemClickEvent.getItem());
        });


        add(recordGrid);
    }

//    Function that opens the recordView and binds the values from the clicked record.
    private void openRecord(Record record) {
        recordBinder.readBean(record);
        dialog.open();
    }
//  configuring the dialog that the openRecord() function opens.
    private void ConfigureRecordDialog() {
            FormLayout recordForm = new FormLayout();

            TextField title = new TextField("Title");
            TextField artist = new TextField("Artists");
            TextField genre = new TextField("Genre");
            TextField medium = new TextField("Medium");
            IntegerField year = new IntegerField("Year");
            Checkbox favourite = new Checkbox("Favourite");

            recordForm.add(title, artist, genre, medium, year, favourite);

            recordBinder.forField(title).bind(Record::getArtist, null);
            recordBinder.forField(artist).bind(Record::getTitle, null);
            recordBinder.forField(genre).bind(Record::getGenre, null);
            recordBinder.forField(medium).bind(Record::getMedium, null);
            recordBinder.forField(year).bind(Record::getYear, null);
            recordBinder.forField(favourite).bind(Record::getFavourite, null);

            dialog.add(recordForm);
    }

}
