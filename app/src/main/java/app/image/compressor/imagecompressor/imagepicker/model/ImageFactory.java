package app.image.compressor.imagecompressor.imagepicker.model;

import app.image.compressor.imagecompressor.imagepicker.helper.ImagePickerUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageFactory {
    
    public static List<Image> singleListFromPath(String path) {
        List<Image> images = new ArrayList<>();
        images.add(new Image(0, ImagePickerUtils.getNameFromFilePath(path), path, true));
        return images;
    }
}
