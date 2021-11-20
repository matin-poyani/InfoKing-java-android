<?php

/* @var $db DB */

if (isset($_POST['quest_id'], $_POST['user_id'], $_POST['q'], $_POST['a']) && in_array($_POST['q'], range(1, 6)) && in_array(strtolower($_POST['a']), range('a', 'd'))) {
    $questId = intval($_POST['quest_id']);
    $userId = intval($_POST['user_id']);
    $q = intval($_POST['q']);
    $a = $db->escape(strtolower($_POST['a']));
    $quest = $db->arrayQuery("SELECT * FROM `quests` WHERE (`id`='{$questId}' AND `finished`='0');");
    if (count($quest) > 0) {
        $quest = $quest[0];
        if ($userId == $quest->user1_id) {
            $db->query("UPDATE `quests` SET `a{$q}u1`={$a} WHERE (`id`='{$quest->id}' AND `q{$q}` IS NOT NULL AND `a{$q}u1` IS NULL);");
            if ($db->arrayQuery("SELECT COUNT(*) AS `total` FROM `quests` WHERE (`id`='{$quest->id}' AND `a{$q}u1` IS NOT NULL);")[0]->total > 0) {
                $result['code'] = 1;
            }
        } elseif ($userId == $quest->user2_id) {
            $db->query("UPDATE `quests` SET `a{$q}u2`={$a} WHERE (`id`='{$quest->id}' AND `q{$q}` IS NOT NULL AND `a{$q}u2` IS NULL);");
            if ($db->arrayQuery("SELECT COUNT(*) AS `total` FROM `quests` WHERE (`id`='{$quest->id}' AND `a{$q}u2` IS NOT NULL);")[0]->total > 0) {
                $result['code'] = 1;
            }
        }
        if ($db->arrayQuery("SELECT COUNT(*) AS `total` FROM `quests` WHERE (`id`='{$questId}' AND `a1u1` IS NOT NULL AND `a1u2` IS NOT NULL AND `a2u1` IS NOT NULL AND `a2u2` IS NOT NULL AND `a3u1` IS NOT NULL AND `a3u2` IS NOT NULL AND `a4u1` IS NOT NULL AND `a4u2` IS NOT NULL AND `a5u1` IS NOT NULL AND `a5u2` IS NOT NULL AND `a6u1` IS NOT NULL AND `a6u2` IS NOT NULL);")[0]->total > 0) {
            $db->query("UPDATE `quests` SET `finished`='1' WHERE (`id`='{$quest->id}');");
        }
    }
}