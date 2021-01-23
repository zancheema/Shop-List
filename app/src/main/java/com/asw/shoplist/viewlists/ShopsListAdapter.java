package com.asw.shoplist.viewlists;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asw.shoplist.data.source.Shop;
import com.asw.shoplist.databinding.ShopListItemBinding;

public class ShopsListAdapter extends ListAdapter<Shop, ShopsListAdapter.ViewHolder> {
    public ShopsListAdapter() {
        super(new ShopItemsDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final ShopListItemBinding binding;

        private ViewHolder(@NonNull ShopListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Shop shop) {
            binding.setShop(shop);
            binding.executePendingBindings();
        }

        public static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ShopListItemBinding binding = ShopListItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }
    }

    static class ShopItemsDiffUtil extends DiffUtil.ItemCallback<Shop> {
        @Override
        public boolean areItemsTheSame(@NonNull Shop oldItem, @NonNull Shop newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shop oldItem, @NonNull Shop newItem) {
            return oldItem.equals(newItem);
        }
    }
}
