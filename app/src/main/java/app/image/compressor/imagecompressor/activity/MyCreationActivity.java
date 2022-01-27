package app.image.compressor.imagecompressor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.adpter.MyCreationAdapter;
import app.image.compressor.imagecompressor.model.ImageModel;
import app.image.compressor.imagecompressor.utils.Helper;

public class MyCreationActivity extends AppCompatActivity {

    ArrayList<ImageModel> mImageModel;
    RecyclerView rv_my_creation;
    MyCreationAdapter myCreationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation);

        mImageModel = new ArrayList<>();

        rv_my_creation = findViewById(R.id.rv_my_creation);

        File[] files = new File(Helper.FILE_PATH).listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                ImageModel ringtoneModel = new ImageModel();
                ringtoneModel.setName(files[i].getName());
                ringtoneModel.setPath(files[i].getPath());
                ringtoneModel.setSize(String.valueOf(files[i].length()));
                mImageModel.add(ringtoneModel);
            }
        }

        rv_my_creation.setLayoutManager(new GridLayoutManager(this, 3));
        myCreationAdapter = new MyCreationAdapter(this, mImageModel);
        rv_my_creation.setAdapter(myCreationAdapter);

    }
}