import java.util.ArrayList;

class Profile {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String gender;
    private final String phoneNumber;

    private final String password;
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Profile> followers = new ArrayList<>();
    private ArrayList<Profile> blackList = new ArrayList<>();

    public ArrayList<Profile> getBlockedUsers(){
        return blackList;
    }

    public void blockUser(Profile profile){
        blackList.add(profile);
    }
    public void unlockUser(Profile profile){
        blackList.remove(profile);
    }
    public void showPosts(){
        for (int i = 0; i < posts.size(); i++) {
            if(posts.get(i).getText().length() < 20){
                System.out.println((i + 1) + ". " + posts.get(i).getText().substring(0,5) + "...");
            }
            else{
                System.out.println((i + 1) + ". " + posts.get(i).getText().substring(0,20) + "...");
            }
        }
    }

    public void showProfile(){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println(followers.size() + " followers");
        System.out.println(posts.size() + " posts");
    }
    public void showFollowers(){
        for(Profile follower: followers){
            System.out.println(follower.getName() + " " + follower.getSurname());
        }
    }
    public ArrayList<Profile> getFollowers(){
        return followers;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile(String name, String surname, int age, String gender, String email,  String phoneNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    
    public void post(String text) {
        Post post = new Post(text);
        posts.add(post);
    }


}
