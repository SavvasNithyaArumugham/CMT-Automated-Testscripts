package com.pearson.automation.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPicker {

	public static int getRandomList(List list) {
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    System.out.println("\nIndex :" + index );
	    return index;
	}
}
