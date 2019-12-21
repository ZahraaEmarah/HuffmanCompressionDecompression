package binaryHuffman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class Main {

	private JFrame frame;
	BinaryDecompression read = new BinaryDecompression();
	BinaryCompression bin = new BinaryCompression();
	folderClass f = new folderClass();
	private JTextField txtOriginaltxt;
	public JPanel start;
	private JTextArea textArea;
	JLabel lbl1;
	JLabel lbl2;
	JButton btnCompress;
	JButton btnDecompress;
	JLabel lblHuffman;
	JCheckBox chckbxIsFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		start = new JPanel();
		start.setOpaque(true);
		start.setForeground(new Color(204, 153, 255));
		start.setBackground(new Color(51, 0, 51));
		start.setBounds(0, 0, 434, 534);
		frame.getContentPane().add(start);

		btnCompress = new JButton("Compress");
		btnCompress.setBounds(61, 152, 143, 26);
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (chckbxIsFolder.isEnabled()) {
					f.compressFolder(txtOriginaltxt.getText());
				} else {
					textArea.setText(" Byte" + "\t" + "Code" + "\t" + "New code" + "\n");
					long startTime = System.currentTimeMillis();
					bin.binC(txtOriginaltxt.getText());
					long endTime = System.currentTimeMillis();
					long totalTime = endTime - startTime;
					lbl1.setText(Long.toString(totalTime));
					textArea.append(bin.bintree.print_table());
					lbl2.setText(Float.toString(bin.ratio()));
				}
			}
		});
		start.setLayout(null);
		btnCompress.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnCompress.setBackground(new Color(204, 153, 255));
		start.add(btnCompress);

		btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				long startTime = System.currentTimeMillis();
				read.DecodeTable(txtOriginaltxt.getText());
				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				lbl1.setText(Long.toString(totalTime));
			}
		});
		btnDecompress.setBounds(227, 152, 143, 27);
		btnDecompress.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnDecompress.setBackground(new Color(204, 153, 255));
		start.add(btnDecompress);

		lblHuffman = new JLabel("Huffman ");
		lblHuffman.setBounds(105, 34, 226, 26);
		lblHuffman.setHorizontalAlignment(SwingConstants.CENTER);
		lblHuffman.setFont(new Font("Bodoni MT", Font.PLAIN, 21));
		lblHuffman.setForeground(new Color(204, 153, 255));
		start.add(lblHuffman);

		txtOriginaltxt = new JTextField();
		txtOriginaltxt.setBounds(196, 86, 194, 20);
		start.add(txtOriginaltxt);
		txtOriginaltxt.setColumns(10);

		JLabel lblEnterTheFile = new JLabel("Enter the file name: ");
		lblEnterTheFile.setBounds(47, 85, 140, 20);
		start.add(lblEnterTheFile);
		lblEnterTheFile.setForeground(new Color(204, 153, 255));
		lblEnterTheFile.setFont(new Font("Bodoni MT", Font.PLAIN, 16));

		textArea = new JTextArea();
		start.add(textArea);
		textArea.setBounds(26, 280, 383, 228);

		JLabel lblRunningTime = new JLabel("Running time: ");
		lblRunningTime.setBounds(26, 211, 115, 26);
		start.add(lblRunningTime);
		lblRunningTime.setForeground(new Color(204, 153, 255));
		lblRunningTime.setFont(new Font("Bodoni MT", Font.PLAIN, 16));

		JLabel lblMs = new JLabel("ms");
		lblMs.setBounds(227, 211, 73, 26);
		start.add(lblMs);
		lblMs.setForeground(new Color(204, 153, 255));
		lblMs.setFont(new Font("Bodoni MT", Font.PLAIN, 16));

		lbl1 = new JLabel("");
		lbl1.setBounds(130, 218, 82, 14);
		start.add(lbl1);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setForeground(new Color(204, 153, 255));
		lbl1.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		lbl1.setBackground(new Color(204, 153, 255));

		JLabel lblCompressionRatio = new JLabel("Compression ratio:");
		lblCompressionRatio.setBounds(26, 237, 137, 26);
		start.add(lblCompressionRatio);
		lblCompressionRatio.setForeground(new Color(204, 153, 255));
		lblCompressionRatio.setFont(new Font("Bodoni MT", Font.PLAIN, 16));

		lbl2 = new JLabel("");
		lbl2.setBounds(162, 243, 195, 14);
		start.add(lbl2);
		lbl2.setForeground(new Color(204, 153, 255));
		lbl2.setBackground(new Color(204, 153, 255));
		lbl2.setFont(new Font("Bodoni MT", Font.PLAIN, 16));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(26, 280, 385, 222);
		start.add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 198, 383, 2);
		start.add(separator);

		chckbxIsFolder = new JCheckBox("is Folder");
		chckbxIsFolder.setBackground(new Color(51, 0, 51));
		chckbxIsFolder.setForeground(new Color(204, 153, 255));
		chckbxIsFolder.setBounds(196, 113, 97, 23);
		start.add(chckbxIsFolder);
	}
}
