package AsyncTasks;

import android.os.AsyncTask;

import Models.StructCategories;
import Web.Commands;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AsyncCategoryGet extends AsyncTask<Void, Void, StructCategories> {
    private Operations operations;

    @Override
    protected void onPreExecute() {
        if (operations != null) {
            operations.before();
        }
    }

    @Override
    protected StructCategories doInBackground(Void... voids) {
        if (App.isOnline()) {
            return Commands.categoryGet();
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
    protected void onPostExecute(StructCategories result) {
        if (operations != null) {
            operations.after(result);
        }
    }

    public AsyncCategoryGet setOperations(Operations operations) {
        this.operations = operations;
        return this;
    }

    public interface Operations {
        void before();

        void after(StructCategories result);
    }
}
