"use strict"

var {page, size} = $.parseQuery(location.href);

var tbody = $('#eListTable > tbody');
let data = null;
loadList();
function loadList() {
    $.getJSON(serverApiAddr + '/json/photo/list', function(result) {
            data = result;
            tbody.html('');
            for (var item of data.list) {
                $("<tr>").html(
                    `<td>${item.no}</td><td><a href='#' data-id='${item.no}' class='viewLink'>${item.contents}</a></td>
                    <td>${item.photo}</td><td>${item.createDate}</td>`).appendTo(tbody);
            }
        });
}
tbody.on('click', 'a.viewLink', function(event) {
    event.preventDefault();
    var no = $(event.target).attr('data-id');
    location.href = `view.html?no=${no}`;
});









