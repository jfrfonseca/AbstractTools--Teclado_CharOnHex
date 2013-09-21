package TestFunctions;

public class Quadratic implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double result = 0.0;
		
		for(int i = 0; i < variables.length; i++){
			result += Math.pow(variables[i], 2);
		}
		
		return -result;
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0; The Fitness will be 1/E
		return retorno;
	}
}
