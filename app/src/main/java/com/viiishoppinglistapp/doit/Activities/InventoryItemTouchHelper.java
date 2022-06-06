package com.viiishoppinglistapp.doit.Activities;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.viiishoppinglistapp.doit.Adapters.AddingItemsAdapter;
import com.viiishoppinglistapp.doit.Adapters.InventoryItemsAdapter;
import com.viiishoppinglistapp.doit.Adapters.UseShoppingListAdapter;
import com.viiishoppinglistapp.doit.R;

public class InventoryItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private InventoryItemsAdapter adapter;

    public InventoryItemTouchHelper(InventoryItemsAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        Drawable icon;
        ColorDrawable background;


        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_delete);
        background = new ColorDrawable(Color.RED);

        assert icon != null;
        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) {
            // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
        } else if (dX < 0) {
            // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        //what happens when savedShoppingList is swiped

        onInventoryItemSwiped(viewHolder, direction);
    }


    //items

    public void onInventoryItemSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        deleteInventoryItem(viewHolder, position);
        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }

    private void deleteInventoryItem(RecyclerView.ViewHolder viewHolder, int position) {
        //deletes item
        AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
        builder.setTitle("Remove Item");
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to remove this item?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.deleteItem(position);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
