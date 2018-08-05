$.parseQuery = function(url) {
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
