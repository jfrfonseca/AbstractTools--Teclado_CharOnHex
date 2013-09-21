package BinaryNotationConverterPackege;

import BinaryNotationConverterPackege.Exceptions.NumberOutRangedException;
import DataStructs.VariableDescription;

public class BinaryNotationConverter {
	
	public static Boolean[] convertToBinaryNotation(Double number, VariableDescription varDesc) throws NumberOutRangedException{
		Boolean[] binaryArray = new Boolean[varDesc.getNumBitsVar()];
		
		if(number < varDesc.getLimMin() || number > varDesc.getLimMax()){
			throw new NumberOutRangedException(); 
		}
		
		Integer numberRepresentation;
		
		numberRepresentation = (int) Math.round(((number - varDesc.getLimMin()) * (Math.pow(2, varDesc.getNumBitsVar())-1))/(varDesc.getLimMax() - varDesc.getLimMin()));
		
		for(int i = 0; i < varDesc.getNumBitsVar(); i++){
			if(numberRepresentation != 0){
				binaryArray[i] = numberRepresentation%2 == 1 ? true : false;
				numberRepresentation /= 2;
			}
			else{
				binaryArray[i] = false;	
			}
		}
		
		return binaryArray;
	}
	
	public static Double convertToInteger(Boolean[] notationNumber, VariableDescription varDesc) throws NumberOutRangedException{
		Double number;
		long numberRepresentation = 0;
		
		if(notationNumber.length != varDesc.getNumBitsVar()){
			throw new NumberOutRangedException(); 
		}
		
		for(int i = 0; i < varDesc.getNumBitsVar(); i++){
			numberRepresentation += (int)((notationNumber[i] ? 1 : 0) * Math.pow(2, i));
		}
		
		number = (((numberRepresentation * (varDesc.getLimMax() - varDesc.getLimMin()))/(Math.pow(2, varDesc.getNumBitsVar())-1)) + varDesc.getLimMin());
		
		return number;
	}
}
