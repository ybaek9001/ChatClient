import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerProgram {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<ClientSocket> clients=new ArrayList<ClientSocket>();
		Socket sock;
		
		ServerSocket svrSocket=new ServerSocket(10009);
		Date date=new Date();
		System.out.println("server started........at "+date.toString());
		
		while(true)
		{
			sock=svrSocket.accept();   //
			System.out.println("connected from...."
					+sock.getRemoteSocketAddress().toString());
			
			ClientSocket clt=new ClientSocket(sock);
			clt.setReceiveListener(new ReceiveListener() {
				
				@Override
				public void OnReceive(String echo) {
					System.out.println("echo data: "+echo);
					
					for(ClientSocket c:clients)
						c.send(echo);
					
					if(echo.equals("bye"))
					{
						clt.close();
					}
				}
			});
			clients.add(clt);
		}
		
						
		//에코 서버(전달자에 다시 전달해주는 1:1 개념)를 구축:입/출력 스트림 도구 생성
		/*InputStream is=sock.getInputStream();
		Scanner scan=new Scanner(is);
		OutputStream os=sock.getOutputStream();
		PrintWriter pw=new PrintWriter(os,true);
		
		String line=scan.nextLine(); //
		System.out.println("echo: "+line);
		pw.println("echo: "+line);
		
		pw.close();
		scan.close();
		os.close();
		is.close();
		dataSocket.close();*/
	}
}
