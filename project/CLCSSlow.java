import java.util.Scanner;


public class CLCSSlow {
	static int[][] arr = new int[2048][2048];
	static char[] A, B;

	private static char[] cut(char[] str, int k){
		char[] newArr = new char[str.length];
		System.arraycopy(str,k,newArr,0,str.length-k);
		System.arraycopy(str, 0, newArr, str.length-k, k);
		return newArr;
	}

	private static int LCS(char[] A,char[] B) {
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

		return arr[m][n];
	}

	private static int getLCS(){
		int maxLCSLen=0;
		for(int k=0;k<A.length;k++){
			int LCSLen =  LCS(cut(A,k),B);
			if(LCSLen>maxLCSLen) maxLCSLen = LCSLen;
		}
		return maxLCSLen;
	}



	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int tc = 0; tc < T; tc++) {
			A = s.next().toCharArray();
			B = s.next().toCharArray();
			System.out.println(getLCS());
		}
	}



}
