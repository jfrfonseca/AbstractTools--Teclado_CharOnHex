package SelectFunctions;

public class StochasticUniversalSampling implements ISelectFunction {
	public Integer[] selectPopulation( Double[] valuesOfFitness) {
		
		Integer[] listOfSelecteds = new Integer[valuesOfFitness.length];
		
		for(int i = 0; i < valuesOfFitness.length; i++){
			listOfSelecteds[i] = (int)Math.floor((Math.random()*(valuesOfFitness.length))%valuesOfFitness.length);
		}
		
		return listOfSelecteds; 
	}

}
