package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import CustomControls.CustomButtonBold;
import CustomControls.CustomTextView;
import CustomControls.CustomTextViewBold;
import ir.ncis.infoking.R;

public class DialogConfirm extends Dialog {
    private CustomTextView txtMessage;
    private CustomTextViewBold txtTitle;
    private CustomButtonBold btnCancel;
    private CustomButtonBold btnConfirm;
    private OnCancelListener onCancelListener;
    private OnConfirmListener onConfirmListener;

    public DialogConfirm(@NonNull Context context) {
        super(context);
        initialize();
    }

    public DialogConfirm(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initialize();
    }

    private void initialize() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirm);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);

        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtMessage = findViewById(R.id.txtMessage);
        txtTitle = findViewById(R.id.txtTitle);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public DialogConfirm setMessage(String message) {
        txtMessage.setText(message);
        return this;
    }

    public DialogConfirm setTitle(String title) {
        txtTitle.setText(title);
        return this;
    }

    public DialogConfirm setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogConfirm.this.onCancelListener.onCancel();
                dismiss();
            }
        });
        return this;
    }

    public DialogConfirm setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogConfirm.this.onConfirmListener.onConfirm();
                dismiss();
            }
        });
        return this;
    }

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }
}
