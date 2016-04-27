package example.codeclan.com.hellofrog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.w3c.dom.Text;

/**
 * Created by tonygoncalves on 26/04/2016.
 */
public class AmphibianDetails extends AppCompatActivity {
    TextView mNameText;
    TextView mSpeciesText;
    TextView mLegsText;
    TextView mMediaText;
    ImageView mUrlText;


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


        Intent intent = getIntent();
        Bundle extras =intent.getExtras();

        String name = extras.getString("name");
        String species = extras.getString("species");
        String media = extras.getString("media");
        String legs = extras.getString("legs");
        String url = extras.getString("url");

        mNameText.setText("Hello " + name);
        mSpeciesText.setText("You are a " + species);
        mMediaText.setText("This is from " + media);
        mLegsText.setText("This frog has " +legs+ " legs");

        Picasso picasso = Picasso.with(this);
        RequestCreator image = picasso.load(url);
        image.into(mUrlText);
    }
}