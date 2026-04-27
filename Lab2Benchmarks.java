import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class Lab2Benchmarks {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testMoveZeroes_Naive() {
        int[] array = Lab2Algorithm.generateRandomArray(100000);
        Lab2Algorithm.moveZeroesNaive(array);
    }

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testMoveZeroes_Greedy() {
        int[] array = Lab2Algorithm.generateRandomArray(100000);
        Lab2Algorithm.moveZeroesGreedy(array);
    }

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testMoveZeroes_Optimal() {
        int[] array = Lab2Algorithm.generateRandomArray(100000);
        Lab2Algorithm.moveZeroesOptimal(array);
    }
}
