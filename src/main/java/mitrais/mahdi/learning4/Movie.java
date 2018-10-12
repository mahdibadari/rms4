package mitrais.mahdi.learning4;

public class Movie {
    private String Type;
    private String Year;
    private String imdbID;
    private String Poster;
    private String Title;

    public Movie (String type, String year, String imdbid, String poster, String title){
        this.Year = year;
        this.Type = type;
        this.imdbID = imdbid;
        this.Poster = poster;
        this.Title = title;
    }

    public String getTitle(){
        return this.Title;
    }
}