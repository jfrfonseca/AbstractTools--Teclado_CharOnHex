package TestFunctions;

public class NegativeTest implements IFitnessFunction {
	public Double getValueOfFitness(Double[] variables) {
		return (1/(0.3 + Math.pow(variables[0],2))) - 1;
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		// TODO Auto-generated method stub
		return null;
	}
}
