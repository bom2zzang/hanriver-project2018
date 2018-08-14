"use strict"

var {page, size} = $.parseQuery(location.href);

var tbody = $('#eListTable > tbody');
let data = null;

if (size != undefined && page != undefined)
    loadList(page, size);
else loadList(1, 3);

$(ePrevBtn).click(function() {
    loadList(data.page - 1, data.size);
});

$(eNextBtn).click(function() {
    loadList(data.page + 1, data.size);
});


function loadList(page, size) {
    $.getJSON(serverApiAddr + '/json/notice/list', 
        {
            page: page,
            size: size
        }, function(result) {
            data = result;
            tbody.html('');
            for (var item of data.list) {
                $("<tr>").html(
                    `<td>${item.no}</td><td><a href='#' data-id='${item.no}' class='viewLink'>${item.title}</a></td>
                    <td>${item.date}</td>`).appendTo(tbody);
            }
            $(ePageNo).html(data.page);
            if (data.page <= 1)
                $(ePrevBtn).attr('disabled', '');
            else 
                $(ePrevBtn).removeAttr('disabled');
            
            if (data.page >= data.totalPage)
                $(eNextBtn).attr('disabled', '');
            else
                $(eNextBtn).removeAttr('disabled');
        });
}
tbody.on('click', 'a.viewLink', function(event) {
    event.preventDefault();
    var id = $(event.target).attr('data-id');
    location.href = `view.html?no=${id}&page=${data.page}&size=${data.size}`;
});









