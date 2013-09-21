package TestFunctions;

public class Rastrigin implements IFitnessFunction {
	public Double getValueOfFitness(Double[] variables) {
		Double divisor = 10.0*variables.length;
		
		for(int i = 0; i < variables.length; i++){
			divisor += (Math.pow(variables[i], 2) - 10*Math.cos(2*Math.PI*variables[i]));
		}
		
		return -divisor;
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0;
		return retorno;
	}
}
