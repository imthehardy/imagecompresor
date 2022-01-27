package app.image.compressor.imagecompressor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.imagepicker.model.Image;
import app.image.compressor.imagecompressor.utils.Helper;

public class ConvertActivity extends AppCompatActivity {

    ImageView img;
    Bitmap bitmap;
    TextView img_size, img_convert;
    long lengthbmp;
    
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("list");
        ArrayList<Image> images = (ArrayList<Image>) args.getSerializable("image");

        img = findViewById(R.id.img);
        img_size = findViewById(R.id.img_size);
        img_convert = findViewById(R.id.img_convert);

        img_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<images.size(); i++){
                    bitmap = BitmapFactory.decodeFile(images.get(i).getPath());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, out);
                    try {
                        Helper.savebitmap(bitmap, 80, "jpg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] imageInByte = out.toByteArray();
                    lengthbmp = imageInByte.length / 1024;
                }
                Glide.with(ConvertActivity.this).asBitmap().load(bitmap).into(img);
                Log.e("TAG", "onCreate: "+lengthbmp);
                img_size.setText((int) lengthbmp+"kb");
            }
        });
    }
}