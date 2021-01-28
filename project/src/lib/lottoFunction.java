package lib;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
public class lottoFunction {
	
	public static int[] generate_numbers() {
		Set<Integer> lottoSet = new HashSet<Integer>();
		Random rd = new Random();
		while(lottoSet.size()<6) {
			lottoSet.add(rd.nextInt(45)+1);
		}
		int[] lotto = new int[6];
		Iterator<Integer> itLotto = lottoSet.iterator();
		int i =0;
		while(itLotto.hasNext()) {
			
			lotto[i]=itLotto.next();
			i++;
		}
		return lotto;
		
	}
	public static int[] draw_winning_numbers() {
		Set<Integer> lottoSet = new HashSet<Integer>();
		Random rd = new Random();
		while(lottoSet.size()<7) {
			lottoSet.add(rd.nextInt(45)+1);
		}
		int[] lotto = new int[7];
		Iterator<Integer> itLotto = lottoSet.iterator();
		int i =0;
		while(itLotto.hasNext()) {
			
			lotto[i]=itLotto.next();
			i++;
		}
		return lotto;
		
	}
	
	public static int count_matching_numbers(int[] a, int[] b) {
		int count=0;
		for(int i =0;i<a.length;i++) {
			for(int j=0;j<b.length;j++) {
				if(a[i]==b[i]) {
					count+=1;
					break;
					
				}
			}
		}
		return count;
	}
	
	public static int check(int[] numbers_test, int[] winning_numbers_test) {
		int count=0;
		int secondary=0;
		for(int i =0;i<winning_numbers_test.length;i++) {
			for(int j=0;j<numbers_test.length;j++) {
				if(i==6&&winning_numbers_test[i]==numbers_test[j]) {
					secondary=1;
				}
				if(i!=6&&winning_numbers_test[i]==numbers_test[j]) {
					count+=1;
					
				}
			}
		}
		
		if(count==6) {
			return 1000000000;
		}else if(count==5&&secondary==1) {
			return 50000000;
		}else if(count==5&&secondary==0) {
			return 1000000;
		}else if(count==4) {
			return 50000;
		}else if(count==3) {
			return 5000;
		}else {
			return 0;
		}
		
	}

}











