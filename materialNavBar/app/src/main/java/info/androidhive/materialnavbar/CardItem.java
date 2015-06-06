package info.androidhive.materialnavbar;

public class CardItem {

    // dingen voor op de cards
    public String name;
    public String age;
    public int photoId;
    public int reportId;
    public int favId;

    public String placeholder;

    //overloading methinks is best ?
    public CardItem(String name, String age, int photoId,int reportId,int favId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.reportId = reportId;
        this.favId = favId;

    }
}