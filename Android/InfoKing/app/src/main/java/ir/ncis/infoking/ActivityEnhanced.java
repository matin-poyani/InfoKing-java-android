package ir.ncis.infoking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ActivityEnhanced extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.ACTIVITY = this;
        if (App.INFLATER == null) {
            App.INFLATER = getLayoutInflater();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.ACTIVITY = this;
    }
}
