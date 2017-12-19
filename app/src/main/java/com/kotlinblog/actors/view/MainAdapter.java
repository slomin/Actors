package com.kotlinblog.actors.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kotlinblog.actors.R;
import com.kotlinblog.actors.data.Actor;
import com.squareup.picasso.Picasso;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context mContext;
    private MainViewModel mViewModel;

    MainAdapter(MainViewModel mViewModel, Context context) {
        this.mViewModel = mViewModel;
        this.mContext = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.actor_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Actor actor = mViewModel.getActorAt(position);
        if (actor != null) {
            Picasso.with(mContext).load(actor.getImage()).into(holder.picture);
            holder.actorName.setText(actor.getName());
            holder.country.setText(mContext.getString(R.string.from, actor.getCountry()));
            holder.dob.setText(mContext.getString(R.string.born, actor.getDob()));
            holder.height.setText(mContext.getString(R.string.height, actor.getHeight()));
            holder.description.setText(actor.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mViewModel.getActorsListSize();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView actorName;
        TextView country;
        TextView dob;
        TextView height;
        TextView description;

        MainViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.ivActorImage);
            actorName = itemView.findViewById(R.id.tvActorName);
            country = itemView.findViewById(R.id.tvCountry);
            dob = itemView.findViewById(R.id.tvDob);
            height = itemView.findViewById(R.id.tvHeight);
            description = itemView.findViewById(R.id.tvDescription);
        }
    }
}
