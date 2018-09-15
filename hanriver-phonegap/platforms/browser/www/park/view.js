"use strict"
var data = null;
var {no, size, page} = $.parseQuery(location.href);

if (no == undefined) {
	$(".viewform").css('display','none');
	$(eName).removeAttr("readOnly");
} else {
	$('.newform').css('display','none');
	$.getJSON(serverApiAddr + `/json/park/view/${no}`,
			function(data) {
		if (data.park == null) {
			alert('잘못되었다.');
			local.href = 'list.html';
			return;
		}
		
		$(eName).val(data.park.name);
		$(eLocation).val(data.park.location);
	});
}

$(eListBtn).click(function() {
	location.href = `list.html?page=${page}&size=${size}`;
});

$(eAddBtn).click(function() {
	$.post(serverApiAddr + '/json/park/add',
			{
				name: $(eName).val(),
				location: $(eLocation).val()
			},
			function(data) {
				location.href = 'list.html';
			},
			'json');
});

$(eUpdateBtn).click(function() {
	$.post(serverApiAddr + '/json/park/update',
			{
		name: $(eName).val(),
		location: $(eLocation).val()
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
	$.post(serverApiAddr + '/json/park/delete',
			{
		name: $(eName).val()
			},
			function(data) {
				 if (data.status == 'success') {
			            location.href = `list.html?page=${page}&size=${size}`;
			        } else {
			            alert('삭제 오류입니다!');
			        }
			    });
			});




