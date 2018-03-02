package core;
import java.util.ArrayList;

/** 
 * @author : Jingwen Shi
 * @date ：2018-3-2 上午12:00:59 
 * @version 1.0 
 */
import core.KeyGenerator;

public class test {
	
	public static void testKeyGenerator(){
		System.out.println("-----------Test KeyGenerator---------");
	    int treeNumber = 2;
	    int dimension[] = {2,2};
	    int maxLevel = 10;
	    float[][][]wholeSpace = {{{0, 180}, {0, 90}}, {{90, 180}, {45, 90}}};
	    KeyGenerator keyGen = new KeyGenerator();
	    keyGen.setInfo(wholeSpace, maxLevel, treeNumber, dimension);

	    float[][] c1 = {{0,1},{3,4}};
	    float[][] c2 = {{9,179},{47,90}};
	    System.out.println(keyGen.findNodes(0,1,c2).get(0));
	    System.out.println(keyGen.findNodes(0,1,c2).get(1));
    
	}
	
	public static void testQueryPlanner(){
		System.out.println("-----------Test QueryPlanner---------");
	    int treeNumber = 2;
	    int dimension[] = {2,2};
	    int maxLevel = 10;
	    float[][][]wholeSpace = {{{0, 180}, {0, 90}}, {{90, 180}, {45, 90}}};
	    
	    float[][] t0 = {{89,179},{47,90}};
	    TreeObj tree0 = new TreeObj();
	    tree0.superVolum = -1;
	    tree0.object = t0;

	    float[][] t1 =  {{160,179},{77,90}};
	    TreeObj tree1 = new TreeObj();
	    tree1.superVolum = -1;
	    tree1.object = t1;

	    TreeObj[] geo = {tree0, tree1};

	    QueryPlanner query = new QueryPlanner();
	    query.setInfo(wholeSpace, maxLevel, treeNumber, dimension);
	    query.queryPlanner(geo);
	}
	
	public static void testShardManager(){

		System.out.println("-----------Test ShardManager---------");
		ShardManager sManager = new ShardManager();
		sManager.setInfo(13);
		for (int i = 0; i < sManager.mapMachine.length; i++) {
			System.out.println(sManager.mapMachine[i]);
		}
		String[] keyStrings = { "0011,1011", "0111,1010", "1010,1011",
				"1111,1010" };
		int[] m = sManager.getMachines(keyStrings);
		System.out.println("Machines: ");
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i]);
		}
		
	}
	
	public static void main(String args[]) {
		testKeyGenerator();
		testQueryPlanner();
		testShardManager();
	}

}
