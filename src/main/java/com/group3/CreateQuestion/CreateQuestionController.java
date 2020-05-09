package com.group3.CreateQuestion;

import com.group3.CreateQuestion.BusinessModels.*;
import com.group3.CreateQuestion.DAO.*;
import com.group3.CreateQuestion.Services.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CreateQuestionController {
	private String title, question, type;
	private ArrayList<QuestionTypes> questionTypesList;

	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO;
	ICurrentTimeStampGenerationService currentTimeStampGenerationService;
	IRetrieveQuestionTypesDAO retrieveQuestionTypesDAO;
	IObtainAllQuestionTypesService obtainAllQuestionTypesService;
	IValidationRulesLoaderDAO validationRulesLoaderDAO;
	IStringValidatorService stringValidatorService;
	IQuestionService questionService;

	Logger logger = LogManager.getLogger(CreateQuestionController.class);

	public CreateQuestionController() {

		daoInjector = DAOAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		currentTimeStampGenerationService = serviceAbstractFactory.createCurrentTimeStampGenerationService();
		retrieveQuestionTypesDAO = daoInjector.createRetrieveQuestionTypesDAO();
		obtainAllQuestionTypesService = serviceAbstractFactory
				.createObtainAllQuestionTypesService(retrieveQuestionTypesDAO);
		validationRulesLoaderDAO = daoInjector.createValidationRulesLoaderDAO();
		stringValidatorService = serviceAbstractFactory.createStringValidatorService(validationRulesLoaderDAO);
	}

	@RequestMapping("/requestQuestionPage")
	public String renderQuestionGenerationPage(Model model) {

		questionTypesList = new ArrayList<>();
		logger.info("createQuestionController invocation");

		questionTypesList = obtainAllQuestionTypesService.getAllQuestionTypes();
		logger.info("question types list length " + questionTypesList.size());
		model.addAttribute("questionTypesList", questionTypesList);

		return "mainQuestionPage.html";
	}

	@RequestMapping("/requestAnswerPage")
	public String renderAnswerPage(Model model, String questionTypeSelector, @RequestParam("title") String title,
			@RequestParam("question") String question) {

		logger.info("response type :: " + questionTypeSelector);

		IReturnControllerPathService returnControllerPathService;
		String controllerPath = new String();

		if (stringValidatorService.isValid(title) && stringValidatorService.isValid(question)
				&& questionTypeSelector.equalsIgnoreCase("Select Type") == false) {

			logger.info("valid title and question after validation : " + title + " " + question);
			this.title = title;
			this.question = question;
			this.type = questionTypeSelector;
			questionService = serviceAbstractFactory.createMakeQuestionGenerationAbstractFactory()
					.makeQuestion(questionTypeSelector);
			returnControllerPathService = serviceAbstractFactory.createReturnControllerPathService();
			logger.info("Question Type decision from parent class :: " + questionService.getQuestionType());
			controllerPath = returnControllerPathService.returnControllerPath(questionService.getQuestionType());
			logger.info("Question Controller Path :: " + controllerPath);
		}

		return "redirect:/" + controllerPath;
	}

	@RequestMapping("/invokeFreeText")
	public String renderFreeTextAnswerPage() {

		String id;
		logger.log(Level.INFO, "REQUEST FORWARDED TO INVOKE FREE TEXT QUESTION GENERATION CONTROLLER! ");
		saveBasicQuestionInformationDAO = daoInjector
				.createSaveBasicQuestionInformationDAO(currentTimeStampGenerationService);
		id = questionService.saveBasicQuestionInformation(title, question, type, saveBasicQuestionInformationDAO);
		logger.log(Level.INFO, "Question id returned from database " + id);

		return "mainQuestionPage.html";
	}

	@RequestMapping("/invokeNumeric")
	public String renderNumericQuestion() {

		String id;
		logger.log(Level.INFO, "REQUEST FORWARDED TO INVOKE NUMERIC QUESTION GENERATION CONTROLLER! ");
		saveBasicQuestionInformationDAO = daoInjector
				.createSaveBasicQuestionInformationDAO(currentTimeStampGenerationService);
		id = questionService.saveBasicQuestionInformation(title, question, type, saveBasicQuestionInformationDAO);
		logger.log(Level.INFO, "Question id returned from database " + id);

		return "mainQuestionPage.html";
	}

	@RequestMapping("/invokeMCQSOne")
	public String rendermcqsPage() {

		return "MCQSChooseOneAnswerUpdatePage.html";
	}

	@RequestMapping("/createMCQSChooseOne")
	public String renderMCQSChooseOne(MCQAnswers mcqsanswer) {

		String questionId;
		int success;
		IServiceAbstractFactory iServiceAbstractFactory = ServiceAbstractFactory.instance();
		ISplitMCQSAnswerService iSplitMCQSAnswerService = iServiceAbstractFactory.createSplitMCQSAnswerService();
		ISaveMCQAnswerstoDataBaseDAO iSaveMCQAnswerstoDataBaseDAO = daoInjector.createSaveMCQAnswertoDataBaseDAO();
		ArrayList<MCQAnswers> mcqAnswersList = mcqsanswer.splitAnswerSevice(iSplitMCQSAnswerService, mcqsanswer);
		MCQAnswers mcqAnswers = BusinessModelAbstractFactory.instance().createMCQSAnswers();
		logger.log(Level.INFO, "mcq Answer List " + mcqAnswersList.size());

		saveBasicQuestionInformationDAO = daoInjector
				.createSaveBasicQuestionInformationDAO(currentTimeStampGenerationService);
		questionId = questionService.saveBasicQuestionInformation(title, question, type,
				saveBasicQuestionInformationDAO);
		success = mcqAnswers.saveAnswerstoDatabase(Integer.parseInt(questionId), mcqAnswersList,
				iSaveMCQAnswerstoDataBaseDAO, (SaveMCQAnswerstoDataBaseService) questionService);
		logger.log(Level.INFO, "mcq Answer List " + success);

		return "redirect:/requestQuestionPage";
	}

}
