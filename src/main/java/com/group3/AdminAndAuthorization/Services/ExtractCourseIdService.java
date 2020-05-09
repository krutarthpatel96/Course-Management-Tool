package com.group3.AdminAndAuthorization.Services;

public class ExtractCourseIdService implements IExtractCourseIdService {
	String courseId;

	public ExtractCourseIdService(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String extractCourseId() {
		System.out.println("insideL " + this.courseId);
		int index = this.courseId.lastIndexOf('-');
		this.courseId = this.courseId.substring(0, index);
		return this.courseId;
	}
}