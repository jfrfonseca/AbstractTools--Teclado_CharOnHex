package TestFunctions;

public class Rosenbrock implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double retorno = 0.0;

		for(int i=0; i<variables.length-1; i++){
			retorno += 100*Math.pow((Math.pow(variables[i], 2)-variables[i+1]), 2) 
						+ Math.pow((1-variables[i]), 2);
		}
		
		return -retorno;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 1.0;	//the best case variables are all 1; The Fitness will be 98221.3
		return retorno;
	}

}
