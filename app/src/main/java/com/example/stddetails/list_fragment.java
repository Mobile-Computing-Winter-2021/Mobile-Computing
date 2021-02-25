package com.example.stddetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class list_fragment extends Fragment implements listAdapter.ItemClickListener{

    public void list_fragment(){

    }
    public static int mypos;


    RecyclerView recyclerView;
    ArrayList<list> students_list=new ArrayList<>();
    listAdapter lp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        students_list=data();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_fragment,container,false);

        recyclerView=view.findViewById(R.id.recycler_list);

        recyclerView.setHasFixedSize(true);
        //listAdapter lp=new listAdapter();
        //recyclerView.setAdapter(lp);
        //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        //recyclerView.setLayoutManager(layoutManager);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //data();


       // recyclerView.setAdapter(new listAdapter(data(),this));

        //Bundle bundle2 = this.getArguments();
        //String std_name = bundle2.getString("message");
        //ArrayList<list> listArrayList=data();
        singleton.get(getActivity()).setSlist(students_list);
        updateUI();

        return view;

    }
    private void updateUI() {
        singleton s = singleton.get(getActivity());
        ArrayList<list> slist = s.getSlist();




        if (lp == null)
        { lp = new listAdapter(slist,this);
            recyclerView.setAdapter(lp);
        } else { lp.notifyDataSetChanged();
        recyclerView.setAdapter(lp);}
    }

    public ArrayList<list> data() {

        students_list.add(new list("Richa Dwivedi - MT20104"));
        students_list.add(new list("Toshi Rawka- MT20105"));
        students_list.add(new list("Shivani Shukla - MT20106"));
        students_list.add(new list("Rahul Shukla - MT20107"));
        students_list.add(new list("Shreya Goel - MT20108"));
        students_list.add(new list("Anubhav Ruhela - MT20109"));
        students_list.add(new list("Sakshi kaushik- MT20110"));
        students_list.add(new list("Dharmendar Kumar - MT20111"));
        students_list.add(new list("Shreya Garg - MT20112"));
        students_list.add(new list("Meenakshi Das - MT20113"));
        students_list.add(new list("Roshan S - MT20114"));
        students_list.add(new list("Sreepurna Mohanty - MT20115"));
        students_list.add(new list("Disha Srivastava - MT20116"));
        students_list.add(new list("Prabhat Dwivedi - MT20117"));
        students_list.add(new list("Sumit Dwivedi - MT20118"));
        students_list.add(new list("Saksi mishra - MT20119"));
        students_list.add(new list("Alok mishra - MT20120"));
        students_list.add(new list("Nidhi allwani - MT20121"));
        students_list.add(new list("Jyoti Dwivedi - MT20122"));
        students_list.add(new list("Ajay pandey - MT20123"));
        students_list.add(new list("Sachin pandey - MT20124"));
        students_list.add(new list("Anshika mishra - MT20125"));
        students_list.add(new list("Ankit- MT20126"));
        students_list.add(new list("Saurabh - MT20127"));
        students_list.add(new list("Prateek - MT20128"));
        students_list.add(new list("Shalini - MT20129"));
        students_list.add(new list("Srishti - MT20130"));
        students_list.add(new list("Antja - MT20131"));
        students_list.add(new list("Shraddha - MT20132"));
        students_list.add(new list("Shweta - MT20133"));

        //singleton.get(getActivity()).setSlist(students_list);
        return students_list;
    }

    @Override
    public void onItemClick(list li) {

       // Fragment fragment=detail_fragment.newInstance(li.getStudent_name());

        //FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.Fragment1,fragment,"detail_fragment");
        //transaction.addToBackStack(null);
        //transaction.commit();
     //   if(li.getStudent_name()== "Richa Dwivedi - MT20104"){
       // Bundle bundle=new Bundle();
       // bundle.putString("name","richa");
       // bundle.putString("dept","Mobile Computing");
       // bundle.putString("email","richa20104@iiiitd.ac.in");
        //detail_fragment fragment=new detail_fragment();
        //fragment.setArguments(bundle);
        //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();}
       /* Bundle bundle=new Bundle();
        switch (li.getStudent_name()) {
            case "Richa Dwivedi - MT20104":

               // bundle.putString("name","Richa Dwivedi");
                bundle.putString("dept","Mobile Computing");
                bundle.putString("email","richa20104@iiiitd.ac.in");



                break;
            case "Toshi Rawka- MT20105":

                //bundle.putString("name","Toshi Rawka");
                bundle.putString("dept","Software Enginerring");
                bundle.putString("email","toshi20105@iiiitd.ac.in");

                break;

            case "Shivani Shukla - MT20106":

                //bundle.putString("name","Shivani Shukla");
                bundle.putString("dept","Artificial Intelligence");
                bundle.putString("email","shivnai20106@iiiitd.ac.in");

                break;

            case "Rahul Shukla - MT20107":

                //bundle.putString("name","Rahul Shula");
                bundle.putString("dept","Mechanical Enginerring");
                bundle.putString("email","rahul20107@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;
            case "Shreya Goel - MT20108":

                //bundle.putString("name","Shreya Goel");
                bundle.putString("dept","Data Enginerring");
                bundle.putString("email","shreya20108@iiiitd.ac.in");

                break;

            case "Anubhav Ruhela - MT20109":

                //bundle.putString("name","Anubhav Ruhela");
                bundle.putString("dept","Without Specialization");
                bundle.putString("email","anubhav20109@iiiitd.ac.in");

                break;

            case "Sakshi kaushik- MT20110":

                //bundle.putString("name","Sakshi Kaushik");
                bundle.putString("dept","Data Engineering");
                bundle.putString("email","sakshi20110@iiiitd.ac.in");

                break;

            case "Dharmendar Kumar - MT20111":

                //bundle.putString("name","Dharmendar Kumar");
                bundle.putString("dept","Without Specialization");
                bundle.putString("email","dharmendar2011@iiiitd.ac.in");

                break;

            case "Shreya Garg - MT20112":

                //bundle.putString("name","Shreya Garg");
                bundle.putString("dept","Without Specialization");
                bundle.putString("email","shreya20112@iiiitd.ac.in");

                break;

            case "Meenakshi Das - MT20113":

                //bundle.putString("name","Meenakshi Das");
                bundle.putString("dept","Information Security");
                bundle.putString("email","das20113@iiiitd.ac.in");

                break;

            case "Roshan S - MT20114":

                //bundle.putString("name","Roshan S");
                bundle.putString("dept","Software Enginerring");
                bundle.putString("email","s20114@iiiitd.ac.in");

                break;

            case "Sreepurna Mohanty - MT20115":

                //bundle.putString("name","Sreepurna Mohanty");
                bundle.putString("dept","ETC");
                bundle.putString("email","mohanty20115@iiiitd.ac.in");

                break;

            case "Disha Srivastava - MT20116":

                //bundle.putString("name","Disha Srivastava");
                bundle.putString("dept","Computer Science and Engineering");
                bundle.putString("email","disha20116@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Prabhat Dwivedi - MT20117":

                //bundle.putString("name","Prabhat Dwivedi");
                bundle.putString("dept","CSA");
                bundle.putString("email","pk20117@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Sumit Dwivedi - MT20118":

                //bundle.putString("name","Sumit Dwivedi");
                bundle.putString("dept","Mobile computing");
                bundle.putString("email","sumit20118@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Saksi mishra - MT20119":

                //bundle.putString("name","Saksi Mishra");
                bundle.putString("dept","Software Enginerring");
                bundle.putString("email","sa20119@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Alok mishra - MT20120":

                //bundle.putString("name","Alok Mishra");
                bundle.putString("dept","AI");
                bundle.putString("email","alok20120@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Nidhi allwani - MT20121":

                //bundle.putString("name","Nidhi Allwani");
                bundle.putString("dept","Data Engineering");
                bundle.putString("email","nidhi20121@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Jyoti Dwivedi - MT20122":

                //bundle.putString("name","Jyoti Dwivedi");
                bundle.putString("dept","Mobile Computing");
                bundle.putString("email","jyoti20122@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Ajay pandey - MT20123":

                //bundle.putString("name","Ajay Pandey");
                bundle.putString("dept","Software Enginerring");
                bundle.putString("email","ajay20123@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Sachin pandey - MT20124":

                //bundle.putString("name","Sachin Pandey");
                bundle.putString("dept","Artificial Intelligence");
                bundle.putString("email","sachin20124@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Anshika mishra - MT20125":

                //bundle.putString("name","Anshika Mishra");
                bundle.putString("dept","Information Security");
                bundle.putString("email","anshika20125@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Ankit- MT20126":

                //bundle.putString("name","Ankit");
                bundle.putString("dept","Data Enginerring");
                bundle.putString("email","ankit20126@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Saurabh - MT20127":

                //bundle.putString("name","Saurabh");
                bundle.putString("dept","computer science");
                bundle.putString("email","saurabh20127@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Prateek - MT20128":

                //bundle.putString("name","Prateek");
                bundle.putString("dept","Data Enginerring");
                bundle.putString("email","prateek20128@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Shalini - MT20129":

                //bundle.putString("name","Shalini");
                bundle.putString("dept","Mobile computing");
                bundle.putString("email","shalini20129@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Srishti - MT20130":

                //bundle.putString("name","Srishti");
                bundle.putString("dept","Data Enginerring");
                bundle.putString("email","srishti20130@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Antja - MT20131":

               // bundle.putString("name","Ankita");
                bundle.putString("dept","Without Specialization");
                bundle.putString("email","ankita20131@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Shraddha - MT20132":

               // bundle.putString("name","Shraddha");
                bundle.putString("dept","ETC");
                bundle.putString("email","shraddha20132@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;

            case "Shweta - MT20133":

                //bundle.putString("name","Shweta");
                bundle.putString("dept","Computer Science");
                bundle.putString("email","shweta20133@iiiitd.ac.in");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).commit();
                break;
        }
        //detail_fragment fragment=new detail_fragment();
        //fragment.setArguments(bundle);
        //getFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).addToBackStack(null).commit();
*/


    }


}
