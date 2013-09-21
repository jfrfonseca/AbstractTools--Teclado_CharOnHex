package TestFunctions;

public class ShekelFoxholes implements IFitnessFunction {
	
	protected final int[][] alpha = new int[][]{
			  { -32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,2 },
			  { -32,-32,-32,-32,-32,-16,-16,-16,-16,-16,0,0,0,0,0,16,16,16,16,16,32,32,32,32,32 }
	};

	public Double getValueOfFitness(Double[] variables) {
		Double retorno = 0.002, sums = 0.0;
		
		for(int i=0; i<24; i++){
			sums += 1/( i+ (Math.pow( (variables[0]-alpha[0][i]) , 6.0) + Math.pow( (variables[1]-alpha[1][i]) , 6.0) ) );
		}
		
		retorno = (1/(retorno+sums) - 0.9980038378);
		return -retorno;
	}

	public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
		Double[] retorno = new Double[number_of_variables_per_individual];
		for(int i=0; i<number_of_variables_per_individual; i++)
			retorno[i] = -31.9786271;	//the best case variables are all -31.9786271;
		return retorno;
	}

}
