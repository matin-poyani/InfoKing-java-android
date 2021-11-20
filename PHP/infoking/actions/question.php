<?php

/* @var $db DB */

if (isset($_POST['question_id'])) {
    $questionId = intval($_POST['question_id']);
    $question = $db->arrayQuery("SELECT * FROM `questions` WHERE (`id`='{$questionId}');");
    if (count($question) > 0) {
        $question = $question[0];
        $result = [
            'code' => 1,
            'data' => [
                'question' => $question->question,
                'a' => $question->a,
                'b' => $question->b,
                'c' => $question->c,
                'd' => $question->d,
            ],
        ];
    }
}