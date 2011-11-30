package com.ushahidi.j2me.models;

import java.util.Vector;


/**
 * Report Model
 * @author Brett McKay
 */
public class Report
{
    private String title;
    private String description;
    private String date;
    private String city;
    private Vector categories;

    /**
     * This method creates an instance of the Report class.
     * The Report class stores all the info for a report
     * @pre none
     * @post Creates an instance of the Report class
     */
    public Report()
    {
        title = "default";
        description = "default";
        date = "default";
        city = "default";
        categories = new Vector();
    }

    /**
     * gets the title of the report
     * @pre none
     * @post String with report title is returned
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * gets the description of the report
     * @pre none
     * @post String with report description is returned
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * gets the date of the report
     * @pre none
     * @post String with report date is returned
     */
    public String getDate()
    {
        return date;
    }

    /**
     * gets the city of the report
     * @pre none
     * @post String with report city is returned
     */
    public String getCity()
    {
        return city;
    }

    /**
     * gets the categories of the report
     * @pre none
     * @post Vector with report categories is returned
     */
    public Vector getCategory()
    {
        return categories;
    }

    /**
     * sets the title of the report
     * @pre none
     * @param String newTitle
     * @post Report's title becomes newTitle
     */
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    /**
     * sets the description of the report
     * @pre none
     * @param String newDescription
     * @post Report's description becomes newDescription
     */
    public void setDescription(String newDescription)
    {
        description = newDescription;
    }

    /**
     * sets the date of the report
     * @pre none
     * @param String newDate
     * @post Report's date becomes newDate
     */
    public void setDate(String newDate)
    {
        date = newDate;
    }

    /**
     * sets the city of the report
     * @pre none
     * @param String newCity
     * @post Report's city becomes newCity
     */
    public void setCity(String newCity)
    {
        city = newCity;
    }

    /**
     * adds a categoriy to report's categories
     * @pre none
     * @param String newCategory
     * @post newCategory is added to report's categories
     */
    public void addCategory(String newCategory)
    {
        categories.addElement(newCategory);
    }
}
