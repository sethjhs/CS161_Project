

public class Path {
	public int startIndex;
	public BoundElement[] boundry;
	
	public Path(int m) {
		boundry = new BoundElement[m];
		for(int i =0;i<m;i++){
			boundry[i].lowerBoundValue=0;
			boundry[i].upperBoundValue=0;
			startIndex=0;
		}
		
	}

}
