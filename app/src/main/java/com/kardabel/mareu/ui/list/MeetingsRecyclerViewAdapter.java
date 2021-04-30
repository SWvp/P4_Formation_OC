package com.kardabel.mareu.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kardabel.mareu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {

    private List<MainViewState> meetings = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_mareu_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsRecyclerViewAdapter.ViewHolder holder, int position) {
        MainViewState meeting = meetings.get(position);



        holder.meetingDetails.setText(meeting.getMeetingDetailsToDisplay());
        holder.mailingList.setText(meeting.getMailingListToDisplay());
        Glide.with(holder.roomAvatar.getContext())
                .load(meeting.getAvatarToDisplay())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.roomAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMeetingItemClick(meeting);

            }
        });

        holder.deleteMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteMeetingClick(meeting.getMeetingId());

            }
        });

    }

    @Override
    public int getItemCount() {
        if (meetings == null){
            return 0;

        }
        else
            return meetings.size();

    }

    public void setMeetings(List<MainViewState> meetings){
        this.meetings = meetings;
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView roomAvatar;
        private TextView meetingDetails;
        private TextView mailingList;
        private ImageButton deleteMeetingButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomAvatar = itemView.findViewById(R.id.room_avatar);
            meetingDetails = itemView.findViewById(R.id.meeting_details);
            mailingList = itemView.findViewById(R.id.mailing_list);
            deleteMeetingButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public interface OnItemClickListener{
        void onMeetingItemClick(MainViewState meeting);
        void onDeleteMeetingClick(int meeting);

    }

// private static class MainDiffCallback extends DiffUtil.ItemCallback<Meeting> {

//     @Override
//     public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
//         return oldItem.getMeetingId() == newItem.getMeetingId();
//     }

//     @Override
//     public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
//         return oldItem.getMeetingDetails().equals(newItem.getMeetingDetails())
//                 && oldItem.getMailingList().equals(newItem.getMailingList())
//                 && oldItem.getRoomAvatar() == newItem.getRoomAvatar();
//     }
// }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
