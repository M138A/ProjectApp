package info.androidhive.materialnavbar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mark on 14-6-15.
 */
public class FavoriteManager implements Serializable {
    private ArrayList<Fact> Favorites;
    private Context MainContext;
    private final String FILENAME = "/favorites.fav";

    public ArrayList<Fact> getFavorites() {
        return Favorites;
    }

    public FavoriteManager(Context x) {
        Favorites = new ArrayList<Fact>();

        MainContext = x;
        serializeRead();
       // Test();
    }
    private void Test()
    {
        Log.i("TEST : ", String.valueOf(Favorites.size()));
    }

    public void addFact(Fact f) {
        if (!removeExistingFact(f)) {
            Favorites.add(f);
            serializeWrite();


        }
    }

    /**
     * Checks if fact is already a favorite. If so, it removes it.
     *
     * @param f The fact in question
     * @return Returns true when the fact already exists
     */
    private boolean removeExistingFact(Fact f) {
        String description = f.getDescription();

        for (int i = 0; i < Favorites.size(); i++) {
            Fact factFromList = Favorites.get(i);
            if (description.equals(factFromList.getDescription())) {
                Favorites.remove(i);
                serializeWrite();
                return true;

            }
        }
        return false;
    }

    private void serializeWrite() {
        try {
            FileOutputStream fos = new FileOutputStream(MainContext.getFilesDir() + FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Favorites);
            oos.close();

        } catch (IOException e) {
            Log.e("SER ERROR", e.toString());
        }
    }

    private void serializeRead() {
        try {
            File x = new File(MainContext.getFilesDir() + FILENAME);
            if(x.exists()  || x.isDirectory()) {
                ObjectInputStream ois = new ObjectInputStream(MainContext.openFileInput(FILENAME));
                ArrayList<Fact> facts = (ArrayList<Fact>) ois.readObject();
                ois.close();
                Favorites = facts;
            }
            else{
                Log.i("test", "No Favorites yet");
            }
        } catch (IOException e) {
            Log.e("ERROR : ", e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR : ", e.toString());
        }
    }
}
