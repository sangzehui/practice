function $() {
	if(arguments.length == 1) {
		var arg = arguments[0];
		if(typeof arg == "function") {
			var event = arg;
			window.addEventListener("DOMContentLoaded", function(){
				event();
			});
		} else if(typeof arg == "string") {
			var selector = arg;
			return document.querySelector(selector);
		}
	}
	return undefined;
}

Element.prototype.on = function(eventName , event) {
	this.addEventListener(eventName, event);
};

