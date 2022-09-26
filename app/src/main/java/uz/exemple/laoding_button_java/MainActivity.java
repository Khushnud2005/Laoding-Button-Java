package uz.exemple.laoding_button_java;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class MainActivity extends AppCompatActivity {

    CircularProgressButton btn_download;
    CircularProgressButton btn_2;

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    void  initViews(){
        btn_download = (CircularProgressButton) findViewById(R.id.btn_download);
        btn_2 = (CircularProgressButton) findViewById(R.id.btn_2);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String,String,String> demoDownload = new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            Thread.sleep(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        return "Done";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if (s.equals("Done")){
                            Toast.makeText(MainActivity.this,"Download Done!",Toast.LENGTH_SHORT).show();
                            btn_download.doneLoadingAnimation(Color.parseColor("#333639"), BitmapFactory.decodeResource(getResources(), br.com.simplepass.loadingbutton.R.drawable.ic_done_white_48dp));

                        }
                    }
                };
                btn_download.startAnimation();
                demoDownload.execute();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_download.revertAnimation();
                        btn_download.setBackgroundResource(R.drawable.button_shape_default_rounded);

                    }

                }, 5000);

            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_2.startAnimation();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_2.revertAnimation();
                        btn_2.setBackgroundResource(R.drawable.button_shape_default_rounded);

                    }

                }, 5000);
            }
        });
    }
}