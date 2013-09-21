package TestFunctions;

public class Griewank implements IFitnessFunction {

	@Override
	public Double getValueOfFitness(Double[] variables) {
		Double result, sum=0.0, prod=0.0;
		
		//make both sums
		for(int i=0; i<variables.length; i++){
			sum+=Math.pow(variables[i], 2.0)/4000;
			prod*=Math.cos(variables[i]/Math.sqrt(i));
		}
		
		//mount up first exponential
		
		//final sum
		result = 1+sum-prod;
		
		return -result;
	}

	@Override
	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0;
		return retorno;
	}

}
