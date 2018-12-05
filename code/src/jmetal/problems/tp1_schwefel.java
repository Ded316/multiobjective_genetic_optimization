//  Schwefel.java

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
public class tp1_schwefel extends Problem {    

 /**
  * Constructor.
  * Creates a default instance of problem Schaffer
  * @param solutionType The solution type must "Real" or "BinaryReal".
  */
  public tp1_schwefel(String solutionType, Integer numberOfVariables) {
    numberOfVariables_   = numberOfVariables;
    numberOfObjectives_  = 1;
    numberOfConstraints_ = 0;
    problemName_         = "Schwefel";
        
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];   
    
	for (int i = 0; i < numberOfVariables_; i++) {
		lowerLimit_[i] = -500;
		upperLimit_[i] =  500;
	}
    
    if (solutionType.compareTo("BinaryReal") == 0)
    	solutionType_ = new BinaryRealSolutionType(this) ;
    else if (solutionType.compareTo("Real") == 0)
    	solutionType_ = new RealSolutionType(this) ;
    else {
    	System.out.println("Error: solution type " + solutionType + " invalid") ;
    	System.exit(-1) ;
    } 
  } //Schwefel

    
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
		double f1, f2, result=0;
		f1 = 418.9828872724339 * numberOfVariables_;
	    
	    for (int j = 0 ; j < numberOfVariables_; j++) {
	    		f2 = x[j]*Math.sin(Math.sqrt(Math.abs(x[j])));
				result += f2;
				//result = f1 - result;
	    }
	    result = f1 - result;
	    
	    solution.setObjective(0,result);
	  } // evaluate
} //Schwefel
