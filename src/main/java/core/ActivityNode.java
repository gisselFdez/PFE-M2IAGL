package core;
/**
 * Describes the node that contains the activity details
 * @author Ana Gissel
 *
 */
public class ActivityNode {
	private String activity;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public ActivityNode(String activity){
		this.activity = activity;
	}
}
