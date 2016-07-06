package com.example.aishwarya.spidertask3;

/**
 * Created by Aishwarya on 7/5/2016.
 */
public class Movies {
    private String Title;
    private String Genre;
    private String Poster;
    public Movies(String Title,String Genre,String Poster) {
// TODO Auto-generated constructor stub
        super();
        this.Title =Title;
        this.Genre =Genre;
        this.Poster =Poster;
    }
    public Movies() {
// TODO Auto-generated constructor stub
    }
    public String getTitle(){
        return Title;
    }

    public void setTitle(String Title){
        this.Title= Title;
    }

    public String getGenre() {
        return Genre;

    }

    public void setGenre(String Genre){
        this.Genre=Genre;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }
}
