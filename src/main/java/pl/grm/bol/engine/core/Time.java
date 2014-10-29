package pl.grm.bol.engine.core;

public class Time {
	private static double delta;
	private static final long SECOND = 1000000000L;

	public static double getTime() {
		return (double)System.nanoTime() / (double)SECOND;
	}
}
