package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.ViewGroup;
import android.view.Window;

import CustomControls.CustomTextView;
import CustomControls.CustomTextViewBold;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class DialogDetail extends Dialog {

    public DialogDetail(@NonNull Context context) {
        super(context);
    }

    public DialogDetail(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detail);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        CustomTextView txtPlayer1 = findViewById(R.id.txtPlayer1);
        CustomTextView txtPlayer2 = findViewById(R.id.txtPlayer2);
        CustomTextViewBold txtQuestion1 = findViewById(R.id.txtQuestion1);
        CustomTextViewBold txtQuestion2 = findViewById(R.id.txtQuestion2);
        CustomTextViewBold txtQuestion3 = findViewById(R.id.txtQuestion3);
        CustomTextViewBold txtQuestion4 = findViewById(R.id.txtQuestion4);
        CustomTextViewBold txtQuestion5 = findViewById(R.id.txtQuestion5);
        CustomTextViewBold txtQuestion6 = findViewById(R.id.txtQuestion6);
        CustomTextView txtA1 = findViewById(R.id.txtA1);
        CustomTextView txtA2 = findViewById(R.id.txtA2);
        CustomTextView txtA3 = findViewById(R.id.txtA3);
        CustomTextView txtA4 = findViewById(R.id.txtA4);
        CustomTextView txtA5 = findViewById(R.id.txtA5);
        CustomTextView txtA6 = findViewById(R.id.txtA6);
        CustomTextView txtB1 = findViewById(R.id.txtB1);
        CustomTextView txtB2 = findViewById(R.id.txtB2);
        CustomTextView txtB3 = findViewById(R.id.txtB3);
        CustomTextView txtB4 = findViewById(R.id.txtB4);
        CustomTextView txtB5 = findViewById(R.id.txtB5);
        CustomTextView txtB6 = findViewById(R.id.txtB6);
        CustomTextView txtC1 = findViewById(R.id.txtC1);
        CustomTextView txtC2 = findViewById(R.id.txtC2);
        CustomTextView txtC3 = findViewById(R.id.txtC3);
        CustomTextView txtC4 = findViewById(R.id.txtC4);
        CustomTextView txtC5 = findViewById(R.id.txtC5);
        CustomTextView txtC6 = findViewById(R.id.txtC6);
        CustomTextView txtD1 = findViewById(R.id.txtD1);
        CustomTextView txtD2 = findViewById(R.id.txtD2);
        CustomTextView txtD3 = findViewById(R.id.txtD3);
        CustomTextView txtD4 = findViewById(R.id.txtD4);
        CustomTextView txtD5 = findViewById(R.id.txtD5);
        CustomTextView txtD6 = findViewById(R.id.txtD6);

        txtPlayer1.setText(App.CURRENT_DETAIL.user1);
        txtPlayer2.setText(App.CURRENT_DETAIL.user2);

        txtQuestion1.setText(App.CURRENT_DETAIL.q1.question);
        txtA1.setText(App.CURRENT_DETAIL.q1.a);
        txtB1.setText(App.CURRENT_DETAIL.q1.b);
        txtC1.setText(App.CURRENT_DETAIL.q1.c);
        txtD1.setText(App.CURRENT_DETAIL.q1.d);
        switch (App.CURRENT_DETAIL.q1.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA1.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA1.setBackgroundResource(R.drawable.button_holo_green);
                        txtB1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA1.setBackgroundResource(R.drawable.button_holo_green);
                        txtC1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA1.setBackgroundResource(R.drawable.button_holo_green);
                        txtD1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA1.setBackgroundResource(R.drawable.button_holo_red);
                        txtB1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB1.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB1.setBackgroundResource(R.drawable.button_holo_green);
                        txtC1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB1.setBackgroundResource(R.drawable.button_holo_green);
                        txtD1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA1.setBackgroundResource(R.drawable.button_holo_red);
                        txtC1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB1.setBackgroundResource(R.drawable.button_holo_red);
                        txtC1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC1.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC1.setBackgroundResource(R.drawable.button_holo_green);
                        txtD1.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA1.setBackgroundResource(R.drawable.button_holo_red);
                        txtD1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB1.setBackgroundResource(R.drawable.button_holo_red);
                        txtD1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC1.setBackgroundResource(R.drawable.button_holo_red);
                        txtD1.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD1.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }

        txtQuestion2.setText(App.CURRENT_DETAIL.q2.question);
        txtA2.setText(App.CURRENT_DETAIL.q2.a);
        txtB2.setText(App.CURRENT_DETAIL.q2.b);
        txtC2.setText(App.CURRENT_DETAIL.q2.c);
        txtD2.setText(App.CURRENT_DETAIL.q2.d);
        switch (App.CURRENT_DETAIL.q2.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q2.you) {
                    case "a":
                        txtA2.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA2.setBackgroundResource(R.drawable.button_holo_green);
                        txtB2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA2.setBackgroundResource(R.drawable.button_holo_green);
                        txtC2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA2.setBackgroundResource(R.drawable.button_holo_green);
                        txtD2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q2.you) {
                    case "a":
                        txtA2.setBackgroundResource(R.drawable.button_holo_red);
                        txtB2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB2.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB2.setBackgroundResource(R.drawable.button_holo_green);
                        txtC2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB2.setBackgroundResource(R.drawable.button_holo_green);
                        txtD2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q2.you) {
                    case "a":
                        txtA2.setBackgroundResource(R.drawable.button_holo_red);
                        txtC2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB2.setBackgroundResource(R.drawable.button_holo_red);
                        txtC2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC2.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC2.setBackgroundResource(R.drawable.button_holo_green);
                        txtD2.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q2.you) {
                    case "a":
                        txtA2.setBackgroundResource(R.drawable.button_holo_red);
                        txtD2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB2.setBackgroundResource(R.drawable.button_holo_red);
                        txtD2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC2.setBackgroundResource(R.drawable.button_holo_red);
                        txtD2.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD2.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }

        txtQuestion3.setText(App.CURRENT_DETAIL.q3.question);
        txtA3.setText(App.CURRENT_DETAIL.q3.a);
        txtB3.setText(App.CURRENT_DETAIL.q3.b);
        txtC3.setText(App.CURRENT_DETAIL.q3.c);
        txtD3.setText(App.CURRENT_DETAIL.q3.d);
        switch (App.CURRENT_DETAIL.q3.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q3.you) {
                    case "a":
                        txtA3.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA3.setBackgroundResource(R.drawable.button_holo_green);
                        txtB3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA3.setBackgroundResource(R.drawable.button_holo_green);
                        txtC3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA3.setBackgroundResource(R.drawable.button_holo_green);
                        txtD3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q3.you) {
                    case "a":
                        txtA3.setBackgroundResource(R.drawable.button_holo_red);
                        txtB3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB3.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB3.setBackgroundResource(R.drawable.button_holo_green);
                        txtC3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB3.setBackgroundResource(R.drawable.button_holo_green);
                        txtD3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q3.you) {
                    case "a":
                        txtA3.setBackgroundResource(R.drawable.button_holo_red);
                        txtC3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB3.setBackgroundResource(R.drawable.button_holo_red);
                        txtC3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC3.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC3.setBackgroundResource(R.drawable.button_holo_green);
                        txtD3.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q3.you) {
                    case "a":
                        txtA3.setBackgroundResource(R.drawable.button_holo_red);
                        txtD3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB3.setBackgroundResource(R.drawable.button_holo_red);
                        txtD3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC3.setBackgroundResource(R.drawable.button_holo_red);
                        txtD3.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD3.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }

        txtQuestion4.setText(App.CURRENT_DETAIL.q4.question);
        txtA4.setText(App.CURRENT_DETAIL.q4.a);
        txtB4.setText(App.CURRENT_DETAIL.q4.b);
        txtC4.setText(App.CURRENT_DETAIL.q4.c);
        txtD4.setText(App.CURRENT_DETAIL.q4.d);
        switch (App.CURRENT_DETAIL.q4.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q4.you) {
                    case "a":
                        txtA4.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA4.setBackgroundResource(R.drawable.button_holo_green);
                        txtB4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA4.setBackgroundResource(R.drawable.button_holo_green);
                        txtC4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA4.setBackgroundResource(R.drawable.button_holo_green);
                        txtD4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q4.you) {
                    case "a":
                        txtA4.setBackgroundResource(R.drawable.button_holo_red);
                        txtB4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB4.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB4.setBackgroundResource(R.drawable.button_holo_green);
                        txtC4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB4.setBackgroundResource(R.drawable.button_holo_green);
                        txtD4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q4.you) {
                    case "a":
                        txtA4.setBackgroundResource(R.drawable.button_holo_red);
                        txtC4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB4.setBackgroundResource(R.drawable.button_holo_red);
                        txtC4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC4.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC4.setBackgroundResource(R.drawable.button_holo_green);
                        txtD4.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q4.you) {
                    case "a":
                        txtA4.setBackgroundResource(R.drawable.button_holo_red);
                        txtD4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB4.setBackgroundResource(R.drawable.button_holo_red);
                        txtD4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC4.setBackgroundResource(R.drawable.button_holo_red);
                        txtD4.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD4.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }

        txtQuestion5.setText(App.CURRENT_DETAIL.q5.question);
        txtA5.setText(App.CURRENT_DETAIL.q5.a);
        txtB5.setText(App.CURRENT_DETAIL.q5.b);
        txtC5.setText(App.CURRENT_DETAIL.q5.c);
        txtD5.setText(App.CURRENT_DETAIL.q5.d);
        switch (App.CURRENT_DETAIL.q5.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q5.you) {
                    case "a":
                        txtA5.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA5.setBackgroundResource(R.drawable.button_holo_green);
                        txtB5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA5.setBackgroundResource(R.drawable.button_holo_green);
                        txtC5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA5.setBackgroundResource(R.drawable.button_holo_green);
                        txtD5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q5.you) {
                    case "a":
                        txtA5.setBackgroundResource(R.drawable.button_holo_red);
                        txtB5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB5.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB5.setBackgroundResource(R.drawable.button_holo_green);
                        txtC5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB5.setBackgroundResource(R.drawable.button_holo_green);
                        txtD5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q5.you) {
                    case "a":
                        txtA5.setBackgroundResource(R.drawable.button_holo_red);
                        txtC5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB5.setBackgroundResource(R.drawable.button_holo_red);
                        txtC5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC5.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC5.setBackgroundResource(R.drawable.button_holo_green);
                        txtD5.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q5.you) {
                    case "a":
                        txtA5.setBackgroundResource(R.drawable.button_holo_red);
                        txtD5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB5.setBackgroundResource(R.drawable.button_holo_red);
                        txtD5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC5.setBackgroundResource(R.drawable.button_holo_red);
                        txtD5.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD5.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }

        txtQuestion6.setText(App.CURRENT_DETAIL.q6.question);
        txtA6.setText(App.CURRENT_DETAIL.q6.a);
        txtB6.setText(App.CURRENT_DETAIL.q6.b);
        txtC6.setText(App.CURRENT_DETAIL.q6.c);
        txtD6.setText(App.CURRENT_DETAIL.q6.d);
        switch (App.CURRENT_DETAIL.q6.answer) {
            case "a":
                switch (App.CURRENT_DETAIL.q6.you) {
                    case "a":
                        txtA6.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "b":
                        txtA6.setBackgroundResource(R.drawable.button_holo_green);
                        txtB6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "c":
                        txtA6.setBackgroundResource(R.drawable.button_holo_green);
                        txtC6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtA6.setBackgroundResource(R.drawable.button_holo_green);
                        txtD6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "b":
                switch (App.CURRENT_DETAIL.q6.you) {
                    case "a":
                        txtA6.setBackgroundResource(R.drawable.button_holo_red);
                        txtB6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB6.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "c":
                        txtB6.setBackgroundResource(R.drawable.button_holo_green);
                        txtC6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                    case "d":
                        txtB6.setBackgroundResource(R.drawable.button_holo_green);
                        txtD6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "c":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA6.setBackgroundResource(R.drawable.button_holo_red);
                        txtC6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB6.setBackgroundResource(R.drawable.button_holo_red);
                        txtC6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC6.setBackgroundResource(R.drawable.button_green);
                        break;
                    case "d":
                        txtC6.setBackgroundResource(R.drawable.button_holo_green);
                        txtD6.setBackgroundResource(R.drawable.button_holo_red);
                        break;
                }
                break;
            case "d":
                switch (App.CURRENT_DETAIL.q1.you) {
                    case "a":
                        txtA6.setBackgroundResource(R.drawable.button_holo_red);
                        txtD6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "b":
                        txtB6.setBackgroundResource(R.drawable.button_holo_red);
                        txtD6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "c":
                        txtC6.setBackgroundResource(R.drawable.button_holo_red);
                        txtD6.setBackgroundResource(R.drawable.button_holo_green);
                        break;
                    case "d":
                        txtD6.setBackgroundResource(R.drawable.button_green);
                        break;
                }
                break;
        }
    }
}
