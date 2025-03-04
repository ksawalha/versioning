var exec = require('cordova/exec');

/**
 * Object representing the app's native version and build number
 * @constructor
 */
var RareloopAppVersion = function () {
    this.version = null;
    this.build = null;
    this.available = false;

    var _this = this;

    document.addEventListener('deviceready', function () {
        _this.getInfo()
            .then(function(info) {
                _this.available = true;
                _this.version = info.version;
                _this.build = parseInt(info.build, 10);
            })
            .catch(function(e) {
                _this.available = false;
                console.error("[AppVersion] Error initializing plugin: ", e);
            });
    }, false);
};

/**
 * Get the app version (now with Promise support)
 * @returns {Promise<{version: string, build: number}>}
 */
RareloopAppVersion.prototype.getInfo = function(successCallback, errorCallback) {
    // Support both Promise and callback patterns
    if (typeof successCallback === 'function' || typeof errorCallback === 'function') {
        exec(successCallback, errorCallback, "RareloopAppVersion", "getAppVersion", []);
    } else {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, "RareloopAppVersion", "getAppVersion", []);
        });
    }
};

// Export as singleton
module.exports = new RareloopAppVersion();
