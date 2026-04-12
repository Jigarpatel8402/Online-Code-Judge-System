public class DefaultScoreCalculator implements ScoreCalculator 
{
    @Override
    public int calculate(int passed, int total) 
    {
        if (total == 0) 
        {
            return 0;
        }
        return (passed * 100) / total;
    }
}
