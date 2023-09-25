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
import com.shadspace.soup_it.Model.PopularBlogList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.activity.Blogs_Read;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class PopularBlogAdapter extends RecyclerView.Adapter<PopularBlogAdapter.MyViewHolder> {

    ArrayList<PopularBlogList> popularBlogLists = new ArrayList<PopularBlogList>();

    Context context;
    Activity activity;
    PopularBlogAdapter popularBlogAdapter;
    String url;


    public PopularBlogAdapter(Context context, Activity activity, PopularBlogAdapter popularBlogAdapter, ArrayList<PopularBlogList> popularBlogLists) {


        this.context = context;
        this.activity = activity;
        this.popularBlogAdapter = popularBlogAdapter;
        this.popularBlogLists = popularBlogLists;

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
    public PopularBlogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_popular_blogs, parent, false);


        return new PopularBlogAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(PopularBlogAdapter.MyViewHolder holder, final int position) {

        holder.PB_Category.setText(popularBlogLists.get(position).getCategory2());
        holder.PB_Title.setText(popularBlogLists.get(position).getTtl2());
        holder.PB_Written.setText(popularBlogLists.get(position).getWrtnby2());
        holder.PB_Date.setText(popularBlogLists.get(position).getTym2());
        holder.PB_Content.setText(popularBlogLists.get(position).getContent2());


        url = popularBlogLists.get(position).getRimg2();


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
        ImageLoadTask obj = new ImageLoadTask(url, holder.PB_Image);
        obj.execute();
        holder.Lay_PopularBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Blogs_Read.class);
                intent.putExtra("Category", popularBlogLists.get(position).getWrtnby2());
                intent.putExtra("Title", popularBlogLists.get(position).getTtl2());
                intent.putExtra("Content", popularBlogLists.get(position).getContent2());
                intent.putExtra("Written", popularBlogLists.get(position).getWrtnby2());
                intent.putExtra("Time", popularBlogLists.get(position).getTym2());
                intent.putExtra("Image", popularBlogLists.get(position).getRimg2());
                activity.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        Log.e("--------------", String.valueOf(popularBlogLists.size()));
        return popularBlogLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView PB_Image;
        TextView PB_Category;
        TextView PB_Title;
        TextView PB_Written;
        TextView PB_Date;
        TextView PB_Content;

        LinearLayout Lay_PopularBlog;

        public MyViewHolder(View itemView) {

            super(itemView);

            PB_Image = itemView.findViewById(R.id.PB_Image);
            PB_Category = itemView.findViewById(R.id.PB_Category);
            PB_Title = itemView.findViewById(R.id.PB_Title);
            PB_Written = itemView.findViewById(R.id.PB_Written);
            PB_Date = itemView.findViewById(R.id.PB_Date);
            PB_Content = itemView.findViewById(R.id.PB_Content);
            Lay_PopularBlog = itemView.findViewById(R.id.Lay_PopularBlog);


        }
    }
}
