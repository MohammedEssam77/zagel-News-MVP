package com.example.win10.zagel.models;

public class SourcesModel {
    /**
     * ID : 1
     * Title : اليوم السابع
     * LocationID : 2
     * Category : News
     * DomainURL : https://www.youm7.com
     * ImgURl : img/source/1.jpg
     * IsSelected : true
     */
    private int ID;
    private String Title;
    private int LocationID;
    private String Category;
    private String DomainURL;
    private String ImgURl;
    private boolean IsSelected;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDomainURL() {
        return DomainURL;
    }

    public void setDomainURL(String DomainURL) {
        this.DomainURL = DomainURL;
    }

    public String getImgURl() {
        return ImgURl;
    }

    public void setImgURl(String ImgURl) {
        this.ImgURl = ImgURl;
    }

    public boolean isIsSelected() {
        return IsSelected;
    }

    public void setIsSelected(boolean IsSelected) {
        this.IsSelected = IsSelected;
    }
}
