package com.wedevol.fcmtest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChallengeAcceptedActivityWithProgressBar extends AppCompatActivity {
    ProgressDialog progressDialog;
    Thread myProgressThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_accepted_with_progress_bar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        myProgressThread = new Thread(new MyPrgressDialogThread());
        myProgressThread.start();
    }


    class MyPrgressDialogThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 15000; i++) {
                ChallengeAcceptedActivityWithProgressBar.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showpDialog();
                    }

                });

            }
            ChallengeAcceptedActivityWithProgressBar.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hidepDialog();
                    Toast.makeText(ChallengeAcceptedActivityWithProgressBar.this,
                            "Finished Loading", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myProgressThread.interrupt();
    }
}
