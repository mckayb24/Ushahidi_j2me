package com.ushahidi.j2me.forms;

import com.sun.lwuit.*;
//import com.sun.lwuit.Container;
//import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.layouts.BorderLayout;
import com.ushahidi.j2me.App;

import com.ushahidi.j2me.models.Report;
import ushahidi.core.I18N;

/**
 * Report Form
 * @author dalezak
 */
public class ReportForm extends Base {

    /**
     * This method creates an instance of the ReportForm class.
     * The ReportForm class displays the details of a report
     * @pre Report != null
     * @pre app must exist
     * @post Creates an instance of the ReportForm class
     * @param App app
     * @param Report newReport : this is the report to be displayed
     */
    public ReportForm(final App app, Report newReport) {
        super(I18N.s(newReport.getTitle()));

        this.setLayout(new BorderLayout());
        Container container = createdBoxLayout();

        final TextArea descriptionText = createTextArea(newReport.getDescription(), false);

        final TextField dateText = createTextField(newReport.getDate(), false);
        final TextField cityText = createTextField(newReport.getCity(), false);
        
        container.addComponent(descriptionText);

        container.addComponent(createLabel(I18N.s("Date")));
        container.addComponent(dateText);

        container.addComponent(createLabel(I18N.s("City")));
        container.addComponent(cityText);
        
        this.addComponent(BorderLayout.CENTER, container);
        
        addCommand(new Command(I18N.s("back")) {
            public void actionPerformed(ActionEvent ev) {
                app.showReports(false);
            }
        });
    }
}
