package com.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	public final static String STUDENT = "STUDENT";	
	public final static String SAVED = "SAVED";	
	public final static String ALREADY_PRESENT = "ALREADY_PRESENT";	
	public final static String ERROR = "ERROR";
	public final static String QUESTION_NOT_FOUND = "QUESTION_NOT_FOUND";
	public static final String DELETED = "DELETED";
	public static final String QUIZ_NOT_FOUND = "QUIZ_NOT_FOUND";
	public static final String PROHIBITED_TO_DELETE_CORRECT_ANSWER = "PROHIBITED_TO_DELETE_CORRECT_ANSWER";
	public static Map<Long, Integer> lastQuestionNumber = new HashMap<Long, Integer>();
	public static Map<Long, Integer> lastAnswerNumber = new HashMap<Long, Integer>();
}
