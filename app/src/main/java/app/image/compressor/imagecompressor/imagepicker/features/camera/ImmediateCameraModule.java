package app.image.compressor.imagecompressor.imagepicker.features.camera;

import android.content.Context;
import android.content.Intent;

import app.image.compressor.imagecompressor.imagepicker.model.ImageFactory;

public class ImmediateCameraModule extends DefaultCameraModule {

    @Override
    public void getImage(Context context, Intent intent, OnImageReadyListener imageReadyListener) {
        if (imageReadyListener == null) {
            throw new IllegalStateException("OnImageReadyListener must not be null");
        }
        if (currentImagePath == null) {
            imageReadyListener.onImageReady(null);
        }
        imageReadyListener.onImageReady(ImageFactory.singleListFromPath(currentImagePath));
    }
}
