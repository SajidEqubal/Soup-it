package com.shadspace.soup_it.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadspace.soup_it.Model.EventList;
import com.shadspace.soup_it.Model.StoryList;
import com.shadspace.soup_it.Model.YourStoryList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Events;
import com.shadspace.soup_it.activity.EventsDetails;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    ArrayList<EventList> eventLists = new ArrayList<EventList>();

    Context context;
    Activity activity;
    EventAdapter eventAdapter;
    String url;

    public EventAdapter(Context context, Activity activity, EventAdapter eventAdapter, ArrayList<EventList> eventLists) {


        this.context = context;
        this.activity = activity;
        this.eventAdapter = eventAdapter;
        this.eventLists = eventLists;


    }

    public static Bitmap toGrayscale(Bitmap srcImage) {

        Bitmap bmpGrayscale = Bitmap.createBitmap(srcImage.getWidth(), srcImage.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpGrayscale);
        Paint paint = new Paint();

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcImage, 0, 0, paint);

        return bmpGrayscale;
    }

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_events, parent, false);


        return new EventAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(EventAdapter.MyViewHolder holder, final int position) {

        holder.E_Title.setText(eventLists.get(position).getEventTitle());
        holder.E_Topic.setText(eventLists.get(position).getEventTopic());
        holder.E_Sdate.setText(eventLists.get(position).getEventStartDate());
        holder.E_Ldate.setText(eventLists.get(position).getEventLastDate());
        holder.E_Fee.setText(eventLists.get(position).getEventFee());
        holder.E_Venue.setText(eventLists.get(position).getEventVenue());
        holder.E_Video.setText(eventLists.get(position).getEventVideo());


        holder.Lay_Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventsDetails.class);
                intent.putExtra("Title", eventLists.get(position).getEventTitle());
                intent.putExtra("Topic", eventLists.get(position).getEventTopic());
                intent.putExtra("Sdate", eventLists.get(position).getEventStartDate());
                intent.putExtra("Ldate", eventLists.get(position).getEventLastDate());
                intent.putExtra("Fee", eventLists.get(position).getEventFee());
                intent.putExtra("Venue", eventLists.get(position).getEventVenue());
                intent.putExtra("Video", eventLists.get(position).getEventVideo());
                intent.putExtra("Available", eventLists.get(position).getEventAvailabe());
                activity.startActivity(intent);


            }
        });

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventsDetails.class);
                intent.putExtra("Title", eventLists.get(position).getEventTitle());
                intent.putExtra("Topic", eventLists.get(position).getEventTopic());
                intent.putExtra("Sdate", eventLists.get(position).getEventStartDate());
                intent.putExtra("Ldate", eventLists.get(position).getEventLastDate());
                intent.putExtra("Fee", eventLists.get(position).getEventFee());
                intent.putExtra("Venue", eventLists.get(position).getEventVenue());
                intent.putExtra("Video", eventLists.get(position).getEventVideo());
                intent.putExtra("Available", eventLists.get(position).getEventAvailabe());
                activity.startActivity(intent);


            }
        });


    }


    @Override
    public int getItemCount() {
        Log.e("--------------", String.valueOf(eventLists.size()));
        return eventLists.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView E_Title;
        TextView E_Topic;
        TextView E_Sdate;
        TextView E_Ldate;
        TextView E_Fee;
        TextView E_Venue;
        TextView E_Video;
        TextView E_Available;
        Button details;

        LinearLayout Lay_Events;

        public MyViewHolder(View itemView) {

            super(itemView);

            E_Title = itemView.findViewById(R.id.E_Title);
            E_Topic = itemView.findViewById(R.id.E_Topic);
            E_Sdate = itemView.findViewById(R.id.E_Sdate);
            E_Ldate = itemView.findViewById(R.id.E_Ldate);
            E_Fee = itemView.findViewById(R.id.E_Fee);
            E_Venue = itemView.findViewById(R.id.E_Venue);
            E_Video = itemView.findViewById(R.id.E_Video);
            E_Available = itemView.findViewById(R.id.E_Available);
            details = itemView.findViewById(R.id.details);

            Lay_Events = itemView.findViewById(R.id.Lay_Events);


        }
    }

    public void updateList(ArrayList<EventList> list) {
        eventLists = list;
        notifyDataSetChanged();
    }


}