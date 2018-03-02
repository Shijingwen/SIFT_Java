package tool;
/** 
 * @author : Jingwen Shi
 * @date ：2018-3-2 上午1:24:50 
 * @version 1.0 
 */

public class StringHelper {
/*
	vector<string> StringHelper::mySplit(string &s, char cSplit){
	    vector<string> afterSplit;
	    string tmp;
	    for (int i = 0; i < s.size(); ++i) {
	        if(s[i]==cSplit){
	            afterSplit.push_back(tmp);
	            tmp = "";
	        }else{
	            tmp += s[i];
	        }
	    }
	    if(tmp!=""){
	        afterSplit.push_back(tmp);
	    }
	    return afterSplit;
	}

	template <class Type> Type StringHelper::stringToNum(const string& str){
	    istringstream iss(str);
	    Type num;
	    iss >> num;
	    return num;
	}

	int StringHelper::myIntParse(string &s, int system){
	    int ret = 0;
	    const char *charArr =  s.c_str();
	    for (int i = 0; i < s.size(); ++i) {
	        string tmpString("0");
	        tmpString[0] = s[i];
	        ret = system*ret + stringToNum<int>(tmpString);
	    }
	    return ret;
	}

	string StringHelper::myJoin(vector<string> &arr, char reg){
	    string string;
	    for (int i = 0; i < arr.size(); i++) {
	        if(i==0){
	            string = string + arr[i];
	        }else {
	            string = string + reg + arr[i];
	        }
	    }
	    return string;
	}
*/
	public String toBinaryString(long number, int bits){
	    String ret = "";
	    while(number!=0)
	    {
            int tmp = (int) (number%2); 
	        ret = Integer.toString(tmp)+ret; 
	        number = number >> 1;
	    }
	    int len = ret.length();
	    for (int j = len; j < bits; j++) {
	        ret = '0' + ret;
	    }
	    return ret;
	}
}
