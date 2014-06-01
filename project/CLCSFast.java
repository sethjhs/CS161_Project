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

	static void buildDPTable() {
		int m = A.length, n = B.length;
		int i, j;
		for (i = 0; i <= m; i++) arr[i][0] = 0;
		for (j = 0; j <= n; j++) arr[0][j] = 0;

		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				if (A[i-1] == B[j-1]) arr[i][j] = Math.max(arr[i][j], arr[i-1][j-1]+1);
			}
		}
	}
	private static BoundElement[] singleShortestPath(int startIndex, int[] lowerBounds, int[] upperBounds){

	}


	private static int findShortestPaths(int upperIndex,int lowerIndex,BoundElement[] lowerBounds, BoundElement[] upperBounds){
		if(upperIndex-lowerIndex<=1)return LCS;
		int midIndex = (upperIndex-lowerIndex)/2;
		
		
		BoundElement[] newBounds = singleShortestPath(upperIndex,lowerBounds,upperBounds);
		System.arraycopy(newBounds, 0, dest, destPos, length);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int tc = 0; tc < T; tc++) {
			A = s.next().toCharArray();
			B = s.next().toCharArray();
			LCS=0;
			BoundElement[] upperBounds = new BoundElement[A.length*2];
			Arrays.fill(upperBounds,A.length);
			BoundElement[] lowerBounds = new BoundElement[A.length*2];
			Arrays.fill(lowerBounds, 0);

			BoundElement[] newBounds = singleShortestPath(0,lowerBounds,upperBounds);

			System.arraycopy(newBounds, 0, lowerBounds, A.length, A.length);
			System.arraycopy(newBounds, 0, upperBounds, 0, A.length);

			System.out.println(findShortestPaths(0,A.length,lowerBounds,upperBounds));

		}
	}

}
