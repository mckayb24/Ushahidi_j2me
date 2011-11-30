package com.ushahidi.j2me.forms;

import com.sun.lwuit.*;

import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.list.DefaultListModel;

import com.ushahidi.j2me.App;
import com.ushahidi.j2me.models.Database;
import ushahidi.core.Instance;

import java.util.Vector;
import ushahidi.core.I18N;

/**
 * Reports Form
 * @author dalezak and Brett McKay
 */
public class Reports extends Base {
    private Vector reportVec;

    /**
     * This method creates an instance of the Reports class.
     * The Reports class creates a form to display the
     * title of all the reports
     * Each title can be clicked on to display
     * that reports details
     * @post Creates an instance of the Reports class
     * @param App app
     * @param List reportList : this is a list with all the report titles
     */
    public Reports(final App app, List reportList) {
        super(I18N.s("Reports"));
        setLayout(new BorderLayout());
        Container container = createdBoxLayout();

        //if a list selection is clicked
        //displays the details of the selected report in a new form
        reportList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            String temp = new String(ae.getSource().toString().substring(118, 128));

            //finds the index of the selected list item
            String num = new String("0");
            for(int i = 0; i < temp.length(); i++)
            {
                if((temp.charAt(i) >= '0')&&(temp.charAt(i) <= '9'))
                {
                    num = num.concat(temp.substring(i, i+1));
                }
            }

            app.showReport(true, (app.getAReport(Integer.parseInt(num))));
        }
        });

        container.addComponent(reportList);
        addComponent(BorderLayout.CENTER, container);

        Command back = new Command(I18N.s("back"));
        addCommand(back);
        setBackCommand(back);
        addCommandListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                app.showDashboard(false);
            }
        });
        
        addCommand(new Command(I18N.s("view")) {
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
    }
}
