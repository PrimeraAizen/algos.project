import java.util.ArrayList;
import java.util.List;

//получение k постов с максимальным количеством лайков
class Max_count {
    private ArrayList<String> name_users = new ArrayList<>();
    private ArrayList<Integer> count_likes = new ArrayList<>();
    private ArrayList<String> text_posts = new ArrayList<>();

    public void setName_users(ArrayList<String> name_users) {
        this.name_users = name_users;
    }

    public void setCount_likes(ArrayList<Integer> count_likes) {
        this.count_likes = count_likes;
    }
    public void setText_posts(ArrayList<String> text_posts) {
        this.text_posts = text_posts;
    }
    public ArrayList<String> getName_users() {
        return name_users;
    }

    public ArrayList<Integer> getCount_likes() {
        return count_likes;
    }
    public ArrayList<String> getText_posts() {
        return text_posts;
    }

    public void showPosts(int k){
        for (int i = 0; i < k; i++) {
            System.out.println(i+1 + ". " + name_users.get(i) + " " + text_posts.get(i));
        }
    }

}
