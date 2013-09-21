package MutationFunctions;

import java.util.HashSet;
import java.util.Set;

import DataStructs.Population;

public class RandomMutation implements IMutationFunction {

	public Set<Integer> mutatePopulation(Population origin, Double probability) {
		Set<Integer> updates = new HashSet<Integer>();
		
		Population mutatedPopulation = origin.clone();
		
		Integer totalMutation = (int)(origin.getNumInd()*probability);
		
		for(int i = 0; i < totalMutation; i++){
			int individualId = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd()),
					binaryItem = (int)Math.floor((Math.random()*(origin.getNumBitsInd()))%(origin.getNumBitsInd()));
			mutatedPopulation.setBinaryItem(individualId, binaryItem, !(mutatedPopulation.getBinaryItem(individualId, binaryItem)));
			
			updates.add(individualId);
		}
		
		origin.setBinaryMatrix(mutatedPopulation.getBinaryMatrix());
		
		return updates;
	}

}
