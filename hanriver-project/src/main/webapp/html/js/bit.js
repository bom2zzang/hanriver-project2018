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
        if (arugments.length == 0) {
            return el[0].innerHTML;
        }
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
        if (settings.dataType == 'json') {
            data = JSON.parse(xhr.responseText);
        }
        
        if (settings.success)
            settings.success(data);
    };
    
    xhr.open(settings.method, url, true);
    xhr.send();
}

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
}

let $ = bit;