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
import bid.a02.a02clientproject.ViewModels.HouseViewModel;

public class HouseDetailActivity extends AppCompatActivity {


    EditText evAddress;
    EditText evNotes;
    String mode;

    House currentHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        evAddress = (EditText) findViewById(R.id.txtAddress);
        evNotes = (EditText) findViewById(R.id.txtNotes);


        mode = getIntent().getExtras().getString("mode").toString();

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

        if (mode.equals("new")) {
            tvTitle.setText("New House");
        } else {
            tvTitle.setText("existing house");
            String houseId = getIntent().getExtras().getString("houseId").toString();

            houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
            currentHouse = houseViewModel.getHouseById(Integer.parseInt(houseId)).getValue();
            evAddress.setText(currentHouse.address);
            evNotes.setText(currentHouse.notes);


        }


    }

    private HouseViewModel houseViewModel;

    private void SaveHouse() {

        if (mode.equals("new")) {
            currentHouse = new House();
        }

        currentHouse.address = evAddress.getText().toString();
        currentHouse.notes = evNotes.getText().toString();
        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
        houseViewModel.saveHouse(currentHouse);
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
