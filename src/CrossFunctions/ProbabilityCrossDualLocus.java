package CrossFunctions;

import java.util.HashSet;
import java.util.Set;

import DataStructs.Population;

public class ProbabilityCrossDualLocus implements ICrossFunction {

	public Set<Integer> crossPopulation(Population origin, Double probability) {
		Set<Integer> updates = new HashSet<Integer>();
		
		Population crossPopulation = origin.clone();
		
		for(int i = 0; i < origin.getNumInd(); i++){
			int select2 = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd());

			Boolean[] individualSelect1 = origin.getIndividual(i), 
					individualSelect2 = origin.getIndividual(select2);
			
			Boolean[] individualCross1 = new Boolean[origin.getNumBitsInd()];
				
			if(Math.random() < probability){

				int locus1 = (int)Math.floor((Math.random()*(origin.getNumBitsInd()-1))%(origin.getNumBitsInd()-1));
				int locus2 = (int)Math.floor((Math.random()*(origin.getNumBitsInd()-1))%(origin.getNumBitsInd()-1));
				
				for(int j = 0; j < origin.getNumBitsInd(); j++){
					if((j < locus1 && j < locus2)||(j > locus1 && j > locus2)){
						individualCross1[j] = individualSelect1[j];
					}
					else{
						individualCross1[j] = individualSelect2[j];
					}
				}

				updates.add(i);
				crossPopulation.setIndividual(i, individualCross1);
			}
		}
		
		origin.setBinaryMatrix(crossPopulation.getBinaryMatrix());
		
		return updates;
	}

}
