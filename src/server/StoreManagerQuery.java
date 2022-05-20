package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Order;
import entity.User;


/**
 * @author Topaz Eldori,Koral Biton
 *
 */
public class StoreManagerQuery {

	/**
	 *  method to execute sql query and return a list of not approved users
	 * @param con
	 * @return ReturnCommand 
	 */
	public static ReturnCommand getUsersRegsiter(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE status='' OR status='WaitingForCancelation';";
		ResultSet rs = null;
		ArrayList<User> NotApprovedUsers = new ArrayList<>();
		
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sqlQuery);
					while(rs.next()) {
						NotApprovedUsers.add( new User(rs.getInt(1), // UserID
								rs.getString(2), // FirstName
								rs.getString(3), // LastName
								rs.getString(4), // CreditCard
								rs.getString(5), // Phone
								rs.getString(6), // Email
								rs.getString(7), // Password
								rs.getString(8), // UserRole
								rs.getString(9), // Status
								rs.getBoolean(10) //ask if we can like this !! IsLogged
								));	}
					return new ReturnCommand("GetNotApprovedUsers", NotApprovedUsers);
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
						}
	
				
				/**
				 * method to execute sql query and return a list of Orders awaiting approval or cancellation
				 * @param con
				 * @return ReturnCommand 
				 */
				public static ReturnCommand getPendingOrders(Connection con) {
					Statement stmt;
					String sqlQuery = "SELECT * From zli.orders WHERE status='WaitingForConfirmation' OR status='WaitingForCancelation +';";
					ResultSet rs = null;
					ArrayList<Order> NotAprroveorders = new ArrayList<>();
					
							try {
								stmt = con.createStatement();
								rs = stmt.executeQuery(sqlQuery);
								while(rs.next()) {
									NotAprroveorders.add( new Order(rs.getInt(1), // orderNumber
											rs.getDouble(2), // total price
											rs.getString(3), // greetingCard
											rs.getString(4), // color
											rs.getString(5), // orderDesc
											rs.getString(6), // fromStore
											LocalDate.parse(rs.getString(7)), // orderCreationDate
											LocalTime.parse(rs.getString(8)), // orderCreationTime
											rs.getInt(9), // customerID
											rs.getString(10), // paymentMethod
											rs.getString(11), // orderStatus
											rs.getString(12), // confirmedDate
											rs.getString(13), // completeDate
											rs.getString(14) // deliveryMethod
									));
								}
					 			return new ReturnCommand("GetPendingOrders", NotAprroveorders);
							} catch (SQLException e) {
								e.printStackTrace();
								return null;
							}

							}
	
				/**
				 * @param con
				 * method to execute sql query that update order status to confirm or cancelled 
				 * @param String OrderNumber
				 * @param String OrderStatus
				 * @return ReturnCommand
				 */
				public static ReturnCommand UpdateStatusOrders(Connection con,String OrderNumber,String OrderStatus) {
					Statement stmt;
					String sql="";
					if(OrderStatus.equals("WaitingForConfirmation")){
					 sql = "UPDATE zli.orders SET Status='Confirm' WHERE orderNumber=' " + OrderNumber + "';" ;
				}
				else {
				 sql = "UPDATE zli.orders SET Status='Canceled' WHERE orderNumber ='  " + OrderNumber+ "';";
					}
					try {
						stmt = con.createStatement();
						stmt.executeUpdate(sql);
						return new ReturnCommand("UpdateStatusOrders", null); 
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
					
				
				/**
				 * ] method to execute sql query that update User status  

				 * @param con
				 * @return ReturnCommand
				 */
				public static ReturnCommand GetApprovedUsers(Connection con) {
					Statement stmt;
					String sqlQuery = "SELECT * From zli.users WHERE status='CONFIRMED' OR status='FROZEN';";
					ResultSet rs = null;
					ArrayList<User> ApprovedUserToPer = new ArrayList<>();
					
							try {
								stmt = con.createStatement();
								rs = stmt.executeQuery(sqlQuery);
								while(rs.next()) {
									ApprovedUserToPer.add( new User(rs.getInt(1), // UserID
											rs.getString(2), // FirstName
											rs.getString(3), // LastName
											rs.getString(4), // CreditCard
											rs.getString(5), // Phone
											rs.getString(6), // Email
											rs.getString(7), // Password
											rs.getString(8), // UserRole
											rs.getString(9), // Status
											rs.getBoolean(10) //ask if we can like this !! IsLogged
											));	}
								return new ReturnCommand("GetApprovedUsers", ApprovedUserToPer);
							} catch (SQLException e) {
								e.printStackTrace();
								return null;
							}
									}
				
				public static ReturnCommand UpdateUserStatus(Connection con,String NewStatus,String IDUser) {
					Statement stmt;
					String sql="";
					if(NewStatus.equals("FROZEN")){
					 sql = "UPDATE zli.users SET Status='FROZEN' WHERE UserID=' " + IDUser + "';" ;
				}
				else {
				 sql = "UPDATE zli.orders SET Status='CONFIRMED' WHERE UserID ='  " + IDUser + "';";
					}
					try {
						stmt = con.createStatement();
						stmt.executeUpdate(sql);
						return new ReturnCommand("ChangeUserStatus", null); 
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
				}
							
				

