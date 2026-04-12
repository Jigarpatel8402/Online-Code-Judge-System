package model;

import java.util.*;

public class User 
{
    private String username;
    private String password;
    private List<Integer> results;
    private List<Submission> submissions;

    public User(String username, String password) 
    {
        this.username = username;
        this.password = password;
        this.results = new ArrayList<>();
        this.submissions = new ArrayList<>();
    }

    public String getUsername() 
    {
        return this.username;
    }

    public boolean checkPassword(String password) 
    {
        return this.password.equals(password);
    }

    public List<Integer> getResults() 
    {
        return this.results;
    }

    public void addResult(int score) 
    {
        this.results.add(score);
    }

    public List<Submission> getSubmissions() 
    {
        return this.submissions;
    }

    public void addSubmission(Submission submission) 
    {
        this.submissions.add(submission);
    }

    public int getTotalScore() 
    {
        int sum = 0;
        for (int score : results) 
        {
            sum += score;
        }
        return sum;
    }

    @Override
    public String toString() 
    {
        return "Username: " + username + 
           ", Results: " + results + 
           ", Submissions: " + submissions.size();
    }
}
