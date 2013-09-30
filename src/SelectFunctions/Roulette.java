package SelectFunctions;

public class Roulette implements ISelectFunction {
	public Integer[] selectPopulation(Double[] valuesOfFitness) {

		Integer[] listOfSelecteds = new Integer[valuesOfFitness.length];
		
		Double fitnessTotal = 0.0;
		
		Double maxFitness = null;
		Double negativeFactor = 0.0;
		
		for(int i = 0; i < valuesOfFitness.length; i++){				
			if(valuesOfFitness[i] < negativeFactor){
				negativeFactor = valuesOfFitness[i];
			}
			
			if(maxFitness == null || valuesOfFitness[i] > maxFitness){
				maxFitness = valuesOfFitness[i];
			}
		}
		//aux for translate if find1 negative values
		Double[] auxFitnessValues = valuesOfFitness.clone();
		
		// translate if in fitness values exists negative
		if(negativeFactor != 0){
			// if all population have a same value of fitness and the fitness are negative the result value is 0 for all
			if(!negativeFactor.equals(maxFitness)){
				for(int i = 0; i < valuesOfFitness.length; i++){
					//add (maxFitness/100) for the worse value have a percentage of total fitness
					auxFitnessValues[i] += ((-1)*negativeFactor)+((maxFitness + (-1)*negativeFactor)/100);
				}
			}
			else{
				for(int i = 0; i < valuesOfFitness.length; i++){
					auxFitnessValues[i] *= -1;
				}
			}
		}
		
		for(Double fitness : auxFitnessValues){
			fitnessTotal += fitness;
		}
		
		Double roletteSelect, auxSelect;
		for(int i = 0; i < valuesOfFitness.length; i++){
			
			roletteSelect = Math.random()*fitnessTotal;
			auxSelect = 0.0;
			
			for(int j = 0; j < valuesOfFitness.length && roletteSelect > auxSelect; j++){
				auxSelect += auxFitnessValues[j];
				if(roletteSelect <= auxSelect){
					listOfSelecteds[i] = j;
				}
			}
		}
		
		
		return listOfSelecteds;
	}
}
