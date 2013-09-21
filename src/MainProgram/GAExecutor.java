package MainProgram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import MutationFunctions.IMutationFunction;
import SelectFunctions.ISelectFunction;
import TestFunctions.IFitnessFunction;

import CrossFunctions.ICrossFunction;
import DataStructs.Population;
import DataStructs.VariableDescription;

public class GAExecutor implements Runnable{
	
	private Integer idExecute;
	
	// Assigned output printer
	private PrinterThread printer;
	
	//Variables Description
	private ArrayList<VariableDescription> variablesDesc;
	private Integer numInd;
	
	private Integer maxGenerations, maxFFcalls;
	private Double acceptableDistance;
	
	private Population population;

	// Probability of operators
	private Double minCrossProbability, minMutationProbability, maxCrossProbability, maxMutationProbability, 
		initialDinamicProbabilityModify, finalDinamicProbabilityModify,	currentCrossProbability, currentMutationProbability;

	// Fitness Function define
	private IFitnessFunction fitnessFunction;
	
	// Selection
	private ISelectFunction selectFunction;	
	
	//Cross
	private ICrossFunction crossFunction;
	
	//Mutation
	private IMutationFunction mutationFunction;
	
	//Control Variables
	private Integer countCallFF, countGeneration, idBestInd;

	
	public GAExecutor(Integer idExecute, PrinterThread printer, ArrayList<VariableDescription> variablesDesc,	Integer numInd, Integer max_generations, Double minCrossProbability, Double minMutationProbability, 
			IFitnessFunction fitnessFunction, ISelectFunction selectFunction, ICrossFunction crossFunction, IMutationFunction mutationFunction, Double acceptableDistance, Integer maxFFcalls,
			Double maxCrossProbability, Double maxMutationProbability, Double initialDinamicProbabilityModify, Double finalDinamicProbabilityModify) throws Exception {
		
		this.idExecute = idExecute;
		this.printer = printer;
		
		this.variablesDesc = variablesDesc;
		this.numInd = numInd;
		this.fitnessFunction = fitnessFunction;
		this.selectFunction = selectFunction;
		this.crossFunction = crossFunction;
		this.mutationFunction = mutationFunction;

		this.minCrossProbability = minCrossProbability;
		this.minMutationProbability = this.currentMutationProbability = minMutationProbability;
		
		this.maxCrossProbability = this.currentCrossProbability = maxCrossProbability;
		this.maxMutationProbability = maxMutationProbability;
		
		this.initialDinamicProbabilityModify = initialDinamicProbabilityModify;
		this.finalDinamicProbabilityModify = finalDinamicProbabilityModify;
				
		if(acceptableDistance == null && maxFFcalls == null && max_generations == null){
			throw new Exception("One of stop conditions need be not null");
		}
		
		this.acceptableDistance = acceptableDistance;
		this.maxFFcalls = maxFFcalls;
		this.maxGenerations = max_generations;
		
		countCallFF = 0;
		countGeneration = 0;
	}
	
	public void run(){
		
		// Initialize with a random population
		population = new Population(numInd, variablesDesc);
		population.createRandomPopulation();
		
		// Generations
		idBestInd = null;
		

		// Auxiliary variables
		Double fitnessValues[] = new Double[numInd];
			
		for(int i = 0; i < numInd; i++){
			fitnessValues[i] = fitnessValueCalculate(population, i);
		}
		
		Double fitnessOfBest = null;
		
		// STOP CONDITIONS BELOW: NUMBER OF GENERATIONS; NUMBER OF FITNESS FUNCTION RECALCULATIONS; EUCLIDEAN DISTANCE; - WATHEVER HAPPENS FIRST			
		while(!stopCondition(countGeneration, countCallFF, idBestInd)){
	
			Boolean bestInd[];
			Double maxFitness = null;

			idBestInd = 0;
			
			for(int i = 0; i < numInd; i++){	
				if(maxFitness == null || fitnessValues[i] > maxFitness){
					idBestInd = i;
					maxFitness = fitnessValues[i];
				}
			}
			
			outputEvaluation(population, fitnessValues, idBestInd, countGeneration, false);
			
			// Elitist
			bestInd = population.getIndividual(idBestInd);
			fitnessOfBest = maxFitness; 
			
			Set<Integer> updates = new HashSet<Integer>();
			
			// Selection reference update: population, fitnessValues
			selectFunction.selectPopulation(population, fitnessValues);
			
			//Cross reference update: population
			Set<Integer> updateCF = crossFunction.crossPopulation(population, currentCrossProbability);
			
			//Mutation reference update: population
			Set<Integer> updateMF = mutationFunction.mutatePopulation(population, currentMutationProbability);	

			updates.addAll(updateCF);
			updates.addAll(updateMF);
			for(Integer update : updates){
				fitnessValues[update] = fitnessValueCalculate(population, update);
			}
			
			//Recorver elitist
			Integer idWorseInd = 0;	
			Integer idBestIndAfterGeneration = 0;
			Double minFitness = null;
			maxFitness = null;
			
			for(int i = 0; i < numInd; i++){
				if(maxFitness == null || fitnessValues[i] > maxFitness){
					idBestIndAfterGeneration = i;
					maxFitness = fitnessValues[i];
				}
				if(minFitness == null || fitnessValues[i] < minFitness){
					idWorseInd = i;
					minFitness = fitnessValues[i];
				}
			}
			
			
					
			if(maxFitness >= fitnessOfBest){
				idBestInd = idBestIndAfterGeneration;
				fitnessOfBest = maxFitness;
			}
			else{
				population.setIndividual(idWorseInd, bestInd);
				fitnessValues[idWorseInd] = fitnessOfBest;
				idBestInd = idWorseInd;
			}
			countGeneration++;
			
			updateProbabilityOperators(fitnessValues, idBestInd);
			
			//TODO - Print it?
			System.out.println(countGeneration+" - "+minCrossProbability+" - "+minMutationProbability+" - "+countCallFF+" - "+fitnessOfBest+" - "+currentEuclideanDistance(population, idBestInd));
			
		}
		//TODO - Print it?
		System.out.println(countGeneration+"-- FIM --");
		//outputEvaluation(population, fitnessValues, idBestInd, countGeneration, true);
	}
	
