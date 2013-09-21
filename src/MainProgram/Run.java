package MainProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataStructs.VariableDescription;
import MutationFunctions.*;
import SelectFunctions.*;
import CrossFunctions.*;
import TestFunctions.*;

public class Run {
	
	protected static PrinterThread printer;

	public static void main(String[] args) {
		
		//TODO - print it?
		System.out.println(" ------------------------ Start Program ------------------------ ");

		ArrayList<IFitnessFunction> fitnessFunctions = new ArrayList<IFitnessFunction>();
		ArrayList<ArrayList<VariableDescription>> variablesDescriptions = new ArrayList<ArrayList<VariableDescription>>();
		
		ArrayList<ISelectFunction> selectFunctions = new ArrayList<ISelectFunction>();
		ArrayList<ICrossFunction> crossFunctions = new ArrayList<ICrossFunction>();
		ArrayList<IMutationFunction> mutationFunctions = new ArrayList<IMutationFunction>();

		ArrayList<Integer> numbersOfIndividuals = new ArrayList<Integer>();
		ArrayList<Double> crossProbabilitys = new ArrayList<Double>(), mutationProbabilitys = new ArrayList<Double>();
		
		Double acceptableDistance = null, neighboringDistance=null, neighboringPenalty=null;
		Integer maxFitnessFunctionCalls = null, maxGeneration = null;
		
		Double maxCrossProbability = null, maxMutationProbability = null, initialDinamicProbabilityModify = null, finalDinamicProbabilityModify = null;

		try {
			Properties props = new Properties();  
			// Find the properties file, with the path pass on args[0]
			FileInputStream fis = new FileInputStream(new File(args[0]));  
		    // Load the data of file in properties class   
			props.load(fis);
		    fis.close();
		    
		    for(String ff : props.getProperty("FF").split(" ")){
			    if(ff.equals("ackley")){
			    	fitnessFunctions.add(new Ackley());
			    }
			    else if(ff.equals("bohachevsky")){
			    	fitnessFunctions.add(new Bohachevsky());
			    }
			    else if(ff.equals("dejong")){
			    	fitnessFunctions.add(new DeJong());
			    }
			    else if(ff.equals("dejongstep")){
			    	fitnessFunctions.add(new DeJongStep());
			    }
			    else if(ff.equals("dejongstep4")){
			    	fitnessFunctions.add(new DeJongStep4());
			    }
			    else if(ff.equals("dynamiccontrolproblem")){
			    	fitnessFunctions.add(new DynamicControlProblem());
			    }
			    else if(ff.equals("function8")){
			    	fitnessFunctions.add(new Function8());
			    }
			    else if(ff.equals("griewank")){
			    	fitnessFunctions.add(new Griewank());
			    }
			    else if(ff.equals("quadratic")){
			    	fitnessFunctions.add(new Quadratic());
			    }
			    else if(ff.equals("rastrigin")){
			    	fitnessFunctions.add(new Rastrigin());
			    }
			    else if(ff.equals("rosenbrock")){
			    	fitnessFunctions.add(new Rosenbrock());
			    }
			    else if(ff.equals("schaffer")){
			    	fitnessFunctions.add(new Schaffer());
			    }
			    else if(ff.equals("schwefel9")){
			    	fitnessFunctions.add(new Schwefel9());
			    }
			    else if(ff.equals("schwefel12")){
			    	fitnessFunctions.add(new Schwefel12());
			    }
			    else if(ff.equals("schwefel14")){
			    	fitnessFunctions.add(new Schwefel14());
			    }
			    else if(ff.equals("sheckelfoxholes")){
			    	fitnessFunctions.add(new ShekelFoxholes());
			    }
			    else if(ff.equals("spheremodel")){
			    	fitnessFunctions.add(new SphereModel());
			    }
			    else{
			    	System.out.println("Fitness Function does not exists.");
			    }
		    }
		    
		    for(String descVariables : props.getProperty("descVariables").split(" ")){
				// Building individual and defining number of population
		    	ArrayList<VariableDescription> variablesDescAux = new ArrayList<VariableDescription>();
				for(String descVariable : descVariables.split(";")){
					String desc[] = descVariable.split(",");
					variablesDescAux.add(new VariableDescription(Integer.parseInt(desc[0]),Double.parseDouble(desc[1]),Double.parseDouble(desc[2])));
				}
				
				variablesDescriptions.add(variablesDescAux);
		    }
		    
		    for(String sf : props.getProperty("SF").split(" ")){
			    if(sf.equals("disputed")){
			    	selectFunctions.add(new DisputedSelect());
			    }
			    else if(sf.equals("roulette")){
			    	selectFunctions.add(new Roulette());
			    }
			    else if(sf.equals("stochasticUniversalSampling")){
			    	selectFunctions.add(new StochasticUniversalSampling());
			    }
			    else{
			    	System.out.println("Select Function does not exists.");
			    }
		    }
		    
		    for(String cf : props.getProperty("CF").split(" ")){
			    if(cf.equals("fixedPercentCrossOneLocus")){
			    	crossFunctions.add(new FixedPercentCrossOneLocus());
			    }
			    else if(cf.equals("fixedPercentCrossUniformLocus")){
			    	crossFunctions.add(new FixedPercentCrossUniformLocus());
			    }
			    else if(cf.equals("probabilityCrossDualLocus")){
			    	crossFunctions.add(new ProbabilityCrossDualLocus());
			    }
			    else if(cf.equals("probabilityCrossOneLocus")){
			    	crossFunctions.add(new ProbabilityCrossOneLocus());
			    }
			    else if(cf.equals("probabilityCrossUniformLocus")){
			    	crossFunctions.add(new ProbabilityCrossUniformLocus());
			    }
			    else if(cf.equals("variableCross")){
			    	crossFunctions.add(new VariablesCross());
			    }
			    else{
			    	System.out.println("Cross Function does not exists.");
			    }
		    }
		    
		    for(String mf : props.getProperty("MF").split(" ")){
			    if(mf.equals("random")){
			    	mutationFunctions.add(new RandomMutation());
			    }
			    else{
			    	System.out.println("Mutation Function does not exists.");
			    }
		    }
		    
		    for(String generations : props.getProperty("numInd").split(" ")){
		    	numbersOfIndividuals.add(Integer.parseInt(generations));
		    }
		    
			// Probability of operators
			for(String cp : props.getProperty("cp").split(" ")){
				crossProbabilitys.add(Double.parseDouble(cp));
			}
			
			for(String mp : props.getProperty("mp").split(" ")){
				mutationProbabilitys.add(Double.parseDouble(mp));
			}
			
			// Dinamic Probability Operators
						// Defines the max cross probability
						if(!props.getProperty("mcp").equals("")){
							maxCrossProbability  = Double.parseDouble(props.getProperty("mcp"));
						}
						
					    // Defines the max mutation probability
						if(!props.getProperty("mmp").equals("")){
							maxMutationProbability = Double.parseDouble(props.getProperty("mmp"));
						}
						
						// Define the initial and final points of modify probability operators
						if(!props.getProperty("dinamicRange").equals("")){
							String range[] = props.getProperty("dinamicRange").split(",");
							initialDinamicProbabilityModify  = Double.parseDouble(range[0]);
							finalDinamicProbabilityModify  = Double.parseDouble(range[1]);
						}
		    
			// Define the max generations of execution
			if(!props.getProperty("generations").equals("")){
				maxGeneration  = Integer.parseInt(props.getProperty("generations"));
			}
			
		    // Defines the max calls of FF

			if(!props.getProperty("mFFc").equals("")){
				maxFitnessFunctionCalls = Integer.parseInt(props.getProperty("mFFc"));
			}
			
		    // Defines the acceptable Euclidian distance to the absolute optimum

			if(!props.getProperty("ad").equals("")){
				acceptableDistance = Double.parseDouble(props.getProperty("ad"));
			}
			
		    // Defines the neighboring distance, or the distance for two individuals to be neighbors

			if(!props.getProperty("nd").equals("")){
				neighboringDistance = Double.parseDouble(props.getProperty("nd"));
			}
			
		    // Defines the fraction of the fitness of an individual that should be deduced for each neighbor of the individual

			if(!props.getProperty("np").equals("")){
				neighboringPenalty = Double.parseDouble(props.getProperty("np"));
			}
			
			String record = "idExecute;numInd;max_generations;acceptableDistance;maxFFcalls;crossProbability;mutationProbability;fitnessFunction;selectFunction;crossFunction;mutationFunction;"+
				"generation;countCallFF;currentEuclideanDistance;averageNeighboring;mediaFitness;bestFitness;status;variables";
			
			//TODO - print it?
		    System.out.println((new Date()).toString()+" --- Inicio --- ");
			
			// Multi-thread run elements
			//calculates an appropriate number of threads
			Integer numberOfThreads = 5; 
			if (args.length>1){
				numberOfThreads = Integer.parseInt(args[1]);
				if (numberOfThreads < 1) numberOfThreads = 1;
			}
			
			//launches the number of threads
			ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
			
			// Output printer
			printer = new PrinterThread("outputEvaluationT.csv");
			printer.start();
			printer.assign(record);
			
			Integer numExec = Integer.parseInt(props.getProperty("numExec"));
			
			for(int i = 0; i < fitnessFunctions.size(); i++){
				IFitnessFunction fitnessFunction = fitnessFunctions.get(i);
				ArrayList<VariableDescription> variablesDescription = variablesDescriptions.get(i);
				
				//Should we add a new FOR loop to compute for each acceptable distance value? and each maximum number of fitness function calls?
				for(ISelectFunction selectFunction : selectFunctions){
					for(ICrossFunction crossFunction : crossFunctions){
						for(IMutationFunction mutationFunction : mutationFunctions){
							for(Integer numbersOfIndividual : numbersOfIndividuals){
								for(Double crossProbability : crossProbabilitys){
									for(Double mutationProbability : mutationProbabilitys){
										for(int idExecute = 0; idExecute < numExec; idExecute++){
											executor.execute(new GAExecutor(idExecute, printer, variablesDescription, numbersOfIndividual, maxGeneration, crossProbability, mutationProbability, 
												fitnessFunction, selectFunction, crossFunction, mutationFunction, acceptableDistance, maxFitnessFunctionCalls, neighboringDistance,
												neighboringPenalty, maxCrossProbability, maxMutationProbability, initialDinamicProbabilityModify, finalDinamicProbabilityModify));	
										}
									}	
								}	
							}
						}
					}
				}
			}
			
			executor.shutdown();
			
			while (!executor.isTerminated());
			printer.shutdown();

			//TODO print it?
		    System.out.println((new Date()).toString()+" --- Fim --- ");
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}    
	}
}
