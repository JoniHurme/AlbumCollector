package com.albumcollector.albumcollector.ui;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class DeleteRecordForm extends Dialog {

    private final RecordService recordService;

    TextField idField = new TextField("ID");

    public DeleteRecordForm(RecordService recordService) {
        this.recordService = recordService;

        FormLayout formLayout = new FormLayout();
        formLayout.add(idField);
        add(idField);

        Button deleteButton = new Button("Delete", buttonClickEvent -> {
            deleteRecord();
            close();
        });

        Button cancelButton = new Button("Cancel", e -> close());
        getFooter().add(deleteButton, cancelButton);
    }

    private void deleteRecord(){
        String idString = idField.getValue().trim();
        Long idLong = Long.parseLong(idString);

        recordService.removeRecord(idLong);
        close();
    }

}
