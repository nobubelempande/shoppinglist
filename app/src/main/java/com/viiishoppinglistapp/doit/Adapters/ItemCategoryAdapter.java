package com.viiishoppinglistapp.doit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.R;

import java.util.ArrayList;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.MyViewHolder>{

   private Context context;
   private Activity activity;
   private ArrayList item_id, item_name, item_category, item_quantity;

    public ItemCategoryAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_category,
                               ArrayList item_quantity){
       this.activity = activity;
       this.context = context;
       this.item_id = item_id;
       this.item_name= item_name;
       this.item_category = item_category;
       this.item_quantity = item_quantity;

   }

   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_item_category_list, parent, false);
       return new MyViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
       holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
       holder.item_category_txt.setText(String.valueOf(item_category.get(position)));
       holder.item_quantity_txt.setText(String.valueOf(item_quantity.get(position)));
   }

   @Override
   public int getItemCount() {
       return item_id.size();
   }

   public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView item_id_txt, item_name_txt, item_category_txt, item_quantity_txt;//item_list_name_txt;
       LinearLayout mainLayout;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           item_id_txt = itemView.findViewById(R.id.item_id_txt);
           item_name_txt = itemView.findViewById(R.id.item_name_txt);
           item_category_txt = itemView.findViewById(R.id.item_date_txt);
           item_quantity_txt = itemView.findViewById(R.id.item_quantity_txt);

           mainLayout = itemView.findViewById(R.id.itemCategoryLayout);
           //Animate Recyclerview
           Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
           mainLayout.setAnimation(translate_anim);
       }
   }
}
