package test2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// int N, M;

		String input = scanner.nextLine();
		String[] NM = input.split(" ");
		int n = Integer.parseInt(NM[0]);
		int m = Integer.parseInt(NM[1]);

		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			int num = scanner.nextInt();
			nums[i] = num;
		}

		int res = 100000;
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					tmp = m - (nums[i] + nums[j] + nums[k]);
					if (tmp >= 0 && tmp < res) {
						res = tmp;
					}
				}
			}
		}
		System.out.println(m - res);
	}
}
