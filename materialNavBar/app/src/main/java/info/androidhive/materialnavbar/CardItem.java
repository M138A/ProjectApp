package info.androidhive.materialnavbar;

public class CardItem {
    public String name;
    public String age;
    public int photoId;
    public String placeholder;

    //overloading methinks
    public CardItem(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
}