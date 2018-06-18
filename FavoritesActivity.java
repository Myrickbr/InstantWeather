package themaverick.instantweather;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG = "FavoritesActivity";

    DatabaseHelper mDatabaseHelper;
    private TextView title;
    private Button addButton;
    private ListView list;
    private EditText city, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        title = (TextView)findViewById(R.id.textView);
        addButton = (Button)findViewById(R.id.button4);
        mDatabaseHelper = new DatabaseHelper(this);
        city = (EditText)findViewById(R.id.city);
        latitude = (EditText)findViewById(R.id.latitude);
        longitude = (EditText)findViewById(R.id.longitude);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String City = city.getText().toString();
                String Latitude = latitude.getText().toString();
                String Longitude = longitude.getText().toString();

                if(City.length() != 0 && Latitude.length() != 0 && Longitude.length() != 0){
                    AddData(City, Latitude, Longitude);
                    city.setText("");
                    latitude.setText("");
                    longitude.setText("");
                    populateListView();
                }
                else{
                    toastMessage("You must put text in text field");
                }
            }
        });
    }

    public void AddData(String city, String latitude, String longitude){
        boolean insertData = mDatabaseHelper.addData(city, latitude, longitude);

        if(insertData){
            toastMessage("Data successfully inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void populateListView(){
        Log.d(TAG, "populateListView: DIsplaying data in the ListView.");

        //Get data and append it to the list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        //Create list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        list.setAdapter(adapter);
    }


}
