package CrossFunctions;

import java.util.Set;

import DataStructs.Population;

public interface ICrossFunction {
	public Set<Integer> crossPopulation(Population origin, Double probability);
}
