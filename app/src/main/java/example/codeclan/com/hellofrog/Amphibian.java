package example.codeclan.com.hellofrog;

//this is the object that we will make several instances

import java.io.Serializable;

/**
 * Created by sandy on 25/04/2016.
 */
public class Amphibian implements Serializable {
    private String mName;
    private String mSpecies;
    private int mNumLegs;

    public Amphibian()
    {
        mNumLegs = 4;
    }
    public Amphibian(String name)
    {
        this();
        mName = name;
    }

    public Amphibian(String name, String species)
    {
        this();
        mName = name;
        mSpecies = species;
    }

    public String getName() {
        return mName;
    }

    public void setName(String nName) {
        mName = nName;
    }

    public String getSpecies() { return mSpecies; }

    public void setSpecies(String species) { mSpecies = species;}

    public int getNumLegs() { return mNumLegs; }
}






