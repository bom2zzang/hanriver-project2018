"use strict"
var data = null;
var {id, size, page} = $.parseQuery(location.href);


$(eListBtn).click(function() {
    location.href = `list.html?page=${page}&size=${size}`;
});


$(eLogin).click(function() {
    $.post(serverApiAddr + '/json/member/logIn', {
        id: $(eId).val(),
        password: $(ePassword).val(),
    },
    function(data) {
        if (data.status == 'success') {
            location.href = `list.html?page=${page}&size=${size}`;
        } else {
            alert('로그인 오류입니다!');
            console.log(data.error);
        }
    }, 'json');
})
