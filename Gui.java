package userInterface;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import businesslogic.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Gui {
	private ClientMap clMap = new ClientMap();
	private ContMap contMap=new ContMap();
	private LogareMap logMap = new LogareMap();
	private int[] idRole;
	private int idClient;
	private JFrame fereastra;
	private JTextField tipTextField;
	private JTextField moneyTextField;
	private JTextField currencyTextField;
	

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Gui window = new Gui();
				window.fereastra.setVisible(true);
			    } 
			catch (Exception e) 
			    {
				e.printStackTrace();
			    }
		                  }
	                                      });
                                        }

    public Gui()
    {
    	initializare();
    }
    
    private void initializare(){
    	
    fereastra=new JFrame();
    fereastra.setBounds(200,200,500,250);
    fereastra.getContentPane().setBackground(Color.lightGray);
    fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fereastra.getContentPane().setLayout(new CardLayout(0, 0));
    	

//////prima fereastra
	JPanel logarePanel=new JPanel();
	logarePanel.setBackground(new Color(70,70,70));
	fereastra.getContentPane().add(logarePanel, "blabla2");
	logarePanel.setLayout(null);
	logarePanel.setVisible(false);
	
	JLabel lblUsername=new JLabel("USERNAME:");
	lblUsername.setForeground(Color.white);
	lblUsername.setBounds(30,50,70,15);
	logarePanel.add(lblUsername);
	
	JTextField usernameTextField=new JTextField();
	usernameTextField.setBounds(30, 70, 100, 20);
	logarePanel.add(usernameTextField);
	usernameTextField.setColumns(10);
	
	JLabel lblParola=new JLabel("PAROLA:");
	lblParola.setForeground(Color.white);
	lblParola.setBounds(30,120,100,15);
	logarePanel.add(lblParola);	
	
	JPasswordField parolaTextField=new JPasswordField();
	parolaTextField.setBounds(30, 140, 100, 20);
	logarePanel.add(parolaTextField);
	parolaTextField.setColumns(10);
	parolaTextField.setEchoChar('*');
	
	  
    JPanel vizualizareConturiPanel = new JPanel();
	fereastra.getContentPane().add(vizualizareConturiPanel, "12");
	vizualizareConturiPanel.setLayout(new BorderLayout());
	vizualizareConturiPanel.setBounds(10, 10, 400, 100);
		logarePanel.setVisible(true);
    
	
////panel administrator
    JPanel administratorPanel=new JPanel();
    fereastra.getContentPane().add(administratorPanel, "blaaa");
	administratorPanel.setLayout(null);
	
	
   
////panel userNormal
	JPanel userNormalPanel=new JPanel();
    fereastra.getContentPane().add(userNormalPanel, "blaaa");
    userNormalPanel.setLayout(null);
    
    
  
    
	JButton butonLogare=new JButton("LOG IN");
	butonLogare.setBackground(Color.black);
	butonLogare.setForeground(Color.white);
	butonLogare.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			String userName=usernameTextField.getText();
			String password=parolaTextField.getText();
			userName=userName.replaceAll("\\s+","");
			if (userName==null || userName.isEmpty())
			{
				if(password==null || password.isEmpty())
				{
					 JOptionPane.showMessageDialog(new JFrame(), "Eroare! Parola/Username introduse gresit.");
				     return;
				}
			}
			
		   if (userName==null || userName.isEmpty())
		   {
			   JOptionPane.showMessageDialog(new JFrame(), "Completati campul USERNAME!");
			   return;
		   }
		   
		   if (password==null || password.isEmpty())
		   {
			   JOptionPane.showMessageDialog(new JFrame(), "Completati campul PAROLA!");
			   return;
		   }
			
		   idRole=logMap.conectare(userName, password);
		   if (idRole[1]==1)
		   {
			   administratorPanel.setVisible(true);
			   logarePanel.setVisible(false);
		   }
		   else 
		   if (idRole[1]==2)
		   {
			   userNormalPanel.setVisible(true);
			   logarePanel.setVisible(false);
		   }
		   if (idRole[2]==0)
		   {
			   JOptionPane.showMessageDialog(new JFrame(), "Incorect!");  
		   }
		}
	});
	butonLogare.setBounds(350, 180, 100, 20);
	logarePanel.add(butonLogare);
	
	ImageIcon image = new ImageIcon("C:/Users/Roxana/Desktop/banca.png");
	JLabel imagelabel = new JLabel(image);
	imagelabel.setBounds(350, 100, 100, 60);
	logarePanel.add(imagelabel);
	
	JPanel vizConturi=new JPanel();
	vizConturi.setBackground(new Color(70,70,70));
	fereastra.getContentPane().add(vizConturi, "blabla2");
	vizConturi.setLayout(null);
	vizConturi.setVisible(false);
	
	JLabel lblCntCli=new JLabel("Id client:");
	lblCntCli.setForeground(Color.white);
	lblCntCli.setBounds(100,10,400,50);
	vizConturi.add(lblCntCli);
	
	JTextField cntCliTextField=new JTextField();
	cntCliTextField.setBounds(100, 45, 100, 20);
	vizConturi.add(cntCliTextField);
	
	
	JButton butonInapoi10=new JButton("<-Inapoi");
	butonInapoi10.setBackground(new Color(150,50,0));
	butonInapoi10.setForeground(Color.WHITE);
	butonInapoi10.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		vizConturi.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi10.setBounds(300,10,120,20);
	vizConturi.add(butonInapoi10);
	
	
	JButton butonLogOut10=new JButton("Deconectare");
	butonLogOut10.setBackground(Color.magenta);
	butonLogOut10.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		vizConturi.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	
	butonLogOut10.setBounds(10,190,120,20);
	vizConturi.add(butonLogOut10);

	JButton butonVizualizareConturi1=new JButton("Vizualizeaza conturile");
	butonVizualizareConturi1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			userNormalPanel.setVisible(false);
			vizConturi.setVisible(true);
		}
		});
	butonVizualizareConturi1.setBounds(20,110,200,15);
	userNormalPanel.add(butonVizualizareConturi1);
	
	JButton butonVizualizareConturi=new JButton("VIZUALIZEAZA");
	 butonVizualizareConturi.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			userNormalPanel.setVisible(false);
			vizualizareConturiPanel.setVisible(true);
			String id=cntCliTextField.getText();
			int idClient=Integer.parseInt(id);
			ArrayList<Cont> conturi=contMap.sendId(idClient);
			String[] coloane=new String[6];
			int j=0;
			for(Field f:Cont.class.getDeclaredFields())
			{
				f.setAccessible(true);
				coloane[j]=f.getName();
				j=j+1;
			}
			Object[][] ob=new Object[conturi.size()][coloane.length];
			for(j=0;j<conturi.size();j++)
			{
				Cont c=conturi.get(j);
				ob[j][0]=new Object();
				ob[j][1]=new Object();
				ob[j][2]=new Object();
				ob[j][3]=new Object();
				ob[j][4]=new Object();
				ob[j][5]=new Object();
				ob[j][0]=c.getIdAccount();
				ob[j][1]=c.getType();
				ob[j][2]=c.getMoney();
				ob[j][3]=c.getCreationDate();
				ob[j][4]=c.getIdClinet();
				ob[j][5]=c.getCurrency();
		     }
			JTable tabelaConturi=new JTable(ob,coloane);
			tabelaConturi.setBounds(80, 190, 350, -120);
			//tabelaConturi.setBackground(Color.YELLOW);
			vizualizareConturiPanel.add(tabelaConturi);
			JScrollPane scrollPane2 = new JScrollPane(tabelaConturi);
			scrollPane2.setBounds(10,10,400,100);
			vizualizareConturiPanel.add(scrollPane2);
			vizualizareConturiPanel.setVisible(true);
			tabelaConturi.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		       public void valueChanged(ListSelectionEvent event) {
		        	//idClient = Integer.parseInt( tabelaConturi.getValueAt(tabelaConturi.getSelectedRow(), 0).toString());
		        }
		    });
		}
	});
	butonVizualizareConturi.setBounds(20,110,200,15);
	vizConturi.add(butonVizualizareConturi);
	
