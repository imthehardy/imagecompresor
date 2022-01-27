package app.image.compressor.imagecompressor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.imagepicker.model.Image;
import app.image.compressor.imagecompressor.utils.Helper;
import app.image.compressor.imagecompressor.views.NiceSpinner;
import app.image.compressor.imagecompressor.views.OnSpinnerItemSelectedListener;

public class AdvanceCompressorActivity extends AppCompatActivity {

    ImageView img;
    NiceSpinner format_spin, select_size_spin;
    Bitmap bitmap;
    EditText width, height;
    String format;
    SeekBar img_quality_seek;
    TextView quality_txt_pre, convert;
    int quality = 100;
    int w, h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancecompressor);

        img = findViewById(R.id.img);
        format_spin = findViewById(R.id.format_spin);
        select_size_spin = findViewById(R.id.select_size_spin);
        width = findViewById(R.id.resize_width);
        height = findViewById(R.id.resize_height);
        img_quality_seek = findViewById(R.id.img_quality_seek);
        quality_txt_pre = findViewById(R.id.quality_txt_pre);
        convert = findViewById(R.id.convert);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("list");
        ArrayList<Image> images = (ArrayList<Image>) args.getSerializable("image");

        String image_format = images.get(0).getPath();

        String  s1 = image_format.substring(image_format.lastIndexOf(".") + 1);

        Glide.with(this).load(images.get(0).getPath()).into(img);

        bitmap = BitmapFactory.decodeFile(images.get(0).getPath());
        width.setText(bitmap.getWidth()+"");
        height.setText(bitmap.getHeight()+"");

        w = Integer.parseInt(width.getText().toString());
        h = Integer.parseInt(height.getText().toString());

        int w_one = bitmap.getWidth() / 2;
        int h_one = bitmap.getHeight() / 2;
        int w_two = (int) (bitmap.getWidth() / 1.8);
        int h_two = (int) (bitmap.getHeight() / 1.8);
        int w_three = (int) (bitmap.getWidth() / 1.5);
        int h_three = (int) (bitmap.getHeight() / 1.5);
        int w_four = (int) (bitmap.getWidth() / 1.2);
        int h_four = (int) (bitmap.getHeight() / 1.2);

        List<String> size = new LinkedList<>(Arrays.asList("custom", w_one+" x "+h_one, w_two+" x "+h_two, w_three+" x "+h_three, w_four+" x "+h_four));
        select_size_spin.attachDataSource(size);

        select_size_spin.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        width.setText(bitmap.getWidth()+"");
                        height.setText(bitmap.getHeight()+"");
                        break;
                    case 1:
                        width.setText(w_one+"");
                        height.setText(h_one+"");
                        break;
                    case 2:
                        width.setText(w_two+"");
                        height.setText(h_two+"");
                        break;
                    case 3:
                        width.setText(w_three+"");
                        height.setText(h_three+"");
                        break;
                    case 4:
                        width.setText(w_four+"");
                        height.setText(h_four+"");
                        break;
                }
            }
        });

        List<String> img_format = new LinkedList<>(Arrays.asList("jpg", "png", "webp", "jpeg"));
        format_spin.attachDataSource(img_format);

        format_spin.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        format = "jpg";
                        break;
                    case 1:
                        format = "png";
                        break;
                    case 2:
                        format = "webp";
                        break;
                    case 3:
                        format = "jpeg";
                        break;
                }
            }
        });

        switch(s1){
            case "jpg":
                format_spin.setSelectedIndex(0);
                break;
            case "png":
                format_spin.setSelectedIndex(1);
                break;
            case "webp":
                format_spin.setSelectedIndex(2);
                break;
            case "jpeg":
                format_spin.setSelectedIndex(3);
                break;
            default:
                format_spin.setSelectedIndex(0);
                break;
        }

        img_quality_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                quality_txt_pre.setText(i+"%");
                quality = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!width.getText().toString().isEmpty() || !height.getText().toString().isEmpty()) {
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, w, h, true);
                    try {
                        Helper.savebitmap(bitmap1, quality, format);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}