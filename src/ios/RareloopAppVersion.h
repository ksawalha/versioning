#import <Cordova/CDV.h>

NS_ASSUME_NONNULL_BEGIN

@interface RareloopAppVersion : CDVPlugin

- (void)getAppVersion:(CDVInvokedUrlCommand*)command;

@end

NS_ASSUME_NONNULL_END
