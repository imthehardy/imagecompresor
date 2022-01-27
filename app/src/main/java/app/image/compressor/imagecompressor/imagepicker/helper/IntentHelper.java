package app.image.compressor.imagecompressor.imagepicker.helper;

import android.content.Context;
import android.content.Intent;

import app.image.compressor.imagecompressor.imagepicker.features.ImagePickerConfig;
import app.image.compressor.imagecompressor.imagepicker.model.Image;

import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_FOLDER_MODE;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_FOLDER_TITLE;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_IMAGE_DIRECTORY;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_IMAGE_TITLE;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_LIMIT;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_MODE;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_RETURN_AFTER_FIRST;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_SELECTED_IMAGES;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.EXTRA_SHOW_CAMERA;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.MAX_LIMIT;
import static app.image.compressor.imagecompressor.imagepicker.features.ImagePicker.MODE_MULTIPLE;

public class IntentHelper {

    public static ImagePickerConfig makeConfigFromIntent(Context context, Intent intent) {
        ImagePickerConfig config = new ImagePickerConfig(context);
        config.setMode(intent.getIntExtra(EXTRA_MODE, MODE_MULTIPLE));
        config.setLimit(intent.getIntExtra(EXTRA_LIMIT, MAX_LIMIT));
        config.setShowCamera(intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true));
        config.setFolderTitle(intent.getStringExtra(EXTRA_FOLDER_TITLE));
        config.setImageTitle(intent.getStringExtra(EXTRA_IMAGE_TITLE));
        config.setSelectedImages(intent.<Image>getParcelableArrayListExtra(EXTRA_SELECTED_IMAGES));
        config.setFolderMode(intent.getBooleanExtra(EXTRA_FOLDER_MODE, true));
        config.setImageDirectory(intent.getStringExtra(EXTRA_IMAGE_DIRECTORY));
        config.setReturnAfterFirst(intent.getBooleanExtra(EXTRA_RETURN_AFTER_FIRST, false));
        return config;
    }
}
