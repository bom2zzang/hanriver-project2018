"use strict"
var data = null;
var {no, size, page} = $.parseQuery(location.href);

if (no == undefined) {
    $('.viewform').css('display', 'none');
    $(eNo).removeAttr("readOnly");
} else {
    $('.newform').css('display', 'none');
    
    $.getJSON(serverApiAddr + `/json/notice/view/${no}`,
        function(data) {
            if (data.notice == null) {
                alert('아이디가 무효합니다.');
                local.href = 'list.html';
                return;
            }
            $(eNo).val(data.notice.no);
            $(eTitle).val(data.notice.title);
            $(eContents).val(data.notice.contents);
    });
}

$(eListBtn).click(function() {
    location.href = `list.html?page=${page}&size=${size}`;
});

$(eAddBtn).click(function() {
    $.post(serverApiAddr + '/json/notice/add', 
        {
            title: $(eTitle).val(), 
            contents: $(eContents).val()
        },
        function(data) {
            location.href = 'list.html';
        },
        'json');
});

$(eUpdateBtn).click(function() {
    $.post(serverApiAddr + '/json/notice/update', {
        no: $(eNo).val(), 
        title: $(eTitle).val(), 
        contents: $(eContents).val()
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
    $.getJSON(serverApiAddr + '/json/notice/delete',{
        no: $(eNo).val()
    }, function(data) {
        if (data.status == 'success') {
            location.href = `list.html?page=${page}&size=${size}`;
        } else {
            alert('삭제 오류입니다!');
        }
    });
});