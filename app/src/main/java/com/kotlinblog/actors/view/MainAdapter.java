package com.kotlinblog.actors.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kotlinblog.actors.App;
import com.kotlinblog.actors.R;
import com.kotlinblog.actors.data.Actor;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import timber.log.Timber;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private final Context mContext;
    private final MainViewModel mViewModel;

    private Picasso mPicasso;

    MainAdapter(MainViewModel mViewModel, Activity activity) {
        this.mViewModel = mViewModel;
        this.mContext = activity;
        this.mPicasso = App.get(activity).getComponent().getPicasso();

        Picasso picasso1 = App.get(activity).getComponent().getPicasso();
        Picasso picasso2 = App.get(activity).getComponent().getPicasso();
        Picasso amIaSingletonPicasso = App.get(activity).getAnotherComponent().getPicasso();

        Retrofit retrofit1 = App.get(activity).getComponent().getRetrofit();
        Retrofit retrofit2 = App.get(activity).getComponent().getRetrofit();
        Retrofit amIaSingletonRetrofit = App.get(activity).getAnotherComponent().getRetrofit();
        Timber.d("objects injected...");
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.actor_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Actor actor = mViewModel.getActorAt(position);
        if (actor != null) {
            mPicasso.load(actor.image).into(holder.picture);
            holder.actorName.setText(actor.name);
            holder.country.setText(mContext.getString(R.string.from, actor.country));
            holder.dob.setText(mContext.getString(R.string.born, actor.dob));
            holder.height.setText(mContext.getString(R.string.height, actor.height));
            holder.description.setText(actor.description);
        }
    }

    @Override
    public int getItemCount() {
        return mViewModel.getActorsListSize();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        final ImageView picture;
        final TextView actorName;
        final TextView country;
        final TextView dob;
        final TextView height;
        final TextView description;

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
