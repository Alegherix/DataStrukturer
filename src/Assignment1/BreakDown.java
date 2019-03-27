package Assignment1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class BreakDown {

    private static final double baseCase = 100.0;
    private static List<Double> values = Arrays.asList(101.0, 200.0, 10000.0, 100000.0);

    private static Function<Double, Double> log2n = value -> value * (Math.log(value)/Math.log(2));


    public static void main(String[] args) {
        double v1 = log2n.apply(baseCase);
        double v2 = log2n.apply(101.0);
        double difference = (v2-v1)/v1;
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(difference);
    }
}
