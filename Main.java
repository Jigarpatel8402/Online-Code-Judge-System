import java.util.Arrays;
import java.util.*;
import model.User;
import model.Problem;
import model.TestCase;
import model.Submission;
import jsoncmd.JSONUtil;

public class Main 
{
    public static void main(String args[])
    {
        System.out.println("== Welcome to Online Code Judge ==");

        Scanner sc = new Scanner(System.in);

        User Admin = new User("admin", "admin123");

        User currentUser = null;
        String userName = null;
        List<User> users = JSONUtil.readUsers();
        List<Problem> problems = JSONUtil.readProblems();
        Evaluator evaluator = new BasicEvaluator();

        while(true)
        {
            if (currentUser != null)
            {
                userName = currentUser.getUsername();
                System.out.print("\u001B[32mCodeJudge/\u001B[0m");
                System.out.print("\u001B[33m@" + userName + "\u001B[0m");
                System.out.print("\u001B[32m>\u001B[0m ");
                System.out.flush();
            }
            else {
                System.out.print("\u001B[32mCodeJudge>\u001B[0m ");
                System.out.flush();
            }

            String command = sc.nextLine();

            if (command.equals(""))
            {
                continue;
            }

            String[] cmd = command.split(" ");

            switch (cmd[0]) 
            {
                case "exit":
                    if (currentUser != null) {
                        System.err.println("Please, logout before exit!");
                    }
                    else {
                        System.out.println("See You Next Time...");
                        return;
                    }
                    break;
                
                case "cls":
                case "clear": 
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } 
                    catch (Exception e) {
                        System.out.println("[Error] clearing screen");
                        continue;
                    }
                    break;
                
                case "allusers":
                    if (cmd.length < 2) 
                    {
                        System.out.println("[Error] Invalid syntax");
                        System.out.println("Usage: allusers AdminPassword");
                        continue;
                    }
                    if (Admin.checkPassword(cmd[1]))
                    {
                        System.out.print("All Users: ");
                        for (User u: users)
                        {
                            System.out.print(u.getUsername() + " ");
                        }
                        System.out.println();
                    }
                    else {
                        System.out.println("[Error] Wrong Password");
                    }
                    break;
                
                case "who":
                    if (currentUser == null)
                    {
                        System.out.println("[Error] No user logged in");
                        System.out.println("Usage: login username password");
                    }
                    else {
                        System.out.println(currentUser.getUsername());
                    }
                    break;
                
                case "newuser":
                    if (cmd.length < 3)
                    {
                        System.out.println("[Error] Invalid syntax");
                        System.out.println("Usage: newuser username password");
                        break;
                    }
                    boolean userExists = false;
                    for (User u: users)
                    {
                        if (u.getUsername().equals(cmd[1]))
                        {
                            userExists = true;
                            System.out.println("[Error] User already exist");
                            System.out.println("Usage: login username password");
                            break;
                        }
                    }
                    if (!userExists)
                    {
                        String newUsername = cmd[1];
                        String newPassword = cmd[2];
                        User newUser = new User(newUsername, newPassword);
                        users.add(newUser);
                        JSONUtil.writeUsers(users);
                        currentUser = newUser;
                        System.out.println("Welcome to CodeJudge, " + currentUser.getUsername() + "!");
                        users = JSONUtil.readUsers();
                    }
                    break;
                
                case "login":
                    if (currentUser != null) {
                        System.out.println("[Error] User is already logged in. Logout first");
                        System.out.println("Usage: logout");
                        continue;
                    }
                    if (cmd.length < 3)
                    {
                        System.out.println("[Error] Invalid syntax");
                        System.out.println("Usage: login username password");
                        break;
                    }
                    boolean exist = false;
                    for (User u: users) 
                    {
                        if (u.getUsername().equals(cmd[1]))
                        {
                            exist = true;
                            if (u.checkPassword(cmd[2]) == true)
                            {
                                currentUser = u;
                                System.out.println("Welcome " + currentUser.getUsername() + "!");
                            }
                            else {
                                System.out.println("[Error] Password is INCORRECT or NULL");
                                System.out.println("Usage: login username password");
                            }
                            break;
                        }
                    }
                    if (exist == false)
                    {
                        System.out.println("[Error] user not found");
                        System.out.println("Usage: newuser username password");
                        continue;
                    }
                    break;
                
                case "logout":
                    if (currentUser == null)
                    {
                        System.out.println("[Error] No user logged in");
                        break;
                    }
                    JSONUtil.writeUsers(users);
                    currentUser = null;
                    System.out.println("Logout Successfully");
                    break;

                case "problems":
                    if (problems.isEmpty())
                    {
                        System.out.println("No problems available.");
                        break;
                    }
                    System.out.println("\n== Problem List ==");
                    for (Problem p : problems)
                    {
                        System.out.println("  " + p.toString());
                    }
                    System.out.println();
                    break;

                case "problem":
                    if (cmd.length < 2)
                    {
                        System.out.println("[Error] Enter problem ID");
                        System.out.println("Usage: problem <id>");
                        break;
                    }
                    try 
                    {
                        int pid = Integer.parseInt(cmd[1]);
                        Problem found = null;
                        for (Problem p : problems)
                        {
                            if (p.getId() == pid)
                            {
                                found = p;
                                break;
                            }
                        }
                        if (found == null)
                        {
                            System.out.println("[Error] Problem not found with ID: " + pid);
                        }
                        else 
                        {
                            System.out.println("\n== " + found.getTitle() + " ==");
                            System.out.println("Difficulty: " + found.getDifficulty());
                            System.out.println("Description: " + found.getDescription());
                            System.out.println("Test Cases:");
                            List<TestCase> tcs = found.getTestCases();
                            for (int i = 0; i < tcs.size(); i++)
                            {
                                System.out.println("  Case " + (i + 1) + " - Input: " + tcs.get(i).getInput() + ", Expected: " + tcs.get(i).getExpectedOutput());
                            }
                            System.out.println();
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("[Error] Invalid ID. Use a number.");
                    }
                    break;

                case "submit":
                    if (currentUser == null)
                    {
                        System.out.println("[Error] Please login first");
                        System.out.println("Usage: login username password");
                        break;
                    }
                    if (cmd.length < 2)
                    {
                        System.out.println("[Error] Enter problem ID");
                        System.out.println("Usage: submit <id>");
                        break;
                    }
                    try 
                    {
                        int subId = Integer.parseInt(cmd[1]);
                        Problem subProblem = null;
                        for (Problem p : problems)
                        {
                            if (p.getId() == subId)
                            {
                                subProblem = p;
                                break;
                            }
                        }
                        if (subProblem == null)
                        {
                            System.out.println("[Error] Problem not found with ID: " + subId);
                            break;
                        }

                        System.out.println("\n== " + subProblem.getTitle() + " ==");
                        System.out.println("Difficulty: " + subProblem.getDifficulty());

                        List<TestCase> testCases = subProblem.getTestCases();
                        List<String> userOutputs = new ArrayList<>();

                        for (int i = 0; i < testCases.size(); i++)
                        {
                            System.out.println("Test Case " + (i + 1) + " - Input: " + testCases.get(i).getInput());
                            System.out.print("Your Output> ");
                            System.out.flush();
                            String userOut = sc.nextLine().trim();
                            userOutputs.add(userOut);
                        }

                        int score = evaluator.evaluate(userOutputs, testCases);

                        int passed = 0;
                        for (int i = 0; i < testCases.size(); i++)
                        {
                            if (testCases.get(i).getExpectedOutput().trim().equals(userOutputs.get(i).trim()))
                            {
                                passed++;
                            }
                        }

                        System.out.println("\nResult: " + passed + "/" + testCases.size() + " passed (" + score + "%)");

                        Submission sub = new Submission(currentUser.getUsername(), subId, userOutputs, score);
                        currentUser.addSubmission(sub);
                        currentUser.addResult(score);
                        JSONUtil.writeUsers(users);

                        System.out.println("Submission saved!\n");
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("[Error] Invalid ID. Use a number.");
                    }
                    break;

                case "myresults":
                    if (currentUser == null)
                    {
                        System.out.println("[Error] Please login first");
                        break;
                    }
                    List<Integer> results = currentUser.getResults();
                    List<Submission> subs = currentUser.getSubmissions();
                    if (results.isEmpty())
                    {
                        System.out.println("No results yet. Try: submit <id>");
                        break;
                    }
                    System.out.println("\n== Results for " + currentUser.getUsername() + " ==");
                    for (int i = 0; i < subs.size(); i++)
                    {
                        System.out.println("  Problem " + subs.get(i).getProblemId() + " | Score: " + subs.get(i).getScore() + "%");
                    }
                    System.out.println("  Total Score: " + currentUser.getTotalScore());
                    System.out.println();
                    break;

                case "leaderboard":
                    if (users.isEmpty())
                    {
                        System.out.println("No users registered.");
                        break;
                    }
                    List<User> sorted = new ArrayList<>(users);
                    sorted.sort((a, b) -> b.getTotalScore() - a.getTotalScore());

                    System.out.println("\n== Leaderboard ==");
                    System.out.println("  Rank | Username       | Total Score");
                    System.out.println("  -----|----------------|------------");
                    for (int i = 0; i < sorted.size(); i++)
                    {
                        System.out.printf("  %-4d | %-14s | %d%n", (i + 1), sorted.get(i).getUsername(), sorted.get(i).getTotalScore());
                    }
                    System.out.println();
                    break;

                case "result":
                    if (currentUser == null)
                    {
                        System.out.println("[Error] Please login first");
                        break;
                    }
                    System.out.println("Result of " + currentUser.getUsername() + " : " + currentUser.getResults());
                    break;

                case "help":
                    System.out.println("\n== Available Commands ==");
                    System.out.println("  newuser <name> <pass>  - Register new user");
                    System.out.println("  login <name> <pass>    - Login");
                    System.out.println("  logout                 - Logout");
                    System.out.println("  who                    - Current user");
                    System.out.println("  problems               - List all problems");
                    System.out.println("  problem <id>           - Show problem details");
                    System.out.println("  submit <id>            - Submit solution");
                    System.out.println("  myresults              - Your scores");
                    System.out.println("  leaderboard            - Top users");
                    System.out.println("  result                 - Raw results list");
                    System.out.println("  clear/cls              - Clear screen");
                    System.out.println("  exit                   - Exit");
                    System.out.println();
                    break;

                default:
                    System.out.println("[Error] Unknown command: " + cmd[0]);
                    System.out.println("Type 'help' for available commands");
                    break;
            }
        }
    }
}
