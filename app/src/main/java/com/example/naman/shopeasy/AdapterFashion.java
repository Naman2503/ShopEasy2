package com.example.naman.shopeasy;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class AdapterFashion extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFashion> data= Collections.emptyList();
    DataFashion current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterFashion(Context context, List<DataFashion> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_fashion, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder1= (MyHolder) holder;
        DataFashion current=data.get(position);
        myHolder1.holderName.setText("Name: " +current.dataname);
        myHolder1.holderBrand.setText("Brand: " + current.databrand);
        myHolder1.holderPrice.setText("Price: " +current.dataprice);
        myHolder1.holderCategory.setText("Category: " +current.datacategory);
        myHolder1.holderSize.setText("Size: " +current.datasize);
        myHolder1.holderQuantity.setText("Quantity: " +current.dataquantity);
        //myHolder1.holderDescription.setText("Description: " +current.datadesc);
        Picasso.with(context).load(current.dataImage)

                .into(myHolder1.holderimg);
        myHolder1.btpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Payment.class);
                context.startActivity(intent);
            }
        });

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView holderName;
        TextView holderBrand;
        TextView holderPrice;
        TextView holderCategory;
        TextView holderSize;
        TextView holderQuantity;
        //TextView holderDescription;
        ImageView holderimg;
        Button btpurchase;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            holderName= (TextView) itemView.findViewById(R.id.containername);
            holderBrand= (TextView) itemView.findViewById(R.id.containerbrand);
            holderPrice= (TextView) itemView.findViewById(R.id.containerprice);
            holderCategory= (TextView) itemView.findViewById(R.id.containercategory);
            holderSize= (TextView) itemView.findViewById(R.id.containersize);
            holderQuantity= (TextView) itemView.findViewById(R.id.containerquantity);
            //holderDescription = (TextView) itemView.findViewById(R.id.containerdescription);
            holderimg= (ImageView) itemView.findViewById(R.id.containerimage);
            btpurchase= (Button) itemView.findViewById(R.id.purchase);
        }

    }

}




