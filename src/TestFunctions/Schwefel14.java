package TestFunctions;

public class Schwefel14 implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		
		Double result = 0.0, sums;

		for(int i=0; i<variables.length; i++){
			sums = 0.0;
			for (int j=0; j<i; i++){
				sums += variables[j];
			}
			result += Math.pow(sums, 2.0);
		}
		
		return -result;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0; The Fitness will be "adjust"
		return retorno;
	}

}
