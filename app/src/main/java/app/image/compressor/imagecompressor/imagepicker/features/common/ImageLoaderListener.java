package app.image.compressor.imagecompressor.imagepicker.features.common;

import app.image.compressor.imagecompressor.imagepicker.model.Folder;
import app.image.compressor.imagecompressor.imagepicker.model.Image;

import java.util.List;

public interface ImageLoaderListener {
    void onImageLoaded(List<Image> images, List<Folder> folders);
    void onFailed(Throwable throwable);
}
