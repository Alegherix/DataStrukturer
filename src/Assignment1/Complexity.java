package Assignment1;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Complexity {

    private final double baseCase = 100.0;
    private List<Double> values = Arrays.asList(101.0, 200.0, 10000.0, 100000.0);

    private static Function<Double, Double> log2n = value -> value * (Math.log(value)/Math.log(2));
    private static Function<Double, Double> n = Double::doubleValue;
    private static Function<Double, Double> nLog2N = value -> Math.pow(value, 2) * (Math.log(value)/Math.log(2));
    private static Function<Double, Double> squared = value -> Math.pow(value, 2);
    private static Function<Double, Double> cubed = value -> Math.pow(value, 3);
    private static Function<Double, Double> exponential = value -> Math.pow(2, value);


    public List<Double> applyFunctionToValue(Function<Double, Double> function){
        return values.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public void calculate(Function<Double, Double> function){
        // Percentage change = (v2-v1)/v1 * 100
        double v1 = function.apply(baseCase);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        for(Double v2 : applyFunctionToValue(function)){
            double change = ((v2-v1)/v1);
            if(change<1.0){
                System.out.println("The percentage change is: " + df.format(change * 100) + "% longer");
            }
            else if(change >= 1.0){
                change+=1;
                System.out.println("The difference is: " + df.format(change) + "x longer");
            }
        }
        System.out.println("--------------------------");

    }



    public static void main(String[] args) {
        Complexity complexity = new Complexity();

        complexity.calculate(log2n);
        complexity.calculate(n);
        complexity.calculate(nLog2N);
        complexity.calculate(squared);
        complexity.calculate(cubed);
        complexity.calculate(exponential);

    }

}

