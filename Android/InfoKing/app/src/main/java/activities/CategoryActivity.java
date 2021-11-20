package activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import AsyncTasks.AsyncCategoryGet;
import AsyncTasks.AsyncCategorySet;
import AsyncTasks.AsyncQuestCheck;
import CustomControls.CustomButtonBold;
import CustomControls.CustomTextView;
import Dialogs.DialogConfirm;
import Dialogs.DialogProgress;
import Models.StructCategories;
import Models.StructQuest;
import ir.ncis.infoking.ActivityEnhanced;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class CategoryActivity extends ActivityEnhanced implements CompoundButton.OnCheckedChangeListener {
    private boolean exitDialogShown = false;
    private int colorNormal, colorSelected;
    private int selectedCat1, selectedCat2, selectedCat3;
    private DialogProgress dialogProgress;
    private AppCompatRadioButton rdoCat1A, rdoCat1B, rdoCat1C;
    private AppCompatRadioButton rdoCat2A, rdoCat2B, rdoCat2C;
    private AppCompatRadioButton rdoCat3A, rdoCat3B, rdoCat3C;
    private CustomButtonBold btnNew, btnSet;
    private CustomTextView txtCat1A, txtCat1B, txtCat1C;
    private CustomTextView txtCat2A, txtCat2B, txtCat2C;
    private CustomTextView txtCat3A, txtCat3B, txtCat3C;
    private StructCategories categories;
    private Timer timer = new Timer();
    private ViewGroup lytGroup1, lytGroup2, lytGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getItems();

        loadCategories();

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCategories();
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCat1 != 0 && selectedCat2 != 0 && selectedCat3 != 0) {
                    new AsyncCategorySet()
                            .setOperations(new AsyncCategorySet.Operations() {
                                @Override
                                public void before() {
                                    dialogProgress.show();
                                }

                                @Override
                                public void after(boolean result) {
                                    dialogProgress.dismiss();
                                    if (result) {
                                        timer.scheduleAtFixedRate(new TimerTask() {
                                            @Override
                                            public void run() {
                                                new AsyncQuestCheck()
                                                        .setOperations(new AsyncQuestCheck.Operations() {
                                                            @Override
                                                            public void before() {
                                                                App.HANDLER.post(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        dialogProgress.show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void after(StructQuest result) {
                                                                if (result != null) {
                                                                    App.CURRENT_QUEST = result;
                                                                    boolean ready = App.CURRENT_QUEST.q1 != 0 && App.CURRENT_QUEST.q2 != 0 && App.CURRENT_QUEST.q3 != 0 && App.CURRENT_QUEST.q4 != 0 && App.CURRENT_QUEST.q5 != 0 && App.CURRENT_QUEST.q6 != 0;
                                                                    if (ready) {
                                                                        App.HANDLER.post(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                dialogProgress.dismiss();
                                                                                App.startActivity(GameActivity.class, true);
                                                                            }
                                                                        });
                                                                        timer.cancel();
                                                                        timer.purge();
                                                                    }
                                                                }
                                                            }
                                                        })
                                                        .execute(App.CURRENT_USER.id);
                                            }
                                        }, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(5));
                                    } else {
                                        App.toast(getString(R.string.error_category_set));
                                    }
                                }
                            })
                            .execute(App.CURRENT_QUEST.id, App.CURRENT_USER.id, selectedCat1, selectedCat2, selectedCat3);
                } else {
                    App.toast(getString(R.string.message_category_not_selected));
                }
            }
        });

        rdoCat1A.setOnCheckedChangeListener(this);
        rdoCat1B.setOnCheckedChangeListener(this);
        rdoCat1C.setOnCheckedChangeListener(this);
        rdoCat2A.setOnCheckedChangeListener(this);
        rdoCat2B.setOnCheckedChangeListener(this);
        rdoCat2C.setOnCheckedChangeListener(this);
        rdoCat3A.setOnCheckedChangeListener(this);
        rdoCat3B.setOnCheckedChangeListener(this);
        rdoCat3C.setOnCheckedChangeListener(this);
    }

    private void loadCategories() {
        new AsyncCategoryGet()
                .setOperations(new AsyncCategoryGet.Operations() {
                    @Override
                    public void before() {
                        dialogProgress.show();
                        lytGroup1.setVisibility(View.GONE);
                        lytGroup2.setVisibility(View.GONE);
                        lytGroup3.setVisibility(View.GONE);
                    }

                    @Override
                    public void after(StructCategories result) {
                        dialogProgress.dismiss();
                        if (result != null) {
                            categories = result;
                            showCategories();
                            lytGroup1.setVisibility(View.VISIBLE);
                            lytGroup2.setVisibility(View.VISIBLE);
                            lytGroup3.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .execute();
    }

    private void showCategories() {
        rdoCat1A.setChecked(false);
        rdoCat1B.setChecked(false);
        rdoCat1C.setChecked(false);
        rdoCat2A.setChecked(false);
        rdoCat2B.setChecked(false);
        rdoCat2C.setChecked(false);
        rdoCat3A.setChecked(false);
        rdoCat3B.setChecked(false);
        rdoCat3C.setChecked(false);
        txtCat1A.setText(categories.group1.get(0).name);
        txtCat1B.setText(categories.group1.get(1).name);
        txtCat1C.setText(categories.group1.get(2).name);
        txtCat2A.setText(categories.group2.get(0).name);
        txtCat2B.setText(categories.group2.get(1).name);
        txtCat2C.setText(categories.group2.get(2).name);
        txtCat3A.setText(categories.group3.get(0).name);
        txtCat3B.setText(categories.group3.get(1).name);
        txtCat3C.setText(categories.group3.get(2).name);
        txtCat1A.setTextColor(colorNormal);
        txtCat1B.setTextColor(colorNormal);
        txtCat1C.setTextColor(colorNormal);
        txtCat2A.setTextColor(colorNormal);
        txtCat2B.setTextColor(colorNormal);
        txtCat2C.setTextColor(colorNormal);
        txtCat3A.setTextColor(colorNormal);
        txtCat3B.setTextColor(colorNormal);
        txtCat3C.setTextColor(colorNormal);
        selectedCat1 = 0;
        selectedCat2 = 0;
        selectedCat3 = 0;
    }

    private void getItems() {
        colorNormal = ContextCompat.getColor(App.CONTEXT, R.color.primaryLight);
        colorSelected = ContextCompat.getColor(App.CONTEXT, R.color.textWhite);
        dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);
        btnNew = (CustomButtonBold) findViewById(R.id.btnNew);
        btnSet = (CustomButtonBold) findViewById(R.id.btnSet);
        lytGroup1 = (ViewGroup) findViewById(R.id.lytGroup1);
        lytGroup2 = (ViewGroup) findViewById(R.id.lytGroup2);
        lytGroup3 = (ViewGroup) findViewById(R.id.lytGroup3);
        rdoCat1A = (AppCompatRadioButton) findViewById(R.id.rdoCat1A);
        rdoCat1B = (AppCompatRadioButton) findViewById(R.id.rdoCat1B);
        rdoCat1C = (AppCompatRadioButton) findViewById(R.id.rdoCat1C);
        rdoCat2A = (AppCompatRadioButton) findViewById(R.id.rdoCat2A);
        rdoCat2B = (AppCompatRadioButton) findViewById(R.id.rdoCat2B);
        rdoCat2C = (AppCompatRadioButton) findViewById(R.id.rdoCat2C);
        rdoCat3A = (AppCompatRadioButton) findViewById(R.id.rdoCat3A);
        rdoCat3B = (AppCompatRadioButton) findViewById(R.id.rdoCat3B);
        rdoCat3C = (AppCompatRadioButton) findViewById(R.id.rdoCat3C);
        txtCat1A = (CustomTextView) findViewById(R.id.txtCat1A);
        txtCat1B = (CustomTextView) findViewById(R.id.txtCat1B);
        txtCat1C = (CustomTextView) findViewById(R.id.txtCat1C);
        txtCat2A = (CustomTextView) findViewById(R.id.txtCat2A);
        txtCat2B = (CustomTextView) findViewById(R.id.txtCat2B);
        txtCat2C = (CustomTextView) findViewById(R.id.txtCat2C);
        txtCat3A = (CustomTextView) findViewById(R.id.txtCat3A);
        txtCat3B = (CustomTextView) findViewById(R.id.txtCat3B);
        txtCat3C = (CustomTextView) findViewById(R.id.txtCat3C);
    }

    @Override
    public void onBackPressed() {
        if (!exitDialogShown) {
            new DialogConfirm(App.ACTIVITY, R.style.TransparentDialog)
                    .setMessage(getString(R.string.label_back))
                    .setTitle(getString(R.string.message_back))
                    .setOnCancelListener(new DialogConfirm.OnCancelListener() {
                        @Override
                        public void onCancel() {
                            exitDialogShown = false;
                        }
                    })
                    .setOnConfirmListener(new DialogConfirm.OnConfirmListener() {
                        @Override
                        public void onConfirm() {
                            timer.cancel();
                            timer.purge();
                            finish();
                        }
                    })
                    .show();
            exitDialogShown = true;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean fromUser) {
        if (button.isChecked() && fromUser) {
            switch (button.getId()) {
                case R.id.rdoCat1A: {
                    rdoCat1B.setChecked(false);
                    rdoCat1C.setChecked(false);
                    txtCat1A.setTextColor(colorSelected);
                    txtCat1B.setTextColor(colorNormal);
                    txtCat1C.setTextColor(colorNormal);
                    selectedCat1 = categories.group1.get(0).id;
                }
                break;
                case R.id.rdoCat1B: {
                    rdoCat1A.setChecked(false);
                    rdoCat1C.setChecked(false);
                    txtCat1A.setTextColor(colorNormal);
                    txtCat1B.setTextColor(colorSelected);
                    txtCat1C.setTextColor(colorNormal);
                    selectedCat1 = categories.group1.get(1).id;
                }
                break;
                case R.id.rdoCat1C: {
                    rdoCat1A.setChecked(false);
                    rdoCat1B.setChecked(false);
                    txtCat1A.setTextColor(colorNormal);
                    txtCat1B.setTextColor(colorNormal);
                    txtCat1C.setTextColor(colorSelected);
                    selectedCat1 = categories.group1.get(2).id;
                }
                break;
                case R.id.rdoCat2A: {
                    rdoCat2B.setChecked(false);
                    rdoCat2C.setChecked(false);
                    txtCat2A.setTextColor(colorSelected);
                    txtCat2B.setTextColor(colorNormal);
                    txtCat2C.setTextColor(colorNormal);
                    selectedCat2 = categories.group2.get(0).id;
                }
                break;
                case R.id.rdoCat2B: {
                    rdoCat2A.setChecked(false);
                    rdoCat2C.setChecked(false);
                    txtCat2A.setTextColor(colorNormal);
                    txtCat2B.setTextColor(colorSelected);
                    txtCat2C.setTextColor(colorNormal);
                    selectedCat2 = categories.group2.get(1).id;
                }
                break;
                case R.id.rdoCat2C: {
                    rdoCat2A.setChecked(false);
                    rdoCat2B.setChecked(false);
                    txtCat2A.setTextColor(colorNormal);
                    txtCat2B.setTextColor(colorNormal);
                    txtCat2C.setTextColor(colorSelected);
                    selectedCat2 = categories.group2.get(2).id;
                }
                break;
                case R.id.rdoCat3A: {
                    rdoCat3B.setChecked(false);
                    rdoCat3C.setChecked(false);
                    txtCat3A.setTextColor(colorSelected);
                    txtCat3B.setTextColor(colorNormal);
                    txtCat3C.setTextColor(colorNormal);
                    selectedCat3 = categories.group3.get(0).id;
                }
                break;
                case R.id.rdoCat3B: {
                    rdoCat3A.setChecked(false);
                    rdoCat3C.setChecked(false);
                    txtCat3A.setTextColor(colorNormal);
                    txtCat3B.setTextColor(colorSelected);
                    txtCat3C.setTextColor(colorNormal);
                    selectedCat3 = categories.group3.get(1).id;
                }
                break;
                case R.id.rdoCat3C: {
                    rdoCat3A.setChecked(false);
                    rdoCat3B.setChecked(false);
                    txtCat3A.setTextColor(colorNormal);
                    txtCat3B.setTextColor(colorNormal);
                    txtCat3C.setTextColor(colorSelected);
                    selectedCat3 = categories.group3.get(2).id;
                }
                break;
            }
        }
    }
}
