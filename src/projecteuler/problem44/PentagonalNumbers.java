package projecteuler.problem44;

import java.util.Arrays;

public class PentagonalNumbers {

	/**
	 * Author: James Norcross
	 * Date: 2/16/15
	 * Purpose: Project Euler Problem 44
	 * Description: Problem deals with pentagonal numbers defined by Pn = n(3n-1)/2.  The problem is to find a Pj and Pk 
	 * such that Pj+Pk is a pentagonal number and Pj-Pk is also a pentagonal number with the final stipulation that 
	 * |Pj-Pk| be minimized.
	 */
	public static void main(String[] args) {
		
		int j, k, jmax = 1000000000;
		long Pj, Pk, diffMax = 1000000000;
		boolean done = false;
		long start = System.currentTimeMillis();
		
		// step through j, k space. Max limit on j is determined by the current minimum difference.  It is set when the first
		// possible solution is achieved below.  If any smaller differences are found jmax is reduced.  k counts down from
		// j-1 to a minimum value which is either k = 1 or a value of k such that Pj-Pk is greater than diffMax.  diffMax is 
		// also set when the first possible solution is found.  Subsequent smaller possible solutions would further reduce diffMax
		for(j = 2; j < jmax; j++) {
			
			Pj = (j*((3*j)-1))/2;
			Pk = Pj;
			
			for(k = j-1; (k > 0) && (Pj - Pk < diffMax); k--) {
				
				Pk = (k*((3*k)-1))/2;
				
				if(isPentagonal(Pj - Pk) && isPentagonal(Pj + Pk)) {
					
					System.out.println("Possible solution");
					System.out.println("Pj-Pk=" + (Pj - Pk) + "   j=" + j + "    k=" + k);
					
					// can limit loop on k now using this as minimum solution
					diffMax = Pj - Pk;
					
					// find max j required to verify that solution is minimum for Pj - Pk.  Basically need to 
					// check to point where Pj - Pj-1 > solution.  Analytically find that Pn - Pn+1 = 3n +1
					int tempMax = (int)((Pj - Pk - 1)/3) + 1;
					if (tempMax < jmax)
						jmax = tempMax;
										
				}
			}
		}
		
		System.out.println("Time to complete " + (System.currentTimeMillis() - start) + " milliseconds");
		
	}
	

	// test for is pentagonal found on Wikipedia
	private static boolean isPentagonal(long number) {
		
		long n = (long)(((Math.sqrt((double)((24*number)+1)))+1)/6.0);
		
		if (number == ((n * ((3*n) - 1))/2)) {
			return true;
		}
		
		return false;
	}
}
