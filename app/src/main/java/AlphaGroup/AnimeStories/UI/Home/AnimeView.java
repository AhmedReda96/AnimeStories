package AlphaGroup.AnimeStories.UI.Home;

import com.android.volley.toolbox.JsonArrayRequest;

import java.util.List;

import AlphaGroup.AnimeStories.POJO.AnimeItemModel;

public interface AnimeView {
    void onGetAnimeData(List<AnimeItemModel> animeItemModelList, JsonArrayRequest jsonArrayRequest);

}
