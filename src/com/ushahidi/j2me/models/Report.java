package com.ushahidi.j2me.models;

import java.util.Vector;


/**
 * Report Model
 * @author dalezak
 */
public class Report
{
    private String title;
    private String description;
    private String date;
    private String city;
    private Vector categories;

    public Report()
    {
        title = "default";
        description = "default";
        date = "default";
        city = "default";
        categories = new Vector();
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getDate()
    {
        return date;
    }

    public String getCity()
    {
        return city;
    }

    public Vector getCategory()
    {
        return categories;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    public void setDescription(String newDescription)
    {
        description = newDescription;
    }

    public void setDate(String newDate)
    {
        date = newDate;
    }

    public void setCity(String newCity)
    {
        city = newCity;
    }

    public void addCategory(String newCategory)
    {
        categories.addElement(newCategory);
    }
}
