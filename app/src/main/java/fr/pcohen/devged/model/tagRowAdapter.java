package fr.pcohen.devged.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.pcohen.devged.R;

public class tagRowAdapter extends RecyclerView.Adapter<tagRowAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tagTextView;

            public ViewHolder(View itemView) {

                super(itemView);

                tagTextView = (TextView) itemView.findViewById(R.id.hashtag);
            }
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /*TODO finir tagRowAdapter https://guides.codepath.com/android/using-the-recyclerview
}
