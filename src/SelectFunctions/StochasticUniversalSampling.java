package SelectFunctions;

import DataStructs.Population;

public class StochasticUniversalSampling implements ISelectFunction {
	public void selectPopulation(Population origin, Double[] valuesOfFitness) {
		
		Population populationSelected = new Population(origin.getNumInd(), origin.getVariablesDesc());
		Double[] copyFitenssValues = valuesOfFitness.clone();
		
		for(int i = 0; i < origin.getNumInd(); i++){
			int selected = (int)Math.floor((Math.random()*(valuesOfFitness.length))%valuesOfFitness.length);
			populationSelected.setIndividual(i, origin.getIndividual(selected)); 
			//reference update
			valuesOfFitness[i] = copyFitenssValues[selected];
		}
		
		origin.setBinaryMatrix(populationSelected.getBinaryMatrix());
	}

}
