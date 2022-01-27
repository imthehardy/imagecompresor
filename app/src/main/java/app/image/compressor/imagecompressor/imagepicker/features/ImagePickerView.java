package app.image.compressor.imagecompressor.imagepicker.features;

import app.image.compressor.imagecompressor.imagepicker.features.common.MvpView;
import app.image.compressor.imagecompressor.imagepicker.model.Folder;
import app.image.compressor.imagecompressor.imagepicker.model.Image;

import java.util.List;

public interface ImagePickerView extends MvpView {
    void showLoading(boolean isLoading);
    void showFetchCompleted(List<Image> images, List<Folder> folders);
    void showError(Throwable throwable);
    void showEmpty();
    void showCapturedImage();
    void finishPickImages(List<Image> images);
}
