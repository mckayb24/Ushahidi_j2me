package com.ushahidi.j2me.forms;

import com.sun.lwuit.*;

import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;

import com.ushahidi.j2me.App;
import ushahidi.core.I18N;
import ushahidi.core.LabelInfo;

/**
 * Settings Form
 * @author dalezak and Brett McKay
 */
public class Settings extends Base {

    private ushahidi.core.Settings settings = new ushahidi.core.Settings();

    public Settings(final App app) {
        super(I18N.s("settings"));

        setLayout(new BorderLayout());
        
        String[] userSetting = settings.getSettings();
        String[] deployTitles;

        //checks if there is a deployment
        if(settings.getDeployment() != null)
        {
            deployTitles = settings.getDeployment();
        }
        else
        {
            deployTitles = new String[1];
            deployTitles[0] = "";
        }

        Container container = createdBoxLayout();

        final ComboBox languages = createComboBox();
        final TextField reports = createTextField();
        //initializes combo box with all the deployments
        final ComboBox deployments = createComboBox(deployTitles);
        final TextField firstName = createTextField();
        final TextField lastName = createTextField();
        final TextField email = createTextField();

        if (userSetting != null) {
            firstName.setText(userSetting[0]);
            lastName.setText(userSetting[1]);
            email.setText(userSetting[2]);
        }
        //LANGUAGE
        container.addComponent(createLabel(I18N.s("language")));
        languages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }
        });
        container.addComponent(languages);

        //eventually used to choose a deployment
        container.addComponent(createLabel(I18N.s("deployment")));
        deployments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }
        });
        container.addComponent(deployments);

        //NUMBER OF REPORTS
        container.addComponent(createLabel(I18N.s("no_of_reports")));
        container.addComponent(reports);

        //FIRST NAME
        container.addComponent(createLabel(I18N.s("first_name")));
        container.addComponent(firstName);

        //LAST NAME
        container.addComponent(createLabel(I18N.s("last_name")));
        container.addComponent(lastName);

        //EMAIL
        container.addComponent(createLabel(I18N.s("email")));
        if (userSetting != null) {
            email.setText(userSetting[2]);
        }
        container.addComponent(email);

        addComponent(BorderLayout.CENTER, container);

        addCommand(new Command(I18N.s("back")) {
            public void actionPerformed(ActionEvent ev) {
                app.showDashboard(false);
            }
        });
        addCommand(new Command(I18N.s("save")) {
            public void actionPerformed(ActionEvent ev) {
                settings.saveSettings(firstName.getText(), lastName.getText(), email.getText());
                if (Dialog.show(I18N.s("restart"), "A restart is needed to load selected instance. Would you wish to exit the application now?", I18N.s("yes"), I18N.s("no"))) {
                    app.exit();
                }
                else {
                    app.showDashboard(false);
                }
            }
        });
        addCommand(new Command(I18N.s("add_deployment")) {
            public void actionPerformed(ActionEvent ev) {
                app.showAddDeploy(true);
            }
        });

    }
}
