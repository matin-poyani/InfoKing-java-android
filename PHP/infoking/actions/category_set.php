<?php

/* @var $db DB */

if (isset($_POST['quest_id'], $_POST['user_id'], $_POST['cat1_id'], $_POST['cat2_id'], $_POST['cat3_id'])) {
    $questId = intval($_POST['quest_id']);
    $userId = intval($_POST['user_id']);
    $cat1Id = intval($_POST['cat1_id']);
    $cat2Id = intval($_POST['cat2_id']);
    $cat3Id = intval($_POST['cat3_id']);
    $quest = $db->arrayQuery("SELECT * FROM `quests` WHERE (`id`='{$questId}');");
    if (count($quest) > 0) {
        $quest = $quest[0];
        if ($quest->user1_id == $userId) {
            if (is_null($quest->q1) && is_null($quest->q3) && is_null($quest->q3)) {
                $q1 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat1Id}') ORDER BY RAND() LIMIT 1;");
                $q3 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat2Id}') ORDER BY RAND() LIMIT 1;");
                $q5 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat3Id}') ORDER BY RAND() LIMIT 1;");
                if (count($q1) > 0 && count($q3) > 0 && count($q5) > 0) {
                    $db->query("UPDATE `quests` SET `q1`='{$q1[0]->id}',`q3`='{$q3[0]->id}',`q5`='{$q5[0]->id}' WHERE (`id`='{$quest->id}');");
                    if ($db->arrayQuery("SELECT COUNT(*) FROM `quests` WHERE (`id`='{$questId}' AND `q1` IS NOT NULL AND `q3` IS NOT NULL AND `q5` IS NOT NULL);")[0]->total > 0) {
                        $result['code'] = 1;
                    }
                }
            } else {
                $result['code'] = 1;
            }
        } elseif ($quest->user2_id == $userId) {
            if (is_null($quest->q2) && is_null($quest->q4) && is_null($quest->q6)) {
                $q2 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat1Id}') ORDER BY RAND() LIMIT 1;");
                $q4 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat2Id}') ORDER BY RAND() LIMIT 1;");
                $q6 = $db->arrayQuery("SELECT `id` FROM `questions` WHERE (`category_id`='{$cat3Id}') ORDER BY RAND() LIMIT 1;");
                if (count($q2) > 0 && count($q4) > 0 && count($q6) > 0) {
                    $db->query("UPDATE `quests` SET `q2`='{$q2[0]->id}',`q4`='{$q4[0]->id}',`q6`='{$q6[0]->id}' WHERE (`id`='{$quest->id}');");
                    if ($db->arrayQuery("SELECT COUNT(*) FROM `quests` WHERE (`id`='{$questId}' AND `q2` IS NOT NULL AND `q4` IS NOT NULL AND `q6` IS NOT NULL);")[0]->total > 0) {
                        $result['code'] = 1;
                    }
                }
            } else {
                $result['code'] = 1;
            }
        }
    }
}