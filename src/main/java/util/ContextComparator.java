package util;

import java.util.Comparator;

import core.ContextNode;

public class ContextComparator implements Comparator<ContextNode> {
    @Override
    public int compare(ContextNode o1, ContextNode o2) {
		if(o1.getManufacturer().equalsIgnoreCase(o2.getManufacturer())){
	        return 0;
	     }
	    return 1;
	}
}
