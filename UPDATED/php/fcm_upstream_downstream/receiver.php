<?php
/**
 * Created by PhpStorm.
 * User: hp
 * Date: 8/8/2017
 * Time: 9:45 AM
 */
include_once("Upstream.php");
$upstream = new Upstream();


$message = $_POST['message'];
$senderToken = $_POST['token'];
$action = $_POST['action'];

$result = $upstream->sendNotificationToOnlySenderFromReceiver($senderToken, $message, $senderToken, $action);

echo $result;