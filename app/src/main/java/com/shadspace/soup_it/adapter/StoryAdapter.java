package com.shadspace.soup_it.adapter;

import android.app.Activity;
import android.content.Context;
import com.squareup.picasso.Picasso;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadspace.soup_it.Model.StoryList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Story_Read;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

    public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

        ArrayList<StoryList> single = new ArrayList<StoryList>();

        Context context;
        Activity activity;
        String getLoc;
        StoryAdapter myAdapter;
        String url;


        public StoryAdapter(Context context, Activity activity, String getLoc, StoryAdapter myAdapter, ArrayList<StoryList> single) {


            this.context = context;
            this.activity = activity;
            this.getLoc = getLoc;
            this.myAdapter = myAdapter;
            this.single = single;


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
        public StoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_story, parent, false);


            return new MyViewHolder(v);


        }

        @Override
        public void onBindViewHolder(StoryAdapter.MyViewHolder holder, final int position) {

            holder.SS_Written.setText(single.get(position).getWrtnby());
            holder.SS_Title.setText(single.get(position).getTtl());
            holder.SS_Story.setText(single.get(position).getStry());
            holder.SS_Date.setText(single.get(position).getTym());




            url = single.get(position).getRimg();


            class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
                private String url;
                private ImageView imageView;

                public ImageLoadTask(String url, ImageView imageView) {
                    this.url = url;
                    this.imageView = imageView;
                }

                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        URL connection = new URL(url);
                        InputStream input = connection.openStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 400, 400, true);
                        return resized;
                    } catch (Exception e) {
                        // Toast.makeText(MyAdapter.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    super.onPostExecute(result);
                 //   imageView.setImageBitmap(toGrayscale(result));
                    imageView.setImageBitmap(result);
//                     toGrayscale(result);

                }
            }
            ImageLoadTask obj = new ImageLoadTask(url, holder.SS_Image);
            obj.execute();

            holder.Lay_Story.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    Intent intent = new Intent(context, Story_Read.class);
                    intent.putExtra("Written", single.get(position).getWrtnby());
                    intent.putExtra("Title", single.get(position).getTtl());
                    intent.putExtra("Story", single.get(position).getStry());
                    intent.putExtra("Time", single.get(position).getTym());
                    intent.putExtra("Image", single.get(position).getRimg());
                    activity.startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {
            Log.e("--------------", String.valueOf(single.size()));
            return single.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView SS_Image;
            TextView SS_Title;
            TextView SS_Written;
            TextView SS_Date;
            TextView SS_Story;

            LinearLayout Lay_Story;

            public MyViewHolder(View itemView) {

                super(itemView);

                SS_Image = itemView.findViewById(R.id.SS_Image);
                SS_Title = itemView.findViewById(R.id.SS_Title);
                SS_Written = itemView.findViewById(R.id.SS_Written);
                SS_Date = itemView.findViewById(R.id.SS_Date);
                SS_Story = itemView.findViewById(R.id.SS_Story);
                Lay_Story = itemView.findViewById(R.id.Lay_Story);


            }
        }
    }




