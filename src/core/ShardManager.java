package core;

import tool.StringHelper;

/** 
 * @author : Jingwen Shi
 * @date ：2018-3-2 下午5:24:35 
 * @version 1.0 
 */
public class ShardManager {
	 	int preBits;
		int numMachine;
		int[] mapMachine;
	public void setInfo(int numMachine){

	    this.numMachine = numMachine;
	    this.preBits = moveBits(numMachine);
	    this.mapMachine = disStrategy(this.preBits, numMachine);

//	    for (int j = 0; j < this.mapMachine.length; j++) {
//	    	System.out.println(this.mapMachine[j]);
//	    }
	}

	public int moveBits(int numMachine){
	    int i = 0;
	    while(Math.pow(2, i) < numMachine){
	        i++;
	    }
	    return i;
	}

	public int[] disStrategy(int preBits, int numMachine){

	    int numPreSpace = (int)Math.pow(2, preBits);
        int[] mapMachine = new int[numPreSpace];
        int count = 0;
	    for (int j = 0; j < numPreSpace; j++) {
	        if(j<numMachine){
	            mapMachine[j] = j;
	        }else{
	        	 mapMachine[j] = ((int) (Math.random()*100)%numMachine);
	        }
	    }
	    return mapMachine;
	}

	public int[] getMachines(String[] keyStrings){

	    int lenKeys = keyStrings.length;
	    int[] machine= new int[lenKeys];

	    for (int i = 0; i < lenKeys; i++) {
	        String preString =  keyStrings[i].split(",")[0];     
	        String tmp = "";
	        if(preBits <= lenKeys){
	            for (int j = 0; j < preBits; j++) {
	                tmp = tmp+preString.toCharArray()[j];
	            }
	        }else{
	            for (int j = 0; j < keyStrings.length; j++) {
	                tmp = tmp+preString.toCharArray()[j];
	            }
	            for (int j = 0; j < preBits - lenKeys; j++) {
	                tmp = '0' + tmp;
	            }
	        }
	        machine[i]= this.mapMachine[Integer.parseInt(tmp, 2)];
	    }
	    return machine;
	}
}
