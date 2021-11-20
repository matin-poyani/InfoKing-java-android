package AsyncTasks;

import android.os.AsyncTask;

import Models.StructQuestion;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncQuestion extends AsyncTask<Integer, Void, StructQuestion> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected StructQuestion doInBackground(Integer... integers) {
        if (App.isOnline()) {
            return Commands.question(integers[0]);
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
    protected void onPostExecute(StructQuestion result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncQuestion setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(StructQuestion result);
    }
}
