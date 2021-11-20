package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import AsyncTasks.AsyncQuestDetail;
import CustomControls.CustomTextView;
import CustomControls.CustomTextViewBold;
import Dialogs.DialogDetail;
import Dialogs.DialogProgress;
import Models.StructArchive;
import Models.StructDetail;
import ir.ncis.infoking.App;
import ir.ncis.infoking.R;

public class AdapterArchive extends RecyclerView.Adapter {
    public ArrayList<StructArchive> archives;

    public AdapterArchive(ArrayList<StructArchive> archives) {
        this.archives = archives;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = App.INFLATER.inflate(R.layout.adapter_archive, parent, false);
        return new ArchiveViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final StructArchive archive = archives.get(position);
        ArchiveViewHolder holder = (ArchiveViewHolder) viewHolder;
        holder.lytRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncQuestDetail()
                        .setOperations(new AsyncQuestDetail.Operations() {
                            DialogProgress dialogProgress = new DialogProgress(App.ACTIVITY, R.style.TransparentDialog);

                            @Override
                            public void before() {
                                dialogProgress.show();
                            }

                            @Override
                            public void after(StructDetail result) {
                                dialogProgress.dismiss();
                                if (result != null) {
                                    App.CURRENT_DETAIL = result;
                                    new DialogDetail(App.ACTIVITY, R.style.TransparentDialog).show();
                                }
                            }
                        })
                        .execute(archive.id, App.CURRENT_USER.id);
            }
        });
        holder.txtOpponent.setText(archive.opponent);
        holder.txtOpponent.setBackgroundResource(archive.result.equals("draw") ? R.drawable.button_light : (archive.result.equals("win") ? R.drawable.button_green : R.drawable.button_red));
        holder.txtScoreOpponent.setText(String.valueOf(archive.score_opponent));
        holder.txtScoreUser.setText(String.valueOf(archive.score_user));
    }

    @Override
    public int getItemCount() {
        return archives.size();
    }

    private static class ArchiveViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView txtScoreOpponent;
        public CustomTextView txtScoreUser;
        public CustomTextViewBold txtOpponent;
        public ViewGroup lytRoot;

        public ArchiveViewHolder(View view) {
            super(view);
            lytRoot = view.findViewById(R.id.lytRoot);
            txtOpponent = view.findViewById(R.id.txtOpponent);
            txtScoreOpponent = view.findViewById(R.id.txtScoreOpponent);
            txtScoreUser = view.findViewById(R.id.txtScoreUser);
        }
    }
}