///////////////platestePanel	
	JPanel platesteUtilitatiPanel=new JPanel();
	fereastra.getContentPane().add(platesteUtilitatiPanel, "123");
	platesteUtilitatiPanel.setLayout(null);
	
	JButton butonInapoi5=new JButton("<-Inapoi");
	butonInapoi5.setBackground(new Color(150,50,0));
	butonInapoi5.setForeground(Color.WHITE);
	butonInapoi5.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		platesteUtilitatiPanel.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi5.setBounds(10,10,120,20);
	platesteUtilitatiPanel.add(butonInapoi5);
	
	JButton butonLogOut1=new JButton("Deconectare");
	butonLogOut1.setBackground(new Color(100,100,0));
	butonLogOut1.setForeground(Color.WHITE);
	butonLogOut1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		platesteUtilitatiPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut1.setBounds(330,10,120,20);
	platesteUtilitatiPanel.add(butonLogOut1);
	
	JLabel lblCompanie=new JLabel ("Catre:");
	lblCompanie.setBounds(50,40,150,20);
	platesteUtilitatiPanel.add(lblCompanie);
	
	String [] comp={"Orange Romania","Vodafone Romania",
			        "DIGI", "RDS","Telekon","Electrica.SA",
			        "E-on gas","Romtelecom","Altele",""};
	
	JComboBox comb=new JComboBox(comp);
	comb.setSelectedItem(comp[9]);
	comb.setBounds(120,40,150,20);
	platesteUtilitatiPanel.add(comb);
	
	JLabel pretLabel=new JLabel("Suma:");
	pretLabel.setBounds(300,40,150,20);
	platesteUtilitatiPanel.add(pretLabel);
	
	JTextField pretTextField=new JTextField();
	pretTextField.setBounds(350,40,100,20);
	platesteUtilitatiPanel.add(pretTextField);
	
	JButton butonPlatire=new JButton ("PLATESTE");
	butonPlatire.setBounds(300, 150, 100, 20);
	butonPlatire.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent arg0){
		JOptionPane.showMessageDialog(new JFrame(), "Plata efectata cu succes");
	}
	});
	butonPlatire.setForeground(new Color(50,100,50));
	butonPlatire.setBounds(300, 150, 100, 20);
	platesteUtilitatiPanel.add(butonPlatire);
	
	
