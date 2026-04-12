package model;

public class TestCase 
{
    private String input;
    private String expectedOutput;

    public TestCase(String input, String expectedOutput) 
    {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public String getInput() 
    {
        return this.input;
    }

    public String getExpectedOutput() 
    {
        return this.expectedOutput;
    }

    @Override
    public String toString() 
    {
        return "Input: " + input + ", Expected: " + expectedOutput;
    }
}
