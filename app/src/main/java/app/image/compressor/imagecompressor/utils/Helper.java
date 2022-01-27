package app.image.compressor.imagecompressor.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {

    public static final int PERMISSION_CODE = 100;
    public static final String FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Image Compressor";

    public static boolean checkSelfPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Context context) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
    }

    public static File savebitmap(Bitmap bmp, int convert, String format) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        if (format == "jpg"){
            bmp.compress(Bitmap.CompressFormat.JPEG, convert, bytes);
        } else if (format == "png"){
            bmp.compress(Bitmap.CompressFormat.PNG, convert, bytes);
        } else if (format == "webp"){
            bmp.compress(Bitmap.CompressFormat.WEBP, convert, bytes);
        } else {
            bmp.compress(Bitmap.CompressFormat.JPEG, convert, bytes);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        File f = new File(FILE_PATH + File.separator + "Image_Compressor_" + currentDateandTime + "." + format);
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }
}