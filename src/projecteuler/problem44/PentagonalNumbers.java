package projecteuler.problem44;

import java.util.Arrays;

public class PentagonalNumbers {

	/**
	 * Author: James Norcross
	 * Date: 2/16/15
	 * Purpose: Project Euler Problem 44
	 * Description: Problem deals with pentagonal numbers defined by Pn = n(3n-1)/2.  The problem is to find a Pj and Pk 
	 * such that Pj+Pk is a pentagonal number and |Pj-Pk| is also a pentagonal number with the final stipulation that 
	 * |Pj-Pk| be minimized.
	 */
	public static void main(String[] args) {
		
		int dIndex, j, k, jmax, kmax;
		long answer = 0;
		boolean done = false;
		
		// note: don't know how many numbers in the pentagonal series will be needed to solve the problem.
		// Could generate each number in the sequence as needed and use an array list to hold them, but
		// for simplicity just generate 30000 elements in the series up front
		long[] pNumbers = new long[30000];
		pNumbers[0] = 0;		
		for(long n = 1; n < 30000; n++) {
			pNumbers[(int)n] = getPentagonalNumber(n);
		}
		
		// since interested in Pj-Pk being pentagonal and minimized will step through pentagonal series
		// starting from P1 trying to find a Pj and Pk that meet the conditions
		dIndex = 1;
		
		while(!done) {
			
			// first need to find maximum j, k that needs to be considered for this dIndex
			j = dIndex + 1;
			k = j + 1;
			while(pNumbers[k] - pNumbers[j] <= pNumbers[dIndex]) {
				j++;
				k++;
			}
			kmax = k-1;
			jmax = j-1;

			
			// now need to test all differences in valid range of j and k
			for(j = dIndex + 1; j <= jmax && !done; j++) {

				for(k = j+1; k <= kmax && !done; k++) {
					//System.out.println(pNumbers[dIndex] + "   " + jmax + "   " + kmax + "   " + (pNumbers[k] - pNumbers[j]));
					//System.out.println(j + "   " + k);

					if((pNumbers[k] - pNumbers[j] == pNumbers[dIndex]) && isPentagonal(pNumbers[k] + pNumbers[j])) {
						done = true;
						answer = pNumbers[dIndex];
					}
				}
			}
			dIndex ++;
		}
		
		System.out.println("The answer is " + answer);
		
	}
	
	private static long getPentagonalNumber(long n) {
		long pN =(n*((3*n)-1))/2;
		return pN;
	}

	private static boolean isPentagonal(long number) {
		
		long n = (int)Math.floor(Math.sqrt((2*number)/3));
		long pN = getPentagonalNumber(n);
		
		while(pN <= number) {
			if(pN == number)
				return true;
			else {
				n++;
				pN = getPentagonalNumber(n);
			}
		}
		
		return false;
	}
}
