package com.example.workouttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttest.fragments.RecycleViewClickInterface;
import com.example.workouttest.model.Workouts;

import java.util.List;

public class WorkoutsListAdapter extends  RecyclerView.Adapter<WorkoutsListAdapter.ViewHolder>  {

    private List<Workouts> workouts;
    private RecycleViewClickInterface recycleViewClickInterface;


    public WorkoutsListAdapter(List<Workouts> workouts, RecycleViewClickInterface recycleViewClickInterface ) {
        this.workouts =workouts;
        this.recycleViewClickInterface = recycleViewClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.frame_textview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
//        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(this.workouts.get(position).getWorkoutName());
    }

    @Override
    public int getItemCount() {
        return  this.workouts != null ? this.workouts.size() : 0;
    }



//    @NonNull
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
//        return new RecyclerViewHolder(view);
//    }




//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        holder.getView().setText(this.workouts.get(position).getWorkoutName());
//    }



    class  ViewHolder  extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleViewClickInterface.OnItemClick(getAdapterPosition());

                    System.out.println(" lotfi lotfi lotfi ");
                }
            });


        }
    }

}