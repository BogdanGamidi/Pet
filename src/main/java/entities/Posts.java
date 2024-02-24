package entities;

public class Posts {
    private int id;
    private String contentOfPost;
    private int userId;

    public Posts() {}
    public Posts(int id, String content_of_post, int user_id) {
        this.id = id;
        this.contentOfPost = content_of_post;
        this.userId = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent_of_post() {
        return contentOfPost;
    }

    public void setContent_of_post(String content_of_post) {
        this.contentOfPost = content_of_post;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    @Override
    public String toString() {
        return "Post " + id + ": [" + contentOfPost + "]" + " by User ID " + userId;
    }
}

