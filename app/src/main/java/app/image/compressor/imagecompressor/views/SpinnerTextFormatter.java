package app.image.compressor.imagecompressor.views;

import android.text.Spannable;

public interface SpinnerTextFormatter<T> {

    Spannable format(T item);
}
