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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadspace.soup_it.Model.GoodNewsList;
import com.shadspace.soup_it.Model.PopularBlogList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Blogs_Read;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class GoodNewsAdapter   extends RecyclerView.Adapter<GoodNewsAdapter.MyViewHolder>{
    ArrayList<GoodNewsList> goodNewsLists = new ArrayList<GoodNewsList>();

    Context context;
    Activity activity;
    GoodNewsAdapter goodNewsAdapter;
    String url;


    public GoodNewsAdapter(Context context, Activity activity, GoodNewsAdapter goodNewsAdapter, ArrayList<GoodNewsList> goodNewsLists) {


        this.context = context;
        this.activity = activity;
        this.goodNewsAdapter = goodNewsAdapter;
        this.goodNewsLists = goodNewsLists;

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
    public GoodNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_goodnews, parent, false);


        return new GoodNewsAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(GoodNewsAdapter.MyViewHolder holder, final int position) {

        holder.GN_Category.setText(goodNewsLists.get(position).getCategory3());
        holder.GN_Title.setText(goodNewsLists.get(position).getTtl3());
        holder.GN_Written.setText(goodNewsLists.get(position).getWrtnby3());
        holder.GN_Date.setText(goodNewsLists.get(position).getTym3());
        holder.GN_Content.setText(goodNewsLists.get(position).getContent3());


        url = goodNewsLists.get(position).getRimg3();


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
                    // Toast.makeText(myAdapter1.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
        ImageLoadTask obj = new ImageLoadTask(url, holder.GN_Image);
        obj.execute();
        holder.Lay_GoodNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Blogs_Read.class);
                intent.putExtra("Category", goodNewsLists.get(position).getWrtnby3());
                intent.putExtra("Title", goodNewsLists.get(position).getTtl3());
                intent.putExtra("Content", goodNewsLists.get(position).getContent3());
                intent.putExtra("Written", goodNewsLists.get(position).getWrtnby3());
                intent.putExtra("Time", goodNewsLists.get(position).getTym3());
                intent.putExtra("Image", goodNewsLists.get(position).getRimg3());
                activity.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        Log.e("--------------", String.valueOf(goodNewsLists.size()));
        return goodNewsLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView GN_Image;
        TextView GN_Category;
        TextView GN_Title;
        TextView GN_Written;
        TextView GN_Date;
        TextView GN_Content;

        LinearLayout Lay_GoodNews;

        public MyViewHolder(View itemView) {

            super(itemView);

            GN_Image = itemView.findViewById(R.id.GN_Image);
            GN_Category = itemView.findViewById(R.id.GN_Category);
            GN_Title = itemView.findViewById(R.id.GN_Title);
            GN_Written = itemView.findViewById(R.id.GN_Written);
            GN_Date = itemView.findViewById(R.id.GN_Date);
            GN_Content = itemView.findViewById(R.id.GN_Content);
            Lay_GoodNews = itemView.findViewById(R.id.Lay_GoodNews);


        }
    }

    public void updateList(ArrayList<GoodNewsList> list){
        goodNewsLists = list;
        notifyDataSetChanged();
    }
}

