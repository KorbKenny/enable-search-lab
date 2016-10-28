package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KorbBookProReturns on 10/25/16.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView, mLine;

    public CustomViewHolder(View itemView) {
        super(itemView);

        mTextView = (TextView)itemView.findViewById(R.id.customRecycleText);
        mLine = (TextView)itemView.findViewById(R.id.line);
    }
}
