package AsyncTasks;

import android.os.AsyncTask;

import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncQuestCancel extends AsyncTask<Integer, Void, Boolean> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        if (App.isOnline()) {
            return Commands.questCancel(integers[0]);
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
    protected void onPostExecute(Boolean result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncQuestCancel setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(boolean result);
    }
}
