import java.util.Arrays;
import java.util.Scanner;


public class CLCSFast {
	class BoundElement{
		public int upperBoundValue;
		public int lowerBoundValue;
	}
	static int[][] arr = new int[2048][2048];
	static char[] A, B;
	static int LCS;

	private static BoundElement[] singleShortestPath(int startIndex, BoundElement[] lowerBounds, BoundElement[] upperBounds){
		int m = A.length, n = B.length;
	    int i, j;
	    for (i = 0; i <= m; i++) arr[i][0] = 0;
	    for (j = 0; j <= n; j++) arr[0][j] = 0;
	    
	    for (i = 1; i <= m; i++) {
	      for (j = lowerBounds[i].lowerBoundValue; j <= upperBounds[i].upperBoundValue; j++) {
	        arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
	        if (A[i-1] == B[j-1]) arr[i][j] = Math.max(arr[i][j], arr[i-1][j-1]+1);
	      }
	    }
	    
	    if(LCS < arr[m][n]) LCS = arr[startIndex+m][n];
	    
	    BoundElement[] bounds = new BoundElement[A.length];
	    
	    i = m;
	    j = n;
	    int val;
	    int left;
	    int up;
	    while(isInBounds(i,j,lowerBounds,upperBounds)) {
	    	//if i=0 just go up, if j=0 just go left
	    	val = arr[i][j];
	    	left = arr[i][j-1];
	    	up = arr[i-1][j];
	    	
	    	if(val == left && left == up) {
	    		
	    	}
	    	
	    	else if()
	    	
	    }
	    

	}


	private static int findShortestPaths(int lowerIndex,int upperIndex,BoundElement[] lowerBounds, BoundElement[] upperBounds){
		if(upperIndex-lowerIndex<=1)return LCS;
		int midIndex = (upperIndex-lowerIndex)/2;
		BoundElement[] newBounds = singleShortestPath(midIndex,lowerBounds,upperBounds);
		findShortestPaths(lowerIndex,midIndex,lowerBounds,newBounds);
		findShortestPaths(midIndex,upperIndex,newBounds,upperBounds);
		
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int tc = 0; tc < T; tc++) {
			A = s.next().toCharArray();
			B = s.next().toCharArray();
			LCS=0;
			BoundElement[] upperBounds = new BoundElement[A.length];
			BoundElement[] lowerBounds = new BoundElement[A.length];

			for(int i=0;i<A.length;i++){
				lowerBounds[i].lowerBoundValue=0;
				lowerBounds[i].upperBoundValue=0;
				upperBounds[i].lowerBoundValue=B.length;
				upperBounds[i].upperBoundValue=B.length;
			}
			
			BoundElement[] newBounds = singleShortestPath(0,lowerBounds,upperBounds);

			System.out.println(findShortestPaths(0,A.length,newBounds,newBounds));

		}
	}

}
