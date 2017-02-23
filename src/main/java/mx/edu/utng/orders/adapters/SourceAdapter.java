package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.model.Source;

/**
 * Created by KAST  on 15/02/2017.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> implements View.OnClickListener{

    List<Source> sources;
    View.OnClickListener listener;

    public SourceAdapter(List<Source> sources){
        this.sources = sources;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SourceAdapter.SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source_layout,parent,false);

        SourceViewHolder holder = new SourceViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SourceAdapter.SourceViewHolder holder, int position) {
        holder.tvSourceName.setText(sources.get(position).getNameSource());
        holder.tvSourceType.setText(String.valueOf(sources.get(position).getTypeSource()));
        holder.ivSource.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }
    }

    public static class SourceViewHolder extends  RecyclerView.ViewHolder  implements View.OnClickListener{
        CardView cvSource;
        TextView tvSourceName;
        TextView tvSourceType;
        ImageView ivSource;
        ImageButton btEditSource;
        ImageButton btDeleteSource;
        View.OnClickListener listener;

        public SourceViewHolder(View itemView) {
            super(itemView);
            cvSource = (CardView) itemView.findViewById(R.id.cv_source);
            ivSource = (ImageView) itemView.findViewById(R.id.iv_source);
            tvSourceName = (TextView) itemView.findViewById(R.id.tv_source_name);
            tvSourceType = (TextView) itemView.findViewById(R.id.tv_source_type);
            btEditSource = (ImageButton) itemView.findViewById(R.id.bt_edit_source);
            btDeleteSource = (ImageButton) itemView.findViewById(R.id.bt_delete_source);
            btEditSource.setOnClickListener(this);
            btDeleteSource.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }
    }
}