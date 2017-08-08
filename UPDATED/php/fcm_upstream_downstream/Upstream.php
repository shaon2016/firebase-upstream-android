<?php
/**
 * Created by PhpStorm.
 * User: hp
 * Date: 8/8/2017
 * Time: 9:30 AM
 */

class Upstream
{
    private $FCM_SERVER_LEGACY_KEY = "AIzaSyAOq45Ai7LNsuhxWDWn521jraGKkwwWNtE";

    private $ID;
    private $TOKEN;
    private $TOKENS;
    private $EMAIL;
    private $MESSAGE;
    private $ACTION;


    private $DB_CONNECTION;
    private $servername = "localhost";
    private $username = "root";
    private $password = "";
    private $dbname = "fcmtest";

    function __construct()
    {
        $this->DB_CONNECTION = mysqli_connect($this->servername, $this->username,
            $this->password, $this->dbname);
    }

    function prepare($data)
    {

        if (array_key_exists('id', $data))
            $this->ID = $data['id'];

        if (array_key_exists('email', $data))
            $this->EMAIL = $data['email'];

        if (array_key_exists('tokens', $data))
            $this->TOKENS = $data['tokens'];

        if (array_key_exists('token', $data))
            $this->TOKEN = $data['token'];


        if (array_key_exists('message', $data))
            $this->MESSAGE = $data['message'];

        if (array_key_exists('action', $data))
            $this->ACTION = $data['action'];
    }


    function getAllDataExceptTheSender() {
        $tokens = array();
        $sql = "SELECT * FROM `users` WHERE identification is null";
        $result = mysqli_query($this->DB_CONNECTION, $sql);

        if(mysqli_num_rows($result) > 0 ){
            while ($row = mysqli_fetch_assoc($result)) {
                $tokens[] = $row["token"];
            }
        }

        return $tokens;
    }

    // multiple function
    // data will be from database
    function sendNotificationToAllExceptSender($tokens, $message, $senderToken,
            $action) {
        $url = 'https://fcm.googleapis.com/fcm/send';
        $fields = array(
            'registration_ids' => $tokens,
            'data' => array( "message" => $message,
                "sender_token" => $senderToken,
                "action" => $action)
        );
        $headers = array(
            'Authorization:key=' . $this->FCM_SERVER_LEGACY_KEY,
            'Content-Type:application/json'
        );
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
        $result = curl_exec($ch);
        if ($result == FALSE) {
            die('Curl failed: ' . curl_error($ch));
        }
        curl_close($ch);
        return $result;
    }


    // single person notification
    // data will be from receiver client
    function sendNotificationToOnlySenderFromReceiver($token, $message, $senderToken, $action) {
        $url = 'https://fcm.googleapis.com/fcm/send';
        $fields = array(
            'to' => $token,
            'data' => array( "message" => $message,
                "sender_token" => $senderToken,
                "action" => $action)
        );
        $headers = array(
            'Authorization:key=' . $this->FCM_SERVER_LEGACY_KEY,
            'Content-Type:application/json'
        );
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
        $result = curl_exec($ch);
        if ($result == FALSE) {
            die('Curl failed: ' . curl_error($ch));
        }
        curl_close($ch);
        return $result;
    }


}