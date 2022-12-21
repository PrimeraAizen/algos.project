import java.util.ArrayList;

class Post {
    private int likes = 0;
    private String text;
    private ArrayList<String> comments = new ArrayList<>();
    private ArrayList<Profile> likedUsers = new ArrayList<>();



    public int getLikes() {
        return likes;
    }

    public ArrayList<Profile> getLikedUsers() {
        return likedUsers;
    }
    public void unlike(Profile profile){
        this.likes -= 1;
        likedUsers.remove(profile);
    }
    public void like(Profile profile) {
        this.likes += 1;
        likedUsers.add(profile);
    }

    public String getText() {
        return text;
    }

    public Post(String text){
        this.text = text;
    }
    public void comment(Profile p, String text){
        comments.add(p.getSurname() + " " + p.getName()+ ": \n"+   text);
    }

    public ArrayList<String> getComments() {
        return comments;
    }
}
