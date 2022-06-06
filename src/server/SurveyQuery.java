package server;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Survey;
import entity.SurveyReport;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class SurveyQuery {
	/**
	 * get all the surveys from the data base
	 * 
	 * @param con
	 * @return array of surveys
	 */
	public static ReturnCommand getAllSurveys(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey";
		ArrayList<Survey> surveys = new ArrayList<>();
		
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int surveyId = rs.getInt(1);
				String surveyName = rs.getString(2);
				String[] questions = new String[6];
				questions[0] = rs.getString(3);
				questions[1] = rs.getString(4);
				questions[2] = rs.getString(5);
				questions[3] = rs.getString(6);
				questions[4] = rs.getString(7);
				questions[5] = rs.getString(8);
				surveys.add(new Survey(surveyId, surveyName, questions));
			}
			return new ReturnCommand("GetAllSurveys", surveys);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get survey by id
	 * 
	 * @param con
	 * @param surveyId
	 * @return Survey
	 */
	public static ReturnCommand getSurveyById(Connection con, int surveyId) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey WHERE SurveyID=" + surveyId + ";";
		Survey survey = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				String surveyName = rs.getString(2);
				String[] questions = new String[6];
				questions[0] = rs.getString(3);
				questions[1] = rs.getString(4);
				questions[2] = rs.getString(5);
				questions[3] = rs.getString(6);
				questions[4] = rs.getString(7);
				questions[5] = rs.getString(8);
				survey = new Survey(surveyId, surveyName, questions);
			}
			return new ReturnCommand("GetSurveyById", survey);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get surveys with report
	 * 
	 * @param con
	 * @return array of surveys
	 */
	public static ReturnCommand getSurveysWithReports(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey WHERE zli.survey.SurveyID IN (SELECT SurveyID FROM zli.survey_reports);";
		ArrayList<Survey> surveys = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int surveyId = rs.getInt(1);
				String surveyName = rs.getString(2);
				String[] questions = new String[6];
				questions[0] = rs.getString(3);
				questions[1] = rs.getString(4);
				questions[2] = rs.getString(5);
				questions[3] = rs.getString(6);
				questions[4] = rs.getString(7);
				questions[5] = rs.getString(8);
				surveys.add(new Survey(surveyId, surveyName, questions));
			}
			return new ReturnCommand("GetSurveysWithReports", surveys);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get a survey report
	 * 
	 * @param con
	 * @param surveyId
	 * @return SurveyReport
	 */
	public static ReturnCommand getSurveyReport(Connection con, int surveyId) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey_reports WHERE SurveyID=" + surveyId + ";";
		SurveyReport surveyReport = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				Survey survey = (Survey) getSurveyById(con, surveyId).getReturnValue();
				String fileName = rs.getString(2);
				int[][] answers = (int[][]) getSurveyAnswers(con, surveyId).getReturnValue();
				surveyReport = new SurveyReport(survey, fileName, answers);
			}
			return new ReturnCommand("GetSurveyReport", surveyReport);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get all the answers of a survey
	 * 
	 * @param con
	 * @param surveyId
	 * @return survey answers matrix
	 */
	public static ReturnCommand getSurveyAnswers(Connection con, int surveyId) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey_answers WHERE SurveyID=" + surveyId + ";";
		int[][] surveyAnswers = new int[6][10];
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				surveyAnswers[0][rs.getInt(3) - 1]++;
				surveyAnswers[1][rs.getInt(4) - 1]++;
				surveyAnswers[2][rs.getInt(5) - 1]++;
				surveyAnswers[3][rs.getInt(6) - 1]++;
				surveyAnswers[4][rs.getInt(7) - 1]++;
				surveyAnswers[5][rs.getInt(8) - 1]++;
			}
			return new ReturnCommand("GetSurveyAnswers", surveyAnswers);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * add survey answers to the data base
	 * 
	 * @param con
	 * @param surveyId
	 * @param userMail
	 * @param answer1
	 * @param answer2
	 * @param answer3
	 * @param answer4
	 * @param answer5
	 * @param answer6
	 * @return true if success
	 */
	public static ReturnCommand addSurveyAnswer(Connection con, int surveyId, String userMail, int answer1, int answer2, int answer3, int answer4, int answer5, int answer6) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO zli.survey_answers (SurveyID, UserEmail, Answer1, Answer2, Answer3, Answer4, Answer5, Answer6) VALUES (?,?,?,?,?,?,?,?);";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, surveyId);
			ps.setString(2, userMail);
			ps.setInt(3, answer1);
			ps.setInt(4, answer2);
			ps.setInt(5, answer3);
			ps.setInt(6, answer4);
			ps.setInt(7, answer5);
			ps.setInt(8, answer6);
			ps.executeUpdate();
			ps.close();
			return new ReturnCommand("AddSurveyAnswer", true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get all the surveys with no report
	 * 
	 * @param con
	 * @return array of surveys
	 */
	public static ReturnCommand getSurveysWithNoReport(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.survey WHERE zli.survey.SurveyID NOT IN (SELECT SurveyID FROM zli.survey_reports);";
		ArrayList<Survey> surveys = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int surveyId = rs.getInt(1);
				String surveyName = rs.getString(2);
				String[] questions = new String[6];
				questions[0] = rs.getString(3);
				questions[1] = rs.getString(4);
				questions[2] = rs.getString(5);
				questions[3] = rs.getString(6);
				questions[4] = rs.getString(7);
				questions[5] = rs.getString(8);
				surveys.add(new Survey(surveyId, surveyName, questions));
			}
			return new ReturnCommand("GetSurveysWithNoReport", surveys);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * add new survey report to the data base
	 * 
	 * @param con
	 * @param surveyId
	 * @param fileName
	 * @param uploadDate
	 * @param filePath
	 * @return true if success
	 */
	public static ReturnCommand addNewSurveyReport(Connection con, String surveyId, String fileName, String uploadDate, String filePath) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO zli.survey_reports(SurveyID,FileName,DateUploaded,PDFFile) VALUES (?,?,?,?)";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, Integer.parseInt(surveyId));
			ps.setString(2, fileName);
			ps.setString(3, uploadDate);
			File pdfFile = new File(filePath);
			FileInputStream fis = new FileInputStream(pdfFile);
			ps.setBinaryStream(4, fis, pdfFile.length());
			ps.executeUpdate();
			return new ReturnCommand("AddNewSurveyReport", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("AddNewSurveyReport", false);
		}
	}
	
	
}
