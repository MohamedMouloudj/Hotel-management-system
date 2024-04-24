package view.UserMangementGui;

import net.miginfocom.swing.MigLayout;
import view.components.items.MyButton;
import view.components.items.MyTextField;
import view.components.sacrollBar.ScrollBar;
import view.components.table.PanelBorder;
import view.components.table.Table;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public abstract class UserManagement extends JPanel {

        protected String collectionName;
        protected JPanel userInputPanel;
        protected JPanel mainPanel;
        protected JPanel buttonPanel;
        protected MyTextField nameField;
        protected MyTextField lastNameField;
        protected MyTextField emailField;
        protected Table table;

        // Constructor
        public UserManagement(String collectionName) {
                this.collectionName = collectionName;
                initComponents(); // Initialize UI components
        }

        // Abstract methods
        public abstract String[] getCollumnNames();

        public abstract ActionListener addActionListener();

        public abstract ActionListener deleteActionListener();

        public abstract ActionListener clearActionListener();

        public abstract ActionListener updateActionListener();

        // Initialize UI components
        private void initComponents() {
                // Creating main layered panel
                JLayeredPane panel = new JLayeredPane();
                // Creating panel border
                PanelBorder panelBorder1 = new PanelBorder();
                // Creating label
                JLabel jLabel1 = new JLabel();
                // Creating scroll pane for the table
                JScrollPane spTable = new JScrollPane();
                // Creating table
                table = new Table();

                // Setting background color for the panel
                setBackground(new Color(242, 242, 242));

                // Setting layout for the main panel
                panel.setLayout(new GridLayout(1, 0, 10, 0));

                // Adding user management panel to the main panel
                panel.add(createUserManagementPanel());

                // Setting background and font for the label
                panelBorder1.setBackground(Color.WHITE);
                jLabel1.setFont(new Font("SansSerif", Font.BOLD, 18));
                jLabel1.setForeground(new Color(127, 127, 127));
                jLabel1.setText(collectionName); // Setting text for the label

                // Setting border for the scroll pane
                spTable.setBorder(null);

                // Setting model for the table
                table.setModel(new DefaultTableModel(new Object[][] {}, getCollumnNames()) {
                        private final boolean[] canEdit = new boolean[getCollumnNames().length];
                        {
                                Arrays.fill(canEdit, false);
                        }

                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                // Setting table view in the scroll pane
                spTable.setViewportView(table);

                // Setting layout for the panel border
                GroupLayout panelBorder1Layout = new GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                                panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addGap(20)
                                                                .addGroup(panelBorder1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(spTable))
                                                                .addContainerGap()));
                panelBorder1Layout.setVerticalGroup(
                                panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addGap(20)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(spTable, GroupLayout.DEFAULT_SIZE, 279,
                                                                                Short.MAX_VALUE)
                                                                .addGap(20)));

                // Setting layout for the main panel
                GroupLayout layout = new GroupLayout(this);
                setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(panelBorder1,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(panel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                875, Short.MAX_VALUE))
                                                                .addGap(20)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20)
                                                                .addComponent(panel, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(20)
                                                                .addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(20)));

                // Setting vertical scrollbar for the table
                spTable.setVerticalScrollBar(new ScrollBar());
                // Setting background color for the vertical scrollbar and viewport
                spTable.getVerticalScrollBar().setBackground(Color.WHITE);
                spTable.getViewport().setBackground(Color.WHITE);
                // Setting corner panel color to match background
                JPanel corner = new JPanel();
                corner.setBackground(Color.WHITE);
                spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner);
        }

        private JPanel createUserManagementPanel() {
                // Creating main panel with MigLayout
                mainPanel = new JPanel(new MigLayout("wrap 2, insets 20", "[65%,fill][30%,fill]"));

                // Creating user input panel with MigLayout
                userInputPanel = new JPanel(new MigLayout("wrap 2, insets 0", "[30%][grow]"));
                // Creating titled border for user input panel
                Border border = BorderFactory.createTitledBorder(collectionName + " Information");
                userInputPanel.setBorder(border);

                // Creating JLabels for user input
                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14)); // Setting font for nameLabel
                JLabel lastNameLabel = new JLabel("LastName:");
                lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14)); // Setting font for lastNameLabel
                JLabel emailLabel = new JLabel("Email:");
                emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14)); // Setting font for emailLabel

                // Creating MyTextFields for user input
                nameField = new MyTextField("Name", "/view/icons/user.png");
                lastNameField = new MyTextField("LastName", "/view/icons/user.png");
                emailField = new MyTextField("Email", "/view/icons/mail.png");

                // Adding components to the user input panel with MigLayout constraints
                userInputPanel.add(nameLabel);
                userInputPanel.add(nameField, "growx, width 60%");
                userInputPanel.add(lastNameLabel);
                userInputPanel.add(lastNameField, "growx, width 60%");
                userInputPanel.add(emailLabel);
                userInputPanel.add(emailField, "growx, width 60%");
                userInputPanel.setBackground(Color.WHITE);

                // Creating button panel with MigLayout
                buttonPanel = new JPanel(new MigLayout("wrap", "push[center]push", "push[][]10[]10[]10[]25[]push"));
                // Adding empty JLabel for spacing
                buttonPanel.add(new JLabel(), "wrap, height 20");

                // Creating and configuring add button
                MyButton addButton = new MyButton("Add");
                addButton.setBackground(new Color(0x00A550));
                addButton.addActionListener(addActionListener());
                // Adding add button to the button panel
                buttonPanel.add(addButton, "w 50%, h 34");

                // Creating and configuring delete button
                MyButton deleteButton = new MyButton("Delete");
                deleteButton.setBackground(new Color(0xED1B24));
                deleteButton.addActionListener(deleteActionListener());
                // Adding delete button to the button panel
                buttonPanel.add(deleteButton, "w 50%, h 34");

                // Creating and configuring update button
                MyButton updateButton = new MyButton("Update");
                updateButton.setBackground(new Color(0x008080));
                updateButton.addActionListener(updateActionListener());
                // Adding update button to the button panel
                buttonPanel.add(updateButton, "w 50%, h 34");

                // Creating and configuring clear button
                MyButton clearButton = new MyButton("Clear");
                clearButton.setBackground(new Color(0x55DD33));
                clearButton.addActionListener(clearActionListener());
                // Adding clear button to the button panel
                buttonPanel.add(clearButton, "w 50%, h 34");

                // Setting background color for the button panel
                buttonPanel.setBackground(Color.WHITE);

                // Adding user input panel and button panel to the main panel with MigLayout
                // constraints
                mainPanel.add(userInputPanel, "span 1 2, grow");
                mainPanel.add(buttonPanel, "span 1 2, grow");
                // Setting background color for the main panel
                mainPanel.setBackground(Color.WHITE);

                return mainPanel;
        }

}

// Card card1 = new Card();
// Card card2 = new Card();
// Card card3 = new Card();

// card1.setData(new Model_Card(new
// ImageIcon(getClass().getResource("/com/raven/icon/stock.png")),
// "Stock Total", "$200000", "Increased by 60%"));
// card2.setData(new Model_Card(new
// ImageIcon(getClass().getResource("/com/raven/icon/profit.png")),
// "Total Profit", "$15000", "Increased by 25%"));
// card3.setData(new Model_Card(new
// ImageIcon(getClass().getResource("/com/raven/icon/flag.png")),
// "Unique Visitors", "$300000", "Increased by 70%"));