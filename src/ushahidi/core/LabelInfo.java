/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ushahidi.core;

/**
 *
 * @author user
 */
public class LabelInfo {

    private String title;
    private String logo;
    private String splash;

    public LabelInfo ()
    {
       title = "brett";
       logo = "/ushahidi/res/logo.png";
       splash = "/ushahidi/res/splash.jpg";
    }

    public String getTitle()
    {
        return title;
    }

    public String getLogo()
    {
        return logo;
    }

    public String getSplash()
    {
        return splash;
    }
}
