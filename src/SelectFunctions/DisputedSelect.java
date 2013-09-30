package SelectFunctions;

public class DisputedSelect implements ISelectFunction{

	public Integer[] selectPopulation(Double[] valuesOfFitness) {
		Integer[] listOfSelecteds = new Integer[valuesOfFitness.length];
		
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
		
		return listOfSelecteds;
	}
	
}
