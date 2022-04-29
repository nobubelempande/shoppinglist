package net.penguincoders.doit;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class AddNewItem extends BottomSheetDialogFragment {
    public static final String TAG= " ActionBottomDialog";

    private EditText etNewItem;
    private EditText etQuantity;
    private EditText etCategory;
    private Button btnSaveItem;


    public static AddNewItem newInstance() {
        return new AddNewItem();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.new_item, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity=getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setupShoppingListEditorLayout(view, savedInstanceState);

    }

    private void setupShoppingListEditorLayout(View view, Bundle savedInstanceState){
        etNewItem = Objects.requireNonNull(getView()).findViewById(R.id.itemName);
        etQuantity = Objects.requireNonNull(getView()).findViewById(R.id.itemQuantity);
        etCategory = Objects.requireNonNull(getView()).findViewById(R.id.itemCategory);
        btnSaveItem = getView().findViewById(R.id.btnNewList);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            //toDo  check bundle keys
            isUpdate = true;
            String strItemName = bundle.getString("name");
            String strQuantity = bundle.getString("quantity");
            String strCategory = bundle.getString("category");
            etNewItem.setText(strItemName);
            etQuantity.setText(strQuantity);
            etCategory.setText(strCategory );
            assert strItemName != null;
            if(strItemName.length()>0)
                btnSaveItem.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
        }
    }



}
