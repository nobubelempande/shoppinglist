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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;
import com.viiishoppinglistapp.doit.Utils.DateHandler;
import com.viiishoppinglistapp.doit.Utils.Validation;

import java.util.Calendar;
import java.util.Objects;

public class AddNewInventoryItem extends BottomSheetDialogFragment {

    public static final String TAG = "VIII-ActionBottomDialog";

    //ArrayAdapter adapter;

    public DatePickerDialog datePickerDialog;

    private DatabaseHandler db;
    private DateHandler date;
    Validation validator;

    private modelItem currItem;

    //lists::
    private EditText etItemPrice;
    private Button btnAddToInventory, btnCancelAdd;
    private TextView tvItemName, tvItemDOE;
    private Switch switchDate;

    public static AddNewInventoryItem newInstance(){
        return new AddNewInventoryItem();
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
        //inflate using new_item.xml
        View view = inflater.inflate(R.layout.new_inventory_item, container, false);
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

        currItem = new modelItem("Default");
        date = new DateHandler();

        setupInventoryItemEditorLayout(view, savedInstanceState);
    }

    private void setupInventoryItemEditorLayout(View view, Bundle savedInstanceState) {
        final String NullDate = "No Expiry Date";

        db = new DatabaseHandler(getActivity());
        db.openDatabase();


        tvItemName = Objects.requireNonNull(getView()).findViewById(R.id.tvInventoryItemName_new);
        etItemPrice = Objects.requireNonNull(getView()).findViewById(R.id.etInventoryItemPrice_new);
        btnAddToInventory = getView().findViewById(R.id.btnAddToInventory_new);
        btnCancelAdd = getView().findViewById(R.id.btnCancelAddToInventory_new);

        tvItemDOE = getView().findViewById(R.id.tvInventoryItemDOE_new);
        setupDateTV();

        switchDate = getView().findViewById(R.id.switchDate);
        switchDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    tvItemDOE.setText(NullDate);
                    tvItemDOE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                } else {
                    //do something when unchecked
                    setupDateTV();
                }
            }
        });

        Log.d(TAG, "setupInventoryItemEditorLayout: Switch Setup");

        final Bundle bundle = getArguments();
        if(bundle != null){

            String strItemName = bundle.getString("itemName");
            tvItemName.setText(strItemName);
            currItem.setItemName(strItemName);

            assert strItemName != null;
            if(strItemName.length()>0){
                btnAddToInventory.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.primary_dark));
                btnCancelAdd.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.primary_dark));
            }

            String strListName = bundle.getString("shoppingListName");
            currItem = db.getItem(currItem.getItemName(), strListName);
        }



        etItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    btnAddToInventory.setEnabled(false);
                    btnAddToInventory.setTextColor(Color.GRAY);

                    btnCancelAdd.setEnabled(false);
                    btnCancelAdd.setTextColor(Color.GRAY);
                }
                else{
                    btnAddToInventory.setEnabled(true);
                    btnAddToInventory.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.primary_dark));

                    btnCancelAdd.setEnabled(true);
                    btnCancelAdd.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.primary_dark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        validator = new Validation(db);

        btnAddToInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPrice = etItemPrice.getText().toString();
                String doe = tvItemDOE.getText().toString();
                if(doe.equals(NullDate)){
                    doe = date.getNoDate();
                }
                Log.d(TAG, "onClick: ");

                if(validator.isItemPriceEmpty(strPrice)){
                    Toast.makeText(getContext(), "Please Enter The Price.", Toast.LENGTH_SHORT).show();
                }
                else{
                    currItem.setItemPrice(Double.parseDouble(strPrice));
                    currItem.setItemDOE(doe);

                    addItemToInventory(currItem);
                    dismiss();
                }
                Log.d(TAG, "onClick End");

            }
        });
        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPrice = etItemPrice.getText().toString();
                String doe = tvItemDOE.getText().toString();
                Log.d(TAG, "onClick: ");

                if(validator.isItemPriceEmpty(strPrice)){
                    Toast.makeText(getContext(), "Please Enter The Price.", Toast.LENGTH_SHORT).show();
                }
                else{
                    currItem.setItemPrice(Double.parseDouble(strPrice));
                    currItem.setItemDOE(doe);

                    db.updateItem(currItem);
                    dismiss();
                }
                Log.d(TAG, "onClick End");
            }
        });
    }

    private void setupDateTV() {
        initDatePicker();
        tvItemDOE.setText(getTodayDate());
        tvItemDOE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    public void addItemToInventory(modelItem currItem) {
        db.updateItem(currItem);
        db.insertInventoryItem(currItem);
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

    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                date = new DateHandler(day, month, year);
                  String strDate = date.getDate();
                tvItemDOE.setText(strDate);
                currItem.setItemDOE(strDate);

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
        currItem.setItemDOE(strDate);

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, yr, m, d);
    }


}
