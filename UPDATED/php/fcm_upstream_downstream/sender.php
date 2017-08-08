<?php
/**
 * Created by PhpStorm.
 * User: hp
 * Date: 8/8/2017
 * Time: 9:45 AM
 */

/*
 * email
 * message
 * action
 * token
 */

include_once("Upstream.php");
$upstream = new Upstream();
// preparing data for notification
$tokens = $upstream->getAllDataExceptTheSender();

//var_dump($tokens);
$message = $_POST['message'];
$senderToken = $_POST['token'];
$action = $_POST['action'];

$result = $upstream->sendNotificationToAllExceptSender($tokens, $message, $senderToken, $action);

echo $result;