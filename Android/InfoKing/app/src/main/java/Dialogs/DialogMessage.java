package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import CustomControls.CustomTextView;
import CustomControls.CustomTextViewBold;
import ir.ncis.infoking.R;

public class DialogMessage extends Dialog {
    private CustomTextViewBold txtTitle;
    private CustomTextView txtMessage;
    private OnOKListener onOKListener;


    public DialogMessage(Context context) {
        super(context);
        initialize();
    }

    public DialogMessage(Context context, int themeResId) {
        super(context, themeResId);
        initialize();
    }

    private void initialize() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_message);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);

        txtTitle = findViewById(R.id.txtTitle);
        txtMessage = findViewById(R.id.txtMessage);
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOKListener != null) {
                    onOKListener.onOK();
                }
                dismiss();
            }
        });
    }

    public DialogMessage setMessage(String message) {
        txtMessage.setText(message);
        return this;
    }

    public DialogMessage setTitle(String title) {
        txtTitle.setText(title);
        return this;
    }

    public DialogMessage setOnOKListener(OnOKListener onOKListener) {
        this.onOKListener = onOKListener;
        return this;
    }

    public interface OnOKListener {
        void onOK();
    }
}
