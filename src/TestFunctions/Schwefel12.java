package TestFunctions;

public class Schwefel12 implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double retorno = 0.0;
		for(int i=0; i<variables.length; i++){
			retorno += variables[i] * Math.sin(variables[i]);
		}
		return -retorno;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 420.9867;	//the best case variables are all 420.9867;
		return retorno;
	}

}
