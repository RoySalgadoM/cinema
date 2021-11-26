package mx.edu.utez.model.movie;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private String synopsis;
    @XmlElement
    private int rating;
    @XmlElement
    private String registered_date;
    @XmlElement
    private String updated_date;
    @XmlElement
    private int state;
    @XmlElement
    private int category;

    public Movie(int id, String title, String description, String synopsis, int rating, String registered_date, String updated_date, int state, int category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.synopsis = synopsis;
        this.rating = rating;
        this.registered_date = registered_date;
        this.updated_date = updated_date;
        this.state = state;
        this.category = category;
    }
    public Movie(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

