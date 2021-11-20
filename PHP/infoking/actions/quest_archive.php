<?php

/* @var $db DB */

if (isset($_POST['user_id'])) {
    $userId = intval($_POST['user_id']);
    $quests = $db->arrayQuery("SELECT * FROM `quests` WHERE ((`user1_id`='{$userId}' OR `user2_id`='{$userId}') AND `finished`='1') ORDER BY `id`;");
    if (count($quests) > 0) {
        $data = [];
        foreach ($quests as $quest) {
            $item = [];
            $item['id'] = $quest->id;
            if ($quest->user1_id == $userId) {
                $opponent = $db->arrayQuery("SELECT `name` FROM `users` WHERE (`id`='{$quest->user2_id}');");
                if (count($opponent) > 0) {
                    $item['opponent'] = $opponent[0]->name;
                    $userAnswers = 0;
                    $opponentAnswers = 0;
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q1}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a1u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a1u2) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q2}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a2u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a2u2) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q3}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a3u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a3u2) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q4}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a4u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a4u2) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q5}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a5u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a5u2) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q6}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a6u1) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a6u2) {
                            $opponentAnswers++;
                        }
                    }
                }
            } else {
                $opponent = $db->arrayQuery("SELECT `name` FROM `users` WHERE (`id`='{$quest->user1_id}');");
                if (count($opponent) > 0) {
                    $item['opponent'] = $opponent[0]->name;
                    $userAnswers = 0;
                    $opponentAnswers = 0;
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q1}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a1u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a1u1) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q2}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a2u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a2u1) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q3}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a3u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a3u1) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q4}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a4u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a4u1) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q5}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a5u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a5u1) {
                            $opponentAnswers++;
                        }
                    }
                    $question = $db->arrayQuery("SELECT `answer` FROM `questions` WHERE (`id`='{$quest->q6}');");
                    if (count($question) > 0) {
                        if ($question[0]->answer == $quest->a6u2) {
                            $userAnswers++;
                        }
                        if ($question[0]->answer == $quest->a6u1) {
                            $opponentAnswers++;
                        }
                    }
                }
            }
            $item['score_user'] = $userAnswers;
            $item['score_opponent'] = $opponentAnswers;
            $item['result'] = $userAnswers > $opponentAnswers ? 'win' : ($userAnswers < $opponentAnswers ? 'lose' : 'draw');
            $data[] = $item;
        }
        $result = [
            'code' => 1,
            'data' => $data,
        ];
    }
}
