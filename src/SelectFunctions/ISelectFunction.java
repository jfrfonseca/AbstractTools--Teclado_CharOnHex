package SelectFunctions;

import DataStructs.Population;

public interface ISelectFunction {
	public void selectPopulation(Population origin, Double[] valuesOfFitness);
}
