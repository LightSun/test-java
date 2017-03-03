package com.heaven7.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * runnable Controller
 * 
 * @author heaven7
 */
public class RunnableController {

	private final List<Runner> runners;

	public RunnableController() {
		runners = new ArrayList<Runner>();
	}

	public RunnableController(Runnable... rs) {
		runners = new ArrayList<Runner>();
		for (int i = 0, size = rs.length; i < size; i++) {
			runners.add(new Runner(rs[i], i));
		}
	}

	/**
	 * @param flags
	 *            should be 1-2-4-8-16-32...and etc. one or sum of them.
	 */
	public void runByFlags(int flags) {
		Runner runner = null;
		for (int i = 0, size = runners.size(); i < size; i++) {
			runner = runners.get(i);
			if ((flags & runner.flag) != 0)
				runner.r.run();
		}
		// runners.clear();
	}

	public void runByFlags(int flags, Runnable... rs) {
		runners.clear();
		for (int i = 0, size = rs.length; i < size; i++) {
			runners.add(new Runner(rs[i], i));
		}
		runByFlags(flags);
	}

	public void clearTasks() {
		runners.clear();
	}

	private static class Runner {
		public final Runnable r;
		public final int flag;

		/** index from 0 */
		public Runner(Runnable r, int index) {
			this.r = r;
			if (index < 0 || index > 29) { // 2^30 (log2 Integer.MAX_VALUE >=30
											// && <31)
				throw new IllegalArgumentException("must: index >=0 && index<=29");
			}
			flag = (int) Math.pow(2, index);
		}
	}

}
