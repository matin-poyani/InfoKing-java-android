<?php

/* @var $db DB */

if (isset($_POST['user_id'])) {
    $userId = intval($_POST['user_id']);
    $thisUser = $db->arrayQuery("SELECT `id` FROM `users` WHERE (`id`='{$userId}');");
    if (count($thisUser) > 0) {
        $thisUser = $thisUser[0];
        $quest = $db->arrayQuery("SELECT `q`.`id` AS `id`,`u1`.`id` AS `user1`,`u2`.`id` AS `user2`,`q1`,`q2`,`q3`,`q4`,`q5`,`q6` FROM `quests` `q` LEFT JOIN `users` `u1` ON `q`.`user1_id`=`u1`.`id` LEFT JOIN `users` `u2` ON `q`.`user2_id`=`u2`.`id` WHERE ((`user1_id`='{$userId}' OR `user2_id`='{$userId}') AND `finished`='0') ORDER BY `id` DESC LIMIT 1;");
        if (count($quest) == 0) {
            $opponent = $db->arrayQuery("SELECT * FROM `users` WHERE (`id`<>'{$thisUser->id}' AND `searching`='1') ORDER BY RAND() LIMIT 1;");
            if (count($opponent) == 0) {
                $db->query("UPDATE `users` SET `searching`='1' WHERE (`id`='{$userId}');");
                $count = $db->arrayQuery("SELECT COUNT(*) AS `total` FROM `users` WHERE (`id`='{$userId}' AND `searching`='1');")[0]->total;
                if ($count > 0) {
                    $result = [
                        'code' => 1,
                        'data' => 'searching',
                    ];
                }
            } else {
                $opponent = $opponent[0];
                $ts = time();
                $db->query("INSERT INTO `quests` (`user1_id`,`user2_id`,`ts`,`finished`) VALUES ('{$opponent->id}','{$thisUser->id}','{$ts}','0');");
                if ($db->affectedRows() > 0) {
                    $questId = $db->insertId();
                    $db->query("UPDATE `users` SET `searching`='0' WHERE (`id` IN ('{$thisUser->id}','{$opponent->id}'));");
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
            }
        } else {
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
}
