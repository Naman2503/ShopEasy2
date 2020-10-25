package com.example.naman.shopeasy;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class AdapterElectronics extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataElectronics> data= Collections.emptyList();
    DataElectronics current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterElectronics(Context context, List<DataElectronics> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_electronics, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final DataElectronics current=data.get(position);
        Log.d("TAG","holder result"+current.dataImage+" "+current.dataname+""+current.databrand+""+
                current.dataprice+""+current.datawarranty+""+current.dataquantity+""+current.datadesc);
        myHolder.holderName.setText("Name: " +current.dataname);
        myHolder.holderBrand.setText("Brand: " + current.databrand);
        myHolder.holderPrice.setText("Price: " +current.dataprice);
        myHolder.holderWarranty.setText("Warranty: " +current.datawarranty);
        myHolder.holderQuantity.setText("Quantity: " +current.dataquantity);
        //myHolder.holderDescription.setText("Description: " +current.datadesc);
// load image into imageview using glide
        Picasso.with(context).load(current.dataImage)

                .into(myHolder.holderimg);
        myHolder.btpurchase.setOnClickListener(new View.OnClickListener() {
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
        TextView holderWarranty;
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
            holderWarranty= (TextView) itemView.findViewById(R.id.containerwarranty);
            holderQuantity= (TextView) itemView.findViewById(R.id.containerquantity);
         //   holderDescription = (TextView) itemView.findViewById(R.id.containerdescription);
            holderimg= (ImageView) itemView.findViewById(R.id.containerimage);
            btpurchase= (Button) itemView.findViewById(R.id.purchase);
        }

    }

}




