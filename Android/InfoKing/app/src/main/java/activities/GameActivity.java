package activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import AsyncTasks.AsyncAnswer;
import AsyncTasks.AsyncQuestDetail;
import AsyncTasks.AsyncQuestion;
import CustomControls.CustomButtonBold;
import CustomControls.CustomTextView;
import CustomControls.CustomTextViewBold;
import Dialogs.DialogProgress;
import Models.StructDetail;
import Models.StructQuestion;
import ir.ncis.infoking.ActivityEnhanced;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class GameActivity extends ActivityEnhanced {
    private int currentQuestion = 1, currentAnswer = 0;
    private int colorNormal, colorSelected;
    private CustomButtonBold btnRetry, btnSet;
    private CustomTextView txtA, txtB, txtC, txtD;
    private CustomTextViewBold txtOpponent, txtQuestion;
    private ViewGroup lytQuestion;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getItems();

        txtOpponent.setText(App.CURRENT_QUEST.opponent);

        loadCurrentQuestion();

        txtA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                currentAnswer = 1;
                txtA.setBackgroundResource(R.drawable.button_holo_green);
                txtA.setTextColor(colorSelected);
            }
        });

        txtB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                currentAnswer = 2;
                txtB.setBackgroundResource(R.drawable.button_holo_green);
                txtB.setTextColor(colorSelected);
            }
        });

        txtC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                currentAnswer = 3;
                txtC.setBackgroundResource(R.drawable.button_holo_green);
                txtC.setTextColor(colorSelected);
            }
        });

        txtD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                currentAnswer = 4;
                txtD.setBackgroundResource(R.drawable.button_holo_green);
                txtD.setTextColor(colorSelected);
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCurrentQuestion();
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnswer != 0) {
                    new AsyncAnswer()
                            .setOperations(new AsyncAnswer.Operations() {
                                private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                                @Override
                                public void before() {
                                    dialogProgress.show();
                                }

                                @Override
                                public void after(boolean result) {
                                    dialogProgress.dismiss();
                                    if (result) {
                                        currentQuestion++;
                                        if (currentQuestion <= 6) {
                                            loadCurrentQuestion();
                                        } else {
                                            timer.scheduleAtFixedRate(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    new AsyncQuestDetail()
                                                            .setOperations(new AsyncQuestDetail.Operations() {
                                                                @Override
                                                                public void before() {
                                                                }

                                                                @Override
                                                                public void after(StructDetail result) {
                                                                    if (result != null && result.finished) {
                                                                        timer.cancel();
                                                                        timer.purge();
                                                                        finish();
                                                                    }
                                                                }
                                                            })
                                                            .execute(App.CURRENT_QUEST.id, App.CURRENT_USER.id);
                                                }
                                            }, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(5));
                                        }
                                    } else {
                                        App.toast(getString(R.string.error_answer));
                                    }
                                }
                            })
                            .execute(App.CURRENT_QUEST.id, App.CURRENT_USER.id, currentQuestion, currentAnswer);
                }
            }
        });
    }

    private void loadCurrentQuestion() {
        int questionId = 0;
        switch (currentQuestion) {
            case 1:
                questionId = App.CURRENT_QUEST.q1;
                break;
            case 2:
                questionId = App.CURRENT_QUEST.q2;
                break;
            case 3:
                questionId = App.CURRENT_QUEST.q3;
                break;
            case 4:
                questionId = App.CURRENT_QUEST.q4;
                break;
            case 5:
                questionId = App.CURRENT_QUEST.q5;
                break;
            case 6:
                questionId = App.CURRENT_QUEST.q6;
                break;
        }
        new AsyncQuestion()
                .setOperations(new AsyncQuestion.Operations() {
                    private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                    @Override
                    public void before() {
                        dialogProgress.show();
                        lytQuestion.setVisibility(View.GONE);
                        btnRetry.setVisibility(View.GONE);
                    }

                    @Override
                    public void after(StructQuestion result) {
                        dialogProgress.dismiss();
                        if (result != null) {
                            txtQuestion.setText(result.question);
                            txtA.setText(result.a);
                            txtB.setText(result.b);
                            txtC.setText(result.c);
                            txtD.setText(result.d);
                            deselectAll();
                            lytQuestion.setVisibility(View.VISIBLE);
                        } else {
                            App.toast(getString(R.string.error_question));
                            btnRetry.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .execute(questionId);
    }

    private void deselectAll() {
        currentAnswer = 0;
        txtA.setTextColor(colorNormal);
        txtB.setTextColor(colorNormal);
        txtC.setTextColor(colorNormal);
        txtD.setTextColor(colorNormal);
        txtA.setBackgroundResource(R.drawable.button_holo_light);
        txtB.setBackgroundResource(R.drawable.button_holo_light);
        txtC.setBackgroundResource(R.drawable.button_holo_light);
        txtD.setBackgroundResource(R.drawable.button_holo_light);
    }

    private void getItems() {
        btnRetry = (CustomButtonBold) findViewById(R.id.btnRetry);
        btnSet = (CustomButtonBold) findViewById(R.id.btnSet);
        colorNormal = ContextCompat.getColor(App.CONTEXT, R.color.primaryLight);
        colorSelected = ContextCompat.getColor(App.CONTEXT, R.color.greenLight);
        lytQuestion = (ViewGroup) findViewById(R.id.lytQuestion);
        txtA = (CustomTextView) findViewById(R.id.txtA);
        txtB = (CustomTextView) findViewById(R.id.txtB);
        txtC = (CustomTextView) findViewById(R.id.txtC);
        txtD = (CustomTextView) findViewById(R.id.txtD);
        txtOpponent = (CustomTextViewBold) findViewById(R.id.txtOpponent);
        txtQuestion = (CustomTextViewBold) findViewById(R.id.txtQuestion);
    }
}
