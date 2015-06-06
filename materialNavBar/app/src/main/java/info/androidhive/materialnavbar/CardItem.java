package info.androidhive.materialnavbar;

public class CardItem {

    // dingen voor op de cards
    public String name;
    public String age;
    public int photoId;
    public String placeholder;

    //overloading methinks is best ?
    public CardItem(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;

    }
}