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
 * @author Brett McKay
 */
public class Instance {
    private HttpConnection connection;
    private InputStream inputStream;
    private KXmlParser parser = null;
    private Settings settings;
    private Report newReport;
    private int catFlag;
    private Vector reports;
    private String urls[];

    /**
     * This method creates an instance of the Instance class.
     * The Instance class parses the Ushahidi incidents api into reports
     * @pre none
     * @post Creates an instance of the Instance class
     */
    public Instance()
    {
        settings = new Settings();
        reports = new Vector();
        urls = new String[1];
        catFlag = 0;
        
        try
        {
            urls = settings.getDeployment();
            System.out.println(urls);
            if(urls != null)
            {
                this.parse(urls[0], "api?task=incidents&by=all");
            }
        }
        catch(IOException ex)
        {
            System.out.println("didn't work");
        }
    }

    /**
     * This method parses the Ushahidi incidents api into reports
     * @pre String url is a valid Ushahidi deployment url
     * @pre String heading is a valid Ushahidi api heading
     * @post The Vector reports contains a list of reports
     * @param String url, String heading
     */
    public void parse(String url, String heading) throws IOException
    {
        //uncomment next line and comment out the line after that to hard code a deployment
        //connection = (HttpConnection) Connector.open("http://haiti.ushahidi.com/api?task=incidents&by=all&resp=xml");

        //sets up the connection to the ushahidi deployment
        connection = (HttpConnection) Connector.open(url+heading+"&resp=xml");
        parser = new KXmlParser();
        inputStream = connection.openInputStream();
        InputStreamReader is = new InputStreamReader(inputStream);

        try
        {
            parser.setInput(is);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "response");

            //pulls out all of the reports from the incidents api
            while(parser.next() != XmlPullParser.END_DOCUMENT)
            {
               if(parser.getEventType() == XmlPullParser.START_TAG)
               {
                   if(parser.getName().equals("incident"))
                   {
                       //when the catFlag is 1 the parser has made it to
                       //the category tag so the whole incident has been seen
                       if(catFlag == 1)
                       {
                            catFlag = 0;
                            reports.addElement(newReport);
                       }
                       newReport = new Report();
                       parser.nextTag();
                   }
                   if(parser.getName().equals("title"))
                   {
                       //There are two title flags one for the incident itself and
                       //another for the category
                       //this makes sure they are saved appropriatley
                       if(catFlag == 0)
                       {
                            newReport.setTitle(parser.nextText());
                            catFlag = 1;
                       }
                       else
                       {
                           newReport.addCategory(parser.nextText());
                       }

                   }
                   if(parser.getName().equals("description"))
                   {
                       newReport.setDescription(parser.nextText());
                   }
                   if(parser.getName().equals("date"))
                   {
                       newReport.setDate(parser.nextText());
                   }
                   if(parser.getName().equals("name"))
                   {
                       newReport.setCity(parser.nextText());
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
    /**
     * This method gets the title of the report in the list of reports
     * located at the index location
     * @pre index > 0 && index < number of reports
     * @post returns the title of the report as a string
     * @param int index
     */
    public String getTitle(int index)
    {
        return ((Report) reports.elementAt(index)).getTitle();
    }

    /**
     * This method returns the number of reports in the instance
     */
    public int getsize()
    {
        return reports.size();
    }

    /**
     * This method returns the report from the list of reports
     * located at the index
     * @pre index > 0 && index < number of reports
     * @post returns the requested report
     * @param int index
     */
    public Report getReport(int index)
    {
        return ((Report) reports.elementAt(index));
    }
}
