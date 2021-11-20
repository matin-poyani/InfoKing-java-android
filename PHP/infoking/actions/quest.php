<?php

/* @var $db DB */

if (isset($_POST['user_id'])) {
    $userId = intval($_POST['user_id']);
    $thisUser = $db->arrayQuery("SELECT * FROM `users` WHERE (`id`='{$userId}');");
    if (count($thisUser) > 0) {
        $thisUser = $thisUser[0];
        $quest = $db->arrayQuery("SELECT `q`.`id` AS `id`,`u1`.`name` AS `u1`,`u2`.`name` AS `u2` FROM `quests` `q` LEFT JOIN `users` `u1` ON (`q`.`user1_id`=`u1`.`id`) LEFT JOIN `users` `u2` ON (`q`.`user2_id`=`u2`.`id`) WHERE ((`user1_id`='{$thisUser->id}' OR `user2_id`='{$thisUser->id}') AND `finished`=0) LIMIT 1;");
        if (count($quest) == 0) {
            $otherUser = $db->arrayQuery("SELECT * FROM `users` WHERE (`id`<>'{$thisUser->id}' AND `searching`='1') ORDER BY RAND() LIMIT 1;");
            if (count($otherUser) == 0) {
                $db->query("UPDATE `users` SET `searching`='1' WHERE (`id`='{$userId}');");
                $count = $db->arrayQuery("SELECT COUNT(*) AS `total` FROM `users` WHERE (`id`='{$userId}' AND `searching`='1');")[0]->total;
                if ($count > 0) {
                    $result = [
                        'code' => 1,
                        'data' => 'searching',
                    ];
                }
            } else {
                $otherUser = $otherUser[0];
                $ts = time();
                $db->query("INSERT INTO `quests` (`user1_id`,`user2_id`,`ts`,`finished`) VALUES ('{$otherUser->id}','{$thisUser->id}','{$ts}','0');");
                if ($db->affectedRows() > 0) {
                    $questId = $db->insertId();
                    $db->query("UPDATE `users` SET `searching`='0' WHERE (`id` IN ('{$thisUser->id}','{$otherUser->id}'));");
                    $quest = $db->arrayQuery("SELECT `q`.`id` AS `id`,`u1`.`name` AS `u1`,`u2`.`name` AS `u2` FROM `quests` `q` LEFT JOIN `users` `u1` ON (`q`.`user1_id`=`u1`.`id`) LEFT JOIN `users` `u2` ON (`q`.`user2_id`=`u2`.`id`) WHERE (`q`.`id`='{$questId}');");
                    if (count($quest) > 0) {
                        $quest = $quest[0];
                        $result = [
                            'code' => 1,
                            'data' => [
                                'id' => $quest->id,
                                'u1' => $quest->u1,
                                'u2' => $quest->u2,
                            ],
                        ];
                    }
                }
            }
        } else {
            $quest = $quest[0];
            $result = [
                'code' => 1,
                'data' => [
                    'id' => $quest->id,
                    'u1' => $quest->u1,
                    'u2' => $quest->u2,
                ],
            ];
        }
    }
}
