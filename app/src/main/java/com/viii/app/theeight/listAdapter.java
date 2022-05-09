package com.viii.app.theeight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList list_id, list_name, list_date;

    listAdapter(Activity activity, Context context, ArrayList list_id, ArrayList list_name, ArrayList list_date){
        this.activity = activity;
        this.context = context;
        this.list_id = list_id;
        this.list_name= list_name;
        this.list_date = list_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull listAdapter.MyViewHolder holder, final int position) {
        holder.list_id_txt.setText(String.valueOf(list_id.get(position)));
        holder.list_name_txt.setText(String.valueOf(list_name.get(position)));
        holder.list_date_txt.setText(String.valueOf(list_date.get(position)));
        //Recyclerview onClickListener
        holder.mainListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.putExtra("list_id", String.valueOf(list_id.get(position)));
                intent1.putExtra("list_name", String.valueOf(list_name.get(position)));
                intent1.putExtra("list_date", String.valueOf(list_date.get(position)));
                activity.startActivityForResult(intent1, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView list_id_txt, list_name_txt, list_date_txt;
        LinearLayout mainListLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.list_id_txt);
            list_name_txt = itemView.findViewById(R.id.list_name_txt);
            list_date_txt = itemView.findViewById(R.id.list_date_txt);
            mainListLayout = itemView.findViewById(R.id.mainInventoryLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainListLayout.setAnimation(translate_anim);
        }
    }
}
