package AlphaGroup.AnimeStories.POJO;

public class AnimeItemModel {
String name,description,rating,categorie,studio,img;
int episode;

    public AnimeItemModel() {
    }

    public AnimeItemModel(String name, String description, String rating, String categorie, String studio, String img, int episode) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.categorie = categorie;
        this.studio = studio;
        this.img = img;
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
