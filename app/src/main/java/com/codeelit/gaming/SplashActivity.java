package com.codeelit.gaming;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    TextView des;
    TextView internet;
    TextView text;
    ImageView img;
    Button tryagain;
    Animation atg, atgtwo, atgthree;

    class C02791 implements Runnable {
        C02791() {
        }

        public void run() {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    class C02802 implements OnClickListener {
        C02802() {
        }

        public void onClick(View v) {
            if (SplashActivity.isNetworkStatusAvialable(SplashActivity.this.getApplicationContext())) {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView((int) R.layout.activity_splash);

        text = (TextView) findViewById(R.id.tv_txt);
        img = findViewById(R.id.splashImg);
        internet = (TextView) findViewById(R.id.internet);
        tryagain = (Button) findViewById(R.id.tryagain);
        des = (TextView) findViewById(R.id.splashSub);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        img.startAnimation(atg);
        text.startAnimation(atgtwo);
        des.startAnimation(atgthree);

        if (isNetworkStatusAvialable(getApplicationContext())) {
            new Handler().postDelayed(new C02791(), 4000);
            return;
        }
        Toast.makeText(getApplicationContext(), "Please check your Internet Connection", 0).show();
        this.internet.setVisibility(0);
        this.tryagain.setVisibility(0);
        this.tryagain.setOnClickListener(new C02802());
    }

    public static boolean isNetworkStatusAvialable(Context context) {
        @SuppressLint("WrongConstant")
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null) {
                return netInfos.isConnected();
            }
        }
        return false;
    }
}
