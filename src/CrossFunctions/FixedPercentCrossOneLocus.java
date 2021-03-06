package CrossFunctions;

import java.util.HashSet;
import java.util.Set;

import DataStructs.Population;

public class FixedPercentCrossOneLocus implements ICrossFunction {

	public Set<Integer> crossPopulation(Population origin, Double probability) {
		Set<Integer> updates = new HashSet<Integer>();
		
		Population crossPopulation = origin.clone();
		Integer totalCross = (int)(origin.getNumInd()*probability);
		
		for(int i = 0; i < totalCross; i++){
			int select1 = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd());
			int select2 = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd());
			
			int locus = (int)Math.floor((Math.random()*(origin.getNumBitsInd()-1))%(origin.getNumBitsInd()-1));

			Boolean[] individualSelect1 = origin.getIndividual(select1), 
					individualSelect2 = origin.getIndividual(select2);
			Boolean[] individualCross1 = new Boolean[origin.getNumBitsInd()], 
					individualCross2 = new Boolean[origin.getNumBitsInd()];
						
			for(int j = 0; j < origin.getNumBitsInd(); j++){
				if(j < locus){
					individualCross1[j] = individualSelect1[j];
					individualCross2[j] = individualSelect2[j];
				}
				else{
					individualCross1[j] = individualSelect2[j];
					individualCross2[j] = individualSelect1[j];
				}
			}

			crossPopulation.setIndividual(select1, individualCross1);
			crossPopulation.setIndividual(select2, individualCross2);

			updates.add(select1);
			updates.add(select2);
		}
		
		origin.setBinaryMatrix(crossPopulation.getBinaryMatrix());
		
		return updates;
	}

}
