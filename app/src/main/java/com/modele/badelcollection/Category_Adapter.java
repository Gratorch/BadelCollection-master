package com.modele.badelcollection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {


    private List<Category_Model> categoryModelList;
    private int lastPosition = -1;

    public Category_Adapter(List<Category_Model> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater .from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder viewHolder, int position) {
    String icon=categoryModelList.get(position).getCategoryIconlink();
    String name =categoryModelList.get(position).getCategoryName();
    viewHolder.setCategorie(name,position);
    viewHolder.setCategoryIcon(icon);
        if(lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(), R.anim.fade_in);
            viewHolder.itemView.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categorieName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon=itemView.findViewById(R.id.category_icon);
            categorieName =itemView.findViewById(R.id.category_name);
        }
        private void setCategoryIcon(String iconURL){
            if(!iconURL.equals("null")) {
                Glide.with(itemView.getContext()).load(iconURL)
                        .apply(new RequestOptions()
                                .placeholder(R.mipmap.icon_placeholder))
                        .into(categoryIcon);
            }else {
                categoryIcon.setImageResource(R.mipmap.home);
            }


        }

        private void setCategorie (final String name,final int position){
            categorieName.setText(name);

            if(!name.equals("")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position != 0) {
                            Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                            categoryIntent.putExtra("CategoryName", name);
                            itemView.getContext().startActivity(categoryIntent);
                        }
                    }
                });
            }
        }
    }
}
