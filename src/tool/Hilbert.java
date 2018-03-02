package tool;


class IntObj{
	public long value;
}


public class Hilbert {
	//rotate/flip a quadrant appropriately
	public static void rot(long n, IntObj x, IntObj y, long rx, long ry){
		if(ry == 0){
			if(rx == 1){
				x.value = n-1 - x.value;
				y.value = n-1 - y.value;
			}
			
			//Swap x and y
			long t = x.value;
			x.value = y.value;
			y.value = t;
		}
	}
	/*
	public static void rot(int n, int x, int y, int rx, int ry){
		if(ry == 0){
			if(rx == 1){
				x = n-1 - x;
				y = n-1 - y;
			}
			
			//Swap x and y
			int t = x;
			x = y;
			y = t;
		}
	}
	*/
	//convert (x,y) to d
	public long xy2d (long n, long x, long y) {
		IntObj xobj= new IntObj();
		IntObj yobj= new IntObj();
		xobj.value = x;
		yobj.value = y;
	    long rx=0,ry=0,s=0,d=0;
	    for (s=n/2; s>0; s/=2) {
	        if((xobj.value & s) > 0)
	        	rx = 1;
	        else 
	        	rx = 0;
	        //System.out.println(rx+"="+x+" & "+s);
	        if((yobj.value & s) > 0)
	        	ry = 1;
	        else 
	        	ry = 0;
	        d += s * s * ((3 * rx) ^ ry);
	        //System.out.println("x="+xobj.value+" y="+yobj.value);
	        rot(s, xobj, yobj, rx, ry);
	        //System.out.println("x="+xobj.value+" y="+yobj.value);
	        //System.out.println("----------------");
	    }
	    
	    return d;
	}
	/*
	public int xy2d (int n, int x, int y) {
	    int rx=0,ry=0,s=0,d=0;
	    for (s=n/2; s>0; s/=2) {
	        if((x & s) > 0)
	        	rx = 1;
	        else 
	        	rx = 0;
	        //System.out.println(rx+"="+x+" & "+s);
	        if((y & s) > 0)
	        	ry = 1;
	        else 
	        	ry = 0;
	        d += s * s * ((3 * rx) ^ ry);
	        System.out.println("x="+x+" y="+y);
	        rot(s, x, y, rx, ry);
	        System.out.println("x="+x+" y="+y);
	        System.out.println("----------------");
	    }
	    
	    return d;
	} */

	//convert d to (x,y)
	public void d2xy(int n, int d, int x, int y) {
	    int rx, ry, s, t=d;
		IntObj xobj= new IntObj();
		IntObj yobj= new IntObj();
		xobj.value = 0;
		yobj.value = 0;
	    for (s=1; s<n; s*=2) {
	        rx = 1 & (t/2);
	        ry = 1 & (t ^ rx);
	        rot(s, xobj, yobj, rx, ry);
	        x += s * rx;
	        y += s * ry;
	        t /= 4;
	    }
	}
}


