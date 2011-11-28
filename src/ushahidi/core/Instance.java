/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ushahidi.core;

import com.ushahidi.j2me.models.Report;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author user
 */
public class Instance {
    private HttpConnection connection;
    private InputStream inputStream;
    private KXmlParser parser = null;
    private Settings settings;
    private Report newReport;
    private int catFlag;
    private Vector reports;

    public Instance()
    {
        settings = new Settings();
        reports = new Vector();
        catFlag = 0;
        
        try
        {
            this.parse(settings.getDeployment()[0], "api?task=incidents&by=all");
        }
        catch(IOException ex)
        {
            System.out.println("didn't work");
        }
    }

    public void parse(String url, String heading) throws IOException
    {
        connection = (HttpConnection) Connector.open("http://haiti.ushahidi.com/api?task=incidents&by=all&resp=xml");
        //connection = (HttpConnection) Connector.open(url+heading+"&resp=xml");
        parser = new KXmlParser();
        inputStream = connection.openInputStream();
        InputStreamReader is = new InputStreamReader(inputStream);
        try
        {
            parser.setInput(is);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "response");

            while(parser.next() != XmlPullParser.END_DOCUMENT)
            {
               if(parser.getEventType() == XmlPullParser.START_TAG)
               {
                   if(parser.getName().equals("incident"))
                   {
                       if(catFlag == 1)
                       {
                            catFlag = 0;
                            reports.addElement(newReport);
                       }
                       newReport = new Report();
                       parser.nextTag();
                   }
                   if(parser.getName().equals("id"))
                   {
                       //System.out.println("id "+parser.nextText());
                   }
                   if(parser.getName().equals("title"))
                   {
                       if(catFlag == 0)
                       {
                            newReport.setTitle(parser.nextText());
                            //System.out.println("title "+parser.nextText());
                            catFlag = 1;
                       }
                       else
                       {
                           newReport.addCategory(parser.nextText());
                           //System.out.println("title "+parser.nextText());
                       }

                   }
                   if(parser.getName().equals("description"))
                   {
                       newReport.setDescription(parser.nextText());
                       //System.out.println("description "+parser.nextText());
                   }
                   if(parser.getName().equals("date"))
                   {
                       newReport.setDate(parser.nextText());
                       //System.out.println("date "+parser.nextText());
                   }
                   if(parser.getName().equals("name"))
                   {
                       newReport.setCity(parser.nextText());
                       //System.out.println("name "+parser.nextText());
                   }
               }
            }
            reports.trimToSize();
            connection.close();
        }
        catch(XmlPullParserException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public String getTitle(int index)
    {
        return ((Report) reports.elementAt(index)).getTitle();
    }

    public int getsize()
    {
        return reports.size();
    }

    public Report getReport(int index)
    {
        return ((Report) reports.elementAt(index));
    }
}
