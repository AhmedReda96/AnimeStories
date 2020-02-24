package AlphaGroup.AnimeStories.UI.Home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import AlphaGroup.AnimeStories.POJO.AnimeItemModel;
import AlphaGroup.AnimeStories.R;

public class AnimePresenter {
    private AnimeView view;

    private String url = "https://api.myjson.com/bins/nog36";
    private List<AnimeItemModel> list= new ArrayList<>();
    ;
    private Context context;
    private AnimeItemModel animeItemModel = new AnimeItemModel();

    public AnimePresenter(AnimeView view) {
        this.view = view;
    }

    public void GetAnimeData() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        AnimeItemModel animeItemModel = new AnimeItemModel();
                        animeItemModel.setName(jsonObject.getString("name"));
                        animeItemModel.setDescription(jsonObject.getString("description"));
                        animeItemModel.setRating(jsonObject.getString("Rating"));
                        animeItemModel.setCategorie(jsonObject.getString("categorie"));
                        animeItemModel.setEpisode(jsonObject.getInt("episode"));
                        animeItemModel.setStudio(jsonObject.getString("studio"));
                        animeItemModel.setImg(jsonObject.getString("img"));
                        list.add(animeItemModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        view.onGetAnimeData(list,jsonArrayRequest);

    }



}



