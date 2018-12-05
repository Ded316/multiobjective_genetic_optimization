//  tp2_fct_agregation.java
//Fonction multi-objectifs : Approche Pareto

package jmetal.problems;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

/**
 * Class representing problem Schaffer
 */
public class tp3_fct_agregation extends Problem {    

	/**
	 * Constructor.
	 * Creates a default instance of problem Schaffer
	 * @param solutionType The solution type must "Real" or "BinaryReal".
	 */
	
	double alpha, beta;
	public tp3_fct_agregation(String solutionType, Integer numberOfVariables) {
		numberOfVariables_   = numberOfVariables;
		numberOfObjectives_  = 2;
		numberOfConstraints_ = 0;
		problemName_         = "Fonction multi-objectifs";

		lowerLimit_ = new double[numberOfVariables_];
		upperLimit_ = new double[numberOfVariables_];

		for (int i = 0; i < numberOfVariables_; i++) {
			lowerLimit_[i] = 0.0;
			upperLimit_[i] = 1.0;
		}

		if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this) ;
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this) ;
		else {
			System.out.println("Error: solution type " + solutionType + " invalid") ;
			System.exit(-1) ;
		}  
	} //Fonction multi-objectifs

	/** 
	 * Evaluates a solution 
	 * @param solution The solution to evaluate
	 * @throws JMException 
	 */
	public void evaluate(Solution solution) throws JMException {
		Variable[] decisionVariables  = solution.getDecisionVariables();

		double [] x = new double[numberOfVariables_];
		for (int i = 0; i < numberOfVariables_; i++)
			x[i] = decisionVariables[i].getValue();
		
		int[] J1 = {2,4,6,8,10}, J2 = {3,5,7,9};
		double f1=0, f2=0, C, N=numberOfVariables_;
		
		//SUM F1
		for (int i = 0; i < J1.length; i++) {
			int j = J1[i];
			f1 += Math.pow((x[j-1] - Math.pow(x[0], 0.5*(1.0+(3.0*(j-2)/(N-2))))), 2);
		}
		//SUM F2
		for (int i = 0; i < J2.length; i++) {
			int j = J2[i];
			f2 += Math.pow((x[j-1] - Math.pow(x[0], 0.5*(1.0+(3.0*(j-2)/(N-2))))), 2);
		}
		f1 = x[0] + (2.0/J1.length)*f1;
		f2 = 1-x[0] + (2.0/J2.length)*f2;
		
	    C = f1 + f2 - Math.abs(Math.sin( (N*Math.PI) * (f1 - f1 + 1))) - 1 ; 
	    if( C >= 0 ){
	        solution.setObjective(0, f1);
	        solution.setObjective(1, f2);
	    }
	    else{
	    	solution.setObjective(0, 100);
	    	solution.setObjective(1, 100);
	    }
	}
} //Fonction multi-objectifs TP3
