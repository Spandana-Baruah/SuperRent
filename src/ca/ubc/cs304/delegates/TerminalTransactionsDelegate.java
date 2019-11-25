package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.ReservationModel;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case SuperRent).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * SuperRent is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	public void deleteReservation(int branchId);
	public void insertReservation(ReservationModel model);
	public void showReservations();
	public void updateReservation(int branchId, String name);
	public void terminalTransactionsFinished();
}
