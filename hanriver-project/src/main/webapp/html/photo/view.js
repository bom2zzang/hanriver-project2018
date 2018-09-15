"use strict"
var data = null;
var {no} = $.parseQuery(location.href);

if (no == undefined) {
    $('.viewform').css('display', 'none');
} else {
    $('.newform').css('display', 'none');
    
    $.getJSON(serverApiAddr + `/json/photo/view/${no}`,
        function(data) {
            if (data.photo == null) {
                alert('번호가 무효합니다.');
                local.href = 'list.html';
                return;
            }
            $(eNo).val(data.photo.no);
            $(eContents).val(data.photo.contents);
            $(ePhoto).val(data.photo.photo);
            $(ePno).val(data.photo.pno);
    });
}

$(eListBtn).click(function() {
    location.href = `list.html`;
});

$(eAddBtn).click(function() {
    $.post(serverApiAddr + '/json/photo/add', 
        {
            contents: $(eContents).val(), 
            photo: $(ePhoto).val(), 
            pno: $(ePno).val()
        },
        function(data) {
            location.href = 'list.html';
        },
        'json');
});

$(eUpdateBtn).click(function() {
    $.post(serverApiAddr + '/json/photo/update', {
    	no: $(eNo).val(),
    	contents: $(eContents).val(), 
        photo: $(ePhoto).val(), 
        pno: $(ePno).val()
    },
    function(data) {
        if (data.status == 'success') {
            location.href = `list.html`;
        } else {
            alert('변경 오류입니다!');
            console.log(data.error);
        }
    }, 'json');
})

$(eDeleteBtn).click(function() {
    $.post(serverApiAddr + '/json/photo/delete',{
        no: $(eNo).val()
    }, function(data) {
        if (data.status == 'success') {
            location.href = `list.html`;
        } else {
            alert('삭제 오류입니다!');
        }
    }, 'json');
});