/////////////////////transferPane;	
	JPanel transferPanel=new JPanel();
	fereastra.getContentPane().add(transferPanel, "1234");
	transferPanel.setLayout(null);
	
	JButton butonInapoi6=new JButton("<-Inapoi");
	butonInapoi6.setBackground(new Color(150,50,0));
	butonInapoi6.setForeground(Color.WHITE);
	butonInapoi6.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		transferPanel.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi6.setBounds(10,10,120,20);
	transferPanel.add(butonInapoi6);
	
	JButton butonLogOut2=new JButton("Deconectare");
	butonLogOut2.setBackground(new Color(100,100,0));
	butonLogOut2.setForeground(Color.WHITE);
	butonLogOut2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		transferPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut2.setBounds(330,10,120,20);
	transferPanel.add(butonLogOut2);
	
	JLabel labelDin=new JLabel("Transfera din contul:");
	labelDin.setForeground(Color.BLUE);
	labelDin.setBounds(30,40,130,15);
	transferPanel.add(labelDin);
	
	JLabel labelIn=new JLabel("Transfera in contul:");
	labelIn.setForeground(Color.BLUE);
	labelIn.setBounds(250,40,130,15);
	transferPanel.add(labelIn);	
	
	String c[] = {"SPENDING","SAVING"," "};
	JComboBox combTransfer = new JComboBox(c);
	combTransfer.setSelectedItem(c[2]);
	combTransfer.setBounds(30, 60, 130, 20);
	transferPanel.add(combTransfer);
	
	JComboBox combTransfer1 = new JComboBox(c);
	combTransfer1.setSelectedItem(c[2]);
	combTransfer1.setBounds(250, 60, 130, 20);
	transferPanel.add(combTransfer1);
	
	JLabel labelSuma=new JLabel("Transfera suma de:");
	labelSuma.setForeground(Color.RED);
	labelSuma.setBounds(100, 100, 200, 15);
	transferPanel.add(labelSuma);
	
	JTextField sumaTextField=new JTextField();
	sumaTextField.setBounds(220, 100, 100, 20);
	sumaTextField.setForeground(Color.red);
	transferPanel.add(sumaTextField);
	
	JButton butonTransfer1=new JButton ("TRANSFERA");
	butonTransfer1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent arg0){
		JOptionPane.showMessageDialog(new JFrame(), "Transfer efectat cu succes");
	}
	});
	butonTransfer1.setForeground(new Color(50,100,50));
	butonTransfer1.setBounds(300, 150, 150, 20);
	transferPanel.add(butonTransfer1);
