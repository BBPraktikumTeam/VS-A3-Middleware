package mware_lib;

import java.util.HashMap;
import java.util.Map;

final class SkeletonBindings {
	private static Map<String, Skeleton> localSkeletons = new HashMap<String, Skeleton>();

	static void addSkeleton(Skeleton skeleton) {
		localSkeletons.put(skeleton.name(), skeleton);
	}

	static Skeleton getSkeleton(String name) {
		return localSkeletons.get(name);
	}
}
