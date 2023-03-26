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

    /* Sort posts by number of likes using insertion sort*/
    public void sortPostsInsertion(){
        for (int i = 1; i < posts.size(); i++) {
            Post key = posts.get(i);
            int j = i - 1;
            while(j >= 0 && posts.get(j).getLikes() < key.getLikes()){
                posts.set(j + 1, posts.get(j));
                j--;
            }
            posts.set(j + 1, key);
        }
    }
    /* Sort posts by number of likes using selection sort*/
    public void sortPostsSelection(){
        for (int i = 0; i < posts.size(); i++) {
            int max = i;
            for (int j = i + 1; j < posts.size(); j++) {
                if(posts.get(j).getLikes() > posts.get(max).getLikes()){
                    max = j;
                }
            }
            Post temp = posts.get(i);
            posts.set(i, posts.get(max));
            posts.set(max, temp);
        }
    }
    /* Sort posts by number of likes using bubble sort*/
    public void sortPostsBubble(){
        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < posts.size() - 1; j++) {
                if(posts.get(j).getLikes() < posts.get(j + 1).getLikes()){
                    Post temp = posts.get(j);
                    posts.set(j, posts.get(j + 1));
                    posts.set(j + 1, temp);
                }
            }
        }
    }
    /* Sort posts by number of likes using merge sort*/
    public void sortPostsMerge(){
        posts = mergeSort(posts);
    }
    private ArrayList<Post> mergeSort(ArrayList<Post> posts){
        if(posts.size() == 1){
            return posts;
        }
        ArrayList<Post> left = new ArrayList<>();
        ArrayList<Post> right = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            if(i < posts.size() / 2){
                left.add(posts.get(i));
            }
            else{
                right.add(posts.get(i));
            }
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }
    private ArrayList<Post> merge(ArrayList<Post> left, ArrayList<Post> right){
        ArrayList<Post> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        while(leftIndex < left.size() && rightIndex < right.size()){
            if(left.get(leftIndex).getLikes() > right.get(rightIndex).getLikes()){
                result.add(left.get(leftIndex));
                leftIndex++;
            }
            else{
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        while(leftIndex < left.size()){
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        while(rightIndex < right.size()){
            result.add(right.get(rightIndex));
            rightIndex++;
        }
        return result;
    }
    /* Sort posts by number of likes using quick sort*/
    public void sortPostsQuick(){
        posts = quickSort(posts, 0, posts.size() - 1);
    }
    private ArrayList<Post> quickSort(ArrayList<Post> posts, int left, int right){
        if(left < right){
            int pivot = partition(posts, left, right);
            quickSort(posts, left, pivot - 1);
            quickSort(posts, pivot + 1, right);
        }
        return posts;
    }
    private int partition(ArrayList<Post> posts, int left, int right){
        int pivot = posts.get(right).getLikes();
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if(posts.get(j).getLikes() > pivot){
                i++;
                Post temp = posts.get(i);
                posts.set(i, posts.get(j));
                posts.set(j, temp);
            }
        }
        Post temp = posts.get(i + 1);
        posts.set(i + 1, posts.get(right));
        posts.set(right, temp);
        return i + 1;
    }
}
