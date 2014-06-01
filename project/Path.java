

public class Path {
	public int startIndex;
	public BoundElement[] boundary;
	
	class BoundElement{
		public int upperBoundValue;
		public int lowerBoundValue;
	}
	
	public Path(int m) {
		boundary = new BoundElement[m];
		for(int i =0;i<m;i++){
			boundary[i]=new BoundElement();
			boundary[i].lowerBoundValue=0;
			boundary[i].upperBoundValue=0;
			startIndex=0;
		}
		
	}

}
