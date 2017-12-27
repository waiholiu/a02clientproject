package bid.a02.a02clientproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnGoToMap(View view) {

        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);




    }

    public void btnGoToTheDb(View view) {
        Intent intent = new Intent(MainActivity.this, DbActivity.class);
        startActivity(intent);
    }

    public void btnGoToServer(View view) {
        Intent intent = new Intent(MainActivity.this, ServerActivity.class);
        startActivity(intent);
    }

    public void btnGoToRetrofit(View view) {
        Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
        startActivity(intent);
    }

    public void btnCamera(View view) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    public void btnNotification_click(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void btnHouse_click(View view) {
        Intent intent = new Intent(MainActivity.this, HousesActivity.class);
        startActivity(intent);
    }
}

