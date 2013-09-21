package MutationFunctions;

import java.util.Set;

import DataStructs.Population;

public interface IMutationFunction {
	public Set<Integer> mutatePopulation(Population origin, Double probability);
}
