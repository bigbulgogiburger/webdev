package main;
import lib.lottoFunction;
public class lottoMain {
	
	public static void print(int[] a) {
		for(int i=0;i<a.length;i++) {
			if(i==a.length-1) {
				System.out.print(a[i]);
			}else {
				System.out.print(a[i]+", ");
			}
		}
	}
	public static void main(String[] args) {
		int[] poll = lottoFunction.generate_numbers();
		print(poll);
		System.out.println();
		int[] winning = lottoFunction.draw_winning_numbers();
		print(winning);
		System.out.println();
		System.out.println(lottoFunction.count_matching_numbers(poll, winning));
		System.out.println(lottoFunction.check(poll, winning));
		int[] a = {1,2,3,4,5,6};
		int[] winnerwinner= {1,2,3,4,5,6,7};
		System.out.println(lottoFunction.check(a, winnerwinner));
	
	}

}
