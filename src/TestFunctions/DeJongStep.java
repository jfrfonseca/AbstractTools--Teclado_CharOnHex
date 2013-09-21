package TestFunctions;

public class DeJongStep implements IFitnessFunction {

		public Double getValueOfFitness(Double[] variables) {
			Double retorno = 0.0;
			for(int i=0; i<variables.length; i++){
				retorno += (variables[i]).intValue();
			}
			return -retorno;
		}

		public Double[] getBestCaseVariables(int number_of_variables_per_individual) {
			Double[] retorno = new Double[number_of_variables_per_individual];
			for(int i=0; i<number_of_variables_per_individual; i++)
				retorno[i] = 0.0;	//the best case variables are all 0;
			return retorno;
		}

}
