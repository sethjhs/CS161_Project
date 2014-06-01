import java.util.Scanner;

public class CLCSFast {
	
	static int[][] arr = new int[2048][2048];
	static char[] A, B;
	static int LCS;

	private static char[] cut(char[] str, int k){
		char[] newArr = new char[str.length];
		System.arraycopy(str,k,newArr,0,str.length-k);
		System.arraycopy(str, 0, newArr, str.length-k, k);
		return newArr;
	}
	
	public static boolean isInBounds(int frameStart, int row, int col, Path upperBound,Path lowerBound){
		if(row<0||col<0||row>A.length||col>B.length)return false;
		
		boolean lowerOk = false;
		boolean upperOk = false;
	
		if(lowerBound.startIndex-frameStart >= row){
			lowerOk=true;
		}else{
			if(col>=lowerBound.boundary[frameStart-lowerBound.startIndex+row].lowerBoundValue) lowerOk=true;
		}
		
		if(A.length+upperBound.startIndex-frameStart<=row){
			upperOk=true;
		}else{
			if(col<=upperBound.boundary[row+frameStart-upperBound.startIndex].upperBoundValue) upperOk=true;
		}
		
		return upperOk&&lowerOk;
	}
	
	private static Path singleShortestPath(int startIndex, Path lowerPath, Path upperPath){
		int m = A.length, n = B.length;
		char [] cutA = cut(A,startIndex);
		
	    int i, j;
	    for (i = 0; i <= m; i++) arr[i][0] = 0;
	    for (j = 0; j <= n; j++) arr[0][j] = 0;
	    
	    int lowerBound;
	    int upperBound;
	    int lowerDiff = lowerPath.startIndex-startIndex;
	    int upperDiff = startIndex-upperPath.startIndex;
	    
	    int left;
	    int up;
	    int diag;
	    
	    for (i = 1; i <= m; i++) {
	      if(i <= lowerDiff || lowerPath.boundary[i-lowerDiff].lowerBoundValue == 0) lowerBound = 1;
	      else lowerBound = lowerPath.boundary[i-lowerDiff].lowerBoundValue;
	      
	      if(i >= m-upperDiff) upperBound = n;
	      else upperBound = upperPath.boundary[i+upperDiff].upperBoundValue;
	      
	      for (j = lowerBound; j <= upperBound; j++) {
	    	if(isInBounds(startIndex, i-1, j, upperPath, lowerPath)) up = arr[i-1][j];
	    	else up = 0;
	    	  
	    	if(j > lowerBound) left = arr[i][j-1];
	    	else left = 0;
	    	
	    	if(isInBounds(startIndex, i-1, j-1, upperPath, lowerPath)) diag = arr[i-1][j-1];
	    	else diag = -1;
	    	  
	        arr[i][j] = Math.max(left, up);
	        if (cutA[i-1] == B[j-1]) arr[i][j] = Math.max(arr[i][j], diag+1);
	      }
	    }
	    
	    if(LCS < arr[m][n]) LCS = arr[m][n];
	    //System.out.println(LCS);
	    
	    Path shortestPath = new Path(m+1);
	    shortestPath.boundary[m].lowerBoundValue = n;
	    shortestPath.boundary[m].upperBoundValue = n;
	    i = m;
	    j = n;
	    int val;
	    while(i > 0 || j > 0) {
	    	val = arr[i][j];
	    	
	    	if(isInBounds(startIndex, i, j-1, upperPath, lowerPath) && val == arr[i][j-1]) {
	    		j--;
	    		shortestPath.boundary[i].lowerBoundValue = j;
	    	}
	    	else if(isInBounds(startIndex, i-1, j, upperPath, lowerPath) || val == arr[i-1][j]) {
	    		i--;
	    		shortestPath.boundary[i].lowerBoundValue = j;
	    		shortestPath.boundary[i].upperBoundValue = j;
	    	}
	    	else {
	    		i--;
	    		j--;
	    		shortestPath.boundary[i].lowerBoundValue = j;
	    		shortestPath.boundary[i].upperBoundValue = j;
	    	}
	    }
	    return shortestPath;
	}


	private static void findShortestPaths(Path lowerPath, Path upperPath){
		if(lowerPath.startIndex-upperPath.startIndex <= 1) return;
		int midIndex = (lowerPath.startIndex+upperPath.startIndex)/2;
		Path newPath = singleShortestPath(midIndex,lowerPath,upperPath);
		findShortestPaths(lowerPath,newPath);
		findShortestPaths(newPath,upperPath);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int tc = 0; tc < T; tc++) {
			A = s.next().toCharArray();
			B = s.next().toCharArray();
			LCS = 0;
			Path lowerPath = new Path(A.length+1);
			Path upperPath = new Path(A.length+1);

			for(int i = 0; i < A.length; i++){
				upperPath.boundary[i].lowerBoundValue = B.length;
				upperPath.boundary[i].upperBoundValue = B.length;
			}
			
			Path newPathLo = singleShortestPath(0,lowerPath,upperPath);
			newPathLo.startIndex = A.length;
			Path newPathHi = singleShortestPath(0,lowerPath,upperPath);
			newPathHi.startIndex = 0;
			
			//System.out.println("hurray!");

			findShortestPaths(newPathLo,newPathHi);
			System.out.println(LCS);

		}
	}

}
