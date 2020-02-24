package AlphaGroup.AnimeStories.Helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;

import AlphaGroup.AnimeStories.POJO.AnimeItemModel;
import AlphaGroup.AnimeStories.R;
import AlphaGroup.AnimeStories.UI.Details.StoryDetails;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeStoriesAdaper extends RecyclerView.Adapter<AnimeStoriesAdaper.ViewHolder> {
    private Context context;
    private List<AnimeItemModel> data;

    public AnimeStoriesAdaper(Context context, List<AnimeItemModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.story_item_model, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.categorie.setText(data.get(position).getCategorie());
        holder.rate.setText(data.get(position).getRating());
        holder.studio.setText(data.get(position).getStudio());


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.color.storyImgBackgound);
        requestOptions.error(R.color.storyImgBackgound);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.fitCenter();
        Glide.with(context)
                .load(data.get(position).getImg())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.prograss_load_photo.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.prograss_load_photo.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.img);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StoryDetails.class);
                i.putExtra("anime_name", data.get(position).getName());
                i.putExtra("anime_description", data.get(position).getDescription());
                i.putExtra("anime_studio", data.get(position).getStudio());
                i.putExtra("anime_category", data.get(position).getCategorie());
                i.putExtra("anime_nb_episode", data.get(position).getEpisode());
                i.putExtra("anime_rating", data.get(position).getRating());
                i.putExtra("anime_img", data.get(position).getImg());

                context.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, categorie, rate, studio;
        LinearLayout view_container;
        ProgressBar prograss_load_photo;
        ;

        public ViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            categorie = itemView.findViewById(R.id.category);
            rate = itemView.findViewById(R.id.rate);
            studio = itemView.findViewById(R.id.studio);
            prograss_load_photo = itemView.findViewById(R.id.prograss_load_photo);

        }
    }
}
