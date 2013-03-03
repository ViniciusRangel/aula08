package com.eib.agenda.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;

import com.eib.agenda.modelo.dao.AbstractDAO;
import com.eib.agenda.modelo.dao.ContatoDAO;
import com.eib.agenda.modelo.vo.Contato;

import java.sql.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Collection;

public class Agenda {
	private JFrame frame;
	private JTextField txId;
	private JTextField txNome;
	private JTextField txEndereco;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JComboBox combContatos;

	public static void main(String[] args) {
		// http://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda window = new Agenda();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Agenda() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 39, 27, 27);
		frame.getContentPane().add(lblId);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 67, 46, 27);
		frame.getContentPane().add(lblNome);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndereo.setBounds(10, 94, 62, 27);
		frame.getContentPane().add(lblEndereo);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(10, 125, 56, 27);
		frame.getContentPane().add(lblTelefone);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 155, 56, 27);
		frame.getContentPane().add(lblEmail);

		txId = new JTextField();
		txId.setBounds(77, 43, 339, 20);
		frame.getContentPane().add(txId);
		txId.setColumns(10);

		txNome = new JTextField();
		txNome.setBounds(77, 71, 339, 20);
		frame.getContentPane().add(txNome);
		txNome.setColumns(10);

		txEndereco = new JTextField();
		txEndereco.setBounds(77, 98, 339, 20);
		frame.getContentPane().add(txEndereco);
		txEndereco.setColumns(10);

		txTelefone = new JTextField();
		txTelefone.setBounds(76, 129, 340, 20);
		frame.getContentPane().add(txTelefone);
		txTelefone.setColumns(10);

		txEmail = new JTextField();
		txEmail.setBounds(76, 159, 340, 20);
		txEmail.setColumns(10);
		frame.getContentPane().add(txEmail);

		JButton BtnCriar = new JButton("Criar");

		BtnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Contato c = new Contato();
				c.setNome(txNome.getText());
				c.setEndereco(txEndereco.getText());
				c.setEmail(txEmail.getText());
				c.setTelefone(txTelefone.getText());

				AbstractDAO<Contato> db = new ContatoDAO();

				int r = db.insert(c);

				String msg_corpo = "Erro ao criar um contato";
				String msg_titulo = "Erro...";
				int msg_tipo = JOptionPane.ERROR_MESSAGE;

				if (r == 1) {
					montaContatos();
					msg_corpo = "O contato foi criado!";
					msg_titulo = "OK...";
					msg_tipo = JOptionPane.INFORMATION_MESSAGE;
				}

				JOptionPane.showMessageDialog(null, msg_corpo, msg_titulo,
						msg_tipo);
			}
		});

		BtnCriar.setBounds(10, 193, 89, 23);
		frame.getContentPane().add(BtnCriar);

		JButton btnExcluir = new JButton("Excluir");

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg_corpo = "Erro ao criar um contato";
				String msg_titulo = "Erro...";
				int msg_tipo = JOptionPane.ERROR_MESSAGE;
				
				Contato c = new Contato();
				c.setId(Integer.parseInt(txId.getText()));

				AbstractDAO<Contato> db = new ContatoDAO();

				int r = db.delete(c);

				if (r == 1) {
					montaContatos();
					msg_corpo = "O contato foi deletado!";
					msg_titulo = "OK...";
					msg_tipo = JOptionPane.INFORMATION_MESSAGE;
				}

				JOptionPane.showMessageDialog(null, msg_corpo, msg_titulo,msg_tipo);
			}
		});

		btnExcluir.setBounds(118, 193, 89, 23);
		frame.getContentPane().add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contato c = new Contato();
				c.setNome(txNome.getText());
				c.setEndereco(txEndereco.getText());
				c.setEmail(txEmail.getText());
				c.setTelefone(txTelefone.getText());
				c.setId(Integer.parseInt(txId.getText()));

				AbstractDAO<Contato> db = new ContatoDAO();

				int r = db.update(c);

				String msg_corpo = "Erro ao alterar um contato";
				String msg_titulo = "Erro...";
				int msg_tipo = JOptionPane.ERROR_MESSAGE;

				if (r == 1) {
					montaContatos();
					msg_corpo = "O contato foi alterado!";
					msg_titulo = "OK...";
					msg_tipo = JOptionPane.INFORMATION_MESSAGE;
				}

				JOptionPane.showMessageDialog(null, msg_corpo, msg_titulo,
						msg_tipo);
			}
		});

		btnAlterar.setBounds(228, 193, 89, 23);
		frame.getContentPane().add(btnAlterar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txEmail.setText("");
				txId.setText("");
				txNome.setText("");
				txTelefone.setText("");
				txEndereco.setText("");
			}
		});

		btnLimpar.setBounds(327, 193, 89, 23);
		frame.getContentPane().add(btnLimpar);

		JLabel lblContatos = new JLabel("Contatos:");
		lblContatos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContatos.setBounds(10, 14, 62, 14);
		frame.getContentPane().add(lblContatos);

		combContatos = new JComboBox();
		combContatos.setBounds(77, 12, 339, 20);
		
		montaContatos();

		combContatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox comb = (JComboBox) arg0.getSource();

				if (comb.getSelectedIndex() != -1) {
					Contato contato = (Contato) comb.getSelectedItem();
					
					AbstractDAO<Contato> db = new ContatoDAO();

					Contato c = db.select(contato.getId());

					txId.setText("" + c.getId());
					txNome.setText(c.getNome());
					txEndereco.setText(c.getEndereco());
					txTelefone.setText(c.getTelefone());
					txEmail.setText(c.getEmail());
				}
			}
		});
		frame.getContentPane().add(combContatos);
		frame.setVisible(true);
	}

	private void montaContatos() {
		 AbstractDAO<Contato> db = new ContatoDAO();
         Collection<Contato>  list =db.select();
		 
		 combContatos.removeAllItems();

		 for (Contato contato : list) {
			  combContatos.addItem(contato);
		 } 
	}
}
