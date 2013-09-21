package TestFunctions;

public interface IFitnessFunction {
	public Double getValueOfFitness(Double[] variables);

	public Double[] getBestCaseVariables(int number_of_variables_per_individual);
}
