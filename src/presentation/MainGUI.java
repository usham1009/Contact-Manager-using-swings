package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	static String[] currentData = new String[10];
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	private JTextField phoneNumber;
	private JTextField addLine1;
	private JTextField addLine2;
	private JTextField city;
	private JTextField state;
	private JTextField zipcode;
	private JTextField emailID;
	private JTextField country;
	private ButtonGroup group;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	String fn = "";
	String mn = "";
	String ln = "";
	String pn = "";
	String ad1 = "";
	String ad2 = "";
	String s = "";
	String c = "";
	String zc = "";
	String m = "";
	String f = "";
	String[][] data = new String[100][100];
	int i, progress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintWriter create = new PrintWriter(new BufferedWriter(
							new FileWriter("data.txt", true)));
					create.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setFields() {

		firstName.setText(currentData[0]);
		middleName.setText(currentData[1]);
		lastName.setText(currentData[2]);
		phoneNumber.setText(currentData[3]);
		addLine1.setText(currentData[4]);
		addLine2.setText(currentData[5]);
		city.setText(currentData[6]);
		state.setText(currentData[7]);
		zipcode.setText(currentData[8]);
		if (currentData[9].equals(null)) {

		} else if (currentData[9].equals("M"))
			rdbtnMale.setSelected(true);
		else if (currentData[9].equals("F"))
			rdbtnFemale.setSelected(true);
		emailID.setText(currentData[10]);
		country.setText(currentData[11]);
	}

	public int findProgress() {
		progress = 0;
		if (!firstName.getText().trim().equals(""))
			progress++;
		if (!lastName.getText().trim().equals(""))
			progress++;
		if (rdbtnFemale.isSelected() || rdbtnMale.isSelected())
			progress++;
		if (!phoneNumber.getText().trim().equals(""))
			progress++;
		if (!emailID.getText().trim().equals(""))
			progress++;
		if (!addLine1.getText().trim().equals(""))
			progress++;
		if (!city.getText().trim().equals(""))
			progress++;
		if (!state.getText().trim().equals(""))
			progress++;
		if (!zipcode.getText().trim().equals(""))
			progress++;
		if (!country.getText().trim().equals(""))
			progress++;
		return progress;
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {

		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		setTitle("Contact Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width / 6, 0, width * 2 / 3, height);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("35px:grow"),
						ColumnSpec.decode("86px"), ColumnSpec.decode("25px"),
						ColumnSpec.decode("46px"),
						FormFactory.UNRELATED_GAP_COLSPEC,
						ColumnSpec.decode("76px"), ColumnSpec.decode("23px"),
						ColumnSpec.decode("90px"),
						FormFactory.UNRELATED_GAP_COLSPEC,
						ColumnSpec.decode("135px"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						RowSpec.decode("29px"), RowSpec.decode("20px"),
						FormFactory.NARROW_LINE_GAP_ROWSPEC,
						RowSpec.decode("50px"),
						FormFactory.NARROW_LINE_GAP_ROWSPEC,
						RowSpec.decode("20px"),
						FormFactory.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("23px"),
						FormFactory.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("20px"),
						FormFactory.PARAGRAPH_GAP_ROWSPEC,
						RowSpec.decode("20px"), RowSpec.decode("33px"),
						RowSpec.decode("23px"),
						FormFactory.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("max(60px;default):grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		// Progress - Creating a progress label
		JLabel lblProgress = new JLabel("Progress: 0/10");
		contentPane.add(lblProgress, "10, 1");

		// First Name - Creating a label
		JLabel lblfirstName = new JLabel("* First Name");
		contentPane.add(lblfirstName, "2, 2, fill, fill");

		// First Name - Creating a text-field
		firstName = new JTextField(20);
		contentPane.add(firstName, "4, 2, 3, 1, fill, top");
		firstName.setColumns(10);
		firstName.setDocument(new JTextFieldLimit(20));

		// Middle Name - Creating a label
		JLabel lblmiddleName = new JLabel("Middle Name");
		contentPane.add(lblmiddleName, "2, 4, fill, fill");

		// Middle Name - Creating a text-field
		middleName = new JTextField(1);
		contentPane.add(middleName, "4, 4, 3, 1, fill, center");
		middleName.setColumns(10);
		middleName.setDocument(new JTextFieldLimit(1));

		// Last Name - Creating a label
		JLabel lbllastName = new JLabel("* Last Name");
		contentPane.add(lbllastName, "2, 6, fill, center");

		// Last Name - Creating a text-field
		lastName = new JTextField(20);
		contentPane.add(lastName, "4, 6, 3, 1, fill, top");
		lastName.setColumns(10);
		lastName.setDocument(new JTextFieldLimit(20));

		// Gender - Creating a label
		JLabel lblGender = new JLabel("* Gender");
		contentPane.add(lblGender, "2, 8, fill, center");

		// Gender - Creating radio-button for M
		rdbtnMale = new JRadioButton("M", false);
		contentPane.add(rdbtnMale, "4, 8, fill, top");

		// Gender - Creating radio-button for F
		rdbtnFemale = new JRadioButton("F", false);
		contentPane.add(rdbtnFemale, "6, 8, fill, top");

		group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);

		// Phone No - Creating a label
		JLabel lblphoneNo = new JLabel("* Phone No");
		contentPane.add(lblphoneNo, "2, 10, fill, center");

		// Phone No - Creating a text-field
		phoneNumber = new JTextField(21);
		contentPane.add(phoneNumber, "4, 10, 3, 1, fill, top");
		phoneNumber.setColumns(10);
		phoneNumber.setDocument(new JTextFieldLimit(21));

		// Address Line 1 - creating a label
		JLabel lbladdLine1 = new JLabel("* Address line 1");
		contentPane.add(lbladdLine1, "8, 2, fill, center");

		// Address Line 1 - creating a text-field
		addLine1 = new JTextField(35);
		contentPane.add(addLine1, "10, 2, fill, top");
		addLine1.setColumns(10);
		addLine1.setDocument(new JTextFieldLimit(35));

		// Address Line 2 - creating a label
		JLabel lbladdline2 = new JLabel("Address line 2");
		contentPane.add(lbladdline2, "8, 4, fill, center");

		// Address Line 2 - creating a text-field
		addLine2 = new JTextField(35);
		contentPane.add(addLine2, "10, 4, fill, center");
		addLine2.setColumns(10);
		addLine2.setDocument(new JTextFieldLimit(35));

		// City - creating a label
		JLabel lblCity = new JLabel("* City");
		contentPane.add(lblCity, "8, 6, fill, center");

		// City - creating a text-field
		city = new JTextField(25);
		city.setText("");
		contentPane.add(city, "10, 6, fill, top");
		city.setColumns(10);
		city.setDocument(new JTextFieldLimit(25));

		// State - creating a label
		JLabel lblState = new JLabel("* State");
		contentPane.add(lblState, "8, 8, fill, center");

		// State - creating a text-field
		state = new JTextField(2);
		contentPane.add(state, "10, 8, fill, center");
		state.setColumns(10);
		state.setDocument(new JTextFieldLimit(2));

		// Zip code - creating a label
		JLabel lblZip = new JLabel("* Zip code");
		contentPane.add(lblZip, "8, 10, fill, center");

		// Zip code - creating a text-field
		zipcode = new JTextField(9);
		contentPane.add(zipcode, "10, 10, fill, top");
		zipcode.setColumns(10);
		zipcode.setDocument(new JTextFieldLimit(9));

		// Save - Creating a button
		JButton saveButton = new JButton("Save");
		contentPane.add(saveButton, "4, 14, 3, 1, center, top");

		// Clear - Creating a button
		JButton clearButton = new JButton("Clear");
		contentPane.add(clearButton, "8, 14, fill, top");

		// ScrollPane- Creating a scrollable container
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "2, 16, 9, 1, fill, top");

		// model - Creating a table model adapter for the table
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(35, 259, 502, 318);
		scrollPane.setViewportView(table);

		// Email ID - Creating a label
		JLabel lblEmailId = new JLabel("* Email ID");
		contentPane.add(lblEmailId, "2, 12, fill, center");

		// Email ID- Creating a textfield
		emailID = new JTextField(100);
		contentPane.add(emailID, "4, 12, 3, 1, fill, top");
		emailID.setColumns(10);
		emailID.setDocument(new JTextFieldLimit(100));

		// Country - Creating a label
		JLabel lblCountry = new JLabel("* Country");
		contentPane.add(lblCountry, "8, 12, fill, center");

		// Country - Cretating a textfield
		country = new JTextField(30);
		contentPane.add(country, "10, 12, fill, top");
		country.setColumns(10);
		country.setDocument(new JTextFieldLimit(30));

		// Adding headers to the table
		model.addColumn("Name");
		model.addColumn("Phone Number");

		/*
		 * // Event handler for JTextfields thehandler handler = new
		 * thehandler(); rdbtnMale.addActionListener(handler);
		 * rdbtnFemale.addActionListener(handler);
		 * firstName.addActionListener(handler);
		 * middleName.addActionListener(handler);
		 * lastName.addActionListener(handler);
		 * phoneNumber.addActionListener(handler);
		 * addLine1.addActionListener(handler);
		 * addLine2.addActionListener(handler); city.addActionListener(handler);
		 * state.addActionListener(handler); zipcode.addActionListener(handler);
		 */

		// Reading and saving the text file
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("data.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				data[i] = line.split(";");
				i++;
			}
			br.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		for (int j = 0; j < i; j++) {
			Object[] rowData = {
					data[j][0] + ' ' + data[j][1] + ' ' + data[j][2],
					data[j][3] };
			model.addRow(rowData);
		}

		// Handling input events to change Progress Label
		DocumentListener docListen = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);
			}
		};

		// Handling Radio button input to vary progress label
		rdbtnMale.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);
			}
		});

		rdbtnFemale.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);
			}
		});

		firstName.getDocument().addDocumentListener(docListen);
		middleName.getDocument().addDocumentListener(docListen);
		lastName.getDocument().addDocumentListener(docListen);
		phoneNumber.getDocument().addDocumentListener(docListen);
		addLine1.getDocument().addDocumentListener(docListen);
		addLine2.getDocument().addDocumentListener(docListen);
		city.getDocument().addDocumentListener(docListen);
		state.getDocument().addDocumentListener(docListen);
		zipcode.getDocument().addDocumentListener(docListen);
		emailID.getDocument().addDocumentListener(docListen);
		country.getDocument().addDocumentListener(docListen);

		// Table item click event handling
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {

				int index = table.getSelectedRow();
				PopulatedGUI frame = new PopulatedGUI();
				frame.showPopulatedGUI(index);
				dispose();
			}
		});

		// Save button click event handling
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String gender = "E";

				if (rdbtnMale.isSelected()) {
					gender = "M";
				} else if (rdbtnFemale.isSelected()) {
					gender = "F";
				}

				boolean flag = false;

				// Reading input values
				String line = null;
				line = firstName.getText() + ';' + middleName.getText() + ';'
						+ lastName.getText() + ';' + phoneNumber.getText()
						+ ';' + addLine1.getText() + ';' + addLine2.getText()
						+ ';' + city.getText() + ';' + state.getText() + ';'
						+ zipcode.getText() + ';' + gender + ';'
						+ emailID.getText() + ';' + country.getText() + ';';

				currentData = line.split(";");

				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);

				if (!flag) {
					// If fields are empty
					if (firstName.getText().equals("")
							|| lastName.getText().equals("")
							|| phoneNumber.getText().equals("")
							|| addLine1.getText().equals("")
							|| city.getText().equals("")
							|| state.getText().equals("")
							|| zipcode.getText().equals("")
							|| gender.equals("E")
							|| emailID.getText().equals("")
							|| country.getText().equals("")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Fields marked with * are mandatory");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();

					}
				}
				if (!flag) {
					// If wrong input
					if (!firstName.getText().matches("[a-zA-Z]+")
							|| !lastName.getText().matches("[a-zA-Z]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Name");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!phoneNumber.getText().matches("[0-9]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Phone Number");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!addLine1.getText().matches("[a-zA-Z0-9,.#-&:' ']+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Address");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!city.getText().matches("[a-zA-Z]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid City");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!state.getText().matches("[a-zA-Z]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid State");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!zipcode.getText().matches("[0-9]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Zip Code");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!emailID.getText().matches(
							"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Email ID");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// If wrong input
					if (!country.getText().matches("[a-zA-Z]+")) {
						flag = true;

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();

						JLabel lblNewLabel = new JLabel(
								"Please Enter a valid Country");
						lblNewLabel.setForeground(Color.RED);
						frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

						frame.setFields();
					}
				}
				if (!flag) {
					// Reject existing names
					for (int j = 0; j < i; j++) {
						if (data[j][0].equalsIgnoreCase(firstName.getText())
								&& data[j][2].equalsIgnoreCase(lastName
										.getText())) {
							flag = true;

							MainGUI frame = new MainGUI();
							frame.setVisible(true);

							dispose();

							JLabel lblNewLabel = new JLabel(
									"Entry with the given name already exists");
							lblNewLabel.setForeground(Color.RED);
							frame.contentPane.add(lblNewLabel, "2, 1, 7, 1");

							frame.setFields();
						}
					}
				}
				// If none of the above Write to the file
				if (!flag) {
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(
								new FileWriter("data.txt", true)));
						line = firstName.getText() + ';' + middleName.getText()
								+ ';' + lastName.getText() + ';'
								+ phoneNumber.getText() + ';'
								+ addLine1.getText() + ';' + addLine2.getText()
								+ ';' + city.getText() + ';' + state.getText()
								+ ';' + zipcode.getText() + ';' + gender + ';'
								+ emailID.getText() + ';' + country.getText()
								+ ';';
						out.println(line);
						out.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {

						MainGUI frame = new MainGUI();
						frame.setVisible(true);

						dispose();
					}
				}
			}

		});

		// Clear button click event handling
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				progress = findProgress();
				lblProgress.setText("Progress: " + progress + "/10");
				lblProgress.setForeground(Color.GREEN);

				MainGUI frame = new MainGUI();
				frame.setVisible(true);

				dispose();
			}
		});
	}

	/*
	 * class thehandler implements ActionListener {
	 * 
	 * public void actionPerformed(ActionEvent event) {
	 * 
	 * if (event.getSource() == firstName) fn = event.getActionCommand(); if
	 * (event.getSource() == middleName) mn = event.getActionCommand(); if
	 * (event.getSource() == lastName) ln = event.getActionCommand(); if
	 * (event.getSource() == phoneNumber) pn = event.getActionCommand(); if
	 * (event.getSource() == addLine1) ad1 = event.getActionCommand(); if
	 * (event.getSource() == addLine2) ad2 = event.getActionCommand(); if
	 * (event.getSource() == city) c = event.getActionCommand(); if
	 * (event.getSource() == state) s = event.getActionCommand(); if
	 * (event.getSource() == zipcode) zc = event.getActionCommand(); if
	 * (event.getSource() == rdbtnMale) m = event.getActionCommand(); if
	 * (event.getSource() == rdbtnFemale) f = event.getActionCommand();
	 * 
	 * 
	 * } }
	 */

	@SuppressWarnings("serial")
	// Document to limit characters in text fields
	class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
}
