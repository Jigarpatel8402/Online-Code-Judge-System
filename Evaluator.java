import java.util.*;
import model.TestCase;

public interface Evaluator 
{
    int evaluate(List<String> userOutputs, List<TestCase> testCases);
}
