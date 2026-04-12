import java.util.*;
import model.TestCase;

public class BasicEvaluator implements Evaluator 
{
    private ScoreCalculator calculator;

    public BasicEvaluator() 
    {
        this.calculator = new DefaultScoreCalculator();
    }

    @Override
    public int evaluate(List<String> userOutputs, List<TestCase> testCases) 
    {
        int passed = 0;
        int total = testCases.size();

        for (int i = 0; i < total; i++) 
        {
            String expected = testCases.get(i).getExpectedOutput().trim();
            String actual = userOutputs.get(i).trim();

            if (expected.equals(actual)) 
            {
                passed++;
            }
        }

        return calculator.calculate(passed, total);
    }
}
