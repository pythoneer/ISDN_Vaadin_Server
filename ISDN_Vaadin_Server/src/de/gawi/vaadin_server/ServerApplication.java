package de.gawi.vaadin_server;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class ServerApplication extends Application 
{

	private static final long serialVersionUID = 1L;
	private ICEPush pusher = new ICEPush();

	static public List<ServerApplication> users = new ArrayList<ServerApplication>();
	
	
	public Window mainWindow;
	
	@Override
	public void init() 
	{
		/*final Window*/ this.mainWindow = new Window("Vaadin_remote_executor Application");
		Label label = new Label("this is remote executor13");
		
		Button but = new Button("button1");
		
		mainWindow.addComponent(but);
		mainWindow.addComponent(label);
		
		mainWindow.addComponent(pusher);
		setMainWindow(mainWindow);
		
		but.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 6212054052917651576L;

			public void buttonClick(ClickEvent event) {
				mainWindow.executeJavaScript("alert('slayer');");
				//mainWindow.executeJavaScript("Titanium.API.fireEvent('meta_me');");
				//mainWindow.executeJavaScript("Titanium.API.info('from server');");
//				mainWindow.executeJavaScript("printer.text('Message from QWebView');");
			}
		});	
		
		// check old sessions and delete
//		List<Vaadin_remote_executorApplication> deletable = new ArrayList<Vaadin_remote_executorApplication>();
//		for(Vaadin_remote_executorApplication user: Vaadin_remote_executorApplication.users)
//		{
//			if( user.isRunning() )
//			{
//				deletable.add(user);
//			}
//		}
//		Vaadin_remote_executorApplication.users.removeAll(deletable);
		
		// add user to userlist
		ServerApplication.users.add(this);
	}// init

	
	
	public void inject()
	{
		System.out.println("INJECTION");
		this.mainWindow.executeJavaScript("alert('injection')");
		mainWindow.executeJavaScript("printer.text('Message from Vaadin to QWebView');");
		
		pusher.push();
	}

	
	
}

//<servlet-class>com.vaadin.terminal.gwt.server.ApplicationServlet</servlet-class>
