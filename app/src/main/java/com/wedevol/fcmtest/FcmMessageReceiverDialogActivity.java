package com.wedevol.fcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.wedevol.fcmtest.ConstantID.BACKEND_URL_BASE;
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
    SessoinManager sessoinManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_msg_recv_dialog);
        this.setFinishOnTouchOutside(false);

        sessoinManager = new SessoinManager(this);
        intent = new Intent();

    }



    public void acceptChallenge(View view) {


        sendAcceptedMessageToChallengeSender();



        // start the challenge between them
        Toast.makeText(this, "" + "Challenge accepted" +
                ". Game will start soon. and receiver token " + token, Toast.LENGTH_SHORT).show();


        intent = new Intent(this, ChallengeAcceptedActivityWithProgressBar.class);
        intent.putExtra("message", "Rejected");
        startActivity(intent);
        finish();
    }

    private void sendAcceptedMessageToChallengeSender() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BACKEND_URL_BASE + "/fcm_upstream_downstream/receiver.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.Log(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.Log(TAG, error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("message", "Start Playing");
                params.put("token", sessoinManager.getKeyGetToken());
                params.put("action", "accepted");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
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
