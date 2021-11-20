package AsyncTasks;

import android.os.AsyncTask;

import Models.StructUser;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncLogin extends AsyncTask<String, Void, StructUser> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected StructUser doInBackground(String... strings) {
        if (App.isOnline()) {
            return Commands.login(strings[0], strings[1]);
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
    protected void onPostExecute(StructUser result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncLogin setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(StructUser result);
    }
}
