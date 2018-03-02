package tool;
/** 
 * @author : Jingwen Shi
 * @date ：2018-3-2 上午12:27:50 
 * @version 1.0 
 */
public class MathHelper {
	public float calVolum(float[][] box)
	{
	    float tmp = 1;
	    for (int j = 0; j < box.length; j++) {
	        tmp *= box[j][1] - box[j][0];
	    }
	    return tmp;
	}
}
