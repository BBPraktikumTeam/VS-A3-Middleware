package mware_lib;

import java.util.HashMap;
import java.util.Map;

final class LocalSkeletons {
	private static Map<String, Skeleton> localSkeletons = new HashMap<String, Skeleton>();

	static void addLocalSkeleton(String name, Skeleton skeleton) {
		localSkeletons.put(name, skeleton);
	}

	static Skeleton getLocalSkeleton(String name) {
		return localSkeletons.get(name);
	}
}
