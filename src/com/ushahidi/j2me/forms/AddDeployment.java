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

/**
 *
 * @author user
 */
public class AddDeployment extends Base 
{
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
            public void actionPerformed(ActionEvent ae) {

            }
        }));

        addCommand(new Command(I18N.s("back")) {
            public void actionPerformed(ActionEvent ev) {
                app.showSettings(false);
            }
        });
    }
}
