package fr.pcohen.devged.view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.pcohen.devged.R;
import fr.pcohen.devged.business.Search;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtTitle) TextView txtTitle;

    public ResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithSearch(Search search){
        this.txtTitle.setText(search.getTitle());
    }

}
