package app.image.compressor.imagecompressor.imagepicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.imagepicker.listeners.OnFolderClickListener;
import app.image.compressor.imagecompressor.imagepicker.model.Folder;

public class FolderPickerAdapter extends RecyclerView.Adapter<FolderPickerAdapter.FolderViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private final OnFolderClickListener folderClickListener;

    private List<Folder> folders;

    public FolderPickerAdapter(Context context, OnFolderClickListener folderClickListener) {
        this.context = context;
        this.folderClickListener = folderClickListener;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ef_imagepicker_item_folder, parent, false);
        return new FolderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FolderViewHolder holder, int position) {

        final Folder folder = folders.get(position);

        Glide.with(context)
                .load(folder.getImages().get(0).getPath())
                .placeholder(R.drawable.folder_placeholder)
                .error(R.drawable.folder_placeholder)
                .into(holder.image);

        holder.name.setText(folders.get(position).getFolderName());
        holder.number.setText(String.valueOf(folders.get(position).getImages().size()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (folderClickListener != null)
                    folderClickListener.onFolderClick(folder);
            }
        });
    }

    public void setData(List<Folder> folders) {
        this.folders = folders;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public static class FolderViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;
        private TextView number;

        public FolderViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            number = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }

}
