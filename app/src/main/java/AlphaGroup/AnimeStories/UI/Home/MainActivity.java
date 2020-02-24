package AlphaGroup.AnimeStories.UI.Home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import AlphaGroup.AnimeStories.Connection.ConnectionDetector;
import AlphaGroup.AnimeStories.Helper.AnimeStoriesAdaper;
import AlphaGroup.AnimeStories.POJO.AnimeItemModel;
import AlphaGroup.AnimeStories.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AnimeView {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.errorImage)
    ImageView errorImage;
    @BindView(R.id.errorTitle)
    TextView errorTitle;
    @BindView(R.id.errorMessage)
    TextView errorMessage;
    @BindView(R.id.btnRetry)
    Button btnRetry;
    @BindView(R.id.errorLayout)
    RelativeLayout errorLayout;

    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    private AnimePresenter presenter;
    private RequestQueue requestQueue;
    private List<AnimeItemModel> getDataList = new ArrayList();
    private AnimeStoriesAdaper animeStoriesAdaper;
private int backFlag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Decelerations();
        getData();

    }

    private void Decelerations() {
        requestQueue = Volley.newRequestQueue(this);

            cd = new ConnectionDetector(this);
        btnRetry.setOnClickListener(this);
        presenter = new AnimePresenter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(false);


    }

    @Override
    protected void onStart() {
        setLocale(this);
        super.onStart();

        if (checkNetwork() == false) {
            showErrorMessage();
        } else {
            hideErrorMessage();
            getData();
        }

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData();
            }
        });

    }

    private void getData() {
        swipe.setRefreshing(true);
        presenter.GetAnimeData();

    }

    private boolean checkNetwork() {
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            return false;
        }
        return true;

    }

    private void showErrorMessage() {
        swipe.setRefreshing(false);
        swipe.setEnabled(false);
        errorLayout.setVisibility(View.VISIBLE);
        errorTitle.setText(getResources().getString(R.string.errorMessage1));
        errorMessage.setText(getResources().getString(R.string.errorMessage2));
    }

    private void hideErrorMessage() {
        swipe.setEnabled(true);
        errorLayout.setVisibility(View.GONE);

    }

    private void setLocale(Context context) {

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());


    }

    @Override
    public void onClick(View v) {
        if (v == btnRetry) {
            if (checkNetwork() == false) {
                showErrorMessage();
            } else {
                hideErrorMessage();
                getData();
            }

        }
    }

    @Override
    public void onGetAnimeData(List<AnimeItemModel> animeItemModelList, JsonArrayRequest jsonArrayRequest) {
        requestQueue.add(jsonArrayRequest);
        animeStoriesAdaper = new AnimeStoriesAdaper(this, animeItemModelList);
        recyclerview.setAdapter(animeStoriesAdaper);
        animeStoriesAdaper.notifyDataSetChanged();
        animeItemModelList.clear();

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                swipe.setRefreshing(false);
            }
        });

    }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            backFlag++;

        checkAppClosed();
        }

        private void checkAppClosed() {
            if (backFlag==2){
                finishAffinity();
                System.exit(0);
            }
            else {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        backFlag = 0;
                    }
                }, 2000);
            }
        }
}

