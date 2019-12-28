package com.ixuea.courses.kanmeitu2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ixuea.courses.kanmeitu2.R;
import com.ixuea.courses.kanmeitu2.domain.Image;
import com.ixuea.courses.kanmeitu2.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private List<Image> datas = new ArrayList<Image>();

    public ImageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //注意这里，一定要将parent传递到inflate方法
        return new ViewHolder(inflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<Image> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyItemChanged(0, this.datas.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }

        public void bindData(Image data) {
            ImageUtil.show((Activity) context, iv, data.getUri());
        }
    }
}
