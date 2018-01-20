package bid.a02.a02clientproject;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import bid.a02.a02clientproject.DataAccess.House;

public class HouseDetailActivity extends AppCompatActivity {


    EditText evAddress;
    EditText evNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        String mode = getIntent().getExtras().getString("mode").toString();

        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);

        if(mode.equals("new"))
        {
            tvTitle.setText("New House");
        }
        else
        {
            tvTitle.setText("existing house");
        }

        evAddress = (EditText)findViewById(R.id.txtAddress);
        evNotes = (EditText)findViewById(R.id.txtNotes);

    }

    private HouseViewModel houseViewModel;

    private void SaveHouse(){

        House myHouse = new House();
        myHouse.address = evAddress.getText().toString();
        myHouse.notes = evAddress.getText().toString();


        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
        houseViewModel.addHouse(myHouse);
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_housedetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
                SaveHouse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
