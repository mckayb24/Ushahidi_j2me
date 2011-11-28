/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.j2me.forms;

import com.sun.lwuit.*;
import com.sun.lwuit.Container;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.ushahidi.j2me.App;
import ushahidi.core.I18N;
import ushahidi.core.Settings;

/**
 *
 * @author user
 */
public class AddDeployment extends Base 
{
    private Settings settings = new Settings();

    public AddDeployment(final App app)
    {
        super(I18N.s("Add Deployment"));
        
        setLayout(new BorderLayout());
        
        Container container = createdBoxLayout();
        
        final TextField addDeployText = createTextField();
        addDeployText.setText("http://");

        container.addComponent(addDeployText);

        addComponent(BorderLayout.CENTER, container);
        //Save
        container.addComponent(createButton(I18N.s("Save"), new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
                settings.saveDeployment(addDeployText.getText());
                if (Dialog.show(I18N.s("restart"), "A restart is needed to load selected instance. Would you wish to exit the application now?", I18N.s("yes"), I18N.s("no")))
                {
                    app.exit();
                }
                else
                {
                    app.showSettings(false);
                }
            }
        }));

        addCommand(new Command(I18N.s("back")) {
            public void actionPerformed(ActionEvent ev) {
                app.showSettings(false);
            }
        });
    }
}
