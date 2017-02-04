package org.lenchan139.twitterhandler;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String inURL;
        if(getIntent().getData()!= null){
            Log.v("InString",getIntent().getData().toString());
            inURL = getIntent().getData().toString();
            //Intent intent = new Intent(this,MainActivity.class);
            //intent.putExtra("InURL",inURL);
            //startActivity(intent);
            String outUrl = null;
            if(inURL.lastIndexOf("/") == inURL.length()-1){ inURL.substring(0,inURL.length()-1);}
            String temp99 = "mobile.twitter.com/";
            String data1 = inURL.substring(inURL.indexOf(temp99)+temp99.length());
            String temp1 = "/status/";
            String temp2 = "/";

            if(data1.indexOf(temp1) == -1 ){
                outUrl = "twitter://user?screen_name=" + data1 + "&src=mobile";
            }else if(data1.indexOf(temp1) >=0 ){
                String tempOut = data1.substring(data1.indexOf(temp1)+temp1.length());

                int toCount = tempOut.indexOf("/");
                if(toCount!=-1){
                    tempOut=tempOut.substring(0,toCount);
                }
                Log.v("tempOut",tempOut);
                outUrl = "twitter://status?status_id=" +tempOut +"&src=mobile";
            }else{
                outUrl = null;
            }
            if(outUrl != null){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(outUrl));
                try {
                    startActivity(browserIntent);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(this, "Twitter not installed yet.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "No a vaild Twiiter Url!", Toast.LENGTH_SHORT).show();
            }

            Log.v("url",outUrl);
            finish();
        }
    }
}
