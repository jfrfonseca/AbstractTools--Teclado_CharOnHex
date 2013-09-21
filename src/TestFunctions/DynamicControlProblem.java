package TestFunctions;

public class DynamicControlProblem implements IFitnessFunction {

	@Override
	public Double getValueOfFitness(Double[] variables) {
		Double retorno = 0.0;
		for(int i=0; i<variables.length-1; i++){
			retorno += Math.pow(variables[i], 2.0) + Math.pow(variables[i+1] - variables[i], 2.0);
		}
		return -retorno;
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0;
		return retorno;
	}

}
