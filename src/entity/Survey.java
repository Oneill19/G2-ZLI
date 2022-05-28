package entity;

import java.io.Serializable;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Survey implements Serializable {
	private final int surveyId;
	private String surveyName;
	private String[] questions;
	
	/**
	 * @param surveyId
	 * @param questions
	 */
	public Survey(int surveyId, String surveyName, String[] questions) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.questions = questions;
	}

	/**
	 * @return the surveyId
	 */
	public int getSurveyId() {
		return surveyId;
	}
	
	/**
	 * @return the survey name
	 */
	public String getSurveyName() {
		return surveyName;
	}

	/**
	 * @param set the survey name
	 */
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	/**
	 * @return the questions
	 */
	public String[] getQuestions() {
		return questions;
	}

	/**
	 * @param set the questions
	 */
	public void setQuestions(String[] questions) {
		this.questions = questions;
	}
}
