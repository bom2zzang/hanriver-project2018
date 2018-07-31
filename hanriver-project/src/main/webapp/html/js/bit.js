"use strict"

let bit = function(value) {
    var el = [];
    if (value instanceof HTMLElement) {
        el[0] = value;
    } else if (value.startsWith('<')) {
        el[0] = document.createElement(
                value.substr(1, value.length -2));
    } else {
        var list = document.querySelectorAll(value);
        for (var i = 0; i < list.length; i++) {
            el[i] = list[i];
        }
    }
    
    el.html = function(value) {
        if (arguments.length == 0) {
            return el[0].innerHTML;
        }
        for (var e of el)
            e.innerHTML = value;
        return el;
    }
    
    el.append = function(child) {
        for (var e of el)
            e.appendChild(child);
        return el;
    }
    
    el.appendTo = function(parent) {
        for (var e of el)
            parent[parent.length - 1].appendChild(e);
        return el;
    };
    
    el.attr = function(name, value) {
        if (arguments.length < 2)
            return el[0].getAttribute(name);
        for (var e of el)
            e.setAttribute(name, value);
        return el;
    };
    
    el.removeAttr = function(name) {
        for (var e of el)
            e.removeAttribute(name);
        return el;
    }
    
    el.on = function(name, p2, p3) {
        var selector = null;
        var handler = null;
        
        if (arguments.length == 2) handler = p2;
        if (arguments.length == 3) {
            selector = p2;
            handler = p3;
        }
        
        for (var e of el) {
            if (!selector) {
                e.addEventListener(name, handler);
            } else {
                e.addEventListener(name, function(event) {
                    var selectorTargets = e.querySelectorAll(selector);
                    
                    for (var target of selectorTargets) {
                        if (event.target == target) {
                            handler(event);
                            break;
                        }
                    }
                });
            }
        }
        return el;
    };
    
    el.click = function(handler){
        return el.on('click', handler);
    };
    
    el.css = function(name, value) {
        if (arguments.length == 1) {
            return el[0].style[name];
        }
        for (var e of el) {
            e.style[name] = value;
        }
        return el;
    }
    el.val = function(value) {
        if (arguments.length == 0) {
            return el[0].value;
        }
        for (var e of el) {
            e.value = value;
        }
        return el;
    }
    
    return el;
}

bit.ajax = function(url, settings) {
    if (settings == undefined)
        settings = {};
    if (settings.dataType == undefined)
        settings.dataType = 'text';
    if (settings.method == undefined)
        settings.method = 'GET';
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState < 4) return;
        if (xhr.status !== 200) {
            if (settings.error)
                error();
            return;
        }
        
        let data = xhr.responseText;
        if (settings.dataType == 'json') 
            data = JSON.parse(xhr.responseText);
        
        if (settings.success)
            settings.success(data);
        
        if (done) 
            done(data);
    };
    
    var qs = '';
    if (settings.data) {
        for (var propName in settings.data) {
            qs += `&${propName}=${settings.data[propName]}`;
        }
    }
    
    if (settings.method == 'GET') {
        if (url.indexOf('?') == -1)
            url += '?';
        url += qs;
        xhr.open(settings.method, url, true);
        xhr.send();
    } else {
        xhr.open(settings.method, url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(qs);
    }
    
    let done;
    xhr.done = function(func) {
        done = func;
    };
    return xhr;
};

bit.getJSON = function(url, p2, p3) {
    let data = {};
    let success = null;
    
    if (arguments.length > 1) {
        if (typeof p2 == "function") success = p2;
        else data = p2;
        
        if (typeof p3 == "function") success = p3;
    }
    return bit.ajax(url, {
        dataType: 'json',
        data: data,
        success: success
    });
};

bit.post = function(url, p2, p3, p4) {
    let data = {};
    let success = null;
    let dataType = 'text';
    
    if (arguments.length == 2) {
        if (typeof p3 == 'function') {
            data = p2;
            success = p3;
        } else if (typeof p2 == 'function') {
            success = p2;
            dataType = p3;
        } else {
            data = p2;
            dataType = p3;
        }
    } else if (arguments.length > 2) {
        data = p2;
        success = p3;
        dataType = p4;
    }
    
    return bit.ajax(url, {
        method: 'POST',
        dataType: dataType,
        data: data,
        success: success
    });
};

bit.parseQuery = function(url) {
    var map = {};
    var qs = url.split('?');
    if (qs.length > 1 ){
        var params = qs[1].split('&');
        for (var param of params) {
            var kv = param.split("=");
            map[kv[0]] = kv[1];
        }
    }
    return map;
};

let $ = bit;