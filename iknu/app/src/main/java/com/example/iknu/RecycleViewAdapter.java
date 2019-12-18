package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.iknu.RecycleViewAdapter.MyViewHolder;

import java.security.Security;
import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {

    Context context;
    ArrayList<String> unfilteredlist;
    ArrayList<String> filteredlist;

    public RecycleViewAdapter(Context context, ArrayList<String> list)
    {
        super();
        this.context = context;
        this.unfilteredlist = list;
        this.filteredlist = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(filteredlist.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textview);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString  = constraint.toString();
                if(charString.isEmpty())
                {
                    filteredlist = unfilteredlist;
                }else
                {
                    ArrayList<String> filteringList = new ArrayList<>();
                    for(String name : unfilteredlist)
                    {
                        if(name.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteringList.add(name);
                        }
                    }
                    filteredlist = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values =filteredlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredlist  = (ArrayList<String>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
