package AlphaGroup.AnimeStories.UI.Details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import AlphaGroup.AnimeStories.R;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StoryDetails extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.categorie)
    TextView categorie;
    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.studio)
    TextView studio;
    @BindView(R.id.collapsingtoolbar_id)
    CollapsingToolbarLayout collapsingtoolbarId;
    @BindView(R.id.description)
    TextView description;

    private String AName, AStudio, ADescription, ACategory, AEpisode, ARate, AImage_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);
        ButterKnife.bind(this);

        collapsingtoolbarId.setTitleEnabled(true);

        RecieveData();
        setDataToView();
    }

    private void setDataToView() {
        name.setText(AName);
        categorie.setText(ACategory);
        description.setText(ADescription);
        rate.setText(ARate);
        studio.setText(AStudio);

        collapsingtoolbarId.setTitle(AName);
        Picasso.get()
                .load(AImage_url)
                .placeholder(R.color.storyImgBackgound)
                .into(img);


    }

    private void RecieveData() {

        AName = getIntent().getExtras().getString("anime_name");
        ADescription = getIntent().getExtras().getString("anime_description");
        AStudio = getIntent().getExtras().getString("anime_studio");
        ACategory = getIntent().getExtras().getString("anime_category");
        AEpisode = String.valueOf(getIntent().getExtras().getInt("anime_nb_episode"));
        ARate = getIntent().getExtras().getString("anime_rating");
        AImage_url = getIntent().getExtras().getString("anime_img");

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    StoryDetails.this.finish();

    }
}
