package AsyncTasks;

import android.os.AsyncTask;

import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncAnswer extends AsyncTask<Integer, Void, Boolean> {
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
            return Commands.answer(integers[0], integers[1], integers[2], integers[3]);
        }
        App.HANDLER.post(new Runnable() {
            @Override
            public void run() {
                App.toast(App.CONTEXT.getString(R.string.error_internet));
            }
        });
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncAnswer setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(boolean result);
    }
}
