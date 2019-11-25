package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.ReservationModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	public TerminalTransactions() {
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 5) {
			System.out.println();
			System.out.println("1. Make reservation");
			System.out.println("2. Delete reservation");
			System.out.println("3. Update reservation");
			System.out.println("4. Show all reservations");
			System.out.println("5. Quit");
			System.out.print("Please choose one of the above 5 options: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:
                    try {
                        handleInsertOption();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
				case 2:  
					handleDeleteOption(); 
					break;
				case 3: 
					handleUpdateOption();
					break;
				case 4:  
					delegate.showReservations();
					break;
				case 5:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
				}
			}
		}		
	}
	
	private void handleDeleteOption() {
		int branchId = INVALID_INPUT;
		while (branchId == INVALID_INPUT) {
			System.out.print("Please enter the Reservation you wish to delete: ");
			branchId = readInteger(false);
			if (branchId != INVALID_INPUT) {
				delegate.deleteReservation(branchId);
			}
		}
	}
	
	private void handleInsertOption() throws ParseException {
		String location = null;
		while (location == null || location.length() <= 0 || !(location.getClass().getSimpleName().equals("test".getClass().getSimpleName()))) {
			System.out.print("Please enter the location where you want to make your Reservation: ");
			location = readLine().trim();
		}
		
		String vtname = null;
		while (vtname == null || vtname.length() <= 0) {
			System.out.print("Please enter the type of the vehicle you wish to Reserve: ");
			vtname = readLine().trim();
		}

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter your name: ");
            name = readLine().trim();
        }

        Long cellphone = null;
        while (cellphone == null) {
            System.out.print("Please enter your cellphone number: ");
            cellphone = readLong(true);
        }

        String fromDate = null;
        java.sql.Date sqlFromDate = null;
        while (fromDate == null || fromDate.length() <= 0) {
            System.out.print("Please enter the date from when you want to make your Reservation: ");
            fromDate = readLine().trim();
            DateFormat fromDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date parsedUtilDate = fromDateFormat.parse(fromDate);
            sqlFromDate = new java.sql.Date(parsedUtilDate.getTime());
        }

        String fromTime = null;
        java.sql.Timestamp sqlFromTime = null;
      //  while (fromTime.length() <= 0) {
            System.out.print("Please enter the time from when you want to make your Reservation: ");
            SimpleDateFormat fromTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            java.util.Date parsedFromDate = fromTimeFormat.parse(fromTime);
            sqlFromTime = new java.sql.Timestamp(parsedFromDate.getTime());
       // }


        String toDate = null;
        java.sql.Date sqlToDate = null;
        while (toDate == null || toDate.length() <= 0) {
            System.out.print("Please the date till when you want to make your Reservation: ");
            toDate = readLine().trim();
            DateFormat toDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date parsedUtilDate = toDateFormat.parse(toDate);
            sqlToDate = new java.sql.Date(parsedUtilDate.getTime());
        }

        String toTime = null;
        java.sql.Timestamp sqlToTime = null;
     //   while (toTime == null || toTime.length() <= 0) {
            System.out.print("Please enter the time till when you want to make your Reservation: ");
            SimpleDateFormat toTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            java.util.Date parsedToDate = toTimeFormat.parse(toTime);
            sqlToTime = new java.sql.Timestamp(parsedToDate.getTime());
      //  }

        ReservationModel model = new ReservationModel(location,
											vtname,
											name,
											cellphone,
											sqlFromDate,
											sqlFromTime,
											sqlToDate,
											sqlToTime);
		delegate.insertReservation(model);
	}
	
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	
	private void handleUpdateOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the Reservation you wish to update: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the Reservation you wish to update: ");
			name = readLine().trim();
		}

		delegate.updateReservation(id, name);
	}
	
	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}

    private long readLong(boolean allowEmpty) {
        String line = null;
        long input = INVALID_INPUT;
        try {
            line = bufferedReader.readLine();
            input = Long.parseLong(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not correct");
            }
        }
        return input;
    }
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
