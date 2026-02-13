package com.albumcollector.albumcollector.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AddRecordForm extends Dialog {


    public AddRecordForm() {

        TextField band = new TextField("Band");
        TextField record = new TextField("Record");

        FormLayout formLayout = new FormLayout();

        formLayout.addFormRow(band, record);

        add(band, record);

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel",
                e -> close());


        getFooter().add(saveButton, cancelButton);
    }

}
