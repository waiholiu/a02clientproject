package bid.a02.a02clientproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bid.a02.a02clientproject.DataAccess.House;

/**
 * Created by wai on 27/12/17.
 */

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {


    private List<House> houseList;

    public HouseAdapter(List<House> contactList) {
        this.houseList = contactList;
    }


    @Override
    public int getItemCount() {
        return houseList.size();
    }

    @Override
    // onBindViewHolder determines how to populate a ViewHolder as its to be displayed.
    public void onBindViewHolder(HouseViewHolder houseViewHolder, int i) {
        // just take the record from the List<ContactInfo> and populate the viewholder with it
        House house = houseList.get(i);
        houseViewHolder.vAddress.setText(house.address);
        houseViewHolder.vNotes.setText(house.notes);
        houseViewHolder.view.setTag(house.id);

    }

    @Override
    // onCreateViewHolder determines what to do to create ViewHolder
    public HouseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // which is just to inflate the card_layout view and return it as a ContactViewHolder
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.house_card_layout, viewGroup, false);

        return new HouseViewHolder(itemView);
    }

    public void addItems(List<House> houseList) {
        this.houseList = houseList;
        notifyDataSetChanged();
    }


    // Extends RecyclerView because this is what is used by the Adapter
    public static class HouseViewHolder extends RecyclerView.ViewHolder {

        protected TextView vAddress;
        protected TextView vNotes;
        protected TextView vEmail;
        protected TextView vTitle;
        protected View view;

        public HouseViewHolder(View v) {
            super(v);
            vAddress =  (TextView) v.findViewById(R.id.houseName);
            vNotes = (TextView)  v.findViewById(R.id.houseNotes);
            view = v;

        }
    }

}
