package com.example.naman.shopeasy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ElectronicsActivity1 extends AppCompatActivity implements View.OnClickListener{
    EditText n,b,p,w,q,d;
    String n1,b1,p1,w1,q1,d1;
    Button bt;
    String imgDecodableString;
    String encodedImage;
    ImageView imgView;
    public static int RESULT_LOAD_IMG=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics1);
        n= (EditText) findViewById(R.id.namee1);
        b=(EditText)findViewById(R.id.brande1);
        p=(EditText)findViewById(R.id.pricee1);
        w=(EditText)findViewById(R.id.warrantye1);
        q=(EditText)findViewById(R.id.quantitye1);
        d=(EditText)findViewById(R.id.desce1);
        bt= (Button) findViewById(R.id.submit);
        imgView = (ImageView) findViewById(R.id.imageview);
        bt.setOnClickListener(this);
        Log.d("TAG","1");
    }

    @Override
    public void onClick(View v) {

//validation
        Log.d("TAG","2");

        if (n.getText().toString().length() == 0)
        {
            n.setError("Fill Name");
            return;
        }
        else if (b.getText().toString().length() == 0)
        {
            b.setError("Fill Brand");
            return;
        }
        else if (p.getText().toString().length() == 0)
        {
            p.setError("Fill Price");
            return;
        }
        else if (w.getText().toString().length() == 0)
        {
            w.setError("Fill Warranty");
            return;
        }
        else if (q.getText().toString().length() == 0)
        {
            q.setError("Fill Quantity");
            return;
        }
        else
        {
            Toast.makeText(this, "All Fields Validated", Toast.LENGTH_SHORT).show();

        }

        Log.d("TAG","3");

//validation END
        if (isOnline())
        {
            n1=n.getText().toString().trim();
            b1=b.getText().toString().trim();
            p1=p.getText().toString().trim();
            w1=w.getText().toString().trim();
            q1=q.getText().toString().trim();
            d1=d.getText().toString().trim();
            //Toast.makeText(this, "result"+n1+" "+b1+" "+p1+" "+w1+" "+q1, Toast.LENGTH_SHORT).show();
            String method="register";

            Log.d("TAG","4"+n1+""+b1+""+p1+""+w1+""+q1);
            Toast.makeText(this, "connection is ok", Toast.LENGTH_SHORT).show();
            Log.d("TAG","bt start");
            BackgroundElectronics backgroundTask=new BackgroundElectronics(this);
            backgroundTask.execute(method,n1,b1,p1,w1,q1,d1,encodedImage);
            Log.d("TAG","pass"+n1+""+b1+""+p1);
            Log.d("TAG","bt end");

        } else {
            Toast.makeText(this, "Connection is Offline", Toast.LENGTH_SHORT).show();
        }
    }

    public void browseImage(View v) {

// Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra("crop", "true");
        galleryIntent.putExtra("outputX", 200);
        galleryIntent.putExtra("outputY", 260);
        galleryIntent.putExtra("aspectX", 1);
        galleryIntent.putExtra("aspectY", 1);
        galleryIntent.putExtra("scale", true);
        galleryIntent.putExtra("return-data", true);
// Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);


    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 0) {
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.d("TAG","6");
                        // Toast.makeText(this, "Picture saved at " + imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                        Toast.makeText(this, "ImageSet", Toast.LENGTH_SHORT).show();
                        imgView.setImageBitmap(thumbnail);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        if (thumbnail != null) {
                            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                        }
                        byte[] b = baos.toByteArray();
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        //Toast.makeText(ElectronicsActivity1.this, encodedImage, Toast.LENGTH_SHORT).show();
                        Toast.makeText(ElectronicsActivity1.this, "Wait for moment ....", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(this, "Activity.RESULT_CANCELLED", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;


                }

            }//onActivityCamera-END
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Log.d("TAG","5");
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                if (cursor != null) {
                    cursor.moveToFirst();
                }

                int columnIndex = 0;
                if (cursor != null) {
                    columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                }
                if (cursor != null) {
                    imgDecodableString = cursor.getString(columnIndex);
                }
                if (cursor != null) {
                    cursor.close();
                }
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                //imageUploadSTART

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                Toast.makeText(ElectronicsActivity1.this, "Wait for moment ...."+encodedImage, Toast.LENGTH_SHORT).show();
                Log.d("error", "images" + encodedImage);
                //close
            }
        } catch (Exception e) {
            //Toast.makeText(this, "Problem Detected!"+e, Toast.LENGTH_LONG).show();
        }
    }

}
