package entity;

import java.io.Serializable;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class SurveyReport implements Serializable {
	private Survey survey;
	private final int[][] answers;
	private String pdfFileName;
	
	/**
	 * @param survey
	 * @param pdfFileName
	 */
	public SurveyReport(Survey survey, String pdfFileName, int[][] answers) {
		this.survey = survey;
		this.pdfFileName = pdfFileName;
		this.answers = answers;
	}

	/**
	 * @return the survey
	 */
	public Survey getSurvey() {
		return survey;
	}

	/**
	 * @param survey the survey to set
	 */
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	/**
	 * @return the pdfFileName
	 */
	public String getPdfFileName() {
		return pdfFileName;
	}

	/**
	 * @param pdfFileName the pdfFileName to set
	 */
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	/**
	 * @return answers array
	 */
	public int[][] getAnswers() {
		return answers;
	}


}
