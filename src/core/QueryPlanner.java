package core;

import tool.MathHelper;
import tool.StringHelper;

/** 
 * @author : Jingwen Shi
 * @date ：2018-3-2 上午2:27:41 
 * @version 1.0 
 */
public class QueryPlanner {

	int MaxLevel;
	int TreeNumber;
	int[] Dimension;
	float[][][] WholeSpace;
	float[] TreeWholeArea;

	public void setInfo(float[][][] whole, int maxLevel, int treeNumber,
			int[] dimension) {
		this.WholeSpace = whole;
		this.MaxLevel = maxLevel;
		this.TreeNumber = treeNumber;
		this.Dimension = dimension;
		this.TreeWholeArea = new float[treeNumber];
		for (int i = 0; i < treeNumber; i++) {
			MathHelper math = new MathHelper();
			this.TreeWholeArea[i] = math.calVolum(this.WholeSpace[i]);
		}
	}

String[] Childerns(String[] orignal){
String[] search = new String[(int)orignal.length*5];
for (int i = 0; i < orignal.length; i++) {
 StringHelper stringHelper = new StringHelper();
 String[] split = orignal[i].split(",");
 
 String pre = split[0];
 search[i*5] = orignal[i];
 split[0] = pre+"00";
 search[i*5+1] = String.join(",", split);
 split[0] = pre+"01";
 search[i*5+2] = String.join(",", split);
 split[0] = pre+"10";
 search[i*5+3] = String.join(",", split);
 split[0] = pre+"11";
 search[i*5+4] = String.join(",", split);
}
for (int i = 0; i < search.length; i++) {
	System.out.println(search[i]);
}
return search;
}

public String[] queryPlanner(TreeObj[] trees){
KeyGenerator key = new KeyGenerator();
key.setInfo(this.WholeSpace, this.MaxLevel, this.TreeNumber, this.Dimension);
String[] originString = key.keyString(trees);
String[] comStrings = Childerns(originString);
return comStrings;
}


}
