package com.viiishoppinglistapp.doit;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.viiishoppinglistapp.doit.Model.modelItem;
import com.viiishoppinglistapp.doit.Model.modelShoppingList;
import com.viiishoppinglistapp.doit.Utils.DatabaseHandler;

import java.util.Objects;

public class AddNewItem extends BottomSheetDialogFragment {

    public static final String TAG = "VIII-ActionBottomDialog";

    ArrayAdapter adapter;

    private DatabaseHandler db;
    private Validator validator;

    private modelShoppingList currShoppingList;
    private modelItem currItem;

    //lists::
    private EditText etItemName, etItemQty;
    private Button btnSaveItem;
    private Spinner spItemType;

    public static AddNewItem newInstance(){
        return new AddNewItem();
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
        View view = inflater.inflate(R.layout.new_item, container, false);
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

        setupItemEditorLayoutUsingShoppingList(view, savedInstanceState);
        //setupItemEditorLayout(view, savedInstanceState);
    }

    private void setupItemEditorLayoutUsingShoppingList(View view, Bundle savedInstanceState) {
        etItemName = Objects.requireNonNull(getView()).findViewById(R.id.etItemName_lyt);
        etItemQty = Objects.requireNonNull(getView()).findViewById(R.id.etItemQty_lyt);    //view from new_item
        spItemType = Objects.requireNonNull(getView()).findViewById(R.id.spItemType_lyt);

        btnSaveItem = getView().findViewById(R.id.btnSaveItem);

        spItemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getId() == R.id.spItemType_lyt){
                    String strItemType = adapterView.getItemAtPosition(position).toString();
                    currItem.setItemType(strItemType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Log.d(MainActivity.TAG, "Setup Save II.");

        String[] itemTypes = getResources().getStringArray(R.array.types);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, itemTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItemType.setAdapter(adapter);

        boolean isUpdate = false;

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        validator = new Validator(db);

        currItem = new modelItem("");


        final Bundle bundle = getArguments();
        if(bundle != null){
            if (bundle.size()<2){
                isUpdate = false;
                String strListName = bundle.getString("listName");
                currShoppingList = db.getShoppingList(strListName);

            }
            else {
                isUpdate = true;
                String strItemName = bundle.getString("name");
                int intQty = bundle.getInt("qty");

                String strType = bundle.getString("type");
                //double dblPrice = bundle.getDouble("price");
                //String strDOE = bundle.getString("doe");

                //currItem
                currItem.setItemName(strItemName);
                currItem.setItemQty(intQty);

                etItemName.setText(strItemName);
                etItemQty.setText(String.valueOf(intQty));
                spItemType.setSelection(adapter.getPosition(strType));


                assert strItemName != null;
                if(strItemName.length()>0) {
                    btnSaveItem.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
                }
                //get currList
                String strListName = bundle.getString("listName");
                currShoppingList = db.getShoppingList(strListName);
            }



        }


        etItemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    btnSaveItem.setEnabled(false);
                    btnSaveItem.setTextColor(Color.GRAY);
                }
                else{
                    btnSaveItem.setEnabled(true);
                    btnSaveItem.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Log.d(MainActivity.TAG, "Setup III.");

        final boolean finalIsUpdate = isUpdate;
        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Validator not created.");
                String name = etItemName.getText().toString();
                String strQty = etItemQty.getText().toString();
                Log.d(MainActivity.TAG, "Validator not created.");

                boolean isNameValid = validator.isItemNameNotEmpty(name);
                boolean isQtyValid = validator.isItemQtyNotEmpty(strQty);
                boolean isTypeValid = validator.isItemTypeSelected(currItem.getItemType());

                Log.d(MainActivity.TAG, "Is Type Valid? - " + isTypeValid);

                if(!isNameValid){
                    Toast.makeText(getContext(),"Please Enter the Name.", Toast.LENGTH_SHORT).show();
                }
                if(!isQtyValid){
                    Toast.makeText(getContext(),"Please Enter the Quantity.", Toast.LENGTH_LONG).show();
                }
                if(!isTypeValid){
                    Toast.makeText(getContext(),"Please Select The Type.", Toast.LENGTH_SHORT).show();
                }
                if(isNameValid && isQtyValid && isTypeValid){
                    currItem.setItemName(name);
                    currItem.setItemQty(Integer.parseInt(strQty));
                    currItem.setItemPrice(0);
                    currItem.setItemDOE("-");
                    currItem.setListName(currShoppingList.getListName());

                    if(finalIsUpdate){
                        currItem.setItemID(bundle.getInt("id"));

                        db.updateItem(currItem);
                    }
                    else {
                        addingNewItemToDB(currItem);
                    }
                    dismiss();
                }
            }
        });
    }

    //Items :

    private void addingNewItemToDB(modelItem currItem) {
        db.insertItem(currItem);
    }
}
