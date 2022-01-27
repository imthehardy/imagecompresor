package app.image.compressor.imagecompressor.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import app.image.compressor.imagecompressor.R;
import app.image.compressor.imagecompressor.model.ImageModel;

public class MyCreationAdapter extends RecyclerView.Adapter<MyCreationAdapter.MyClass> {

    Context mContext;
    ArrayList<ImageModel> modelArrayList;

    public MyCreationAdapter(Context mContext, ArrayList<ImageModel> modelArrayList) {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mycreationadapter, parent, false);
        return new MyClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCreationAdapter.MyClass holder, int position) {

        Glide.with(mContext).load(modelArrayList.get(position).getPath()).into(holder.saved_img);

        holder.img_name.setText(modelArrayList.get(position).getName());
        double sizeInKb = Long.valueOf(modelArrayList.get(position).getSize()) / 1024.0;
        if (sizeInKb >= 1024) {
            double sizeInMbs = sizeInKb / 1024.0;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            holder.img_size.setText(decimalFormat.format(sizeInMbs) + " MBs");
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            holder.img_size.setText(decimalFormat.format(sizeInKb) + " KBs");
        }

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        ImageView saved_img;
        TextView img_name, img_size;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            saved_img = itemView.findViewById(R.id.saved_img);
            img_name = itemView.findViewById(R.id.img_name);
            img_size = itemView.findViewById(R.id.img_size);
        }
    }
}
