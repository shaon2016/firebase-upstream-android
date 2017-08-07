package com.wedevol.fcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by hp on 8/7/2017.
 */

public class FcmMessageReceiverDialogActivity extends AppCompatActivity {
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_msg_recv_dialog);
        this.setFinishOnTouchOutside(false);

        intent = new Intent();
        //String message = intent.getExtras().getString("message");

        //Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    public void acceptChallenge(View view) {
        // start the challenge between them
        Toast.makeText(this, "" + "Challenge accepted" +
                ". Game will start soon.", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, ChallengeMsgActivity.class);
        intent.putExtra("message", "accepted");
        startActivity(intent);
        finish();
    }


    public void rejectChallenge(View view) {
        //Challenge rejected.
        // Let the server know that his/her challenge rejected

        Toast.makeText(this, "" + "Challenge rejected", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, ChallengeMsgActivity.class);
        intent.putExtra("message", "Rejected");
        startActivity(intent);
        finish();
    }
}
