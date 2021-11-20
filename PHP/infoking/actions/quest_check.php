<?php

/* @var $db DB */

if (isset($_POST['user_id'])) {
    $userId = intval($_POST['user_id']);
    $quest = $db->arrayQuery("SELECT `q`.`id` AS `id`,`u1`.`id` AS `user1`,`u2`.`id` AS `user2`,`q1`,`q2`,`q3`,`q4`,`q5`,`q6` FROM `quests` `q` LEFT JOIN `users` `u1` ON `q`.`user1_id`=`u1`.`id` LEFT JOIN `users` `u2` ON `q`.`user2_id`=`u2`.`id` WHERE ((`user1_id`='{$userId}' OR `user2_id`='{$userId}') AND `finished`='0') ORDER BY `id` DESC LIMIT 1;");
    if (count($quest) > 0) {
        $quest = $quest[0];
        $id = $quest->user1 == $userId ? $quest->user2 : $quest->user1;
        $opponent = $db->arrayQuery("SELECT `name` FROM `users` WHERE (`id`='{$id}');");
        if (count($opponent) > 0) {
            $opponent = $opponent[0];
            $result = [
                'code' => 1,
                'data' => [
                    'id' => intval($quest->id),
                    'opponent' => $opponent->name,
                    'q1' => intval($quest->q1),
                    'q2' => intval($quest->q2),
                    'q3' => intval($quest->q3),
                    'q4' => intval($quest->q4),
                    'q5' => intval($quest->q5),
                    'q6' => intval($quest->q6),
                ],
            ];
        }
    }
}
