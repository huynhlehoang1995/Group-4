package com.ssense.ssense.FragmentController;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssense.ssense.R;

import static com.ssense.ssense.R.layout;


public class ConfirmFragment extends Fragment {

    EditText edtName, edtAddress, edtPhone, edtMail;
    Button btnConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_confirm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtAddress = getView().findViewById(R.id.edtAddress);
        edtMail = getView().findViewById(R.id.edtEmail);
        edtName = getView().findViewById(R.id.edtName);
        edtPhone = getView().findViewById(R.id.edtPhone);
        btnConfirm = getView().findViewById(R.id.btnConfirm);


            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edtPhone.getText().toString().isEmpty() || edtName.getText().toString().isEmpty() || edtAddress.getText().toString().isEmpty() || edtMail.getText().toString().isEmpty())
                        Toast.makeText(getActivity().getBaseContext(),"Please complete your information",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity().getBaseContext(),"Success",Toast.LENGTH_LONG).show();
                }
            });

    }
}
