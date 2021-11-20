<?php

/* @var $db DB */

if (isset($_POST['quest_id'], $_POST['user_id'])) {
    $questId = intval($_POST['quest_id']);
    $userId = intval($_POST['user_id']);
    $quest = $db->arrayQuery("SELECT * FROM `quests` WHERE (`id`='{$questId}' AND (`user1_id`='{$userId}' OR `user2_id`='{$userId}'));");
    if (count($quest) > 0) {
        $quest = $quest[0];
        $user1 = $quest->user1_id == $userId ? '(شما)' : $db->arrayQuery("SELECT `name` FROM `users` WHERE (`id`='{$quest->user1_id}');")[0]->name;
        $user2 = $quest->user2_id == $userId ? '(شما)' : $db->arrayQuery("SELECT `name` FROM `users` WHERE (`id`='{$quest->user2_id}');")[0]->name;
        $q1 = null;
        $q2 = null;
        $q3 = null;
        $q4 = null;
        $q5 = null;
        $q6 = null;
        if (!is_null($quest->q1)) {
            $q1 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q1}');")[0];
            $q1->you = $quest->user1_id == $userId ? $quest->a1u1 : $quest->a1u2;
        }
        if (!is_null($quest->q2)) {
            $q2 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q2}');")[0];
            $q2->you = $quest->user1_id == $userId ? $quest->a2u1 : $quest->a2u2;
        }
        if (!is_null($quest->q3)) {
            $q3 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q3}');")[0];
            $q3->you = $quest->user1_id == $userId ? $quest->a3u1 : $quest->a3u2;
        }
        if (!is_null($quest->q4)) {
            $q4 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q4}');")[0];
            $q4->you = $quest->user1_id == $userId ? $quest->a4u1 : $quest->a4u2;
        }
        if (!is_null($quest->q5)) {
            $q5 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q5}');")[0];
            $q5->you = $quest->user1_id == $userId ? $quest->a5u1 : $quest->a5u2;
        }
        if (!is_null($quest->q6)) {
            $q6 = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$quest->q6}');")[0];
            $q6->you = $quest->user1_id == $userId ? $quest->a6u1 : $quest->a6u2;
        }
        $result = [
            'code' => 1,
            'data' => [
                'user1' => $user1,
                'user2' => $user2,
                'q1' => $q1,
                'q2' => $q2,
                'q3' => $q3,
                'q4' => $q4,
                'q5' => $q5,
                'q6' => $q6,
                'finished' => $quest->finished,
            ],
        ];
    }
}
