package AsyncTasks;

import android.os.AsyncTask;

import Models.StructQuest;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncQuestCheck extends AsyncTask<Integer, Void, StructQuest> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected StructQuest doInBackground(Integer... integers) {
        if (App.isOnline()) {
            return Commands.questCheck(integers[0]);
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
    protected void onPostExecute(StructQuest result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncQuestCheck setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(StructQuest result);
    }
}
