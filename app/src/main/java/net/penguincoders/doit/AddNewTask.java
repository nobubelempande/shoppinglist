package net.penguincoders.doit;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import net.penguincoders.doit.Model.mShoppingList;
import net.penguincoders.doit.Utils.DatabaseHandler;

import java.util.Objects;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private DatabaseHandler db;


    //lists::
    private EditText etNewListName;
    private Button btnSaveList;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    //required

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflate using new_shopping_list.xml
        View view = inflater.inflate(R.layout.new_shopping_list, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupShoppingListEditorLayout(view, savedInstanceState);
    }

    //shoppingLists :

    private void setupShoppingListEditorLayout(View view, Bundle savedInstanceState) {
        etNewListName = Objects.requireNonNull(getView()).findViewById(R.id.newTaskText);     //view from new_shopping_list
        btnSaveList = getView().findViewById(R.id.newTaskButton);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            //toDo  check bundle keys
            isUpdate = true;
            String strListName = bundle.getString("name");
            etNewListName.setText(strListName);
            assert strListName != null;
            if(strListName.length()>0)
                btnSaveList.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
        }

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        etNewListName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    btnSaveList.setEnabled(false);
                    btnSaveList.setTextColor(Color.GRAY);
                }
                else{
                    btnSaveList.setEnabled(true);
                    btnSaveList.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etNewListName.getText().toString();
                if(finalIsUpdate){
                    db.updateShoppingList(bundle.getInt("id"), text);
                }
                else {
                    addingListToDB(text);
                }
                dismiss();
            }
        });
    }

    private void addingListToDB(String listName) {
        mShoppingList currList = new mShoppingList();
        currList.setList_name(listName);
        db.insertShoppingList(currList);
    }


}
