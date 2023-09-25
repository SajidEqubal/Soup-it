package com.shadspace.soup_it.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadspace.soup_it.Model.GoodNewsList;
import com.shadspace.soup_it.Model.YourStoryList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Blogs_Read;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class YourStoryAdapter  extends RecyclerView.Adapter<YourStoryAdapter.MyViewHolder> {
    ArrayList<YourStoryList> yourStoryLists = new ArrayList<YourStoryList>();

    Context context;
    Activity activity;
    YourStoryAdapter yourStoryAdapter;
    String url;


    public YourStoryAdapter(Context context, Activity activity, YourStoryAdapter yourStoryAdapter, ArrayList<YourStoryList> yourStoryLists) {


        this.context = context;
        this.activity = activity;
        this.yourStoryAdapter = yourStoryAdapter;
        this.yourStoryLists = yourStoryLists;

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
    public YourStoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_your_story, parent, false);


        return new YourStoryAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(YourStoryAdapter.MyViewHolder holder, final int position) {

        holder.YS_Title.setText("●  "+yourStoryLists.get(position).getTitlee());
        holder.YS_Type.setText(yourStoryLists.get(position).getTypee());
        holder.YS_Story.setText("    "+yourStoryLists.get(position).getStoryy());
        holder.YS_Name.setText(yourStoryLists.get(position).getNamee());
        holder.YS_Phone.setText(yourStoryLists.get(position).getPhonee());
        holder.YS_Date.setText("●  "+yourStoryLists.get(position).getDatee());
        holder.YS_Status.setText("Status : "+yourStoryLists.get(position).getStatuss());




        holder.Lay_Story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(context, Blogs_Read.class);
              //  intent.putExtra("Title", yourStoryLists.get(position).getTitlee());
              //  intent.putExtra("Type", yourStoryLists.get(position).getTypee());
              //  intent.putExtra("Story", yourStoryLists.get(position).getStoryy());
              //  intent.putExtra("Name", yourStoryLists.get(position).getNamee());
              //  intent.putExtra("Phone", yourStoryLists.get(position).getPhonee());
             //   intent.putExtra("Date", yourStoryLists.get(position).getDatee());
             //   intent.putExtra("Status", yourStoryLists.get(position).getStatuss());
             //   activity.startActivity(intent);

                Toast.makeText(context, "Please Wait", Toast.LENGTH_SHORT).show();




            }
        });

    }


    @Override
    public int getItemCount() {
        Log.e("--------------", String.valueOf(yourStoryLists.size()));
        return yourStoryLists.size();






    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView YS_Title;
        TextView YS_Type;
        TextView YS_Story;
        TextView YS_Name;
        TextView YS_Phone;
        TextView YS_Date;
        TextView YS_Status;

        LinearLayout Lay_Story;

        public MyViewHolder(View itemView) {

            super(itemView);

            YS_Title = itemView.findViewById(R.id.YS_Title);
            YS_Type = itemView.findViewById(R.id.YS_Type);
            YS_Story = itemView.findViewById(R.id.YS_Story);
            YS_Name = itemView.findViewById(R.id.YS_Name);
            YS_Phone = itemView.findViewById(R.id.YS_Phone);
            YS_Date = itemView.findViewById(R.id.YS_Date);
            YS_Status = itemView.findViewById(R.id.YS_Status);
            Lay_Story = itemView.findViewById(R.id.Lay_Story);


        }
    }

    public void updateList(ArrayList<YourStoryList> list){
        yourStoryLists = list;
        notifyDataSetChanged();
    }
}


