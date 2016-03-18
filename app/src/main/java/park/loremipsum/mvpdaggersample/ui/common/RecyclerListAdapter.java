package park.loremipsum.mvpdaggersample.ui.common;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class RecyclerListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Getter
    private final List<T> dataSet;

    public RecyclerListAdapter() {
        this.dataSet = new ArrayList<>();
    }

    public void addAll(List<T> newItems) {
        dataSet.addAll(newItems);
    }

    public void replace(List<T> newItems) {
        dataSet.clear();
        if (newItems != null && newItems.size() > 1) {
            dataSet.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
