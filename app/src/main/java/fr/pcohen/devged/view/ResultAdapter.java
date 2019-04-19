package fr.pcohen.devged.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.pcohen.devged.R;
import fr.pcohen.devged.business.Search;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{

    private List<Search> searchs;

    public ResultAdapter(List<Search> searchs) {
        this.searchs = searchs;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_result, viewGroup, false);

        return new ResultViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ResultViewHolder resultViewHolder, int i) {
        resultViewHolder.updateWithSearch(this.searchs.get(i));
    }

    @Override
    public int getItemCount() {
        return this.searchs.size();
    }
}
