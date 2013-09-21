package DataStructs;

public class VariableDescription {
	
	private final Integer numBitsVar;
	private Double limMin, limMax;
	
	// this variable is a auxiliary record of position on individual
	// for more easy access on select variable
	private Integer ralativePosOnIndividual;

	public VariableDescription(Integer numBitsVar, Double limMin, Double limMax) {
		this.numBitsVar = numBitsVar;
		this.limMin = limMin;
		this.limMax = limMax;
	}

	public Integer getNumBitsVar() {
		return numBitsVar;
	}

	public Double getLimMin() {
		return limMin;
	}

	public Double getLimMax() {
		return limMax;
	}

	public Integer getRalativePosOnIndividual() {
		return ralativePosOnIndividual;
	}

	public void setRalativePosOnIndividual(Integer ralativePosOnIndividual) {
		this.ralativePosOnIndividual = ralativePosOnIndividual;
	}
	
	
}
