package com.example.stddetails;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class detail_fragment extends Fragment {

    EditText name,dept,email;
    ArrayList<list> mylist=new ArrayList<list>();
    Button update,back;

    TextView tname,tdept,temail;
    //@Nullable
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    //private String mParam1;


    public detail_fragment() {
        // Required empty public constructor
    }

    //public static Fragment newInstance(String student_name) {
    //}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static detail_fragment newInstance(String param1) {
        detail_fragment fragment = new detail_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }*/




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.detail_fragment,container,false);
        TextView show =view.findViewById(R.id.show);
        //show.setText(mParam1);

        name=view.findViewById(R.id.std_name);
        dept=view.findViewById(R.id.department);
        email=view.findViewById(R.id.email);

        update=view.findViewById(R.id.update_details);
        back=view.findViewById(R.id.Back);

        tname=view.findViewById(R.id.tname);
        tdept=view.findViewById(R.id.tdept);
        temail=view.findViewById(R.id.temail);

        mylist=singleton.get(getActivity()).getSlist();

        list l=mylist.get(list_fragment.mypos);
        name.setText(l.getStudent_name());

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               String std= name.getText().toString();

               l.setStudent_name(std);
               mylist.set(list_fragment.mypos,l);
               singleton.get(getActivity()).setSlist(mylist);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



       /* Bundle bundle = this.getArguments();
      //  String std_name = bundle.getString("name");
       // name.setText(std_name);

        String department=bundle.getString("dept");
        dept.setText(department);

        String id=bundle.getString("email");
        email.setText(id);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tname.setText(name.getText().toString());
                tdept.setText(dept.getText().toString());
                temail.setText(email.getText().toString());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2=new Bundle();
               // list_fragment fragment=new list_fragment();

                list_fragment fragment=new list_fragment();
                //fragment.setArguments(bundle);
                fragment.setArguments(bundle2);
                bundle2.putString("message", name.getText().toString());
                getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).addToBackStack(null).commit();


            }
        });*/




        return view;
    }


}
