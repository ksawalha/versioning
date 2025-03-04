#import "RareloopAppVersion.h"
#import <Foundation/Foundation.h>

@implementation RareloopAppVersion

- (void)getAppVersion:(CDVInvokedUrlCommand*)command {
    NSDictionary* infoDict = [NSBundle mainBundle].infoDictionary;
    
    NSString* appVersion = infoDict[@"CFBundleShortVersionString"];
    NSString* buildNumber = infoDict[@"CFBundleVersion"];

    if (!appVersion || !buildNumber) {
        CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR
                                                  messageAsString:@"Missing version/build in Info.plist"];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        return;
    }

    NSDictionary* response = @{
        @"version": appVersion,
        @"build": buildNumber
    };

    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                messageAsDictionary:response];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
