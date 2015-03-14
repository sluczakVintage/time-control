package com.nfc.timecontrol.adm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Sebastian
 *
 */
public class LocalService extends Service {

	/**
	 * 
	 */
	private NotificationManager notificationMgr;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		notificationMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		displayNotificationMessage("Service in background");
		
		Thread serviceThread = new Thread(null, new ServiceWorker(), "ADM NFC Timecontrol");
		serviceThread.start();
	}
	
	/**
	 * @author Sebastian
	 *
	 */
	class ServiceWorker implements Runnable
	{
		public void run() {
			//wykonuje przetwarzanie w tle...
			//zatrzymuje usg³ugê po zakoñczeniu...
			//BackgroundService.this.stopSelf();
		}
	}
	
	@Override
	public void onDestroy()
	{
		displayNotificationMessage("Halting service");
		notificationMgr.cancel(R.id.app_notification_id);
		super.onDestroy();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	/**
	 * Sets notification text
	 * @param message String that should be shown in notification area
	 */
	private void displayNotificationMessage(String message) {
		Notification notification = new Notification(R.drawable.icon, message, System.currentTimeMillis());
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, AdministerActivity.class), 0);
		
		notification.setLatestEventInfo(this, getText(R.string.app_name).toString(), message, contentIntent);
		
		notificationMgr.notify(R.id.app_notification_id, notification);
	}
	
}
