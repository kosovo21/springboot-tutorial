package io.kosovo21.springboot.util;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CopyHelper {

	public static void copyProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	private static String[] getNullPropertyNames(Object source) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
	}

}
