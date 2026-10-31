package com.bulkmail.model;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

public class GoogleAnalyticTracker {
public static void main(String[] args) {
	JGoogleAnalyticsTracker.setProxy(System.getenv("http_proxy")); 
	AnalyticsConfigData config = new AnalyticsConfigData("UA-203137377-1"); 
	JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(config, GoogleAnalyticsVersion.V_4_7_2);
	while(true)
	{
	tracker.trackEvent("event", "email", "open");
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
}
