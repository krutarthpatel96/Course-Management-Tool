package com.group3.groupmanager;

import com.group3.AdminAndAuthorization.*;
import com.group3.CreateQuestion.DeleteQuestionController;
import com.group3.CreateQuestion.QuestionManagerActionController;
import com.group3.CreateQuestion.RetrieveQuestionController;

import com.group3.CreateQuestion.CreateQuestionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.annotation.ComponentScan;

import com.group3.Course.CourseController;
import com.group3.ForgotPassword.ForgetPasswordController;
import com.group3.Login.LoginController;
import com.group3.SignUp.UserDetailsController;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackageClasses = { DeleteQuestionController.class, RetrieveQuestionController.class,
		QuestionManagerActionController.class, CreateQuestionController.class, ForgetPasswordController.class,
		CourseController.class, LoginController.class, UserDetailsController.class, CreateCourseController.class,
		AdminDashBoardMainPageController.class, AdminLogOutController.class, DeleteCourseController.class,
		GrantInstructorAccessController.class, LogoutAdminController.class, ViewCourseController.class,
		CreateQuestionController.class })
public class GroupmanagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(GroupmanagerApplication.class, args);

	}

}
