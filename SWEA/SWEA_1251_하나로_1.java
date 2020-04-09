package SWEA;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SWEA_1251_하나로 {
	
	public static int N, parents[];
	public static Node nodes[], lines[];
	public static double E;
	public static long min;
	
	public static class Node {
		int x, y;
		long weight;
		
		Node(int x, int y, long weigth){
			this.x = x;
			this.y = y;
			this.weight = weigth;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N];
			nodes = new Node[N];
			lines = new Node[N * (N - 1) / 2];
			min = 0;
			String input1[] = br.readLine().split(" ");
			String input2[] = br.readLine().split(" ");
			//환경 부담 세율 실수
			E = Double.parseDouble(br.readLine());
			
			for(int i = 0; i < N; i++) 
				nodes[i] = new Node(Integer.parseInt(input1[i]), Integer.parseInt(input2[i]), 0);
			
			int cnt = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					lines[cnt++] = new Node(i, j, calcWeight(nodes[i].x, nodes[j].x, nodes[i].y, nodes[j].y));
				}
			}
			
			Arrays.fill(parents, -1);
			Arrays.sort(lines, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return Long.compare(o1.weight, o2.weight);
				}
			});
			
			for(int i = 0; i < lines.length; i++) {
				if(union(lines[i].x, lines[i].y)) {
					min += lines[i].weight;
				}
			}
			System.out.println("#" + t + " " + Math.round(E * min));
		}
	}
	
	public static long calcWeight(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	public static int find(int x) {
		if (parents[x] < 0) return x;
        return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
            return true;
        }
        return false;
	}
}
