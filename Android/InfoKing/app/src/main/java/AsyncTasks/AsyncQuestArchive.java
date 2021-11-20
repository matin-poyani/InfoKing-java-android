package AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import Models.StructArchive;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncQuestArchive extends AsyncTask<Integer, Void, ArrayList<StructArchive>> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected ArrayList<StructArchive> doInBackground(Integer... integers) {
        if (App.isOnline()) {
            return Commands.questArchive(integers[0]);
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
    protected void onPostExecute(ArrayList<StructArchive> result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncQuestArchive setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(ArrayList<StructArchive> result);
    }
}
