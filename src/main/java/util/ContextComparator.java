package util;

import java.util.Comparator;

import core.StaticContextNode;

public class ContextComparator implements Comparator<StaticContextNode> {
    @Override
    public int compare(StaticContextNode o1, StaticContextNode o2) {
		if(o1.getManufacturer().equalsIgnoreCase(o2.getManufacturer())){
	        return 0;
	     }
	    return 1;
	}
}
