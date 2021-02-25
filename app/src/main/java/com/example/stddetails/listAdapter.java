package com.example.stddetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    List<list> students_list1;
    private ItemClickListener clickListener;

    public listAdapter(List<list> students_list, ItemClickListener clickListener) {

        this.students_list1=students_list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.students_list,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {

       holder.val.setText(students_list1.get(position).getStudent_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_fragment.mypos=position;
                detail_fragment fragment=new detail_fragment();
                AppCompatActivity myact=(AppCompatActivity) v.getContext();

                //fragment.setArguments(bundle);
                myact.getSupportFragmentManager().beginTransaction().replace(R.id.Fragment1,fragment).addToBackStack(null).commit();


               // clickListener.onItemClick(students_list1.get(position));
                //Bundle bundle=new Bundle();
                //bundle.putString("name","richa");
                //detail_fragment fragment=new detail_fragment();
                //fragment.setArguments(bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return students_list1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView val;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            val=itemView.findViewById(R.id.details);



        }
    }
    public interface ItemClickListener{
        public void onItemClick(list li);



        }
    }