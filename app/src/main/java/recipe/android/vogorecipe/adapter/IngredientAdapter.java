package recipe.android.vogorecipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.android.vogorecipe.R;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mGroupIngredient;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvIngredient)
        TextView tvIngredient;

        @BindView(R.id.imvDelete)
        ImageView imvDelete;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public IngredientAdapter(Context context, List<String> groupIngredient) {
        mContext = context;
        mGroupIngredient = groupIngredient;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_ingredient, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String item = mGroupIngredient.get(position);
        holder.tvIngredient.setText(item);
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeIngredient(item);
            }
        });
    }

    public List<String> getListOfIngredients() {
        return mGroupIngredient;
    }

    private void removeIngredient(String item) {
        mGroupIngredient.remove(item);
        notifyDataSetChanged();
    }

    public void addIngredient(String ingredient) {
        mGroupIngredient.add(ingredient);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGroupIngredient.size();
    }
}