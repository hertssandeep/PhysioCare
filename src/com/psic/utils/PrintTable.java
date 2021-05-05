package com.psic.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class PrintTable {
		
	public static  void print(List<List<String>> headers, List<String> head) {
		Map<Integer, Integer> map = getMaxLength(headers, head);
		for(int i=0; i< headers.get(0).size(); i++) {
			if(i==0)
				System.out.print("+");
			for(int temp =0; temp<(map.get(i)+4); temp++) {
				System.out.print("-");
			}
			System.out.print("+");
		}
		
		
		System.out.println();
		
		for(int i=0; i<head.size(); i++) {
			System.out.print("|  ");
			System.out.print(head.get(i));
			int spaces = map.get(i);
			for(int j=0; j<(spaces -head.get(i).length()); j++) {
				System.out.print(" ");
			}
			System.out.print("  ");
		}
		System.out.print("|");
		System.out.println();
		for(int i=0; i< headers.get(0).size(); i++) {
			if(i==0)
				System.out.print("+");
			for(int temp =0; temp<(map.get(i)+4); temp++) {
				System.out.print("-");
			}

			System.out.print("+");
		}
		
		System.out.println("");
		
		for(int i=0; i<headers.size(); i++) {
			List<String> row = headers.get(i);
			for(int j=0; j<row.size(); j++) {
				System.out.print("|  ");
				System.out.print(row.get(j));
				int spaces = map.get(j);
				for(int temp =0; temp< (spaces-row.get(j).length()); temp++) {
					System.out.print(" ");
				}
				System.out.print("  ");
				
			}
			System.out.print("|");
			System.out.println("");
		}
		for(int i=0; i< headers.get(0).size(); i++) {
			if(i==0)
				System.out.print("+");
			for(int temp =0; temp<(map.get(i)+4); temp++) {
				System.out.print("-");
			}

			System.out.print("+");
		}
		System.out.println();
	}
	
	public static Map<Integer, Integer> getMaxLength(List<List<String>> list, List<String> headers) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<headers.size(); i++) {
			map.put(i, headers.get(i).length());
		}
		for(int i=0; i<list.size(); i++) {
			List<String> row = list.get(i);
			for(int j=0; j<row.size(); j++) {
				if(map.containsKey(j) && map.get(j) < row.get(j).length()) {
					map.put(j, row.get(j).length());
				}else {
					if(!map.containsKey(j))
						map.put(j, row.get(j).length());
				}
			}
		}
		
		return map;
	}
	
}
