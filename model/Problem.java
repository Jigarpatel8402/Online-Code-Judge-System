package model;

import java.util.*;

public class Problem 
{
    private int id;
    private String title;
    private String description;
    private String difficulty;
    private List<TestCase> testCases;

    public Problem(int id, String title, String description, String difficulty, List<TestCase> testCases) 
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.testCases = testCases;
    }

    public int getId() 
    {
        return this.id;
    }

    public String getTitle() 
    {
        return this.title;
    }

    public String getDescription() 
    {
        return this.description;
    }

    public String getDifficulty() 
    {
        return this.difficulty;
    }

    public List<TestCase> getTestCases() 
    {
        return this.testCases;
    }

    @Override
    public String toString() 
    {
        return "ID: " + id + " | " + title + " [" + difficulty + "]";
    }
}
