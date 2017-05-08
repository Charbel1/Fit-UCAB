package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class  M01LoginFragment extends Fragment {

    private TextView _tvOlvidoClave;
    private View _view;
    private OnFragmentSwap _callBack;
    public M01LoginFragment() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    /**
     * Metodo encargado de instanciar la vista
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m01_login, container, false);
        instanciarComponentes();
        manageChangeFragmentRecovery();
        return _view;
    }

    private void manageChangeFragmentRecovery() {
        _tvOlvidoClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M01RecoveryFragment",null);
            }
        });

    }
    /**
     * Metodo encargado para instanciar los componentes de esta vista
     */
    private void instanciarComponentes (){
        _tvOlvidoClave=(TextView) _view.findViewById(R.id.tv_m01_olvidoClave);

        //llamar a ese metodo
    }
}