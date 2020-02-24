package AlphaGroup.AnimeStories.UI.Start;

import AlphaGroup.AnimeStories.R;
import AlphaGroup.AnimeStories.UI.Home.MainActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {
    private int backFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler();
    }


    public void handler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();

            }
        }, 3000);

    }

    @Override
    protected void onStart() {
        super.onStart();

      handler();

    }



    @Override
    public void onBackPressed() {


        backFlag++;

        if (backFlag == 2) {
            finish();
            System.exit(0);        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backFlag = 0;
            }
        }, 2000);
    }
}