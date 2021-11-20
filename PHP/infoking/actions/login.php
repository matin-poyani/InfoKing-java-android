<?php

/* @var $db DB */

if (isset($_POST['user'], $_POST['pass'])) {
    $user = $db->escape(strtolower($_POST['user']));
    $pass = sha1($_POST['pass']);
    $user = $db->arrayQuery("SELECT * FROM `users` WHERE (LOWER(`user`)={$user} AND `pass`='{$pass}');");
    if (count($user) > 0) {
        $user = $user[0];
        $result = [
            'code' => 1,
            'data' => [
                'id' => $user->id,
                'name' => $user->name,
                'searching' => $user->searching ? 'true' : 'false',
            ],
        ];
    }
}
