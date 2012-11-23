package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSkeletonBindings {

	@Test
	public void testSkeletonBindings() {
		assertNull(SkeletonBindings.getSkeleton("name_testSkeletonBindings_01"));

		Skeleton skel = new DummySkeleton();
		SkeletonBindings.addSkeleton(skel);
		assertSame(skel,
				SkeletonBindings.getSkeleton("name_testSkeletonBindings_01"));
	}
}
