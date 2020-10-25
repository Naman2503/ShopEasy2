package com.example.naman.shopeasy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundHomeAndKitchen extends AsyncTask<String,Void,String> {

    Context ctx;
    public BackgroundHomeAndKitchen(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.0.110/project/HomeAndKitchenSend.php";

        Log.d("TAG","doINbackground start");
        String method=params[0];
        if (method.equals("register"))
        {
            String Customer_name=params[1];
            String Customer_brand=params[2];
            String Customer_price=params[3];
            String Customer_warranty=params[4];
            String Customer_quantity=params[5];
            String Customer_type=params[6];
            String Customer_features=params[7];
            String Customer_img=params[8];
            Log.d("TAG","param in doInBack"+Customer_name+""+""+Customer_brand+""+Customer_price+""+
                    Customer_warranty+""+Customer_quantity+""+Customer_type+""+Customer_features+""+Customer_img);
            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permiteted in equals sign
                String data= URLEncoder.encode("Customer_name","UTF-8")+"="+ URLEncoder.encode(Customer_name,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_brand","UTF-8")+"="+ URLEncoder.encode(Customer_brand,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_price","UTF-8")+"="+ URLEncoder.encode(Customer_price,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_warranty","UTF-8")+"="+ URLEncoder.encode(Customer_warranty,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_quantity","UTF-8")+"="+ URLEncoder.encode(Customer_quantity,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_type","UTF-8")+"="+ URLEncoder.encode(Customer_type,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_features","UTF-8")+"="+ URLEncoder.encode(Customer_features,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_img","UTF-8")+"="+ URLEncoder.encode(Customer_img,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();


                Log.d("TAG","urldata"+data);


                //get response from server
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "Successful Registration";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if (result.equals("Successful Registration"))
        {
            //Toast.makeText(ctx, "Welcome", Toast.LENGTH_SHORT).show();


        }


    }
}


