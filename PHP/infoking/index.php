<?php
require_once __DIR__ . '/DB.php';

$db = new DB();

$result = ['code' => -1];

if (isset($_GET['action'])) {
    $action = strtolower($_GET['action']);
    switch ($action) {
        case 'answer':
        case 'category_get':
        case 'category_set':
        case 'login':
        case 'quest_archive':
        case 'quest_cancel':
        case 'quest_check':
        case 'quest_detail':
        case 'quest_new':
        case 'question':
        case 'register':
            $result['code'] = 0;
            require_once __DIR__ . '/actions/' . $action . '.php';
            break;
    }
}

header('Content-Type: application/json');
echo json_encode($result, JSON_UNESCAPED_UNICODE);