package DataStructs;

import java.util.ArrayList;

import BinaryNotationConverterPackege.BinaryNotationConverter;
import BinaryNotationConverterPackege.Exceptions.NumberOutRangedException;

public class Population implements Cloneable{
	
	private Boolean[][] binaryMatrix;

	private final ArrayList<VariableDescription> variablesDesc; 
	private final Integer numInd, numBitsInd;

	public Population(Integer numInd, ArrayList<VariableDescription> variablesDesc) {
		this.numInd = numInd;
		this.variablesDesc = variablesDesc;
		
		Integer auxNumBitsInd = 0;
		for(VariableDescription varDesc : this.variablesDesc){
			varDesc.setRalativePosOnIndividual(auxNumBitsInd);
			auxNumBitsInd += varDesc.getNumBitsVar();
		}
		
		numBitsInd = auxNumBitsInd;
		
		binaryMatrix = new Boolean[numInd][numBitsInd];
	}
	
	public void createRandomPopulation(){
		for(int i = 0; i < numInd; i++){
			for(int j = 0; j < numBitsInd; j++){
				binaryMatrix[i][j] = (Math.random() > 0.5) ? true : false;
			}
		}
	}
	
	public Boolean getBinaryItem(Integer individualId, Integer binaryItem) {
		return binaryMatrix[individualId][binaryItem];
	}

	public void setBinaryItem(Integer individualId, Integer binaryItem, Boolean value) {
		binaryMatrix[individualId][binaryItem] = value;
	}
	
	public Double getVaribleOfIndividual(Integer individualId, Integer varId){
		Boolean[] notationNumber = new Boolean[variablesDesc.get(varId).getNumBitsVar()];
		Double valueNumber = null;
		
		for(int i = 0; i < notationNumber.length; i++){
			notationNumber[i] = binaryMatrix[individualId][i+(variablesDesc.get(varId).getRalativePosOnIndividual())];
		}
		
		try {
			valueNumber = BinaryNotationConverter.convertToInteger(notationNumber, variablesDesc.get(varId));
		} catch (NumberOutRangedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valueNumber;
	}
	
	public Boolean[] getIndividual(Integer individualId){
		Boolean[] individual = new Boolean[numBitsInd];
		
		for(int i = 0; i < numBitsInd; i++){
			individual[i] = binaryMatrix[individualId][i];
		}
		
		return individual;
	}
	
	public void setIndividual(Integer individualId, Boolean[] individual){
		for(int i = 0; i < numBitsInd; i++){
			binaryMatrix[individualId][i] = individual[i];
		}
	}
	
	public Boolean[][] getBinaryMatrix() {
		return binaryMatrix;
	}

	public void setBinaryMatrix(Boolean[][] binaryMatrix) {
		this.binaryMatrix = binaryMatrix.clone();
	}
	
	public Integer getNumInd() {
		return numInd;
	}
	
	public Integer getNumBitsInd() {
		return numBitsInd;
	}
	
	public ArrayList<VariableDescription> getVariablesDesc() {
		return variablesDesc;
	}
	
	public Population clone(){
		Population clone = new Population(numInd, variablesDesc);
		for(int i = 0; i < numInd; i++){
			// the method clone of Boolean[][] does not work
			clone.setIndividual(i, this.getIndividual(i));
		}
		return clone;
	}
}
