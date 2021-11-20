<?php

/* @var $db DB */

if (isset($_POST['user_id'])) {
    $userId = intval($_POST['user_id']);
    $db->query("UPDATE `users` SET `searching`='0' WHERE (`id`='{$userId}');");
    if ($db->arrayQuery("SELECT COUNT(*) AS `total` FROM `users` WHERE (`id`='{$userId}' AND `searching`='0');")[0]->total > 0) {
        $result['code'] = 1;
    }
}
