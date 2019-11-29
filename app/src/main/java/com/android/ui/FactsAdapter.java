package com.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.R;
import com.android.model.Facts;
import com.bumptech.glide.Glide;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "FactsAdapter";

    private List<Facts> mFactsList;

    public FactsAdapter(List<Facts> factsList) {
        mFactsList = factsList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_facts, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mFactsList != null && mFactsList.size() > 0) {
            return mFactsList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvTitle.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Facts mFacts = mFactsList.get(position);

            if (mFacts.getImageHref() != null) {
                Glide.with(itemView.getContext())
                        .load(mFacts.getImageHref())
                        .placeholder(R.drawable.ic_error)
                        .error(R.drawable.ic_error)
                        .into(ivThumbnail);
            }
            if (mFacts.getTitle() != null) {
                tvTitle.setText(mFacts.getTitle());
            }

            if (mFacts.getDescription() != null) {
                tvDescription.setText(mFacts.getDescription());
            }


        }
    }

}
