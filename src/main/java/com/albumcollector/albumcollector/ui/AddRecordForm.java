package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class AddRecordForm extends Dialog {

    private final RecordService recordService;

    //    Fields for values.
    TextField bandField = new TextField("Band");
    TextField recordField = new TextField("Record");
    TextField genreField = new TextField("Genre");
    TextField mediumField = new TextField("Medium");
    TextField yearField = new TextField("Year");
    Checkbox favouriteCheck = new Checkbox();


    public AddRecordForm(RecordService recordService, List<Record> recordProvider) {

        this.recordService = recordService;

        FormLayout formLayout = new FormLayout();
        formLayout.addFormRow(bandField, recordField);
        formLayout.addFormRow(genreField, favouriteCheck, mediumField);
//        add(formLayout);
        add(bandField, recordField, genreField, favouriteCheck, mediumField, yearField);


        Button saveButton = new Button("Save", buttonClickEvent -> {
            SaveRecord();

            close();
        });
        Button cancelButton = new Button("Cancel",
                e -> close());


        getFooter().add(saveButton, cancelButton);
    }

    private void SaveRecord(){
//        Get the wanted values
        String band = bandField.getValue().trim();
        String title = recordField.getValue().trim();
        String genre = genreField.getValue().trim();
        String medium = mediumField.getValue().trim();
        Boolean favourite = favouriteCheck.getValue();
        String yearString = yearField.getValue().trim();
        int yearInteger = Integer.parseInt(yearString);

//        Send the wanted values
        Record record = new Record();
        record.setArtist(band);
        record.setTitle(title);
        record.setGenre(genre);
        record.setMedium(medium);
        record.setFavourite(favourite);
        record.setYear(yearInteger);

        recordService.insertNewRecord(record);
    }
}
