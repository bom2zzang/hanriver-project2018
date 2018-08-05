"use strict"
var data = null;
var {id, size, page} = $.parseQuery(location.href);

if (id == undefined) {
    $('.viewform').css('display', 'none');
    $(eId).removeAttr("readOnly");
} else {
    $('.newform').css('display', 'none');
    
    $.getJSON(serverApiAddr + `/json/member/view/${id}`,
        function(data) {
            if (data.member == null) {
                alert('아이디가 무효합니다.');
                local.href = 'list.html';
                return;
            }
            $(eId).val(data.member.id);
            $(eEmail).val(data.member.email);
            $(eTel).val(data.member.tel);
    });
}

$(eListBtn).click(function() {
    location.href = `list.html?page=${page}&size=${size}`;
});

$(eAddBtn).click(function() {
    $.post(serverApiAddr + '/json/member/add', 
        {
            id: $(eId).val(), 
            email: $(eEmail).val(), 
            password: $(ePassword).val(),
            tel: $(eTel).val()
        },
        function(data) {
            location.href = 'list.html';
        },
        'json');
});

$(eUpdateBtn).click(function() {
    $.post(serverApiAddr + '/json/member/update', {
        id: $(eId).val(),
        email: $(eEmail).val(),
        password: $(ePassword).val(),
        tel: $(eTel).val()
    },
    function(data) {
        if (data.status == 'success') {
            location.href = `list.html?page=${page}&size=${size}`;
        } else {
            alert('변경 오류입니다!');
            console.log(data.error);
        }
    }, 'json');
})

$(eDeleteBtn).click(function() {
    $.getJSON(serverApiAddr + '/json/member/delete',{
        id: $(eId).val()
    }, function(data) {
        if (data.status == 'success') {
            location.href = `list.html?page=${page}&size=${size}`;
        } else {
            alert('삭제 오류입니다!');
        }
    });
});