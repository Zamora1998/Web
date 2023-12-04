package com.example.web.model;

// ActorAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.web.R;
import com.example.web.model.ActorStaff;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private List<ActorStaff> actoresList;

    public ActorAdapter(List<ActorStaff> actoresList) {
        this.actoresList = actoresList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        ActorStaff actor = actoresList.get(position);
        holder.textViewNombreActor.setText(actor.getNombreActor());
        holder.textViewRol.setText(actor.getRol());
    }

    @Override
    public int getItemCount() {
        return actoresList.size();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombreActor;
        TextView textViewRol;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreActor = itemView.findViewById(R.id.textViewNombreActor);
            textViewRol = itemView.findViewById(R.id.textViewRol);
        }
    }
}
