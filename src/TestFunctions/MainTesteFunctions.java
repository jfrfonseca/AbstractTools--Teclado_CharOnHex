package TestFunctions;

public class MainTesteFunctions {

	public static void main(String[] args) {
		IFitnessFunction fitness = new Rastrigin();
		Double[] variables = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		System.out.println(fitness.getValueOfFitness(variables));
	}
}
