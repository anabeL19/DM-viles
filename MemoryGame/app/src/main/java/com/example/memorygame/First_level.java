package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class First_level extends AppCompatActivity {
    private ImageView[] img_v_taps = new ImageView[4];
    private ImageView[] img_vs = new ImageView[4];
    private ArrayList<ImageView> imgs = new ArrayList<ImageView>();
    private int score = 0;
    private int count = 0, c = 50;
    private TextView score_acu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level);

        score_acu = findViewById(R.id.tview_1);

        img_v_taps[0] = (ImageView) findViewById(R.id.tap1);
        img_v_taps[1] = (ImageView) findViewById(R.id.tap2);
        img_v_taps[2] = (ImageView) findViewById(R.id.tap3);
        img_v_taps[3] = (ImageView) findViewById(R.id.tap4);

        img_vs[0] = (ImageView) findViewById(R.id.img_drum);
        img_vs[1] = (ImageView) findViewById(R.id.img_violin);
        img_vs[2] = (ImageView) findViewById(R.id.img_violin2);
        img_vs[3] = (ImageView) findViewById(R.id.img_drum2);
    }

    public void Image1(View  view) {
        if (img_v_taps[0].getVisibility() == View.VISIBLE) {
            img_v_taps[0].setVisibility(View.INVISIBLE);
        }
        Game(img_v_taps[0], img_vs[0]);
    }

    public void Image2(View view) {
        if (img_v_taps[1].getVisibility() == View.VISIBLE) {
            img_v_taps[1].setVisibility(View.INVISIBLE);
        }
        Game(img_v_taps[1], img_vs[1]);
    }

    public void Image3(View view) {
        if (img_v_taps[2].getVisibility() == View.VISIBLE) {
            img_v_taps[2].setVisibility(View.INVISIBLE);
        }
        Game(img_v_taps[2], img_vs[2]);
    }

    public void Image4(View view) {
        if (img_v_taps[3].getVisibility() == View.VISIBLE) {
            img_v_taps[3].setVisibility(View.INVISIBLE);
        }
        Game(img_v_taps[3], img_vs[3]);
    }

    public void Game(ImageView tap_actual, ImageView img_actual) {
        if (tap_actual.getVisibility() == View.INVISIBLE) {
            for (int i=0; i<img_v_taps.length; i++) {
                if ( tap_actual != img_v_taps[i] && !imgs.contains(img_vs[i])) {
                    if (img_v_taps[i].getVisibility() == View.INVISIBLE) {
                        Check(img_actual, img_vs[i], tap_actual, img_v_taps[i]);
                    }
                }
            }
        }
    }

    public void Check(ImageView img1, ImageView img2, ImageView tap1, ImageView tap2) {
        Bitmap bitmap = ((BitmapDrawable)img1.getDrawable()).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable)img2.getDrawable()).getBitmap();
        if (score == 1) {
            if (bitmap == bitmap2) {
                score ++;
                imgs.add(img1);
                imgs.add(img2);
                Toast.makeText(this, "good!", Toast.LENGTH_SHORT).show();
                count = count + c;
                score_acu.setText("Score: "+ count+"");
                if (score == 2) {
                    Toast.makeText(this, "WIN level 1!", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(this, Second_level.class);
                    startActivity(in);
                }
            }
            else {
                Toast.makeText(this, "diferents!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (bitmap == bitmap2) {
                score++;
                imgs.add(img1);
                imgs.add(img2);
                Toast.makeText(this, "good!", Toast.LENGTH_SHORT).show();
                count = count + c;
                score_acu.setText("Score: "+ count+"");
            }
            else {
                Toast.makeText(this, "diferents!", Toast.LENGTH_SHORT).show();
                tap1.setVisibility(View.VISIBLE);
                tap2.setVisibility(View.VISIBLE);
            }
        }
    }
}