package example.codeclan.com.hellofrog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tonygoncalves on 26/04/2016.
 */
public class AmphibianDetails extends AppCompatActivity {
    TextView mNameText;
    TextView mSpeciesText;
    TextView mLegsText;
    TextView mMediaText;
    ImageView mUrlText;
    Button mAddFavouriteButton;
    AmphibianList mFavourites;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d("HelloFrog:","AmphibianDetails.onCreate called");




        mNameText = (TextView) findViewById(R.id.text_name);
        mSpeciesText = (TextView)findViewById(R.id.text_species);
        mMediaText = (TextView)findViewById(R.id.text_media);
        mLegsText = (TextView)findViewById(R.id.text_legs);
        mUrlText = (ImageView)findViewById(R.id.img_detail);
        mAddFavouriteButton =(Button)findViewById(R.id.add_favourite_button);


        Intent intent = getIntent();
        Bundle extras =intent.getExtras();

        String name = extras.getString("name");
        String species = extras.getString("species");
        String media = extras.getString("media");
        String legs = extras.getString("legs");
        String url = extras.getString("url");


        ArrayList<Amphibian> favourites = (ArrayList<Amphibian>)intent.getSerializableExtra("favourites");
        mFavourites = new AmphibianList(favourites);

        mNameText.setText(name);
        mSpeciesText.setText("You are a " + species);
        mMediaText.setText("You are from " + media);
        mLegsText.setText("You have " +legs+ " legs. Just in case you hadn't realised");

        Picasso picasso = Picasso.with(this);
        RequestCreator image = picasso.load(url);
        image.into(mUrlText);

        mAddFavouriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = mNameText.getText().toString();
                String species = mSpeciesText.getText().toString();

                Log.d("HelloFrog: ", name + species + "is to be added to the list");

                Amphibian newFavourite = new Amphibian(name, species);
                mFavourites.add(newFavourite);

                Log.d("HelloFrog: ", "Favourites contains " + mFavourites.length() + " entries");
                for (Amphibian a : mFavourites.getList()) {
                    Log.d("HelloFrog: ", a.toString());
                }

                Intent intent = new Intent();
                intent.putExtra("favourites", mFavourites.getList());
                setResult(RESULT_OK, intent);

            }
        });

    }

    public static AmphibianList getFavourites(Intent intent){
        ArrayList<Amphibian> favourites =(ArrayList<Amphibian>)intent.getSerializableExtra("favourites");
        return new AmphibianList(favourites);
    }
}

