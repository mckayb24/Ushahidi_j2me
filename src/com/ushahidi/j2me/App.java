package com.ushahidi.j2me;

import com.ushahidi.j2me.models.Report;

/**
 *
 * @author dalezak
 */
public interface App {

    public void exit();

    public void showSplash(boolean forward);

    public void showDashboard(boolean backward);

    /**
     * displays the form with all of the report titles
     * @param boolean backward : tells the transition which way to slide
     */
    public void showReports(boolean backward);

    /**
     * displays the form with the report details
     * @param boolean backward : tells the transition which way to slide
     * @param Report report : the report that will be displayed
     */
    public void showReport(boolean backward, Report report);

    public void showSettings(boolean backward);

    public void showSynchronize(boolean backward);

    /**
     * displays the form where a new deployment is entered
     * @param boolean backward : tells the transition which way to slide
     */
    public void showAddDeploy(boolean backward);

    public void showCreate(boolean backward);

    /**
     * gets the report located at index
     * @param int index
     * @pre index > 0 && index < number of reports
     */
    public Report getAReport(int index);

    public ushahidi.core.Settings getSettings();

}
