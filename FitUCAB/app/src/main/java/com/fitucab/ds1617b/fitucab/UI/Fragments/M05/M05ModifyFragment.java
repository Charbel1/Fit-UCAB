package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M05PrincipalActivity;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Fragment del tipo dialogo que permite eliminar y modicicar actividad
 */

public class M05ModifyFragment extends Fragment {
    TextView _tv_date, _tv_time;

    // Valores para los parametros  de fecha
    int _year, _month, _day;
    //Valorea para la hora
    int _hour, _min;

    EditText _et_Calories;

    Button _modify;
    ImageView _delete, _close;

    private Activit _activit;


    View _view;
    private OnFragmentSwap _callBack;

    String _idActivity;


    public M05ModifyFragment() {
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            _callBack = (OnFragmentSwap) activity;

        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m05_modify, container, false);
        setupViewValues();
        inflateData();
        return _view;
    }

    private void setupViewValues() {
         _delete = (ImageView) _view.findViewById(R.id.iv_m05_deleteactivity);
         _modify = (Button) _view.findViewById(R.id.btn_m05_updateactivity);
        _close =  (ImageView) _view.findViewById(R.id.iv_m05_close);
        _et_Calories= (EditText) _view.findViewById(R.id.editText);
        setHasOptionsMenu(true);

        selectedOpcionToolbar();
    }


// Aqui llena los copos can la inforacion de la actividad para posteriormente eliminarla o actualizarla
    public void inflateData(){
        Log.e("STATIC DEL OTRO LADO ", M05PrincipalActivity.get_activit().get_name());
        _activit = M05PrincipalActivity.get_activit();
        _idActivity= String.valueOf(_activit.get_id());
        String cal = String.valueOf(_activit.get_calor());
            _et_Calories.setText(cal);
    }


    /**
     * Metodo para la seleccion de alguna opcion en el toolbar
     */

    public void selectedOpcionToolbar(){
       // Si se escoge la opcion de eliminar, realizar accion correspondiente
       _delete.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialogConfirmation();
                   }
               }
       );
       // Si se escoge modificar, realizar la accion correspondiente
       _modify.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               }

       );
        _close.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _callBack.onSwap("M05PrincipalActivityFragment",null);
            }
        });

   }

    /**
     * Dialogo emergente para confirmacion de eliminacion de actividad
     */


    public void dialogConfirmation() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(R.string._ttl_m05_questiondeleteactivity);
        builder.setMessage(R.string._dlg_m05_quetiondeleteactivity)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        makeDelete();
                    }
                })
                .setNegativeButton(R.string._dlg_m05_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void makeDelete()
    {
        String consult ="http://192.168.250.3:8080/untitled_war_exploded/M05_ServicesActivity/" +
                "deleteActivity?idReg="+_idActivity;

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.e("RESPONSE ", response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
