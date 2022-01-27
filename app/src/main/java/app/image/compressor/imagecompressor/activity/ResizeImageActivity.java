package app.image.compressor.imagecompressor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.utils.Helper;
import app.image.compressor.imagecompressor.views.NiceSpinner;
import app.image.compressor.imagecompressor.views.OnSpinnerItemSelectedListener;

public class ResizeImageActivity extends AppCompatActivity {

    EditText width, height;
    TextView submit;
    ImageView resize_img;
    Bitmap bitmap;
    NiceSpinner format_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resizeimage);

        String s = getIntent().getStringExtra("list");

        width = findViewById(R.id.img_width);
        height = findViewById(R.id.img_height);
        submit = findViewById(R.id.submit);
        resize_img = findViewById(R.id.resize_img);
        format_spinner = findViewById(R.id.format_spinner);

        bitmap = BitmapFactory.decodeFile(s);
        width.setText(bitmap.getWidth()+"");
        height.setText(bitmap.getHeight()+"");

        int w_one = bitmap.getWidth() / 2;
        int h_one = bitmap.getHeight() / 2;
        int w_two = (int) (bitmap.getWidth() / 1.8);
        int h_two = (int) (bitmap.getHeight() / 1.8);
        int w_three = (int) (bitmap.getWidth() / 1.5);
        int h_three = (int) (bitmap.getHeight() / 1.5);
        int w_four = (int) (bitmap.getWidth() / 1.2);
        int h_four = (int) (bitmap.getHeight() / 1.2);

        List<String> dataset = new LinkedList<>(Arrays.asList("custom", w_one+" x "+h_one, w_two+" x "+h_two, w_three+" x "+h_three, w_four+" x "+h_four));
        format_spinner.attachDataSource(dataset);

        format_spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int w, h;
                if (!width.getText().toString().isEmpty() || !height.getText().toString().isEmpty()) {
                    w = Integer.parseInt(width.getText().toString());
                    h = Integer.parseInt(height.getText().toString());
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, w, h, true);
                    try {
                        Helper.savebitmap(bitmap1,100, "jpg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(ResizeImageActivity.this).asBitmap().load(bitmap1).into(resize_img);
                } else {
                    Toast.makeText(ResizeImageActivity.this, "enter value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}