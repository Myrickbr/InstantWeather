package themaverick.instantweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    TextView Title, cityQuestion;
    EditText textbox1, textbox2;
    private String Latitude;
    private String Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Title = (TextView)findViewById(R.id.textView2);
        cityQuestion = (TextView)findViewById(R.id.textView3);
        textbox1 = (EditText)findViewById(R.id.editText);
        textbox2 = (EditText)findViewById(R.id.editText2);

        //Search button
        Button searchButton = (Button)findViewById(R.id.button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //First, must obtain user input (Latitude, Longitude
                Latitude = textbox1.getText().toString();
                Longitude = textbox2.getText().toString();

                //Now convert to double and check if coordinates are valid
                if(Latitude.matches("") || Longitude.matches("")){
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP| Gravity.LEFT, 0, 0);
                    toast.makeText(SearchActivity.this, "Invalid Coordinates", toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    double tempLatitude = Double.parseDouble(Latitude);
                    double tempLongitude = Double.parseDouble(Longitude);
                    if (tempLatitude > 360 || tempLatitude < -360 || tempLongitude > 360 || tempLongitude < -360) {
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                        toast.makeText(SearchActivity.this, "Invalid Coordinates", toast.LENGTH_SHORT).show();
                    } else {
                        //Start new activity and pass Latitude and Longitude
                        Intent i = new Intent(SearchActivity.this, MainActivity.class);
                        i.putExtra("EXTRA_KEY_1", Latitude);
                        i.putExtra("EXTRA_KEY_2", Longitude);
                        startActivity(i);
                    }
                }

            }
        });

        //Favorites button
        Button favoritesButton = (Button)findViewById(R.id.button3);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, FavoritesActivity.class));
            }

        });

    }


}
