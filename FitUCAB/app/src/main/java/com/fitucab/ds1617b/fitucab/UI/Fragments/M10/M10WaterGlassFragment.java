package com.fitucab.ds1617b.fitucab.UI.Fragments.M10;



import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Model.Water;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M10WaterGlassActivity;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link M10WaterGlassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link M10WaterGlassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class M10WaterGlassFragment extends Fragment {

    M10WaterGlassActivity m10 = new M10WaterGlassActivity();
    M10HistoyFragment m10h = new M10HistoyFragment();
    private OnFragmentInteractionListener mListener;

    public  static ImageButton _btnAddWater;
    public static ImageButton _btnLessWater;
    public EditText _EtnWater;
    private Gson gson;
    private  String fecha;
    private Context contexto;
    private  Water water;
    private View _view;
    int idusuario =1;
    private IpStringConnection Url = new IpStringConnection();



    public M10WaterGlassFragment() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters

    public static M10WaterGlassFragment newInstance(String param1, String param2) {

        M10WaterGlassFragment fragment = new M10WaterGlassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m10_water_glass, container, false);
        contexto = _view.getContext();
        Setup();
        addWater();
        deletWater();



        return _view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {

            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void Setup()
    {
        _btnAddWater = (ImageButton) _view.findViewById(R.id.btn_m10_AddWater);
        _btnLessWater = (ImageButton) _view.findViewById(R.id.btn_m10_lessWater);
        _EtnWater=(EditText)_view.findViewById(R.id.Et_m10_water);

        gson = new Gson();
    }


    public void addWater()
    {

        _btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addwateraux();
            }
        });
    }

    public void deletWater()
    {
        _btnLessWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blockbtn();
                String url1 = "M10_WaterGlass/DeletLast?time=" + m10._EtnDate.getText() + "&fkp="+idusuario;
                String aux = Url.getIp() + url1;
                RequestQueue queue = Volley.newRequestQueue(contexto);
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, aux,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.}
                                try {
                                    water = gson.fromJson(response, Water.class);
                                    _EtnWater.setText(water.get_cantidad().toString());
                                     m10h.set_list(_view.getContext());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    unlockbtn();
                    }
                });
                // Add the request to the RequestQueue.
                try {

                    queue.add(stringRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addwateraux()
    {
        blockbtn();
        String url1 = "M10_WaterGlass/addWater?time="+m10._EtnDate.getText()+"&glasstype=350&fkp="+idusuario;
        String aux = Url.getIp()+url1;
        RequestQueue queue = Volley.newRequestQueue(contexto);
        // Request a string response from the provided URL.
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, aux,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.}
                            try {
                                water = gson.fromJson(response, Water.class);
                                _EtnWater.setText(water.get_cantidad().toString());
                                m10h.set_list(_view.getContext());




                            } catch (Exception e) {
                                unlockbtn();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                unlockbtn();
                }
            });

            queue.add(stringRequest);



        }catch (Exception e){
            e.printStackTrace();
        }

    }
        public void blockbtn()
    {
        _btnAddWater.setEnabled(false);
        _btnLessWater.setEnabled(false);

    }
        public void unlockbtn() {
        _btnAddWater.setEnabled(true);
        _btnLessWater.setEnabled(true);
        }
    public void setCant (String cant)
    {
        _EtnWater.setText(cant);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}