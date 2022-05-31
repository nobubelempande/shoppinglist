package com.viiishoppinglistapp.doit.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.AddNewShoppingList;
import com.viiishoppinglistapp.doit.AddShoppingListItemsActivity;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedHomeActivity;
import com.viiishoppinglistapp.doit.UseShoppingListActivity;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class UnusedShoppingListsAdapter extends RecyclerView.Adapter<UnusedShoppingListsAdapter.ViewHolder> {

    private DatabaseHandler db;
    private TabbedHomeActivity activity;


    //models
    private List<modelShoppingList> allShoppingLists;

    //constructor
    public UnusedShoppingListsAdapter(DatabaseHandler db, TabbedHomeActivity activity) {
        this.db = db;
        this.activity = activity;

    }

    public void setAllUnusedShoppingLists(List<modelShoppingList> allLists) {
        this.allShoppingLists = allLists;
        notifyDataSetChanged();     //updates recycler view
    }


    public Context getContext() {
        return activity;
    }

    @Override
    public int getItemCount() {
        //total number of items to be shown
        // = number of items in list (classList)
        //----> return todoList.size();
        return allShoppingLists.size();
    }


    //required

    //ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCurrShoppingList, tvUseDate;
        RelativeLayout lyt;

        //constructor
        ViewHolder(View view) {
            super(view);

            tvCurrShoppingList = view.findViewById(R.id.tvUnusedListName_layout);
            tvUseDate = view.findViewById(R.id.tvUnusedListUseDate_layout);
            lyt = view.findViewById(R.id.lytUnusedShoppingList);
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //responsible for inflating views

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout_unused_shopping_list, parent, false);      //layout with cardViews
        return new ViewHolder(itemView);
    }

    @Override   //onCreate for layout
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //method called when item is added to task layout
        db.openDatabase();
        //setting up item details to show on checkbox

        //---SavedShoppingLists
        setupUnusedShoppingLists(holder, position);

    }



    //Functions :

    public void setupUnusedShoppingLists(final ViewHolder holder, int position){
        final modelShoppingList currList = allShoppingLists.get(position);
        final String listName = currList.getListName();
        final String useDate = currList.getUseDate();

        holder.tvCurrShoppingList.setText(listName);
        holder.tvUseDate.setText(useDate);
        holder.lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_unused_shopping_list);

                //button functions
                Button btnAddItems = dialog.findViewById(R.id.btnViewItems_dialog);
                Button btnUseList = dialog.findViewById(R.id.btnUseList_dialog);

                btnAddItems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //goto new page
                        Bundle bundle = new Bundle();
                        bundle.putString("list_name", listName);
                        Intent I = new Intent(getContext(), AddShoppingListItemsActivity.class);
                        I.putExtras(bundle);
                        getContext().startActivity(I);

                        //Toast.makeText(getContext(), "opening shopping list: " + listName, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btnUseList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //goto new page
                        Bundle bundle = new Bundle();
                        bundle.putString("list_name", listName);
                        Intent I = new Intent(getContext(), UseShoppingListActivity.class);
                        I.putExtras(bundle);
                        getContext().startActivity(I);

                        //Toast.makeText(getContext(), "opening shopping list: " + listName, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    public void deleteShoppingList(int position) {
        modelShoppingList currList = allShoppingLists.get(position);
        db.deleteShoppingList(currList.getListID());
        allShoppingLists.remove(position);
        notifyItemRemoved(position);
    }

    public void editShoppingList(int position) {
        modelShoppingList currList = allShoppingLists.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", currList.getListID());
        bundle.putString("name", currList.getListName());
        bundle.putString("useDate", currList.getUseDate());
        AddNewShoppingList fragment = new AddNewShoppingList();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewShoppingList.TAG);
    }

    //toDO
    public void createShoppingListPDF(String strName) throws FileNotFoundException {
        modelShoppingList currList = db.getShoppingList(strName);

        //pdf
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "myPDF.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document docShoppingList = new Document(pdfDocument);


        docShoppingList.close();
        Toast.makeText(getContext(), "PDF Created.", Toast.LENGTH_LONG).show();
    }

}