	private Boolean stopCondition(Integer countGeneration, Integer countCallFF, Integer idBestInd) {
		Boolean stop = false;
		if(idBestInd != null){
			stop = ((maxGenerations != null && countGeneration >= maxGenerations) || (maxFFcalls != null && countCallFF > maxFFcalls) || (acceptableDistance != null && currentEuclideanDistance(population, idBestInd) <= acceptableDistance));
		}
		return stop;
	}
	
	private Double fitnessValueCalculate(Population population, int i) {
		Double fitnessValue;
		Double variables[] = new Double[population.getVariablesDesc().size()];
		
		for(int j= 0; j < variables.length; j++){
			variables[j] = population.getVaribleOfIndividual(i, j);
		}
		
		fitnessValue = fitnessFunction.getValueOfFitness(variables);
		
		countCallFF++;
		return fitnessValue;
	}
	
	private void updateProbabilityOperators(Double fitnessValues[], Integer idBestInd) {
		Double mediaFitness = 0.0;
		
		Double negativeFactor = 0.0;
		
		for(int i = 0; i < fitnessValues.length; i++){				
			if(fitnessValues[i] < negativeFactor){
				negativeFactor = fitnessValues[i];
			}
		}
		
		for(Double fitnessValue : fitnessValues){
			mediaFitness += fitnessValue+(-1)*negativeFactor;
		}
		mediaFitness /= fitnessValues.length;
		
		Double geneticVariability = mediaFitness/(fitnessValues[idBestInd]+(-1)*negativeFactor);
		//System.out.println("Entra: "+(geneticVariability > initialDinamicProbabilityModify &&  geneticVariability < finalDinamicProbabilityModify));
		if(geneticVariability > initialDinamicProbabilityModify &&  geneticVariability < finalDinamicProbabilityModify){
			Double factor = (geneticVariability - initialDinamicProbabilityModify)/(finalDinamicProbabilityModify - initialDinamicProbabilityModify);

			currentCrossProbability = maxCrossProbability - (maxCrossProbability - minCrossProbability)*factor;
			currentMutationProbability = minMutationProbability + (maxMutationProbability - minMutationProbability)*factor;
		}
		else if(geneticVariability < initialDinamicProbabilityModify){
			currentCrossProbability = maxCrossProbability;
			currentMutationProbability = minMutationProbability;
		}
		else if(geneticVariability > finalDinamicProbabilityModify){
			currentCrossProbability = minCrossProbability;
			currentMutationProbability = maxMutationProbability;
		}
		System.out.println("currentCrossProbability: "+currentCrossProbability+" currentMutationProbability: "+currentMutationProbability);
	}
	
	private Double euclideanDistanceCalculate(Double[] point_a, Double[] point_b) {
		Double distance = 0.0;
		for(int i=0; (i<point_a.length && i<point_b.length); i++){
			distance += Math.pow((point_a[i]-point_b[i]), 2.0);
		}
		return Math.sqrt(distance);
	}
	
	private Double currentEuclideanDistance(Population population, int idBestInd){		
		Double variables[] = new Double[population.getVariablesDesc().size()];
		
		for(int j= 0; j < variables.length; j++){
			variables[j] = population.getVaribleOfIndividual(idBestInd, j);
		}
		return euclideanDistanceCalculate(variables, fitnessFunction.getBestCaseVariables(variables.length));
	}

	private void outputEvaluation(Population population, Double fitnessValues[], Integer idBestInd, Integer generation, boolean finish) {
		String record = "";
		record += "\n"+idExecute;
		record += ";"+numInd;
		
		// Stop conditions
		record += ";"+maxGenerations;
		record += ";"+acceptableDistance;
		record += ";"+maxFFcalls;
		
		// Genetics probabilities
		record += ";"+minCrossProbability;
		record += ";"+minMutationProbability;
		
		// Functions
		record += ";"+fitnessFunction.getClass().getName();
		record += ";"+selectFunction.getClass().getName();
		record += ";"+crossFunction.getClass().getName();
		record += ";"+mutationFunction.getClass().getName();
						
		// stop conditions
		record += ";"+generation;
		record += ";"+countCallFF;
		record += ";"+Double.toString(currentEuclideanDistance(population, idBestInd)).replace(".", ",");
		
		Double mediaFitness = 0.0;
		for(Double fitnessValue : fitnessValues){
			mediaFitness += fitnessValue;
		}
		mediaFitness /= fitnessValues.length;
		
		record += ";"+Double.toString(mediaFitness).replace(".", ",");
		record += ";"+Double.toString(fitnessValues[idBestInd]).replace(".", ",");
		
		if(finish){
			record += ";finish";
			for(int j= 0; j < population.getVariablesDesc().size(); j++){
				record += ";"+Double.toString(population.getVaribleOfIndividual(idBestInd, j)).replace(".", ",");
			}
		}
		else{
			record += ";running";
		}
		
		printer.assign(record);
	}
}
