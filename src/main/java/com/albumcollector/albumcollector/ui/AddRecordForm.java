package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class AddRecordForm extends Dialog {

    private final RecordService recordService;

    TextField bandField = new TextField("Band");
    TextField recordField = new TextField("Record");


    public AddRecordForm(RecordService recordService, List<Record> recordProvider) {

        this.recordService = recordService;

        FormLayout formLayout = new FormLayout();
        formLayout.addFormRow(bandField, recordField);
        add(bandField, recordField);


        Button saveButton = new Button("Save", buttonClickEvent -> {
            SaveRecord();

            close();
        });
        Button cancelButton = new Button("Cancel",
                e -> close());


        getFooter().add(saveButton, cancelButton);
    }

    private void SaveRecord(){
        String band = bandField.getValue().trim();
        String name = recordField.getValue().trim();

        Record record = new Record();
        record.setBand(band);
        record.setName(name);

        recordService.insertNewRecord(record);

//        recordListDataProvider.refreshAll();


    }
}
