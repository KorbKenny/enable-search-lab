package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by KorbBookProReturns on 10/25/16.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    List<ItemObject> mObjectList;

    public CustomRecyclerViewAdapter(List<ItemObject> objectList) {
        mObjectList = objectList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CustomViewHolder(inflater.inflate(R.layout.custom_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mTextView.setText(mObjectList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mObjectList.size();
    }


    public void replaceData(List<ItemObject> newList) {
        mObjectList = newList;
        notifyDataSetChanged();
    }
}
