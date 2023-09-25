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

import com.shadspace.soup_it.Model.BlogList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Blogs_Read;
import com.shadspace.soup_it.activity.Story_Read;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    ArrayList<BlogList> blogs = new ArrayList<BlogList>();

    Context context;
    Activity activity;
    String getLoc;
    BlogAdapter myAdapter1;
    String url;


    public BlogAdapter(Context context, Activity activity, String getLoc, BlogAdapter myAdapter1, ArrayList<BlogList> blogs) {


        this.context = context;
        this.activity = activity;
        this.getLoc = getLoc;
        this.myAdapter1 = myAdapter1;
        this.blogs = blogs;


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
    public BlogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_blogs, parent, false);


        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(BlogAdapter.MyViewHolder holder, final int position) {

        holder.BB_Category.setText(blogs.get(position).getCategory1());
        holder.BB_Title.setText(blogs.get(position).getTtl1());
        holder.BB_Written.setText(blogs.get(position).getWrtnby1());
        holder.BB_Date.setText(blogs.get(position).getTym1());
        holder.BB_Content.setText(blogs.get(position).getContent1());





        url = blogs.get(position).getRimg1();


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
        ImageLoadTask obj = new ImageLoadTask(url, holder.BB_Image);
        obj.execute();

        holder.Lay_Blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Blogs_Read.class);
                intent.putExtra("Category", blogs.get(position).getCategory1());
                intent.putExtra("Title", blogs.get(position).getTtl1());
                intent.putExtra("Content", blogs.get(position).getContent1());
                intent.putExtra("Written", blogs.get(position).getWrtnby1());
                intent.putExtra("Time", blogs.get(position).getTym1());
                intent.putExtra("Image", blogs.get(position).getRimg1());
                activity.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        Log.e("--------------", String.valueOf(blogs.size()));
        return blogs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView BB_Image;
        TextView BB_Category;
        TextView BB_Title;
        TextView BB_Written;
        TextView BB_Date;
        TextView BB_Content;

        LinearLayout Lay_Blog;

        public MyViewHolder(View itemView) {

            super(itemView);

            BB_Image = itemView.findViewById(R.id.BB_Image);
            BB_Category = itemView.findViewById(R.id.BB_Category);
            BB_Title = itemView.findViewById(R.id.BB_Title);
            BB_Written = itemView.findViewById(R.id.BB_Written);
            BB_Date = itemView.findViewById(R.id.BB_Date);
            BB_Content = itemView.findViewById(R.id.BB_Content);
            Lay_Blog = itemView.findViewById(R.id.Lay_Blog);


        }
    }
}

