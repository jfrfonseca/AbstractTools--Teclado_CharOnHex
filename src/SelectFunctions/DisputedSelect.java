package SelectFunctions;

import DataStructs.Population;

public class DisputedSelect implements ISelectFunction{

	public void selectPopulation(Population origin, Double[] valuesOfFitness) {
		Integer[] listOfSelecteds = new Integer[valuesOfFitness.length];

		Double[] copyFitenssValues = valuesOfFitness.clone();
		
		for(int i = 0; i < valuesOfFitness.length; i++){
			int selecionado1 = (int)Math.floor((Math.random()*(valuesOfFitness.length))%valuesOfFitness.length);
			int selecionado2 = (int)Math.floor((Math.random()*(valuesOfFitness.length))%valuesOfFitness.length);
			
			if(valuesOfFitness[selecionado1] > valuesOfFitness[selecionado2]){
				listOfSelecteds[i] = selecionado1;
			}
			else{
				listOfSelecteds[i] = selecionado2;
			}
		}
		
		Population populationSelected = new Population(origin.getNumInd(), origin.getVariablesDesc());
		
		for(int i = 0; i < origin.getNumInd(); i++){
			populationSelected.setIndividual(i, origin.getIndividual(listOfSelecteds[i])); 
			//reference update
			valuesOfFitness[i] = copyFitenssValues[listOfSelecteds[i]];
		}
		
		origin.setBinaryMatrix(populationSelected.getBinaryMatrix());
	}
	
}
