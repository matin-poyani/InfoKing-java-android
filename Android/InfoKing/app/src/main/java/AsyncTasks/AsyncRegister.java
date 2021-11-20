package AsyncTasks;

import android.os.AsyncTask;

import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncRegister extends AsyncTask<String, Void, Boolean> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        if (App.isOnline()) {
            return Commands.register(strings[0], strings[1], strings[2]);
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

    public AsyncRegister setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(boolean result);
    }
}
