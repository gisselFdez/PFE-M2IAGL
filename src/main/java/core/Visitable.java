package core;

/**
 * Element interface to implement the visitor pattern
 * @author Ana Gissel
 *
 */
public interface Visitable {
	public void accept(Visitor visitor);
}
