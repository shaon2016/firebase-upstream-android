package com.wedevol.fcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import static com.wedevol.fcmtest.MainActivity.RANDOM;

/**
 * Created by hp on 8/7/2017.
 */

public class FcmMessageReceiverDialogActivity extends AppCompatActivity {
    private static final String TAG = "FcmMessageReceiver";
    public static final String FCM_PROJECT_SENDER_ID = "431269160141";
    public static final String FCM_SERVER_CONNECTION = "@gcm.googleapis.com";
    public static final String BACKEND_ACTION_MESSAGE = "MESSAGE";
    public static final String BACKEND_ACTION_ACCEPT = "com.wedevol.ACCEPT";
    public static final String BACKEND_ACTION_REJECT = "com.wedevol.REJECT";
    public static final Random RANDOM = new Random();
    Intent intent;
    String token = null;
    private TokenService tokenService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_msg_recv_dialog);
        this.setFinishOnTouchOutside(false);

        intent = new Intent();
        //String message = intent.getExtras().getString("message");

        //Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();

        //Get token from Firebase
        FirebaseMessaging.getInstance().subscribeToTopic("challenge");
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
    }

    public void acceptChallenge(View view) {
        String setMsgId = Integer.toString(RANDOM.nextInt());
        Log.d(TAG, "msgId: " + setMsgId);

        String message = "accepted";

        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(FCM_PROJECT_SENDER_ID + FCM_SERVER_CONNECTION)
                .setMessageId(setMsgId)
                .addData("message", message)
                .addData("action", BACKEND_ACTION_ACCEPT)
                .addData("receiver_token", token)
                .build());
        // To send a message to other device through the XMPP Server, you should add the
        // receiverId and change the action name to BACKEND_ACTION_MESSAGE in the data



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

        String setMsgId = Integer.toString(RANDOM.nextInt());
        Log.d(TAG, "msgId: " + setMsgId);

        String message = "rejected";

        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(FCM_PROJECT_SENDER_ID + FCM_SERVER_CONNECTION)
                .setMessageId(setMsgId)
                .addData("message", message)
                .addData("action", BACKEND_ACTION_REJECT)
                .addData("receiver_token", token)
                .build());
        // To send a message to other device through the XMPP Server, you should add the
        // receiverId and change the action name to BACKEND_ACTION_MESSAGE in the data


        Toast.makeText(this, "" + "Challenge rejected", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, ChallengeMsgActivity.class);
        intent.putExtra("message", "Rejected");
        startActivity(intent);
        finish();
    }
}
