/** 
	OutSystems - Mobile Services
	Vitor Oliveira - 03/03/2015

*/

function SilentLogin() {
}

exports.silentLogin = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "SilentLogin", "silentLogin", []);
};