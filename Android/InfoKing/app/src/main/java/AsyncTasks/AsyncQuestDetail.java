package AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import Models.StructArchive;
import Models.StructDetail;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncQuestDetail extends AsyncTask<Integer, Void, StructDetail> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected StructDetail doInBackground(Integer... integers) {
        if (App.isOnline()) {
            return Commands.questDetail(integers[0], integers[1]);
        }
        App.HANDLER.post(new Runnable() {
            @Override
            public void run() {
                App.toast(App.CONTEXT.getString(R.string.error_internet));
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(StructDetail result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncQuestDetail setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(StructDetail result);
    }
}
