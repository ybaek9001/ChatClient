import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		ClientSocket client=new ClientSocket("211.238.142.248", 10009);
		
		client.setReceiveListener(new ReceiveListener() {
			
			@Override
			public void OnReceive(String echo) {
				System.out.println("data echo: "+echo);
			}
		});
		
		Scanner scan=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("msg:");
			String msg=scan.nextLine();  // ���ξ�����
			//������ ���??
			client.send(msg);  // ����
			
			if(msg.equals("bye"))
			{
				client.close();
				break;
			}
				
		}
		
		//nout.println(msg);
		//String echo=nscan.nextLine();
		
	
		//���ӷ���
		/*Socket socket=new Socket("211.238.142.106", 10009);
		System.out.println("connected from...."
				+socket.getRemoteSocketAddress().toString());
		
		//ä�õ��� ����
		InputStream is=socket.getInputStream();
		Scanner scan=new Scanner(is);
		OutputStream os=socket.getOutputStream();
		PrintWriter pw=new PrintWriter(os,true); // true�� �����÷��� ���
		
		//Ŭ���̾�Ʈ ��������
		Scanner uscan=new Scanner(System.in);
		System.out.println("msg:");
		String msg=uscan.nextLine();
		pw.println(msg);
		String echo=scan.nextLine();
		System.out.println(echo);
		
		pw.close();
		scan.close();
		os.close();
		is.close();
		socket.close();*/
	}
}
