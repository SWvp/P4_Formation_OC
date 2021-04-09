package com.kardabel.mareu.mareu.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> meetings = new ArrayList<>();
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

        Meeting meeting = meetings.get(position);
        holder.meetingDetails.setText(meeting.getMeetingDetails());
        holder.mailingList.setText(meeting.getMailingList());
        Glide.with(holder.roomAvatar.getContext())
                .load(meeting.getRoomAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.roomAvatar);

    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public void setMeetings(List<Meeting> meetings){
        this.meetings = meetings;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView roomAvatar;
        private TextView meetingDetails;
        private TextView mailingList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomAvatar = itemView.findViewById(R.id.room_avatar);
            meetingDetails = itemView.findViewById(R.id.meeting_details);
            mailingList = itemView.findViewById(R.id.mailing_list);
        }
    }

    public interface OnItemClickListener{
        void onMeetingItemClick(Meeting meeting);

    }

    private static class MainDiffCallback extends DiffUtil.ItemCallback<Meeting> {

        @Override
        public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.getMeetingId() == newItem.getMeetingId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.getMeetingDetails().equals(newItem.getMeetingDetails())
                    && oldItem.getMailingList().equals(newItem.getMailingList())
                    && oldItem.getRoomAvatar() == newItem.getRoomAvatar();
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
