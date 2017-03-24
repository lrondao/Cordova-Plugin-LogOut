//
//  LogOut.h
//  OutSystems
//
//  Created by Vitor Oliveira on 26/11/14.
//  @author Vítor Oliveira (vitor.oliveira@outsystems.com)
//  @author João Pereira Gonçalves (joao.goncalves@outsystems.com)
//

#import "SilentLogin.h"
#import "ApplicationViewController.h"
#import "OSNavigationController.h"
#import "LoginScreenController.h"
#import "OSNavigationController.h"


@implementation SilentLogin

- (OSNavigationController *) navigationController {
    if([self.appDelegate.window.rootViewController class] == [OSNavigationController class]){
        return (OSNavigationController*) self.appDelegate.window.rootViewController;
    }
    return nil;
}

- (void)silentLogin:(CDVInvokedUrlCommand *)command {
    CDVPluginResult* pluginResult;
    if([self.appDelegate.window.rootViewController class] == [OSNavigationController class]){
        OSNavigationController* osn = (OSNavigationController*) self.appDelegate.window.rootViewController;
        [OutSystemsAppDelegate unsetAutoLoginPerformed];
        [osn pushRootViewController:nil];
        
    }
    else{
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}

- (OutSystemsAppDelegate *)appDelegate {
    return (OutSystemsAppDelegate*)[[UIApplication sharedApplication] delegate];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:
(NSInteger)buttonIndex {
    if (buttonIndex == 1) {
        // do code for yes click
        
        NSHTTPCookieStorage *storage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
        for (NSHTTPCookie *cookie in [storage cookies]) {
            [storage deleteCookie:cookie];
        }
        [[NSUserDefaults standardUserDefaults] synchronize];
        
        NSURL * url = self.webView.request.URL;
        [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[url absoluteString]]]];
    } else {
        // otherwise
    }
}
@end
