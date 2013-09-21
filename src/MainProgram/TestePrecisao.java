package MainProgram;

import TestFunctions.*;
import BinaryNotationConverterPackege.BinaryNotationConverter;
import BinaryNotationConverterPackege.Exceptions.NumberOutRangedException;
import DataStructs.VariableDescription;

public class TestePrecisao {

	public static void main(String[] args) throws NumberOutRangedException {
		
		int precisao = 9;
		double minVal = -5.12, maxVal = 5.12, valorOri = 0.0;
		
		VariableDescription vd = new VariableDescription(precisao, minVal, maxVal);
		Double parametro = BinaryNotationConverter.convertToInteger(BinaryNotationConverter.convertToBinaryNotation(valorOri, vd), vd);
		Double parametros[] = {parametro,parametro,parametro,parametro,parametro,parametro,parametro,parametro,parametro,parametro};
		
		Quadratic fQuadratic = new Quadratic();
		Rastrigin fRastrigin = new Rastrigin();

		System.out.println("Valores Vetor: "+parametros[0]);
		System.out.println("Num valores: "+parametros.length);
		System.out.println("fQuadratic: "+fQuadratic.getValueOfFitness(parametros));
		System.out.println("fRastrigin: "+fRastrigin.getValueOfFitness(parametros));
	}
}

/*
Valores Vetor: 0.010019569471624301
Num valores: 10
fQuadratic: -0.0010039177239670567
fRastrigin: -0.19910389288582664 
 
 */