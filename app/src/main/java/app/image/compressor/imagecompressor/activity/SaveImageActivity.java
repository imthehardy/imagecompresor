package app.image.compressor.imagecompressor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.imagepicker.model.Image;

public class SaveImageActivity extends AppCompatActivity {

    ImageView saved_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveimage);

        Uri uri = Uri.parse(getIntent().getStringExtra("saved_img"));

        saved_img = findViewById(R.id.saved_img);
        Glide.with(this).load(uri).into(saved_img);
    }
}