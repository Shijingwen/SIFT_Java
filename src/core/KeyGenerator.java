package core;

import java.text.NumberFormat;
import java.util.*; 

import tool.Hilbert;
import tool.MathHelper;
import tool.StringHelper;



public class KeyGenerator {
	float[][][] WholeSpace;
	int MaxLevel;
	int TreeNumber;
	int[] Dimension;
	float[] TreeWholeArea;

	public void setInfo(float[][][] whole, int maxLevel, int treeNumber,
			int[] dimension) {
		this.WholeSpace = whole;
		this.MaxLevel = maxLevel;
		this.TreeNumber = treeNumber;
		this.Dimension = dimension;
		this.TreeWholeArea = new float[treeNumber];
		MathHelper math = new MathHelper();
		for (int i = 0; i < treeNumber; i++) {
			this.TreeWholeArea[i] = math.calVolum(this.WholeSpace[i]);
		}
	}

	public int siftLevel(int treeID, float s) {
		int l = 0; // Layer in which to index g and which is greater than s

		for (int i = 0; i < this.MaxLevel; i++) {
			int numSubspace = (int) Math.pow(2, this.Dimension[treeID] * i);
			float levelArea = this.TreeWholeArea[treeID] / numSubspace;
			if (levelArea >= s) {
				l = i;
			}
		}
		return l;
	}

	boolean judgeAdd(float[][] bound, float[][] object) {
		boolean flag = true;

		for (int i = 0; i < bound.length; i++) {
			if (((object[i][0] < bound[i][0]) && (object[i][1] < bound[i][0]))
					|| ((object[i][0] > bound[i][1]) && (object[i][1] > bound[i][1]))) {
				flag = false;
			}
		}
		return flag;
	}

	public float[][] genBound(int treeID, int level, int[] grid) {
		int size = Dimension[treeID];
		float[][] bound = new float[size][2];
		for (int i = 0; i < size; i++) {
			float span = (WholeSpace[treeID][i][1] - WholeSpace[treeID][i][0])
					/ (int) Math.pow(2, level);
			bound[i][0] = WholeSpace[treeID][i][0] + span * grid[i];
			bound[i][1] = bound[i][0] + span;
		}
		return bound;
	}

	ArrayList<String> findNodes(int treeID, int level, float[][] object) {
		ArrayList<String> keyVector = new ArrayList<String>();
		int levelSubspace = (int) Math.pow(2, level * Dimension[treeID]);

		for (int i = 0; i < levelSubspace; i++) {
			int[] grid = new int[Dimension[treeID]];
			int num = i;
			for (int j = 0; j < Dimension[treeID]; j++) {
				int full = (int) Math.pow(2, level
						* (Dimension[treeID] - j - 1));
				grid[Dimension[treeID] - j - 1] = num / full;
				num = num - grid[Dimension[treeID] - j - 1] * full;
			}

			float[][] bound = genBound(treeID, level, grid);
			boolean flag = judgeAdd(bound, object);

			Hilbert hilbert = new Hilbert();
			long number = hilbert.xy2d((int) Math.pow(2, level), grid[0],
					grid[1]);
			if (flag) {
				int bits = (int) Math.pow(2, level);
				StringHelper sh = new StringHelper();
				String binary = sh.toBinaryString(number, bits);
				keyVector.add(binary);
			}
		}
		return keyVector;
	}

	ArrayList<ArrayList<String>> allKeyList(TreeObj[] trees) {

		ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < this.TreeNumber; i++) {
			float[][] object = trees[i].object;
			float superVolum;
			MathHelper math = new MathHelper();
			if (trees[i].superVolum < 0) {
				superVolum = math.calVolum(object);
			} else {
				superVolum = trees[i].superVolum;
			}

			int level = siftLevel(i, superVolum);
			ArrayList<String> treeList = findNodes(i, level, object);
			keyList.add(treeList);
		}
		return keyList;
	}

	public String[] combineTrees(ArrayList<ArrayList<String>> keyList,
			int count, int[] lens) {

		String[] finalString = new String[count];
		for(int i = 0;i < count; i++){
			finalString[i]="";
		}
		
		for (int i = 0; i < keyList.size(); i++) {
			ArrayList<String> treeList = keyList.get(i);
			for (int j = 0; j < keyList.size(); ++j) {
				String number = new String();
				number = keyList.get(i).get(j);
				int writeSpan = 1;
				for (int k = i + 1; k < lens.length; k++) {
					writeSpan *= lens[k];
				}
				for (int k = 0; k < count / (writeSpan * lens[i]); k++) {
					int start = k * writeSpan * lens[i] + j * writeSpan;
					for (int l = 0; l < writeSpan; l++) {
						String oldString;
						oldString = finalString[start + l];
						String newString = "";
						if (oldString == "") {
							newString = number;
						} else {
							newString = oldString + ',' + number;
						}

						finalString[start + l] = newString;
					}
				}
			}
		}
		return finalString;
	}

	String[] keyString(TreeObj[] trees) {

		ArrayList<ArrayList<String>> keyList = allKeyList(trees);
		int count = 1;
		int[] lens = new int[(int) keyList.size()];
		for (int i = 0; i < keyList.size(); i++) {
			lens[i] = (int) keyList.get(i).size();
			count *= lens[i];
		}

		return combineTrees(keyList, count, lens);
	}

}
