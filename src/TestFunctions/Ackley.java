package TestFunctions;

public class Ackley implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double result = 0.0, sum1=0.0, sum2=0.0, exp1, exp2;
		
		//make both sums
		for(int i=0; i<variables.length; i++){
			sum1+=Math.pow(variables[i], 2.0);
			sum2+=Math.cos(2*Math.PI*variables[i]);
		}
		
		//mount up first exponential
		exp1 = -20*Math.exp(-0.2*Math.pow( ((1/variables.length) * sum1) , 0.5) );
		exp2 = Math.exp( ((1/variables.length) * sum2)) + 20 + Math.E;
		
		//final sum
		result = exp1-exp2;
		
		return -result;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0;
		return retorno;
	}

}
