package TestFunctions;

public class Cone3D implements IFitnessFunction {
	public Double getValueOfFitness(Double[] variables) {
		return 1/(Math.E+Math.pow(variables[0], 2)+Math.pow(variables[1], 2));
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		// TODO Auto-generated method stub
		return null;
	}
}
