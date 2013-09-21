package TestFunctions;

public class Function8 implements IFitnessFunction {

	public Double getValueOfFitness(Double[] variables) {
		Double retorno = ( Math.pow(variables[0],2.0) + Math.pow(variables[1],2.0) )/2 - Math.cos(20*Math.PI*variables[0])*Math.cos(20*Math.PI*variables[1]) +2;		
		return -retorno;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = 0.0;	//the best case variables are all 0;
		return retorno;
	}


}
