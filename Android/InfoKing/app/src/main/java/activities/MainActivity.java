package activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Adapters.AdapterArchive;
import AsyncTasks.AsyncQuestArchive;
import AsyncTasks.AsyncQuestCancel;
import AsyncTasks.AsyncQuestCheck;
import AsyncTasks.AsyncQuestNew;
import CustomControls.CustomButtonBold;
import CustomControls.CustomTextViewBold;
import Dialogs.DialogConfirm;
import Dialogs.DialogProgress;
import Models.StructArchive;
import Models.StructQuest;
import ir.ncis.infoking.ActivityEnhanced;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class MainActivity extends ActivityEnhanced implements View.OnClickListener {
    private boolean exitDialogShown = false;
    private AdapterArchive adapterArchive;
    private CustomTextViewBold txtName;
    private CustomButtonBold btnCancel;
    private CustomButtonBold btnCurrentQuest;
    private CustomButtonBold btnSearch;
    private ImageView imgSeparator;
    private ProgressBar prgSearching;
    private RecyclerView rvArchive;
    private SwipeRefreshLayout swpReload;
    private Timer timer = new Timer();
    private ViewGroup lytSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getItems();

        rvArchive.setAdapter(adapterArchive);
        rvArchive.setLayoutManager(new LinearLayoutManager(App.ACTIVITY, LinearLayoutManager.VERTICAL, false));
        rvArchive.setHasFixedSize(true);

        txtName.setText(App.CURRENT_USER.name);
        showSearchControls(App.CURRENT_USER.searching);

        new AsyncQuestCheck()
                .setOperations(new AsyncQuestCheck.Operations() {
                    private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                    @Override
                    public void before() {
                        dialogProgress.show();
                    }

                    @Override
                    public void after(StructQuest result) {
                        dialogProgress.dismiss();
                        if (result != null) {
                            App.CURRENT_QUEST = result;
                            lytSearch.setVisibility(View.GONE);
                            btnCurrentQuest.setVisibility(View.VISIBLE);
                            btnCurrentQuest.setText(result.opponent);
                            imgSeparator.setVisibility(View.VISIBLE);
                        } else {
                            lytSearch.setVisibility(View.VISIBLE);
                            btnCurrentQuest.setVisibility(View.GONE);
                            imgSeparator.setVisibility(View.GONE);
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    new AsyncQuestCheck()
                                            .setOperations(new AsyncQuestCheck.Operations() {
                                                @Override
                                                public void before() {
                                                }

                                                @Override
                                                public void after(StructQuest result) {
                                                    if (result != null) {
                                                        App.CURRENT_QUEST = result;
                                                        lytSearch.setVisibility(View.GONE);
                                                        btnCurrentQuest.setVisibility(View.VISIBLE);
                                                        imgSeparator.setVisibility(View.VISIBLE);
                                                        btnCurrentQuest.setText(result.opponent);
                                                        timer.cancel();
                                                        timer.purge();
                                                    }
                                                }
                                            })
                                            .execute(App.CURRENT_USER.id);
                                }
                            }, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(5));
                        }
                    }
                })
                .execute(App.CURRENT_USER.id);

        swpReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadArchive();
            }
        });

        loadArchive();
    }

    private void loadArchive() {
        new AsyncQuestArchive()
                .setOperations(new AsyncQuestArchive.Operations() {
                    private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                    @Override
                    public void before() {
                        dialogProgress.show();
                        swpReload.setRefreshing(true);
                    }

                    @Override
                    public void after(ArrayList<StructArchive> result) {
                        dialogProgress.dismiss();
                        swpReload.setRefreshing(false);
                        adapterArchive.archives = result;
                        adapterArchive.notifyDataSetChanged();
                    }
                })
                .execute(App.CURRENT_USER.id);
    }

    private void showSearchControls(boolean isSearching) {
        btnCancel.setVisibility(isSearching ? View.VISIBLE : View.GONE);
        btnSearch.setVisibility(isSearching ? View.GONE : View.VISIBLE);
        prgSearching.setVisibility(isSearching ? View.VISIBLE : View.GONE);
    }

    private void getItems() {
        adapterArchive = new AdapterArchive(new ArrayList<StructArchive>());
        btnCancel = (CustomButtonBold) findViewById(R.id.btnCancel);
        btnCurrentQuest = (CustomButtonBold) findViewById(R.id.btnCurrentQuest);
        btnSearch = (CustomButtonBold) findViewById(R.id.btnSearch);
        imgSeparator = (ImageView) findViewById(R.id.imgSeparator);
        lytSearch = (ViewGroup) findViewById(R.id.lytSearch);
        prgSearching = (ProgressBar) findViewById(R.id.prgSearching);
        rvArchive = (RecyclerView) findViewById(R.id.rvArchive);
        swpReload = (SwipeRefreshLayout) findViewById(R.id.swpReload);
        txtName = (CustomTextViewBold) findViewById(R.id.txtName);

        btnCancel.setOnClickListener(this);
        btnCurrentQuest.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (!exitDialogShown) {
            new DialogConfirm(App.ACTIVITY, R.style.TransparentDialog)
                    .setMessage(getString(R.string.message_exit))
                    .setTitle(getString(R.string.label_exit))
                    .setOnCancelListener(new DialogConfirm.OnCancelListener() {
                        @Override
                        public void onCancel() {
                            exitDialogShown = false;
                        }
                    })
                    .setOnConfirmListener(new DialogConfirm.OnConfirmListener() {
                        @Override
                        public void onConfirm() {
                            System.exit(0);
                        }
                    })
                    .show();
            exitDialogShown = true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel: {
                new AsyncQuestCancel()
                        .setOperations(new AsyncQuestCancel.Operations() {
                            private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                            @Override
                            public void before() {
                                dialogProgress.show();
                            }

                            @Override
                            public void after(boolean result) {
                                dialogProgress.dismiss();
                                if (result) {
                                    App.CURRENT_USER.searching = false;
                                    showSearchControls(App.CURRENT_USER.searching);
                                }
                            }
                        })
                        .execute(App.CURRENT_USER.id);
            }
            break;
            case R.id.btnCurrentQuest: {
                boolean ready = App.CURRENT_QUEST.q1 != 0 && App.CURRENT_QUEST.q2 != 0 && App.CURRENT_QUEST.q3 != 0 && App.CURRENT_QUEST.q4 != 0 && App.CURRENT_QUEST.q5 != 0 && App.CURRENT_QUEST.q6 != 0;
                App.startActivity(ready ? GameActivity.class : CategoryActivity.class);
            }
            break;
            case R.id.btnSearch: {
                new AsyncQuestNew()
                        .setOperations(new AsyncQuestNew.Operations() {
                            private DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                            @Override
                            public void before() {
                                dialogProgress.show();
                            }

                            @Override
                            public void after(StructQuest result) {
                                dialogProgress.dismiss();
                                if (result != null) {
                                    lytSearch.setVisibility(View.GONE);
                                    btnCurrentQuest.setVisibility(View.VISIBLE);
                                    btnCurrentQuest.setText(result.opponent);
                                    imgSeparator.setVisibility(View.VISIBLE);
                                } else {
                                    lytSearch.setVisibility(View.VISIBLE);
                                    btnCurrentQuest.setVisibility(View.GONE);
                                    imgSeparator.setVisibility(View.GONE);
                                    showSearchControls(App.CURRENT_USER.searching);
                                }
                            }
                        })
                        .execute(App.CURRENT_USER.id);
            }
            break;
        }
    }
}
