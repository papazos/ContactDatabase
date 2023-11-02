package com.example.logbook3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textName.setText("Name: " + user.getName());
        holder.textDob.setText("DOB: " + user.getDob());
        holder.textEmail.setText("Email: " + user.getEmail());
        int avatarResource = getAvatarResource(user.getAvatar());
        holder.avatarImage.setImageResource(avatarResource);
    }

    private int getAvatarResource(int avatar) {
        switch (avatar) {
            case 1:
                return R.drawable.avt1;
            case 2:
                return R.drawable.avt2;
            case 3:
                return R.drawable.avt3;
            case 4:
                return R.drawable.avt4;
            case 5:
                return R.drawable.avt5;

            default:
                return R.drawable.default_avatar; // Set a default image resource
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textDob;
        TextView textEmail;
        ImageView avatarImage;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textDob = itemView.findViewById(R.id.textDob);
            textEmail = itemView.findViewById(R.id.textEmail);
            avatarImage = itemView.findViewById(R.id.avatarImage);
        }
    }
}