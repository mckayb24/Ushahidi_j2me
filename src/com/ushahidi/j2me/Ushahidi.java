package com.ushahidi.j2me;

import com.sun.lwuit.Display;
import com.sun.lwuit.List;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import com.ushahidi.j2me.forms.AddDeployment;
import com.ushahidi.j2me.forms.Dashboard;
import com.ushahidi.j2me.forms.Details;
import com.ushahidi.j2me.forms.Reports;
import com.ushahidi.j2me.forms.ReportForm;
import com.ushahidi.j2me.forms.Settings;
import com.ushahidi.j2me.forms.Splash;
import com.ushahidi.j2me.forms.Synchronize;
import com.ushahidi.j2me.models.Report;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.midlet.*;
import ushahidi.core.Instance;
import ushahidi.core.LabelInfo;

/**
 * @author dalezak
 */
public class Ushahidi extends MIDlet implements App {
    private ushahidi.core.Settings settings;
    private LabelInfo lInfo;
    private Instance incidents;

    public Ushahidi() {
        settings = new ushahidi.core.Settings();
        incidents = new Instance();
        lInfo = new LabelInfo();
    }

    public void startApp() {
        Display.init(this);
        try {
            Resources resources = Resources.open("/res/"+lInfo.getTheme()+".res");
            UIManager.getInstance().setThemeProps(resources.getTheme(lInfo.getTheme()));
         }
         catch(IOException ex) {
             new Alert("UIManager error", ex.getMessage(), null, AlertType.ERROR).setTimeout(50);
         }
        new Splash(this).show();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        //settings.saveDeployment();
        notifyDestroyed();
    }

    public void exit() {
        //settings.saveDeployment();
        notifyDestroyed();
    }

    public void showSplash(boolean forward) {
        Splash splash = new Splash(this);
        splash.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        splash.show();
    }

    public void showDashboard(boolean forward) {
        Dashboard dashboard = new Dashboard(this);
        dashboard.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        dashboard.show();
    }

    public void showReports(boolean forward) {
        Reports reports = new Reports(this, this.getReportTitles());
        reports.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        reports.show();
    }

    public void showReport(boolean forward, Report newReport)
    {
        ReportForm report = new ReportForm(this, newReport);
        report.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        report.show();
    }
//    public void showDetails(boolean forward, Report report) {
//        Details details = new Details(this, report);
//        details.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
//        details.show();
//    }

    public void showSettings(boolean forward) {
        Settings settings = new Settings(this);
        settings.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        settings.show();
    }

    public void showSynchronize(boolean forward) {
        Synchronize synchronize = new Synchronize(this);
        synchronize.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        synchronize.show();
    }

    public void showCreate(boolean forward) {
        //new Report(this).show();
    }

    public ushahidi.core.Settings getSettings() {
        return settings;
    }

    public void showAddDeploy(boolean forward) {
        AddDeployment addDeployment = new AddDeployment(this);
        addDeployment.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, !forward, 500));
        addDeployment.show();
    }

    public List getReportTitles()
    {
       Vector reportVec = new Vector();
       List reportList;
       
       for(int i = 0; i < incidents.getsize(); i++)
        {
            reportVec.addElement(incidents.getTitle(i));
        }
        
        reportList = new List(reportVec);
        return reportList;
    }

    public Report getAReport(int index)
    {
        return incidents.getReport(index);
    }
}
