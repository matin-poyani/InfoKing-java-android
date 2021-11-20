<?php

/* @var $db DB */

if (isset($_POST['name'], $_POST['user'], $_POST['pass'])) {
    $name = $db->escape($_POST['name']);
    $user = $db->escape($_POST['user']);
    $pass = sha1($_POST['pass']);
    $db->query("INSERT INTO `users` VALUES (NULL,{$name},{$user},'{$pass}');");
    if ($db->affectedRows() > 0) {
        $result['code'] = 1;
    }
}