/////////////////////////////generare raport Panel	
	JPanel generareRaportPanel=new JPanel();
	fereastra.getContentPane().add(generareRaportPanel, "12345");
	generareRaportPanel.setLayout(null);
	
	JButton butonInapoi7=new JButton("<-Inapoi");
	butonInapoi7.setBackground(new Color(150,50,0));
	butonInapoi7.setForeground(Color.WHITE);
	butonInapoi7.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		generareRaportPanel.setVisible(false);
		administratorPanel.setVisible(true);
	}
	});
	butonInapoi7.setBounds(10,10,120,20);
	generareRaportPanel.add(butonInapoi7);
	
	JButton butonLogOut3=new JButton("Deconectare");
	butonLogOut3.setBackground(new Color(100,100,0));
	butonLogOut3.setForeground(Color.WHITE);
	butonLogOut3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		generareRaportPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut3.setBounds(330,10,120,20);
	generareRaportPanel.add(butonLogOut3);
	
	JLabel labelDin1=new JLabel("De la data:");
	labelDin1.setBounds(30, 30, 100, 20);
	labelDin1.setForeground(new Color(100,100,30));
	generareRaportPanel.add(labelDin1);
	
	JDateChooser alegeDataDin1=new JDateChooser();
	alegeDataDin1.setBounds(30,50,150,20);
	generareRaportPanel.add(alegeDataDin1);
	
	JLabel labelDin2=new JLabel("Pana la data:");
	labelDin2.setBounds(300, 30, 100, 20);
	labelDin2.setForeground(new Color(100,100,30));
	generareRaportPanel.add(labelDin2);
	
	JDateChooser alegeDataDin2=new JDateChooser();
	alegeDataDin2.setBounds(300,50,150,20);
	generareRaportPanel.add(alegeDataDin2);
	
	JButton butonGenerareRaport1=new JButton ("GENEREAZA RAPORTUL");
	butonGenerareRaport1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent arg0){
		JOptionPane.showMessageDialog(new JFrame(), "S-a generat raportul");
	}
	});
	butonGenerareRaport1.setForeground(new Color(50,100,50));
	butonGenerareRaport1.setBounds(200, 150, 200, 20);
	generareRaportPanel.add(butonGenerareRaport1);
	
	

	
	JButton btnPlatesteUtilitati=new JButton("Plateste utilitati");
	btnPlatesteUtilitati.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			userNormalPanel.setVisible(false);
			platesteUtilitatiPanel.setVisible(true);
		}
	});
		btnPlatesteUtilitati.setBounds(20,170,200,15);
		userNormalPanel.add(btnPlatesteUtilitati);
		
		
		
	JButton butonTransfer=new JButton("Transfer bani");
	butonTransfer.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			userNormalPanel.setVisible(false);
			transferPanel.setVisible(true);
		}
	});
	
	butonTransfer.setBounds(250,170,200,15);
	userNormalPanel.add(butonTransfer);
	
	JButton butonGenerareRaport=new JButton("Generare raport");
	butonGenerareRaport.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			administratorPanel.setVisible(false);
			generareRaportPanel.setVisible(true);
		}
	});
	
	butonGenerareRaport.setBounds(20,50,200,15);
	administratorPanel.add(butonGenerareRaport);
	
	
	JButton butonInapoi4=new JButton("<-Inapoi");
	butonInapoi7.setBackground(new Color(150,50,0));
	butonInapoi7.setForeground(Color.WHITE);
	butonInapoi4.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonInapoi4.setBounds(10,10,120,20);
	userNormalPanel.add(butonInapoi4);
	

	
	JPanel creazaContPan=new JPanel();
    creazaContPan.setBackground(Color.gray);
    fereastra.getContentPane().add(creazaContPan, "blabla");
    creazaContPan.setLayout(null);
    creazaContPan.setVisible(false);
    
    JPanel sPanel = new JPanel();
    sPanel.setBackground(Color.green);
    fereastra.getContentPane().add(sPanel, "blabla1");
	sPanel.setLayout(null);
	sPanel.setVisible(false);
	
	
    
    JLabel lblAccountType = new JLabel("Cont:");
	lblAccountType.setBounds(90, 30, 100, 30);
	lblAccountType.setForeground(Color.BLUE);
	creazaContPan.add(lblAccountType);
	
	JLabel lblMoney = new JLabel("Money:");
	lblMoney.setBounds(90, 75, 70,30);
	lblMoney.setForeground(Color.BLUE);
	creazaContPan.add(lblMoney);
	
	JLabel lblCurrency = new JLabel("Currency:");
	lblCurrency.setBounds(90, 120, 95, 30);
	lblCurrency.setForeground(Color.BLUE);
	creazaContPan.add(lblCurrency);
	
	tipTextField = new JTextField();
	tipTextField.setBounds(150, 40, 90, 30);
	creazaContPan.add(tipTextField);

	
	moneyTextField = new JTextField();
	moneyTextField.setBounds(150, 85, 90, 30);
	creazaContPan.add(moneyTextField);
	
	
	currencyTextField = new JTextField();
	currencyTextField.setBounds(150, 130, 90, 30);
	creazaContPan.add(currencyTextField);

	
	JLabel lblIdCl = new JLabel("Id client");
	lblIdCl.setBounds(250, 30, 100, 20);
	lblIdCl.setForeground(Color.BLUE);
	creazaContPan.add(lblIdCl);
	
	JTextField idClTextField = new JTextField();
	idClTextField.setBounds(250, 50 , 100, 20);
	creazaContPan.add(idClTextField);
	
	
	 
	JButton butonAdaugareCont=new JButton("Adauga cont");
	butonAdaugareCont.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
    	String type = tipTextField.getText();
		type = type.replaceAll("\\s+","");
		int money = Integer.parseInt(moneyTextField.getText());
		String currency = currencyTextField.getText();
		int idClient = Integer.parseInt(idClTextField.getText());
		contMap.inserareCont(idClient, type, money, currency);
		JOptionPane.showMessageDialog(new JFrame(), "Contul a fost creat cu succes!");
    }
    });
	butonAdaugareCont.setBounds(250,100,150,20);
	creazaContPan.add(butonAdaugareCont);
	
	JPanel upClientPan=new JPanel();
	fereastra.getContentPane().add(upClientPan, "1");
	upClientPan.setBackground( new Color(190, 0, 50) );
	upClientPan.setLayout(null);
	upClientPan.setVisible(false);
	
	JLabel lblNumeNou1=new JLabel("Modificati datele pentru clientul cu id-ul:");
	lblNumeNou1.setForeground(Color.white);
	lblNumeNou1.setBounds(100,10,400,50);
	upClientPan.add(lblNumeNou1);
	
	JTextField numeNouTextField1=new JTextField();
	numeNouTextField1.setBounds(100, 45, 100, 20);
	upClientPan.add(numeNouTextField1);
	
	JLabel lblNumeNou=new JLabel("Introduceti un nume nou:");
	lblNumeNou.setForeground(Color.white);
	lblNumeNou.setBounds(100,50,200,50);
	upClientPan.add(lblNumeNou);
	
	JTextField numeNouTextField=new JTextField();
	numeNouTextField.setBounds(100, 90, 100, 20);
	upClientPan.add(numeNouTextField);
	
	
	JLabel lblAdresaNoua=new JLabel("Introduceti o adresa noua:");
	lblAdresaNoua.setForeground(Color.white);
	lblAdresaNoua.setBounds(100,100,200,50);
	upClientPan.add(lblAdresaNoua);
	
	JTextField adresaNouaTextField=new JTextField();
	adresaNouaTextField.setBounds(100, 140, 100, 20);
	upClientPan.add(adresaNouaTextField);
	
	JButton butonUpdateClient=new JButton("UPDATE");
	butonUpdateClient.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String name=numeNouTextField.getText();
			String address=adresaNouaTextField.getText();
			String id=numeNouTextField1.getText();
			int idClient=Integer.parseInt(id);
			name=name.replaceAll("\\s+", "");
			address=address.replaceAll("\\s+","");
			
			if(name.equals(""))
			{
				clMap.updateAdr(idClient, address);
				JOptionPane.showMessageDialog(new JFrame(), "Adresa modificata!");
			}
			if(address.equals(""))
			{
				int idLogin = clMap.updateName(idClient, name);
				logMap.updateLog(idLogin, name);
				JOptionPane.showMessageDialog(new JFrame(), "Nume modificat!");
			}
			if(!address.equals("") && !name.equals(""))
			{
				clMap.updateCl(idClient, name, address);
				JOptionPane.showMessageDialog(new JFrame(),"Update realizat cu suucess!");
		    }
			
      }
  });
	
	butonUpdateClient.setBounds(300, 100, 100, 20);
	upClientPan.add(butonUpdateClient);

	JButton butonInapoi3=new JButton("<-Inapoi");
	butonInapoi3.setBackground(new Color(150,50,0));
	butonInapoi3.setForeground(Color.WHITE);
	butonInapoi3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		upClientPan.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi3.setBounds(10,10,120,20);
	upClientPan.add(butonInapoi3);
	
	JButton butonLogOut=new JButton("Deconectare");
	butonLogOut.setBackground(Color.magenta);
	butonLogOut.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		upClientPan.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut.setBounds(300,10,120,20);
	upClientPan.add(butonLogOut);
	
	/*
	JButton butonInapoi1=new JButton("<-Inapoi");
	butonInapoi1.setBackground(Color.blue);
	butonInapoi1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		updateClientPan.setVisible(false);
		sPanel.setVisible(true);
	}
	});
	butonInapo11.setBounds(300,80,120,10);
	updateClientPan.add(butonInapoi1);
	*/
	
	JButton butonInapoi2=new JButton("<-Inapoi");
	butonInapoi2.setBackground(new Color(150,50,0));
	butonInapoi2.setForeground(Color.WHITE);
	butonInapoi2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		creazaContPan.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi2.setBounds(10,190,120,20);
	creazaContPan.add(butonInapoi2);
    
    
 ///////////////////administratorPanel
    ImageIcon image1 = new ImageIcon("C:/Users/Roxana/Desktop/admin.png");
	JLabel imagelabel1 = new JLabel(image1);
	imagelabel1.setBounds(380, 10, 100, 60);
	administratorPanel.add(imagelabel1);
	
	JPanel adaugareAngajatPanel = new JPanel();
	fereastra.getContentPane().add(adaugareAngajatPanel, "xxjs");
	adaugareAngajatPanel.setBounds(800, 300, 300, 300);
	adaugareAngajatPanel.setLayout(null);
	adaugareAngajatPanel.setVisible(false);
	
	
	
	
	JButton butonAdaugareAngajat=new JButton("Adauga angajat");
	butonAdaugareAngajat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			administratorPanel.setVisible(false);
			adaugareAngajatPanel.setVisible(true);
		}
	});
	
	butonAdaugareAngajat.setBounds(20,100,200,15);
	administratorPanel.add(butonAdaugareAngajat);
	
	JButton butonStergereAngajat=new JButton("Sterge angajat");
	butonStergereAngajat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			administratorPanel.setVisible(false);
			adaugareAngajatPanel.setVisible(true);
		}
	});
	
	butonStergereAngajat.setBounds(20,130,200,15);
	administratorPanel.add(butonStergereAngajat);
	
	JButton butonUpdateAngajat=new JButton("Update angajat");
	butonUpdateAngajat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			administratorPanel.setVisible(false);
			adaugareAngajatPanel.setVisible(true);
		}
	});
	
	butonUpdateAngajat.setBounds(20,160,200,15);
	administratorPanel.add(butonUpdateAngajat);
	
	JButton butonVizualizareAngajat=new JButton("Vizualizare angajati");
	butonVizualizareAngajat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			administratorPanel.setVisible(false);
			adaugareAngajatPanel.setVisible(true);
		}
	});
	
	butonVizualizareAngajat.setBounds(20,190,200,15);
	administratorPanel.add(butonVizualizareAngajat);
	
	
	
	JLabel lblName = new JLabel("Nume: ");
	lblName.setForeground(new Color(100,100,10));
	lblName.setBounds(30, 30, 50, 15);
	adaugareAngajatPanel.add(lblName);
	

	JTextField nameTextField = new JTextField();
	nameTextField.setBounds(30, 60, 100, 15);
	adaugareAngajatPanel.add(nameTextField);
	nameTextField.setColumns(10);
	
	JLabel lblParola1 = new JLabel("Parola:");
	lblParola1.setForeground(new Color(100,100,10));
	lblParola1.setBounds(30, 90, 50, 15);
	adaugareAngajatPanel.add(lblParola1);
	

	JTextField parolaTextField1 = new JTextField();
	parolaTextField1.setBounds(30, 120, 100, 15);
	adaugareAngajatPanel.add(parolaTextField1);
	
	JLabel lblRol1 = new JLabel("Rol:");
	lblRol1.setForeground(new Color(100,100,10));
	lblRol1.setBounds(30, 150, 50, 15);
	adaugareAngajatPanel.add(lblRol1);
	
	
	JTextField rolTextField1 = new JTextField();
	rolTextField1.setBounds(30, 180, 100, 15);
	adaugareAngajatPanel.add(rolTextField1);
	
	
	JButton butonInapoi8=new JButton("<-Inapoi");
	butonInapoi8.setBackground(new Color(150,50,0));
	butonInapoi8.setForeground(Color.WHITE);
	butonInapoi8.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		adaugareAngajatPanel.setVisible(false);
		administratorPanel.setVisible(true);
	}
	});
	butonInapoi8.setBounds(10,10,120,20);
	adaugareAngajatPanel.add(butonInapoi8);
	
	JButton butonLogOut4=new JButton("Deconectare");
	butonLogOut4.setBackground(Color.magenta);
	butonLogOut4.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		adaugareAngajatPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut4.setBounds(300,10,120,20);
	adaugareAngajatPanel.add(butonLogOut4);
    
	JButton butonAdAngajat = new JButton("ADAUGA");
	butonAdAngajat.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String name = nameTextField.getText();
			name = name.replaceAll("\\s+","");
			String password = parolaTextField1.getText();
			int role = Integer.parseInt(rolTextField1.getText());
			int idLogin = logMap.insesareLog(name, password, role);
			JOptionPane.showMessageDialog(new JFrame(), "The client was created !");
			
		}
	});
	butonAdAngajat.setBounds(300, 160, 100, 20);
	adaugareAngajatPanel.add(butonAdAngajat);
	
	
	
	JButton butonLogOut5=new JButton("Deconectare");
	butonLogOut5.setBackground(Color.magenta);
	butonLogOut5.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		administratorPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut5.setBounds(10,10,120,20);
	administratorPanel.add(butonLogOut5);
	
	 JPanel sPanel1 = new JPanel();
	    sPanel1.setBackground(new Color(50,100,150));
	    fereastra.getContentPane().add(sPanel1, "blabla12");
		sPanel1.setLayout(null);
		sPanel1.setVisible(false);
	
	JButton butonVizualizareClienti = new JButton("Vizualizeaza datele clientilor");
	butonVizualizareClienti.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			userNormalPanel.setVisible(false);
			sPanel1.setVisible(true);
			
			ArrayList<Client> clients= clMap.getClients();
			String[] columnNames = new String[6];
			int i = 0;
			
			for(Field f : Client.class.getDeclaredFields())
			{
				f.setAccessible(true);
				columnNames[i] = f.getName();
				i++;
			}
			Object[][] data = new Object[clients.size()][columnNames.length];
			for(i = 0; i < clients.size(); i++ ){
				Client a = clients.get(i);
				data[i][0] = new Object();
                data[i][1] = new Object();
                data[i][2] = new Object();
                data[i][3] = new Object();
                data[i][4] = new Object();
                data[i][5] = new Object();
                data[i][0] = a.getIdClient();
                data[i][1] = a.getNameClient();
                data[i][2] = a.getCardNr();
                data[i][3] = a.getCNP();
                data[i][4] = a.getAddress();
                data[i][5] = a.getIdLogin();
			}
			JTable table = new JTable(data, columnNames);
			table.setBounds(80, 190, 320, -125);
		
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 10, 400, 50);
			sPanel1.add(scrollPane);
			sPanel1.setVisible(true);
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	idClient = Integer.parseInt( table.getValueAt(table.getSelectedRow(), 0).toString());
		        }
		    });
		}
	});
	
	butonVizualizareClienti.setBounds(280,110,200,15);
	userNormalPanel.add(butonVizualizareClienti);
	
	
	JButton butonUpdateClient1= new JButton("Update clienti");
	butonUpdateClient1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		upClientPan.setVisible(true);
	}
	});
	
	butonUpdateClient1.setBounds(280,80,200,15);
	userNormalPanel.add(butonUpdateClient1);
	
	JPanel stClientPanel=new JPanel();
	fereastra.getContentPane().add(stClientPanel, "1");
	stClientPanel.setBackground( new Color(190, 0, 50) );
	stClientPanel.setLayout(null);
	
	
	JLabel lblStCl=new JLabel("Stergeti clientul cu id-ul:");
	lblStCl.setForeground(Color.white);
	lblStCl.setBounds(100,10,400,50);
	stClientPanel.add(lblStCl);
	
	JTextField stClTextField=new JTextField();
	stClTextField.setBounds(100, 45, 100, 20);
	stClientPanel.add(stClTextField);
	
	
	JButton butonAdaugareClienti1 = new JButton("Sterge clienti");
	butonAdaugareClienti1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			userNormalPanel.setVisible(false);
			stClientPanel.setVisible(true);
		}
	});
	
	butonAdaugareClienti1.setBounds(280,50,200,15);
	userNormalPanel.add(butonAdaugareClienti1);
	
	JButton butonStergereClient=new JButton("STERGE");
	butonStergereClient.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String id=stClTextField.getText();
			int idClient=Integer.parseInt(id);
			
			
			
				clMap.stergereClient(idClient);
				JOptionPane.showMessageDialog(new JFrame(), "Clientul a fost sters!");
			
			
      }
  });
	
	butonStergereClient.setBounds(300, 100, 100, 20);
	stClientPanel.add(butonStergereClient);
	
	
	JButton butonInapoi9=new JButton("<-Inapoi");
	butonInapoi9.setBackground(new Color(150,50,0));
	butonInapoi9.setForeground(Color.WHITE);
	butonInapoi9.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		stClientPanel.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi9.setBounds(10,10,120,20);
	adaugareAngajatPanel.add(butonInapoi9);
	
	
	JButton butonLogOut6=new JButton("Deconectare");
	butonLogOut6.setBackground(Color.magenta);
	butonLogOut6.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		sPanel1.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	
	butonLogOut6.setBounds(10,190,120,20);
	sPanel1.add(butonLogOut6);
	
	JButton butonLogOut7=new JButton("Deconectare");
	butonLogOut7.setBackground(Color.magenta);
	butonLogOut7.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut7.setBounds(300,10,120,20);
	userNormalPanel.add(butonLogOut7);
	
	
	JButton butonInapoi11=new JButton("<-Inapoi");
	butonInapoi11.setBackground(new Color(150,50,0));
	butonInapoi11.setForeground(Color.WHITE);
	butonInapoi11.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		stClientPanel.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi11.setBounds(350,190,120,20);
	stClientPanel.add(butonInapoi11);
	
	JButton butonAdCont=new JButton("Adauga conturi");
	butonAdCont.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		creazaContPan.setVisible(true);
	}
	});
	butonAdCont.setBounds(20,50,200,15);
	userNormalPanel.add(butonAdCont);
	
	JPanel updateContPanel=new JPanel();
	updateContPanel.setBackground(Color.gray);
    fereastra.getContentPane().add(updateContPanel, "blabladk");
    updateContPanel.setLayout(null);
    
    
    
    JLabel lblAccountType1 = new JLabel("Id client:");
    lblAccountType1.setBounds(90, 30, 100, 30);
    lblAccountType1.setForeground(Color.BLUE);
    updateContPanel.add(lblAccountType1);
	
	JLabel lblAccountType2 = new JLabel("Id cont:");
	lblAccountType2.setBounds(90, 75, 70,30);
	lblAccountType2.setForeground(Color.BLUE);
	updateContPanel.add(lblAccountType2);
	
	JLabel lblAccountType3 = new JLabel("Tip cont:");
	lblAccountType3.setBounds(90, 120, 95, 30);
	lblAccountType3.setForeground(Color.BLUE);
	updateContPanel.add(lblAccountType3);
	
	
	JTextField AccountType1Field1 = new JTextField();
	AccountType1Field1.setBounds(150, 40, 90, 30);
	updateContPanel.add(AccountType1Field1);

	
	JTextField AccountType1Field2 = new JTextField();
	AccountType1Field2.setBounds(150, 85, 90, 30);
	updateContPanel.add(AccountType1Field2);
	
	
	JTextField AccountType1Field3 = new JTextField();
	AccountType1Field3.setBounds(150, 130, 90, 30);
	updateContPanel.add(AccountType1Field3);
	
	JButton butonLogOut15=new JButton("Deconectare");
	butonLogOut15.setBackground(Color.magenta);
	butonLogOut15.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		logarePanel.setVisible(true);
	}
	});
	butonLogOut15.setBounds(300,10,120,20);
	updateContPanel.add(butonLogOut15);
	
	
	JButton butonInapoi15=new JButton("<-Inapoi");
	butonInapoi15.setBackground(new Color(150,50,0));
	butonInapoi15.setForeground(Color.WHITE);
	butonInapoi15.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		updateContPanel.setVisible(false);
		userNormalPanel.setVisible(true);
	}
	});
	butonInapoi15.setBounds(350,190,120,20);
	updateContPanel.add(butonInapoi15);
	
	JButton butonUpdateCont=new JButton("UPDATE");
	butonUpdateCont.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String idCl=AccountType1Field1.getText();
			String idCnt=AccountType1Field2.getText();
			String type=AccountType1Field3.getText();
			int idClient=Integer.parseInt(idCl);
			int idCont=Integer.parseInt(idCnt);
			contMap.updateCont(idClient, idCont, type);
			JOptionPane.showMessageDialog(new JFrame(), "Update efectuat cu succes!");
			
			
			
			
      }
  });
	
	butonUpdateCont.setBounds(300, 100, 100, 20);
	updateContPanel.add(butonUpdateCont);
	
	
	JTextField tipTextField = new JTextField();
	tipTextField.setBounds(150, 40, 90, 30);
	creazaContPan.add(tipTextField);

	
	JTextField moneyTextField = new JTextField();
	moneyTextField.setBounds(150, 85, 90, 30);
	creazaContPan.add(moneyTextField);
	
	
	JTextField currencyTextField = new JTextField();
	currencyTextField.setBounds(150, 130, 90, 30);
	creazaContPan.add(currencyTextField);

	
	
	JButton butonUpCont=new JButton("Update conturi");
	butonUpCont.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
		userNormalPanel.setVisible(false);
		updateContPanel.setVisible(true);
	}
	});
	butonUpCont.setBounds(20,80,200,15);
	userNormalPanel.add(butonUpCont);
	
	
	    }
}