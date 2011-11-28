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
 * @author dalezak
 */
public class Reports extends Base {
    //private Instance incidents;
    private Vector reportVec;

    public Reports(final App app, List reportList) {
        super(I18N.s("Reports"));
        setLayout(new BorderLayout());
        //incidents = new Instance();
        Container container = createdBoxLayout();

//        System.out.println("the size is "+incidents.getsize());
//        for(int i = 0; i < incidents.getsize(); i++)
//        {
//            reportVec.addElement(incidents.getTitle(i));
//        }
//
        //reportList = new List(reportVec);
        reportList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            String temp = new String(ae.getSource().toString().substring(121, 125));
            int i = 0;
            while((temp.charAt(i) >= '0')&&(temp.charAt(i) <= '9'))
            {
                i++;
            }

            app.showReport(true, (app.getAReport(Integer.parseInt(temp.substring(0,i)))));
             //new Report(this, Integer.parseInt(temp.substring(0, i)));
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
