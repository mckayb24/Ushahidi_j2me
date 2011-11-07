/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ushahidi.core;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.InputConnection;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import java.util.Vector;

/**
 *
 * @author Brett McKay
 * This class stores the variable to control the look of
 * the Ushahidi app
 */
public class LabelInfo {

    private String title;
    private String logo;
    private String splash;
    private String theme;
    private int bgColor;
    private int barColor;
    private Vector inputs;

    public LabelInfo ()
    {
        title = "Ushahidi";//default title = Ushahidi
        theme = "Ushahidi";//default theme = Ushahidi
        logo = "logo";//default logo = logo
        splash = "splash";//default splash = splash
        bgColor = 11359573;//default color 11359573
        barColor = 8002836;//default color 8002836

        //opens the white text file
        InputStream is = getClass().getResourceAsStream("/ushahidi/res/white.txt");
        StringBuffer sb = new StringBuffer();

        inputs = new Vector();
        try
        {
            int chars, i = 0;

            //reads the white.txt file to the end
            //stores all of the words in inputs
            while((chars = is.read()) != -1)
            {
                if((((char) chars) == ' ')||(((char) chars) == '\r') || (((char) chars) == '\n'))
                {
                    if(sb.length() > 0)
                    {
                        inputs.addElement(sb.toString());
                    }
                    sb = new StringBuffer();
                }
                else
                {
                    sb.append((char) chars);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        //if white.txt is formated properly
        //every even entry will be a heading
        //and the enty one after will be the field for that heading
        //i.e title(heading) Ushahidi(field)
        for(int i = 0; i < inputs.size(); i+=2)
        {
            if(inputs.elementAt(i).toString().equalsIgnoreCase("title"))
            {
                title = inputs.elementAt(i+1).toString();
            }
            else if(inputs.elementAt(i).toString().equalsIgnoreCase("logo"))
            {
                logo = inputs.elementAt(i+1).toString();
            }
            else if(inputs.elementAt(i).toString().equalsIgnoreCase("splash"))
            {
                splash = inputs.elementAt(i+1).toString();
            }
            else if(inputs.elementAt(i).toString().equalsIgnoreCase("bgColor"))
            {
                bgColor = Integer.parseInt(inputs.elementAt(i+1).toString());
            }
            else if(inputs.elementAt(i).toString().equalsIgnoreCase("barColor"))
            {
                barColor = Integer.parseInt(inputs.elementAt(i+1).toString());
            }
            else if(inputs.elementAt(i).toString().equalsIgnoreCase("theme"))
            {
                theme = inputs.elementAt(i+1).toString();
            }
        }
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

    public int getBgColor()
    {
        return bgColor;
    }
    public int getBarColor()
    {
        return barColor;
    }
    public String getTheme()
    {
        return theme;
    }
    
}
