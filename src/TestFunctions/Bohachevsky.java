package TestFunctions;

public class Bohachevsky implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double retorno = 0.0;
		
		for(int i=0; i<variables.length-1; i++){
			retorno += Math.pow(variables[i], 2) 
						+ 2*Math.pow(variables[i+1], 2) 
						- 0.3*Math.cos(3*Math.PI*variables[i]) 
						- 0.4*Math.cos(4*Math.PI*variables[i+1]) 
						+ 0.7;
		}

		return -retorno;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0; The Fitness will be 79.4713
		return retorno;
	}

}
