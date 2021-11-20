package activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import AsyncTasks.AsyncLogin;
import CustomControls.CustomEditText;
import Dialogs.DialogConfirm;
import Dialogs.DialogProgress;
import Models.StructUser;
import ir.ncis.infoking.ActivityEnhanced;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class LoginActivity extends ActivityEnhanced {
    private boolean exitDialogShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final CustomEditText edtUser = (CustomEditText) findViewById(R.id.edtUser);
        final CustomEditText edtPass = (CustomEditText) findViewById(R.id.edtPass);
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtUser.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(edtPass.getWindowToken(), 0);
                final DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);
                new AsyncLogin()
                        .setOperations(new AsyncLogin.Operations() {
                            @Override
                            public void before() {
                                dialogProgress.show();
                            }

                            @Override
                            public void after(StructUser result) {
                                dialogProgress.dismiss();
                                if (result != null) {
                                    App.CURRENT_USER = result;
                                    App.startActivity(MainActivity.class, true);
                                } else {
                                    App.toast(getString(R.string.error_login));
                                }
                            }
                        })
                        .execute(edtUser.getText().toString(), edtPass.getText().toString());
            }
        });
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
}
