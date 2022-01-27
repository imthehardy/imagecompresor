package app.image.compressor.imagecompressor.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.imagepicker.features.ImagePicker;
import app.image.compressor.imagecompressor.imagepicker.model.Image;
import com.yalantis.ucrop.UCrop;
import app.image.compressor.imagecompressor.utils.Helper;

public class HomeActivity extends AppCompatActivity {

    TextView normal_image_compressor, image_crop, image_resize, advance_image_compressor, my_creation;
    private final int SELECT_PHOTO = 1;
    private final int SELECT_PHOTO_CROP = 2;
    private final int SELECT_PHOTO_RESIZE = 3;
    private final int SELECT_PHOTO_COMPRESSOR = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Helper.checkSelfPermission(this)) {
            Helper.requestPermission(this);
        } else {
            File file = new File(Helper.FILE_PATH);
            if (!file.exists()){
                file.mkdir();
            }
            init();
        }
    }

    public void init() {
        normal_image_compressor = findViewById(R.id.normal_image_compressor);
        advance_image_compressor = findViewById(R.id.advance_image_compressor);
        image_crop = findViewById(R.id.image_crop);
        image_resize = findViewById(R.id.image_resize);
        my_creation = findViewById(R.id.my_creation);

        normal_image_compressor.setOnClickListener(view -> {
            ImagePicker.create(HomeActivity.this)
                    .returnAfterFirst(true)
                    .folderMode(true)
                    .folderTitle("Albums")
                    .imageTitle("Tap to select")
                    .limit(9)
                    .showCamera(false)
                    .imageDirectory("Camera")
                    .start(SELECT_PHOTO);
        });

        advance_image_compressor.setOnClickListener(view -> {
            ImagePicker.create(HomeActivity.this)
                    .returnAfterFirst(true)
                    .folderMode(true)
                    .folderTitle("Albums")
                    .imageTitle("Tap to select")
                    .limit(1)
                    .single()
                    .showCamera(false)
                    .imageDirectory("Camera")
                    .start(SELECT_PHOTO_COMPRESSOR);
        });

        image_crop.setOnClickListener(view -> {
            ImagePicker.create(HomeActivity.this)
                    .returnAfterFirst(true)
                    .folderMode(true)
                    .folderTitle("Albums")
                    .imageTitle("Tap to select")
                    .limit(1)
                    .single()
                    .showCamera(false)
                    .imageDirectory("Camera")
                    .start(SELECT_PHOTO_CROP);
        });

        image_resize.setOnClickListener(view -> {
            ImagePicker.create(HomeActivity.this)
                    .returnAfterFirst(true)
                    .folderMode(true)
                    .folderTitle("Albums")
                    .imageTitle("Tap to select")
                    .limit(1)
                    .single()
                    .showCamera(false)
                    .imageDirectory("Camera")
                    .start(SELECT_PHOTO_RESIZE);
        });

        my_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MyCreationActivity.class));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Helper.PERMISSION_CODE) {
            if (grantResults.length > 0) {
                boolean readStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean writeStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (readStorageAccepted && writeStorageAccepted) {
                    File file = new File(Helper.FILE_PATH);
                    if (!file.exists()){
                        file.mkdir();
                    }
                    init();
                } else {
                    Helper.requestPermission(this);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);
                    Intent intent = new Intent(HomeActivity.this, ConvertActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("image", (Serializable) images);
                    intent.putExtra("list", args);
                    startActivity(intent);
                }
            }
        } else if (requestCode == SELECT_PHOTO_COMPRESSOR) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);
                    Intent intent = new Intent(HomeActivity.this, AdvanceCompressorActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("image", (Serializable) images);
                    intent.putExtra("list", args);
                    startActivity(intent);
                }
            }
        } else if (requestCode == SELECT_PHOTO_CROP) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);
                    String s = images.get(0).getPath();
                    File file = new File(s);
                    Uri uri = Uri.fromFile(file);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    UCrop.of(uri, Uri.fromFile(new File(Helper.FILE_PATH, File.separator + "Image_Compressor_" + currentDateandTime+".png"))).useSourceImageAspectRatio().start(this);
                }
            }
        } else if (requestCode == SELECT_PHOTO_RESIZE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);
                    Intent intent = new Intent(HomeActivity.this, ResizeImageActivity.class);
                    intent.putExtra("list", images.get(0).getPath());
                    startActivity(intent);
                }
            }
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Intent intent = new Intent(this, SaveImageActivity.class);
            intent.putExtra("saved_img", resultUri.toString());
            startActivity(intent);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.e("TAG", "onActivityResult: " + cropError);
            Toast.makeText(this, cropError + "", Toast.LENGTH_SHORT).show();
        }
    }
}