/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Wrappers;

/**
 *
 * @author HP
 */
public class DoubtWrapper {
    Integer[] categorydata;
    Integer[] tagsdata;
    String description;

    
     public DoubtWrapper() {
    }
     
    public Integer[] getCategorydata() {
        return categorydata;
    }

    public void setCategorydata(Integer[] categorydata) {
        this.categorydata = categorydata;
    }

    public Integer[] getTagsdata() {
        return tagsdata;
    }

    public void setTagsdata(Integer[] tagsdata) {
        this.tagsdata = tagsdata;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
