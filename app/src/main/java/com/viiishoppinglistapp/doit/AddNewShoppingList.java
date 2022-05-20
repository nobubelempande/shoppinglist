package com.viiishoppinglistapp.doit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;
import com.viiishoppinglistapp.doit.Utils.DateHandler;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Objects;

public class AddNewShoppingList extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private DatePickerDialog datePickerDialog;

    private modelShoppingList currShoppingList;

    private DatabaseHandler db;
    private DateHandler date;
    private Validator validator;

    //lists::
    private EditText etNewListName;
    private TextView tvNewListUseDate;
    private Button btnSaveList;

    public static AddNewShoppingList newInstance(){
        return new AddNewShoppingList();
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

        currShoppingList = new modelShoppingList();
        setupShoppingListEditorLayout(view, savedInstanceState);
    }

    //shoppingLists :

    private void setupShoppingListEditorLayout(View view, Bundle savedInstanceState) {
        etNewListName = Objects.requireNonNull(getView()).findViewById(R.id.tvListName_newShoppingList);     //view from new_shopping_list
        btnSaveList = getView().findViewById(R.id.btnSaveShoppingList);

        btnSaveList.setEnabled(true);

        initDatePicker();
        tvNewListUseDate = getView().findViewById(R.id.tvListDate_newShoppingList);
        tvNewListUseDate.setText(getTodayDate());
        tvNewListUseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){

            if(bundle.size()>1){
                isUpdate = true;
                String strListName = bundle.getString("name");
                String strDate = bundle.getString("useDate");
                etNewListName.setText(strListName);
                tvNewListUseDate.setText(strDate);
                assert strListName != null;
                if(strListName.length()>0)
                    btnSaveList.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
            }
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
                String date = tvNewListUseDate.getText().toString();

                validator = new Validator(db);
                boolean isNameValid = validator.isShoppingListNameValid(text);

                if (isNameValid){
                    currShoppingList = new modelShoppingList();

                    currShoppingList.setListName(text);
                    currShoppingList.setUseDate(date);

                    if(finalIsUpdate){
                        currShoppingList.setListID(bundle.getInt("id"));

                        db.updateShoppingList(currShoppingList);
                    }
                    else {
                        addingListToDB(currShoppingList);
                    }
                    dismiss();
                }
                else{
                    Toast.makeText(getContext(),"Give Your Shopping List A Name.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int yr = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        m = m + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);

        date = new DateHandler(d, m, yr);
        return date.getDate();
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                date = new DateHandler(day, month, year);
                String strDate = date.getDate();
                tvNewListUseDate.setText(strDate);
                currShoppingList.setUseDate(strDate);

            }
        };

        Calendar cal = Calendar.getInstance();
        int yr = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        m+=1;
        int d = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        date = new DateHandler(d, m, yr);
        String strDate = date.getDate();
        currShoppingList.setUseDate(strDate);

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, yr, m, d);
    }

    private void addingListToDB(modelShoppingList list) {
        db.insertShoppingList(list);
    }


}
