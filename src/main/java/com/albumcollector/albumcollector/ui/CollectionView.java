package com.albumcollector.albumcollector.ui;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
                this.recordService = recordService;
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
            IntegerField idField = new IntegerField("ID");

            recordForm.add(title, artist, genre, medium, year, favourite);

            recordBinder.forField(title).bind(Record::getArtist, null);
            recordBinder.forField(artist).bind(Record::getTitle, null);
            recordBinder.forField(genre).bind(Record::getGenre, null);
            recordBinder.forField(medium).bind(Record::getMedium, null);
            recordBinder.forField(year).bind(Record::getYear, null);
            recordBinder.forField(favourite).bind(Record::getFavourite, null);
//            Converts the Long value into an int value and vice versa. Setter is still null as it is auto generated.
            recordBinder.forField(idField)
                    .withConverter(
                            i -> i == null ? null : i.longValue(),
                            l -> Math.toIntExact(l == null ? null : l.intValue()))
                    .bind(Record::getId, null);

//            Start of the footer element
            Button close = new Button("Close", buttonClickEvent -> {
                    dialog.close();
            });
            Button delete = new Button("Delete record", buttonClickEvent -> {

//                    Takes the value from idField and parses it into a Long from a String.
//                    Then passes the Long value to record service and deletes the record.
                    Integer intID = idField.getValue();
                    Long idLong = Long.parseLong(String.valueOf(intID));

                    recordService.removeRecord(idLong);
                    dialog.close();
            });
            HorizontalLayout footer = new HorizontalLayout(close, delete, idField);

//            Create the dialog with wanted elements.
            dialog.add(recordForm, footer);
    }

}
