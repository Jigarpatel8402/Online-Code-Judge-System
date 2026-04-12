package jsoncmd;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.User;
import model.Problem;

public class JSONUtil {

    private static final String USER_FILE = "users.json";
    private static final String PROBLEM_FILE = "problems.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // READ USERS
    public static List<User> readUsers() {
        try {
            FileReader reader = new FileReader(USER_FILE);

            List<User> users = gson.fromJson(
                reader,
                new TypeToken<List<User>>(){}.getType()
            );

            reader.close();

            return (users != null) ? users : new ArrayList<>();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // WRITE USERS
    public static void writeUsers(List<User> users) {
        try {
            FileWriter writer = new FileWriter(USER_FILE);

            gson.toJson(users, writer);

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving JSON");
        }
    }

    // READ PROBLEMS
    public static List<Problem> readProblems() {
        try {
            FileReader reader = new FileReader(PROBLEM_FILE);

            List<Problem> problems = gson.fromJson(
                reader,
                new TypeToken<List<Problem>>(){}.getType()
            );

            reader.close();

            return (problems != null) ? problems : new ArrayList<>();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // WRITE PROBLEMS
    public static void writeProblems(List<Problem> problems) {
        try {
            FileWriter writer = new FileWriter(PROBLEM_FILE);

            gson.toJson(problems, writer);

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving problems JSON");
        }
    }
}
