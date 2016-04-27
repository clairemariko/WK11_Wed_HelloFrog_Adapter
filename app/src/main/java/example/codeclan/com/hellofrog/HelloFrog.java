package example.codeclan.com.hellofrog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

//this is the main page

/**
 * Created by sandy on 25/04/2016.
 */
public class
HelloFrog extends AppCompatActivity {

    private static final String API_URL = "http://cc-amphibian-api.herokuapp.com/";

    EditText mNameEditText;
    EditText mSpeciesEditText;
    Button mSubmitButton;
    ListView mListView;

    //need to have an instance of our json class
    JSONAdapter mJSONAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HelloFrog:", "onCreate called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        queryAmphibians();

        mNameEditText = (EditText) findViewById(R.id.name_input);
        mSpeciesEditText = (EditText) findViewById(R.id.species_input);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mListView = (ListView)findViewById(R.id.amphibian_list_view);

        //this is basically self which is the context which is a child of activity?
        mJSONAdapter = new JSONAdapter(this, getLayoutInflater());
        queryAmphibians();
        //we could pass in a different adapter here if we have made a coutry adapter
        mListView.setAdapter(mJSONAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                JSONObject jsonObject = (JSONObject) mJSONAdapter.getItem(position);
                Log.d("HelloFrog: ", jsonObject.toString());
                Intent intent = new Intent(HelloFrog.this, AmphibianDetails.class);
                intent.putExtra("name", jsonObject.optString("name"));
                intent.putExtra("media", jsonObject.optString("media"));
                intent.putExtra("species", jsonObject.optString("species"));
                intent.putExtra("legs", jsonObject.optString("numberOfLegs"));
                intent.putExtra("url", jsonObject.optString("imageUrl"));
                startActivity(intent);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HelloFrog:", "submit button clicked!");
                Intent intent = new Intent(HelloFrog.this, AmphibianDetails.class);
                intent.putExtra("name", mNameEditText.getText().toString());
                intent.putExtra("species", mSpeciesEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void queryAmphibians(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject jsonObject){
               Log.d("HelloFrog:", jsonObject.toString());
                JSONArray data = jsonObject.optJSONArray("Amphibians");
                if (data != null){

                    mJSONAdapter.updateData(data);

                }else{
                    Log.e("HelloFrog: ", "no data found");
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error){
                Log.e("HelloFrog:", "failure: " + statusCode + " " + throwable.getMessage());
            }
        });
    }
}
