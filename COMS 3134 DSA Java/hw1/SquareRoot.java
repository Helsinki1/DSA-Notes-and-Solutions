public class SquareRoot {

    public static final double EPSILON = 1e-7;

    public static double sqrt(double num, double epsilon){

        if(num < 0 || Double.isNaN(num)){
            return Double.NaN;
        }
        if(num == 0 || num == Double.POSITIVE_INFINITY){
            return num;
        }

        double previous = 0;
        double current = num;

        while(Math.abs(current - previous) > epsilon){
            previous = current;
            current = 0.5*(previous + (num/previous));
        }
        return current;
    }

    public static void main(String[] args){

        double value = 0;
        double epsilon = EPSILON;

        if(args.length > 2 || args.length < 1){
            System.err.println("Usage: java SquareRoot <value> [epsilon]");
            System.exit(1);
        }
        try{
            value = Double.parseDouble(args[0]);
        } catch(NumberFormatException nfe){
            System.err.println("Error: Value argument must be a double.");
            System.exit(1);
        }
        if(args.length == 2){
            try{
                epsilon = Double.parseDouble(args[1]);
            } catch(NumberFormatException nfe){
                System.err.println("Error: Epsilon argument must be a positive double.");
                System.exit(1);
            }
            if(epsilon <= 0){
                System.err.println("Error: Epsilon argument must be a positive double.");
                System.exit(1);
            }
        }

        // if(args.length == 1){
        //     double value = Double.parseDouble(args[0]);
        // }else if(args.length == 2){
        //     double value = Double.parseDouble(args[0]);
        //     double epsilon = Double.parseDouble(args[1]);
        // }

        System.out.printf("%.8f\n", sqrt(value, epsilon)); // test the integer part length
        System.exit(0);
    }
}
