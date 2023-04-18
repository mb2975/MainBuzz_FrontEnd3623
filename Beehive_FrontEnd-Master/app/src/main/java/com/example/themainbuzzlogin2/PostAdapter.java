package com.example.themainbuzzlogin2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<PostModel> postModelArrayList;



    public PostAdapter(Context context, ArrayList<PostModel> postModelArrayList) {

        this.context = context;
        this.postModelArrayList = postModelArrayList;
    }

    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
    PostModel model = postModelArrayList.get(position);
    holder.username.setText(model.getUsername());
    holder.postPrompt.setText(model.getPostPrompt());
    holder.postImage.setImageResource(model.getPostImage());
    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView postPrompt;
        private final ImageView postImage;

        boolean isImageFitToScreen = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.Post_Picture);
            username = itemView.findViewById(R.id.Poster_Account);
            postPrompt = itemView.findViewById(R.id.Poster_Prompt);


            //VERY IN PROGRESS FULLSCREEN PHOTO, CURRENTLY DOESN"T WORK, MAY SCRAP LATER - JG
            postImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isImageFitToScreen) {
                    isImageFitToScreen = true;
                    postImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    postImage.setAdjustViewBounds(true);
                }
                else{
                    isImageFitToScreen = false;
                        Log.d("m", "Help");
                    postImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    postImage.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                }
            });
        }
    }
}
