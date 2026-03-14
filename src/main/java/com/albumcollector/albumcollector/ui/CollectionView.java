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
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
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
        private final Dialog viewDialog = new Dialog();


        public CollectionView(RecordService recordService){
                this.recordService = recordService;
        add(new H1("This is the collection page!"));

//      Init the nav bar and render it.
            SideNav nav = new SideNav();
//      Add links to the nav bar
            SideNavItem homeLink = new SideNavItem("Home", MainLayout.class);
            SideNavItem collectionLink = new SideNavItem("Collection", CollectionView.class);
            nav.addItem(homeLink, collectionLink);
            add(nav);

//      Configuration must be called somewhere
        ConfigureRecordDialog();
//        Configuring the grid of records
        recordGrid.addColumn(Record::getTitle).setHeader("Record title");
        recordGrid.addColumn(Record::getArtist).setHeader("Record artist");
        recordGrid.addColumn(Record::getYear).setHeader("Record Year");
        recordGrid.addColumn(Record::getMedium).setHeader("Medium");
        List<Record> recordList = recordService.findAll();
        recordGrid.setItems(recordList);

        recordGrid.addItemClickListener(recordItemClickEvent -> {
                openRecord(recordItemClickEvent.getItem());
                refreshGrid();
        });

//        Buttons to interact with the grid. Add and such.
        Button addRecordBTN = new Button("Add a Record", buttonClickEvent -> {
//            recordBinder.
//            viewDialog.open();
//            new AddRecordForm(recordService, recordList).open();
        } );


        add(recordGrid, addRecordBTN);
    }

    //    Function that opens the recordView and binds the values from the clicked record.
    private void openRecord(Record record) {
        recordBinder.setBean(record);
        viewDialog.open();
    }
//  configuring the viewDialog that the openRecord() function opens.
    private void ConfigureRecordDialog() {
        FormLayout recordForm = new FormLayout();

            TextField title = new TextField("Title");
            title.setReadOnly(true);
            TextField artist = new TextField("Artists");
            artist.setReadOnly(true);
            TextField genre = new TextField("Genre");
            genre.setReadOnly(true);
            TextField medium = new TextField("Medium");
            medium.setReadOnly(true);
            IntegerField year = new IntegerField("Year");
            year.setReadOnly(true);
            Checkbox favourite = new Checkbox("Favourite");
            favourite.setReadOnly(true);
            IntegerField idField = new IntegerField("Record ID");

            recordForm.add(title, artist, genre, medium, year, favourite);

//            Binds attributes of Record entity class into the correct fields of the grid.
            recordBinder.forField(artist).bind(Record::getArtist, Record::setArtist);
            recordBinder.forField(title).bind(Record::getTitle, Record::setTitle);
            recordBinder.forField(genre).bind(Record::getGenre, Record::setGenre);
            recordBinder.forField(medium).bind(Record::getMedium, Record::setMedium);
            recordBinder.forField(year).bind(Record::getYear, Record::setYear);
            recordBinder.forField(favourite).bind(Record::getFavourite, Record::setFavourite);
//            Converts the Long value into an int value and vice versa. Setter is still null as it is auto generated.
            recordBinder.forField(idField)
                    .withConverter(
                            i -> i == null ? null : i.longValue(),
                            l -> Math.toIntExact(l == null ? null : l.intValue()))
                    .bind(Record::getId, null);

//            Start of the viewFooter element
            Button close = new Button("Close", buttonClickEvent -> {
                    viewDialog.close();
            });
//            Delete button
            Button delete = new Button("Delete record", buttonClickEvent -> {

//                    Takes the value from idField and parses it into a Long from a String.
//                    Then passes the Long value to record service and deletes the record.
                    Integer intID = idField.getValue();
                    Long idLong = Long.parseLong(String.valueOf(intID));

                    recordService.removeRecord(idLong);
                    refreshGrid();
                    viewDialog.close();
            });

//            Edit button
            Button edit = new Button("Edit", buttonClickEvent -> {
                title.setReadOnly(false);
                artist.setReadOnly(false);
                genre.setReadOnly(false);
                medium.setReadOnly(false);
                year.setReadOnly(false);
                favourite.setReadOnly(false);
            });

            Button save = new Button("Save", buttonClickEvent -> {
                Record record;
                record = recordBinder.getBean();
                recordService.insertNewRecord((record));
                refreshGrid();
                viewDialog.close();
            });

            HorizontalLayout viewFooter = new HorizontalLayout(close, delete, edit, save, idField);

//            Create the viewDialog with wanted elements.
            viewDialog.add(recordForm, viewFooter);
    }

    public void refreshGrid(){
            recordGrid.setItems(recordService.findAll());
    }

}
