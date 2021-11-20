<?php

/* @var $db DB */

$categories = $db->arrayQuery("SELECT * FROM `categories`;");
foreach ($categories as $id => $category) {
    if ($db->arrayQuery("SELECT COUNT(*) AS `total` FROM `questions` WHERE (`category_id`='{$category->id}');")[0]->total == 0) {
        unset($categories[$id]);
    }
}
if (count($categories) >= 3) {
    shuffle($categories);
    $group1 = array_slice($categories, 0, 3);
    shuffle($categories);
    $group2 = array_slice($categories, 0, 3);
    shuffle($categories);
    $group3 = array_slice($categories, 0, 3);
    $result = [
        'code' => 1,
        'data' => compact('group1', 'group2', 'group3'),
    ];
}