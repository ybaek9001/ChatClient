import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatWindow extends Frame{
	private ClientSocket socket;
	
	
	private TextArea outputText;	//텍스트 여러 줄
	private MenuBar mainMenu;
	private Menu mnFile;
	private MenuItem miFileConnect;
	private MenuItem miFileExit;
	private Panel inputPanel;
	private TextField inputText;	//텍스트 한줄
	private Button sendButton;

	
	
	public ChatWindow() {
		mainMenu = new MenuBar();
		
		mnFile = new Menu("File");
		miFileConnect = new MenuItem("접속");
		miFileConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new ClientSocket("211.238.142.115",10009);
					outputText.setText("connected to ");
					
					socket.setReceiveListener(new ReceiveListener() {
						@Override
						public void OnReceive(String echo) {
							String txt = outputText.getText();
							txt += "\n\r" + echo;
							outputText.setText(txt);
						}
					});
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		miFileExit= new MenuItem("Exit");
		miFileExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		
		mnFile.add(miFileConnect);
		mnFile.add(miFileExit);
		mainMenu.add(mnFile);
		
		
		setMenuBar(mainMenu);
		
		outputText = new TextArea();
		
		inputPanel = new Panel();
		inputPanel.setPreferredSize(new Dimension(10,70));
		inputPanel.setLayout(new FlowLayout());
		inputText = new TextField();
		inputText.setPreferredSize(new Dimension(300, 60));
		sendButton = new Button("Send");
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				socket.send(inputText.getText());
				
			}
		});
		
		this.add(outputText);
		this.inputPanel.add(inputText);
		this.inputPanel.add(sendButton);
		
		this.setMenuBar(mainMenu);
		this.setLayout(new BorderLayout());
		
		
		this.add(outputText, BorderLayout.CENTER);
		this.add(inputPanel, BorderLayout.PAGE_END);
		this.setSize(400, 600);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		ChatWindow win = new ChatWindow();
		win.setVisible(true);
	}
}
