package view.components.reservationComponents;

import model.hotel.Hotel;
import model.hotel.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ReservationPanelGuest extends JPanel {
    private JTable requestsTable;
    private JTable reservationsTable;

    public ReservationPanelGuest() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create the requests table
        DefaultTableModel requestsTableModel = new DefaultTableModel(new Object[]{"Room Number", "Email", "Phone", "Check In", "Check Out", "Adults", "Children", "Total Cost", "Actions"}, 0);
        requestsTable = new JTable(requestsTableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 8; // Only the Actions column is editable
            }
        };

        // Create the reservations table
        DefaultTableModel reservationsTableModel = new DefaultTableModel(new Object[]{"Room Number", "Email", "Phone", "Check In", "Check Out", "Adults", "Children", "Total Cost"}, 0);
        reservationsTable = new JTable(reservationsTableModel);

        // Add the tables to scroll panes
        JScrollPane requestsScrollPane = new JScrollPane(requestsTable);
        JScrollPane reservationsScrollPane = new JScrollPane(reservationsTable);

        // Add the scroll panes to the panel
        add(requestsScrollPane);
        add(reservationsScrollPane);
        reservationsTableModel.addRow(new Object[]{"rahim" ,"rahim " ,"rahim","rahim","rahim","rahim","rahim","rahim","rahim"});


        // Populate the requests table with data
        System.out.print(Hotel.getReservationRequests().values());
        for (Reservation r : Hotel.getReservationRequests().values()) {
            Object[] row = new Object[9];
            row[0] = r.getRoomNumber();
            row[1] = r.getGuestEmail();
            row[2] = r.getPhoneNumber();
            row[3] = r.getCheckInDate();
            row[4] = r.getCheckOutDate();
            row[5] = r.getAdults();
            row[6] = r.getChildren();
            row[7] = r.getTotalCost();

            // Add a button to the Actions column
            row[8] = "Accept/Decline";
            requestsTableModel.addRow(row);
        }

        // Add an action listener to the Accept/Decline button
        Action acceptDecline = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Reservation r = Hotel.getReservationRequests().get(requestsTableModel.getValueAt(modelRow, 0)); // Get the reservation

                // Ask the user if they want to accept or decline the reservation
                int response = JOptionPane.showConfirmDialog(null, "Do you want to accept this reservation?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    // Move the reservation to the reservations table
                    reservationsTableModel.addRow(new Object[]{r.getRoomNumber(), r.getGuestEmail(), r.getPhoneNumber(), r.getCheckInDate(), r.getCheckOutDate(), r.getAdults(), r.getChildren(), r.getTotalCost()});
                    requestsTableModel.removeRow(modelRow);
                    r.setConfirmed(true);
                } else {
                    // Color the row red
                    for (int i = 0; i < requestsTable.getColumnCount(); i++) {
                        requestsTable.getCellRenderer(modelRow, i).getTableCellRendererComponent(requestsTable, requestsTableModel.getValueAt(modelRow, i), false, false, modelRow, i).setBackground(Color.RED);
                    }
                    r.setConfirmed(false);
                }
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(requestsTable, acceptDecline, 8);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
}
