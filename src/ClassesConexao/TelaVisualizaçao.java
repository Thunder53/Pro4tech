package ClassesConexao;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TelaVisualizaçao extends JFrame {

	private JPanel contentPane;
	private JTable tbDescVaga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVisualizaçao frame = new TelaVisualizaçao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaVisualizaçao() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ariane Sousa\\Desktop\\PROJETOS\\Pro4Tech\\icons\\iconPro4Tech.jpg"));
		lblNewLabel.setBounds(0, 0, 553, 90);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("VOCÊ ESTÁ NA PÁGINA DE VISUALIZAÇÃO DE VAGAS!");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(144, 138, 634, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("CANDIDATAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 64));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 18));
		btnNewButton.setBounds(645, 746, 247, 60);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("APERTE NO BOTÃO DE BUSCAR PARA VIZUALIZAR AS VAGAS ABERTAS!");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(144, 166, 840, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("DIGITE A VAGA DESEJADA E APERTE O BOTÃO CANDIDATAR OU VISUZALIZAR!");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(144, 243, 910, 33);
		contentPane.add(lblNewLabel_1_1_1);
		
		JComboBox cbxvagas = new JComboBox();
		cbxvagas.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				 vagasDAO dao = new vagasDAO();
			        
			        ArrayList <String> vg = dao.vagas();
			        cbxvagas.removeAll();
			        for(String f:vg){
			            
			            cbxvagas.addItem(f);
			            
			        }
				
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		cbxvagas.setBounds(321, 320, 878, 43);
		contentPane.add(cbxvagas);
		
		JButton btnVisualizar = new JButton("VISUALIZAR");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object vaga = cbxvagas.getSelectedItem();
					try {
					
					Connection con = Conexao.faz_conexao();
					
					String sql = "select nome_vaga, carga_horaria, requisitos, escolaridade from Vagas where nome_vaga = '" + vaga + "'";
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					
					DefaultTableModel modelo = (DefaultTableModel) tbDescVaga.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("nome_vaga"), rs.getString("carga_horaria"), rs.getString("requisitos"), rs.getString("escolaridade") });
					}
					rs.close();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnVisualizar.setForeground(Color.BLACK);
		btnVisualizar.setFont(new Font("Arial", Font.BOLD, 18));
		btnVisualizar.setBackground(new Color(255, 128, 64));
		btnVisualizar.setBounds(1219, 318, 241, 43);
		contentPane.add(btnVisualizar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Arial", Font.PLAIN, 17));
		scrollPane_1.setBounds(321, 409, 878, 187);
		contentPane.add(scrollPane_1);
		
		tbDescVaga = new JTable();
		tbDescVaga.setBackground(Color.WHITE);
		tbDescVaga.setFont(new Font("Arial", Font.PLAIN, 17));
		tbDescVaga.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME VAGA", "CARGA HOR\u00C1RIA", "REQUISITOS", "ESCOLARIDADE"
			}
		));
		scrollPane_1.setViewportView(tbDescVaga);
		
		
	}
}
