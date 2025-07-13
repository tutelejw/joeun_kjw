package kjw.m07.d14;

public class Prob21 {

	private static String replaceString(String[]arr){
        String targetStr=null;
        int aCount=-1;

        for(String s:arr){
            int currentACount=0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='a'){
                    currentACount++;
                }
            }
            if(currentACount>aCount){
                aCount=currentACount;
                targetStr=s;
            }
        }

        if(targetStr!=null){
            String tempStr="";
            for(int i=0;i<targetStr.length();i++){
                tempStr+=(targetStr.charAt(i)=='a'?'A':targetStr.charAt(i));
            }
           
            targetStr=tempStr;
            return targetStr;
        }else{
            return "";
        }
    }

    public static void main(String[] args) {
        String[] arr={"java program", "array", "java program area", "append"};
        String result=Prob21.replaceString(arr);
        System.out.println("변경된 문자열 : "+result);
    }//end of main
}//end of class

