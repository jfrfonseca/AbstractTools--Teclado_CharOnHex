package CrossFunctions;

import java.util.HashSet;
import java.util.Set;

import DataStructs.Population;
import DataStructs.VariableDescription;

public class VariablesCross implements ICrossFunction {

	public Set<Integer> crossPopulation(Population origin, Double probability) {
		Set<Integer> updates = new HashSet<Integer>();
		
		Population crossPopulation = origin.clone();
		Integer totalCross = (int)(origin.getNumInd()*probability);
		
		for(int i = 0; i < totalCross; i++){
			int select1 = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd());
			int select2 = (int)Math.floor((Math.random()*(origin.getNumInd()))%origin.getNumInd());
			

			Boolean[] individualCross1 = new Boolean[origin.getNumBitsInd()], 
					individualCross2 = new Boolean[origin.getNumBitsInd()];
			
			for(VariableDescription varDesc : origin.getVariablesDesc()){
				int locus = varDesc.getRalativePosOnIndividual() + (int)Math.floor((Math.random()*(varDesc.getNumBitsVar()-1))%(varDesc.getNumBitsVar()-1));
	
				Boolean[] individualSelect1 = origin.getIndividual(select1), 
						individualSelect2 = origin.getIndividual(select2);
					
				for(int w = varDesc.getRalativePosOnIndividual(); w < varDesc.getRalativePosOnIndividual()+varDesc.getNumBitsVar(); w++){
					if(w < locus){
						individualCross1[w] = individualSelect1[w];
						individualCross2[w] = individualSelect2[w];
					}
					else{
						individualCross1[w] = individualSelect2[w];
						individualCross2[w] = individualSelect1[w];
					}
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
