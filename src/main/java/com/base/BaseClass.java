package com.base;

import com.utils.Utility;

public class BaseClass {
	
	//Browser Launch
	public void launchBrowser(String browser)
	{
		Utility.browserLaunch(browser);
	}
	
	//Load the Site or URL
	public void loadUrl(String url)
	{
		Utility.loadSite(url);
	}
	
	//Close the browser
	public void closeAllBrowsers()
	{
		Utility.closeBrowser();
	}
}