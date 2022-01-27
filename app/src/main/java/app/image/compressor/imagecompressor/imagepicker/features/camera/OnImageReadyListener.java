package app.image.compressor.imagecompressor.imagepicker.features.camera;

import app.image.compressor.imagecompressor.imagepicker.model.Image;

import java.util.List;

public interface OnImageReadyListener {
    void onImageReady(List<Image> image);
}
