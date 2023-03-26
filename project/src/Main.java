import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Profile currentProfile;
    static ArrayList<Profile> profiles = new ArrayList<>();
    static ArrayList<Max_count> maxCounts = new ArrayList<>();

    public static void main(String[] args) {
        defaultUsers();
        System.out.println("Welcome to the Social Network!");
        loginPage();
    }

    public static void defaultUsers() {
        Profile default1 = new Profile("Ace", "Portgas D.", 19, "Male", "d_ace@sdu.kz", "+111", "Gold");
        Profile default2 = new Profile("Luffy", "Monkey D.", 19, "Male", "luffy@sdu.kz", "+222", "pirateking");
        Profile default3 = new Profile("IIIedry", "King", 19, "Male", "IIIedry1203@sdu.kz", "+333", "IIIedry");
        Profile default4 = new Profile("Natsu", "Dragnyl", 19, "Male", "luffy@sdu.kz", "+444", "fire");
        default1.post("My name is Portgas D. Ace, the second commander of Whitebeard Pirates.");
        default1.post("My father is the king of pirate Gol D. Roger");
        default2.post("I'm Monkey D. Luffy. The guy who will become the king of the pirates.");
        default2.post("I'm the captain of Strawhat Pirates");
        default3.post("My name is Iliyas. I'm a student of SDU. I'm 19 years old.");
        default4.post("I'm Natsu Dragnyl. The guy who will become the king of the fire dragons.");
        profiles.add(default1);
        profiles.add(default2);
        profiles.add(default3);
        profiles.add(default4);


    }

    public static void loginPage() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Are you a new user? (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                isRunning = false;
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                System.out.println("Please enter your surname:");
                String surname = scanner.nextLine();
                System.out.println("Please enter your age:");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter your email:");
                String email = scanner.nextLine();
                System.out.println("Please enter your gender:");
                String gender = scanner.nextLine();
                System.out.println("Please enter your phone number:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                Profile profile1 = new Profile(name, surname, age, gender, email, phoneNumber, password);
                profiles.add(profile1);
                currentProfile = profile1;
                profilePage(currentProfile);
            } else if (answer.equalsIgnoreCase("no")) {
                isRunning = false;
                boolean check = true;
                while (check) {
                    System.out.println("Please, enter your email:");
                    String email = scanner.nextLine();
                    for (int i = 0; i < profiles.size(); i++) {
                        if (email.equals(profiles.get(i).getEmail())) {
                            System.out.println("Please, enter your password:");
                            String password = scanner.nextLine();
                            if (password.equals(profiles.get(i).getPassword())) {
                                currentProfile = profiles.get(i);
                                check = false;
                                profilePage(currentProfile);
                            } else {
                                System.out.println("Wrong password!");
                                System.out.println("Please, try again!");
                                System.out.println();
                            }
                        }
                    }
                }
            } else {
                System.out.println("Please, enter yes or no");
            }
        }
    }

    public static void profilePage(Profile profile) {
        System.out.println("What do you want to do?");
        System.out.println("0. Log out");
        System.out.println("1. Create a post");
        System.out.println("2. Show my posts");
        System.out.println("3. Show my profile");
        System.out.println("4. Show list of users");
        System.out.println("5. Show list of maximum like posts");
        System.out.println("6. Show list of Descending like posts");
        System.out.println("7. Show list of highest Common subscribers");
        System.out.println("8. Sort posts by likes");
        int answer = scanner.nextInt();
        scanner.nextLine();
        switch (answer) {
            case 1 -> {
                System.out.println("Please, enter your post:");
                String text = scanner.nextLine();
                profile.post(text);
                profilePage(profile);
            }
            case 2 -> {
                profile.showPosts();
                profilePage(profile);
            }
            case 3 -> {
                profile.showProfile();
                profilePage(profile);
            }
            case 4 -> {
                showUsers(profile);
            }
            case 5 -> {
                showMaxLikePosts();
                profilePage(profile);
            }
            case 6 -> {
                showDescendingLikePosts();
                profilePage(profile);
            }
            case 7 -> {
                showMaxCommonSubscribers();
                profilePage(profile);
            }
            case 8 -> {
                profile.sortPostsMerge();
                profilePage(profile);
            }
            case 0 -> loginPage();

        }

    }

    public static void showUsers(Profile profile1) {
        System.out.println("Please, enter the number of the user whose profile you want to see:");
        System.out.println("0. Go back");
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).equals(currentProfile)) {
                continue;
            } else {
                System.out.println((i + 1) + ". " + profiles.get(i).getSurname() + " " + profiles.get(i).getName());
            }
        }
        int answer = scanner.nextInt();
        scanner.nextLine();
        if (answer == 0) profilePage(currentProfile);
        if (profiles.get(answer - 1).getBlockedUsers().contains(profile1)) {
            System.out.println("You have no access to see this users profile!");
            showUsers(profile1);
        } else {
            profiles.get(answer - 1).showProfile();
        }

        System.out.println("What do you want to do?");
        System.out.println("0. Go back");
        if (profiles.get(answer - 1).getFollowers().contains(currentProfile)) {
            System.out.println("1. Unfollow");
        } else {
            System.out.println("1. Follow");
        }
        System.out.println("2. Show posts");
        System.out.println("3. Show followers");
        if (profile1.getBlockedUsers().contains(profiles.get(answer - 1))) {
            System.out.println("4. Unlock user");
        } else {
            System.out.println("4. Block user");
        }
        int answer2 = scanner.nextInt();
        scanner.nextLine();
        switch (answer2) {
            case 1 -> {
                if (profiles.get(answer - 1).getFollowers().contains(currentProfile)) {
                    for (Profile profile : profiles) {
                        if (profile.getEmail().equals(profiles.get(answer - 1).getEmail())) {
                            profile.getFollowers().remove(currentProfile);
                        }
                    }
                } else {
                    for (Profile profile : profiles) {
                        if (profile.getEmail().equals(profiles.get(answer - 1).getEmail())) {
                            profile.getFollowers().add(currentProfile);
                        }
                    }
                }
                showUsers(profile1);
            }
            case 2 -> {
                showPost(profiles.get(answer - 1));
                showUsers(profile1);
            }
            case 3 -> {
                profiles.get(answer - 1).showFollowers();
                showUsers(profile1);
            }
            case 4 -> {
                if (profile1.getBlockedUsers().contains(profiles.get(answer - 1))) {
                    profile1.unlockUser(profiles.get(answer - 1));
                } else {
                    profile1.blockUser(profiles.get(answer - 1));
                }
                showUsers(profile1);
            }
            case 0 -> profilePage(profiles.get(answer - 1));
        }
    }

    public static void showPost(Profile profile) {
        System.out.println("Enter the number of post which you'd like see: ");
        System.out.println("0. Go Back");
        profile.showPosts();
        int answer = scanner.nextInt();
        if (answer == 0) showUsers(currentProfile);
        System.out.println(profile.getPosts().get(answer - 1).getText());
        System.out.println(profile.getPosts().get(answer - 1).getLikes() + " likes");
        System.out.println(profile.getPosts().get(answer - 1).getComments().size() + " comments");
        System.out.println("What do you want to do?");
        System.out.println("0. Go back");
        if (profile.getPosts().get(answer - 1).getLikedUsers().contains(currentProfile)) {
            System.out.println("1. Unlike");
        } else {
            System.out.println("1. Like");
        }
        System.out.println("2. Comment");
        System.out.println("3. See comments");
        int answer2 = scanner.nextInt();
        switch (answer2) {
            case 0 -> showPost(profile);
            case 1 -> {
                if (profile.getPosts().get(answer - 1).getLikedUsers().contains(currentProfile)) {
                    profile.getPosts().get(answer - 1).unlike(currentProfile);
                } else {
                    profile.getPosts().get(answer - 1).like(currentProfile);
                }
                showPost(profile);
            }
            case 2 -> {
                System.out.println("Enter your comment text: ");
                scanner.nextLine();
                String comment = scanner.nextLine();
                profile.getPosts().get(answer - 1).comment(currentProfile, comment);
                showPost(profile);
            }
            case 3 -> {
                for (int i = 0; i < profile.getPosts().get(answer - 1).getComments().size(); i++) {
                    System.out.print(profile.getPosts().get(answer - 1).getComments().get(i));
                    System.out.println();
                }
                showPost(profile);
            }
        }
    }

    public static void showDescendingLikePosts() {
System.out.println("Enter the number of place: ");
        System.out.println("0. Go Back");
        System.out.println("1. Show posts with max and descending likes");
        int answer = scanner.nextInt();
        scanner.nextLine();
        if (answer == 0) profilePage(currentProfile);
        int max = 0;
        for (Profile profile : profiles) {
            for (Post post : profile.getPosts()) {
                if (post.getLikes() > max) {
                    max = post.getLikes();
                }
            }
        }
        int count = 0;
        for (Profile profile : profiles) {
            for (Post post : profile.getPosts()) {
                if (post.getLikes() <= max) {
                    count++;
                }
            }
        }
        Post[] posts = new Post[count];
        int i = 0;
        for (Profile profile : profiles) {
            for (Post post : profile.getPosts()) {
                if (post.getLikes() <= max) {
                    posts[i] = post;
                    i++;
                }
            }
        }
        posts = sort(posts);
        for (int j = 0; j < posts.length; j++) {
            System.out.println(j + 1 + ". " + posts[j].getText());
            }
        showDescendingLikePosts();
    }
    private static Post[] sort(Post[] posts) {
        for (int i = 0; i < posts.length; i++) {
            for (int j = 0; j < posts.length - 1; j++) {
                if (posts[j].getLikes() < posts[j + 1].getLikes()) {
                    Post temp = posts[j];
                    posts[j] = posts[j + 1];
                    posts[j + 1] = temp;
                }
            }
        }
        return posts;
    }
    private static void showMaxLikePosts(){
        boolean flag = true;
        while(flag) {
            System.out.println("Enter the number of place: ");
            System.out.println("0. Go Back");
            System.out.println("1. Show posts with max likes");
            int answer = scanner.nextInt();
            if (answer == 0 || answer == 1) {
                scanner.nextLine();
                if (answer == 0) profilePage(currentProfile);
                int max = 0;
                for (Profile profile : profiles) {
                    for (Post post : profile.getPosts()) {
                        if (post.getLikes() > max) {
                            max = post.getLikes();
                        }
                    }
                }
                for (Profile profile : profiles) {
                    for (Post post : profile.getPosts()) {
                        if (post.getLikes() == max) {
                            System.out.println(post.getText());
                        }
                    }
                }

                showMaxLikePosts();
                flag = false;
            } else {
                System.out.println("Enter a message from the selection");
                System.out.println();
            }
        }
    }
    private static void showMaxCommonSubscribers() {
        boolean flag = true;
        while(flag) {
            System.out.println("Enter the number of place: ");
            System.out.println("0. Go Back");
            System.out.println("1. Show users with max common subscribers");
            int answer = scanner.nextInt();
            if (answer == 0 || answer == 1) {
                scanner.nextLine();
                if (answer == 0) profilePage(currentProfile);
                int max = 0;
                for (Profile profile : profiles) {
                    if (currentProfile.getFollowers().contains(profile)) {
                        for (Profile profile1 : profiles) {
                            if (profile.getFollowers().contains(profile1)) {
                                if (currentProfile.getFollowers().contains(profile1)) {
                                    max++;
                                }
                            }
                        }
                    }
                }
                if (max == 0) {
                    System.out.println("You don't have common subscribers");
                } else {
                    for (Profile profile : profiles) {
                        if (currentProfile.getFollowers().contains(profile)) {
                            for (Profile profile1 : profiles) {
                                if (profile.getFollowers().contains(profile1)) {
                                    if (currentProfile.getFollowers().contains(profile1)) {
                                        System.out.println(profile1.getName());
                                    }
                                }
                            }
                        }
                    }
                }
                showMaxCommonSubscribers();
                flag = false;
            } else {
                System.out.println("Enter a message from the selection");
                System.out.println();
            }
        }
    }

}