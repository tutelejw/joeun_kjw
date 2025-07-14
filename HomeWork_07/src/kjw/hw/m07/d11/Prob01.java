//public class Util{                   //static 변경?????? private 변경???????????
package kjw.hw.m07.d11;

class Util{                   //static 변경?????? private 변경???????????
	public Util(){
	}

	//인자로 받은 limit 까지의 소수의 갯수를 return 메서드
	public int findPrimeCount(int limit){
		//r구현  / 숫자가 소수인지 판단 - 1부터 limit 까지 개별로 숫자하나씩 받아서 소수 판단? 맞으면 카운트? 결과 리턴
		boolean isPrime = false;
		int primeCnt=0;
			for(int i = 2; i<=limit; i++){			
				isPrime = true;
				for (int j = 2; j<i; j++ ){
					if(i%j == 0 ){
						isPrime = false;
						break;
					}
				}
			if(isPrime){
				primeCnt += 1;
				//System.out.print(i + ((limit) !=i?",":"\n"));
			}
		}
		return primeCnt;
	}

	//인자로 받은 limit 까지의 소수 갯수 배열크기를 갖는 int 배열에 담아 return 메서드 <<--
	public int[] findPrimeReturnArray(int limit){   // limit에는 67이 들어오고
		int primeCnt = findPrimeCount(limit);  // findPrimeCount 호출해서 19개 받아오고
		int prime[] = new int[primeCnt]; //19개 방을 만들고..
		
		int tmp = 0;
		boolean isPrime = false;
		
		for(int i = 2; i<=limit; i++){			
			isPrime = true;
			for (int j = 2; j<i; j++ ){
				if(i%j == 0 ){
					isPrime = false;
					break;
				}
				if (isPrime ) {
					prime[tmp] = i;
					tmp++;
					//System.out.print("prime" + prime[tmp] );
				}
				
			}
			
		}
	return prime;
	}
}//out of class


public class Prob01 {
	public static void main (String[] args){
		int limit = 67;
		
		Util util = new Util();
		
		int primeCount = util.findPrimeCount(limit);
		System.out.println("1~" + limit + "까지의 소수의 개수는 " + primeCount + "ea 이며 소수는 아래와 같다.");
		System.out.println("////////////////////////////////////////////////////////");

		int[] primeArray=util.findPrimeReturnArray(limit);
		System.out.print("primeArray---" + primeArray);

		for (int i = 0; i < primeArray.length;i++ )	{
		System.out.print(primeArray[i] + ((primeArray.length-1) != i?",":""));
		}
	}//out of main
}// out of class










