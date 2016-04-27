package example.codeclan.com.hellofrog;

//this is the object that we will make several instances

/**
 * Created by sandy on 25/04/2016.
 */
public class Amphibian {
    private String mName;

    public Amphibian(String name)
    {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String nName) {
        mName = nName;
    }
}
