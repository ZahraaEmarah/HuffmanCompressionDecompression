package huffman;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Main {

	private JFrame frame;
	DataProcessing d = new DataProcessing();
	huffmanTree huff = new huffmanTree();
	private JTextField txtOriginaltxt;
	public JPanel panel_1;
	public JPanel panel;
	private JTextArea textArea;
	JLabel lbl1;
	JLabel lbl2;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setForeground(new Color(204, 153, 255));
		panel.setBackground(new Color(51, 0, 51));
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.setBounds(58, 159, 143, 42);
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(" Byte"+"\t"+"Code"+"\t"+"New code"+"\n");
				long startTime = System.currentTimeMillis();
				d.Read_data(txtOriginaltxt.getText());
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				lbl1.setText(Long.toString(totalTime));
				panel.setVisible(false);
				panel_1.setVisible(true);
				lbl1.setVisible(true);
				txtOriginaltxt.setVisible(false);
			    textArea.append(d.tree.print_table());		
			    lbl2.setText(d.ratio());
			}
		});
		panel.setLayout(null);
		btnCompress.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnCompress.setBackground(new Color(204, 153, 255));
		panel.add(btnCompress);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.setBounds(238, 159, 143, 42);
		btnDecompress.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnDecompress.setBackground(new Color(204, 153, 255));
		panel.add(btnDecompress);
		
		JLabel lblHuffman = new JLabel("Huffman ");
		lblHuffman.setBounds(105, 34, 226, 26);
		lblHuffman.setHorizontalAlignment(SwingConstants.CENTER);
		lblHuffman.setFont(new Font("Bodoni MT", Font.PLAIN, 21));
		lblHuffman.setForeground(new Color(204, 153, 255));
		panel.add(lblHuffman);
		
		JLabel lblEnterTheFile = new JLabel("Enter the file name: ");
		lblEnterTheFile.setBounds(41, 84, 148, 26);
		lblEnterTheFile.setForeground(new Color(204, 153, 255));
		lblEnterTheFile.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		panel.add(lblEnterTheFile);
		
		txtOriginaltxt = new JTextField();
		txtOriginaltxt.setText("original.txt");
		txtOriginaltxt.setBounds(199, 88, 198, 20);
		panel.add(txtOriginaltxt);
		txtOriginaltxt.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 0, 51));
		panel_1.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(25, 81, 384, 157);
		panel_1.add(textArea);
		
		JLabel lblRunningTime = new JLabel("Running time: ");
		lblRunningTime.setBounds(25, 11, 115, 26);
		lblRunningTime.setForeground(new Color(204, 153, 255));
		lblRunningTime.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		panel_1.add(lblRunningTime);
		
		JLabel lblMs = new JLabel("ms");
		lblMs.setBounds(213, 11, 73, 26);
		lblMs.setForeground(new Color(204, 153, 255));
		lblMs.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		panel_1.add(lblMs);
		
		JLabel lblCompressionRatio = new JLabel("Compression ratio:");
		lblCompressionRatio.setBounds(25, 44, 137, 26);
		lblCompressionRatio.setForeground(new Color(204, 153, 255));
		lblCompressionRatio.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		panel_1.add(lblCompressionRatio);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(25, 81, 385, 157);
		panel_1.add(scrollPane);
		
		lbl2 = new JLabel("");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setForeground(new Color(204, 153, 255));
		lbl2.setBackground(new Color(204, 153, 255));
		lbl2.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		lbl2.setBounds(165, 51, 56, 14);
		panel_1.add(lbl2);
		
		lbl1 = new JLabel("");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setForeground(new Color(204, 153, 255));
		lbl1.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		lbl1.setBackground(new Color(204, 153, 255));
		lbl1.setBounds(134, 18, 82, 14);
		panel_1.add(lbl1);
	}
}
