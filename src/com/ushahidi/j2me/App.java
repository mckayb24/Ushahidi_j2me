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

    public void showReports(boolean backward);

    public void showReport(boolean backward, Report report);

    //public void showDetails(boolean backward, com.ushahidi.j2me.models.Report report);

    public void showSettings(boolean backward);

    public void showSynchronize(boolean backward);

    public void showAddDeploy(boolean backward);

    public void showCreate(boolean backward);

    public Report getAReport(int index);

    public ushahidi.core.Settings getSettings();

}
