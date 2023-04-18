package com.example.themainbuzzlogin2;

public class PostModel {
    private String username;
    private String postPrompt;
    private int postImage;

    public PostModel(String username, String postPrompt, int postImage){
        this.username = username;
        this.postImage = postImage;
        this.postPrompt = postPrompt;
    }

    public String getUsername(){
        return username;
    }

    public String getPostPrompt(){
        return postPrompt;
    }

    public int getPostImage(){
        return postImage;
    }

    public void setPostPrompt(){
        this.postPrompt = postPrompt;
    }

    public void setPostImage(){
        this.postImage = postImage;
    }

}
