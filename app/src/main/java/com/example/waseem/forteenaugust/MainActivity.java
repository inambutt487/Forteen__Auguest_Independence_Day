package com.example.waseem.forteenaugust;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    Button btn_cam, btn_gal, sharebtn, rate_btn;

    InterstitialAd mInterstitialAd;

    int PERMISSION_ALL = 1;
    String[] PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3444255945927869/3797162179");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });


        if (!hasPermissions(this, PERMISSION)) {
            ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_ALL);
        }

        sharebtn = (Button) findViewById(R.id.shareapp);
        sharebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            try {
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_SUBJECT, "Karachi Kings Photo Frame DP Maker PSL Themes 2018");
                                String sAux = "\nLet me recommend you this application\n\n";
                                sAux = sAux + "com.utilititesapps.karachi.kings.live.psl.profilephoto.editor.maker \n\n";
                                i.putExtra(Intent.EXTRA_TEXT, sAux);
                                startActivity(Intent.createChooser(i, "choose one"));
                            } catch (Exception e) {
                                //e.toString();
                            }
                        }
                    }
                }
        );

        rate_btn = (Button) findViewById(R.id.rateapp);

        rate_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://search?q="
                                            + "FreeAppsCorner&hl=en")));
                        }
                    }
                }
        );


        BannerAdmob();

        btn_cam = (Button) findViewById(R.id.cmra);

        btn_gal = (Button) findViewById(R.id.glry);
        final Intent intent = new Intent(MainActivity.this, SecondActivity.class);


        btn_cam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {

                            intent.putExtra("message", 1);
                            startActivity(intent);
                        }

                    }
                }

        );

        btn_gal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {

                            intent.putExtra("message", 2);
                            startActivity(intent);
                        }

                    }
                }

        );

    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView1 = (AdView) this.findViewById(R.id.adView1);
        AdView adView2 = (AdView) this.findViewById(R.id.adView2);
        adView1.loadAd(new AdRequest.Builder().build());
        adView2.loadAd(new AdRequest.Builder().build());
    }

    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            AlertDialog.Builder ad1 = new AlertDialog.Builder(this);
            ad1.setMessage("Do you want to rate this app? ");
            ad1.setCancelable(false);
            ad1.setIcon(R.drawable.iconmain);
            ad1.setTitle("Rate This App");

            ad1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            ad1.setNeutralButton("Exit", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    finish();
                }
            });

            ad1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id="
                                    + "com.utilititesapps.karachi.kings.live.psl.profilephoto.editor.maker")));
                }
            });
            AlertDialog alert = ad1.create();
            alert.show();
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
