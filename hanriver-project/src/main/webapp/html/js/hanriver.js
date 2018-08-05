"use strict"

// 자주 사용할 함수를 라이브러리화 시킨다.
let bit = function(value) {
    var e;
    if (value instanceof HTMLElement) {
    	e = value;
    }
    else if (value.startsWith('<')) {
	    e = document.createElement(value.substr(1, value.length - 2));
    } else {
	    e = document.querySelector(value);
    }
    
	e.append = function(child) {
		e.appendChild(child);
		return e;
	}
	
	e.attr = function(name, value) {
		if (arguments.length > 2) {
			e.getAttribute(name);
		} else {
			e.setAttribute(name, value);
		}
		return e;
	}
	
	e.removeAttr = function(name) {
		e.removeAttribute(name);
	}
	
	e.on = function(name, p2) {
		e.
	}
	
	return e;
};


bit.parseQuery = function(url) {
    var paramMap = {};

    var qs = url.split('?');
    if (qs.length > 1) {
        var params = qs[1].split('&');
        for (var param of params) {
            var kv = param.split('=')
            paramMap[kv[0]] = kv[1];
        }
    }
    return paramMap;
};


let $ = bit;












