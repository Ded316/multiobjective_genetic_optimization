//  Schaffer.java

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
public class tp1_schaffer extends Problem {    

	/**
	 * Constructor.
	 * Creates a default instance of problem Schaffer
	 * @param solutionType The solution type must "Real" or "BinaryReal".
	 */
	public tp1_schaffer(String solutionType, Integer numberOfVariables) {
		numberOfVariables_   = numberOfVariables;
		numberOfObjectives_  = 1;
		numberOfConstraints_ = 0;
		problemName_         = "Schaffer";

		lowerLimit_ = new double[numberOfVariables_];
		upperLimit_ = new double[numberOfVariables_];

		for (int i = 0; i < numberOfVariables_; i++) {
			lowerLimit_[i] = -100;
			upperLimit_[i] =  100;
		}

		if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this) ;
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this) ;
		else {
			System.out.println("Error: solution type " + solutionType + " invalid") ;
			System.exit(-1) ;
		}  
	} //Schaffer


	/** 
	 * Evaluates a solution 
	 * @param solution The solution to evaluate
	 * @throws JMException 
	 */
	public void evaluate(Solution solution) throws JMException {
		Variable[] decisionVariables  = solution.getDecisionVariables();

		double [] x = new double[numberOfVariables_] ;
		for (int i = 0; i < numberOfVariables_; i++)
			x[i] = decisionVariables[i].getValue() ;
		
		double result=0, f1, f2;


		for (int j = 0 ; j <= numberOfVariables_-2; j++) {
			f1 = Math.pow(0.25,(Math.pow(2,x[j]) + Math.pow(2,x[j])));
			f2 = Math.pow(2,Math.sin(50*(Math.pow(0.10,(Math.pow(2,x[j]) + Math.pow(2,x[j])))))+1);
			result += f1 * f2;   
		}

		solution.setObjective(0,result);
	} // evaluate
} //Schaffer
