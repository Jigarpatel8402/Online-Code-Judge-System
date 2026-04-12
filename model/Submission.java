package model;

import java.util.*;

public class Submission 
{
    private String username;
    private int problemId;
    private List<String> userOutputs;
    private int score;

    public Submission(String username, int problemId, List<String> userOutputs, int score) 
    {
        this.username = username;
        this.problemId = problemId;
        this.userOutputs = userOutputs;
        this.score = score;
    }

    public String getUsername() 
    {
        return this.username;
    }

    public int getProblemId() 
    {
        return this.problemId;
    }

    public List<String> getUserOutputs() 
    {
        return this.userOutputs;
    }

    public int getScore() 
    {
        return this.score;
    }

    @Override
    public String toString() 
    {
        return "Problem " + problemId + " | Score: " + score;
    }
}
